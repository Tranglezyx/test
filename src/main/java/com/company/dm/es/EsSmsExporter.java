package com.company.dm.es;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EsSmsExporter {

    private static final String SMS_INDEX_TEMPLATE = "sms_text_to_{}";
    private static final String ES_IP = "";
    private static final int ES_PORT = 9201;

    private static final RestHighLevelClient esClient = new RestHighLevelClient(RestClient.builder(new HttpHost
            (ES_IP, ES_PORT, "http")));

    private static long userId = 508784;

    public static void main(String[] args) throws Exception {
//        String start = "20250101";
//        String end = "20250630";

        String start = "20250724";
        String end = "20250724";

        Date startDate = DateUtils.parseDate(start, "yyyyMMdd");
        Date endDate = DateUtils.parseDate(end, "yyyyMMdd");
        Date tmpDate = startDate;
        WriteSheet sheet = EasyExcel.writerSheet().build();
        String fileName = StrUtil.format("数据导出/{}.xlsx", userId);
        ExcelWriter writer = EasyExcel.write(fileName, SmsExcelDTO.class).excelType(ExcelTypeEnum.XLSX).build();
        while (tmpDate.getTime() <= endDate.getTime()) {
            String format = DateFormatUtils.format(tmpDate, "yyyyMMdd");
            System.out.println(StrUtil.format("导出日期:{}", format));
            queryFromEs(format, fileName, writer, sheet);
            tmpDate = DateUtils.addDays(tmpDate, 1);
        }
        writer.finish();

        System.out.println("导出结束");
    }


    private static void queryFromEs(String date, String fileName, ExcelWriter writer, WriteSheet sheet) throws Exception {
        int pageSize = 10000;

        String index = StrUtil.format(SMS_INDEX_TEMPLATE, date);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
//                .must(QueryBuilders.termQuery("userId", userId))
                .must(QueryBuilders.termQuery("splitsCurrent", 1));
        long count = queryCount(index, boolQuery);

        if (count > 0) {
            queryAndExport(index, pageSize, writer, sheet, boolQuery);
        }
    }

    private static long queryCount(String index, BoolQueryBuilder boolQuery) {
        CountRequest countRequest = new CountRequest(index);
        countRequest.query(boolQuery);
        try {
            CountResponse countResponse = esClient.count(countRequest, RequestOptions.DEFAULT);
            long count = countResponse.getCount();
            return count;
        } catch (Exception e) {
            System.out.println(StrUtil.format("{}-查询出错,error:{}", index, e.getMessage()));
            return 0;
        }
    }


    private static void queryAndExport(String index, int pageSize, ExcelWriter writer, WriteSheet sheet, BoolQueryBuilder boolQuery) throws IOException {
        Object[] searchAfter = null;

        while (true) {
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                    .query(boolQuery)
                    .fetchSource(new String[]{
                            "userId",
                            "userSubAccountId",
                            "extensionCode",
                            "sendStatus",
                            "submitTime",
                            "signContent",
                            "targetNumber",
                            "messageContent"}, null)
                    .size(pageSize)
                    .sort(SortBuilders.fieldSort("_id").order(SortOrder.ASC));

            if (searchAfter != null) {
                sourceBuilder.searchAfter(searchAfter);
            }

            SearchRequest request = new SearchRequest(index).source(sourceBuilder);
            SearchResponse response = null;
            try {
                response = esClient.search(request, RequestOptions.DEFAULT);
            } catch (Exception e) {
                System.out.println(StrUtil.format("{}-查询出错,error:{}", index, e.getMessage()));
                response = null;
            }
            if (response == null) {
                return;
            }
            SearchHits hits = response.getHits();
            if (hits.getHits().length > 0) {
                searchAfter = hits.getHits()[hits.getHits().length - 1].getSortValues();
            } else {
                return;
            }
            List<SmsExcelDTO> list = new ArrayList<>();
            for (SearchHit hit : hits) {
                Map<String, Object> src = hit.getSourceAsMap();
                String sendStatus = String.valueOf(src.getOrDefault("sendStatus", ""));

                String submitTime = String.valueOf(src.getOrDefault("submitTime", ""));
                SmsExcelDTO smsExcelDTO = SmsExcelDTO.builder()
//                        .userId(String.valueOf(src.getOrDefault("userId", "")))
//                        .userSubAccountId(String.valueOf(src.getOrDefault("userSubAccountId", "")))
//                        .extensionCode(String.valueOf(src.getOrDefault("extensionCode", "")))
                        .targetNumber(String.valueOf(src.getOrDefault("targetNumber", "")))
                        .sendStatus("1".equals(sendStatus) ? "成功" : "3".equals(sendStatus) ? "未知" : "失败")
                        .submitTime(DateFormatUtils.format(new Date(Long.valueOf(submitTime)), "yyyy-MM-dd HH:mm:ss"))
//                        .signContent(String.valueOf(src.getOrDefault("signContent", "")))
                        .messageContent(String.valueOf(src.getOrDefault("messageContent", "")))
                        .build();
                list.add(smsExcelDTO);
            }
            writer.write(list, sheet);
        }
    }

}
