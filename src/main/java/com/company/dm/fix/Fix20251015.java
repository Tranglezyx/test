package com.company.dm.fix;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvReadConfig;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.test.mapper.SmsToMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/10/15 14:32
 * @Description:
 */
@Slf4j
public class Fix20251015 {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Trangle\\Downloads\\模板发送数据.xlsx";

        File excelFile = new File(path);
        String excelFileName = excelFile.getName();
        TemplateSendCount.ImportExcelDataListener<TemplateSendCount> importExcelDataListener = new TemplateSendCount.ImportExcelDataListener();
        if (excelFileName.endsWith(ExcelTypeEnum.XLS.getValue())) {
            EasyExcel.read(excelFile, TemplateSendCount.class, importExcelDataListener).excelType(ExcelTypeEnum.XLS).sheet().doRead();
        } else if (excelFileName.endsWith(ExcelTypeEnum.XLSX.getValue())) {
            EasyExcel.read(excelFile, TemplateSendCount.class, importExcelDataListener).excelType(ExcelTypeEnum.XLSX).sheet().doRead();
        } else {
            throw new RuntimeException("只支持xls,xlsx类型文件");
        }
        List<TemplateSendCount> dataList = importExcelDataListener.getDatas();

        String resource = "mybatis.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            try (SqlSession session = sqlSessionFactory.openSession()) {
                SmsToMapper mapper = session.getMapper(SmsToMapper.class);

                Map<String, String> templateSignMap = new HashMap<>();
                List<String> templateIdList = new ArrayList<>();
                for (int i = 0; i < dataList.size(); i++) {
                    TemplateSendCount sendCount = dataList.get(i);
                    if (StringUtils.isEmpty(sendCount.getSign())) {
                        log.info("签名为空 >>> ");
                        continue;
                    }
                    String sign = sendCount.getSign().trim().substring(1, sendCount.getSign().length() - 1);
                    String developerId = sendCount.getDeveloperId();
                    String productId = sendCount.getProductId();
                    String templateId = sendCount.getTemplateId();

//                    if(!developerId.equals("1361850056")){
//                        continue;
//                    }

                    Long smsSignId = mapper.selectSmsSignId(sign, developerId);
                    if (smsSignId == null) {
                        log.info("签名不存在 >>> ");
                        continue;
                    }

                    templateIdList.add(templateId);

                    templateSignMap.put(templateId, sendCount.getSign());
                    log.info("进度 >>> {} / {}", i + 1, dataList.size());
                }
                log.info("templateIdList = {}", JSONObject.toJSONString(templateIdList));
                if(CollectionUtils.isEmpty(templateIdList)){
                    return;
                }

                List<TemplateParam> templateParamList = mapper.selectTemplateParamByIdList(templateIdList);
                Map<String, List<TemplateParam>> collect = templateParamList.stream().collect(Collectors.groupingBy(item -> item.getProduct_id() + "-" + item.getSms_sign_id()));

                String sqlTemplate = "INSERT INTO sms_sign_product_template(name, sms_sign_id, product_id, template_content, template_params, audit_status, audit_note, audit_user, audit_time, created_time)\n" +
                        "VALUES ('{}', {}, {}, '{}', '{}', 1, '系统根据发送量生成,默认审核通过', -1, now(), now());\n";
                for (Map.Entry<String, List<TemplateParam>> entry : collect.entrySet()) {
                    List<TemplateParam> paramList = entry.getValue();
                    String smsSignId = paramList.get(0).getSms_sign_id();
                    String productId = paramList.get(0).getProduct_id();
                    String templateContent = paramList.get(0).getTemplate_content();
                    String name = paramList.get(0).getTemplate_name();

                    int templateCount = mapper.selectSmsSignTemplateProductCount(Long.valueOf(smsSignId), productId);
                    if (templateCount > 0) {
                        log.info("签名产品模板已存在 >>> ");
                        continue;
                    }

                    List<Map<String, Object>> list = new ArrayList<>();
                    for (TemplateParam templateParam : paramList) {
                        list.add(templateParam.toMap());
                    }
                    String templateParams = JSONObject.toJSONString(list);

                    String sql = StrUtil.format(sqlTemplate, name, smsSignId, productId, templateContent, templateParams);
                    FileUtil.appendUtf8String(sql, "C:\\Users\\Trangle\\Downloads\\sql");
                }
                log.info("处理完成 >>> ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
