package com.test.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

@Slf4j
public class ESApp {

    public static void main(String[] args) throws IOException {
        getIndex();
//        query();
    }

    public static void getIndex() throws IOException {
        // 创建Elasticsearch客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder("192.168.11.131:31203")
        );

        try {
            // 构建获取索引请求
            GetIndexRequest request = new GetIndexRequest();

            // 执行获取索引请求
            GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

            // 处理获取到的索引信息
            String[] indices = response.getIndices();
            System.out.println("索引列表：");
            for (String index : indices) {
                System.out.println(index);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 关闭Elasticsearch客户端
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void query() throws IOException {
        // 创建客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        // 构建查询请求
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("my-index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        // 执行查询
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        // 处理查询结果
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        // 关闭客户端
        client.close();
    }
}
