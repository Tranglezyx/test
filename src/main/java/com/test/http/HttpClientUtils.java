package com.test.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author trangle
 */
public class HttpClientUtils {

    public static String request(String uri, String data, String contentType) throws IOException {
        // 创建 HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Content-Type", contentType);
        httpPost.setEntity(new StringEntity(data));
        CloseableHttpResponse response = closeableHttpClient
                .execute(httpPost);
        HttpEntity httpEntity = response.getEntity();
        String responseStr = EntityUtils.toString(httpEntity);
        // 释放资源
        closeableHttpClient.close();
        return responseStr;
    }
}
