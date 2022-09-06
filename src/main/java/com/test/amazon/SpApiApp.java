package com.test.amazon;

import cn.wangdian.amazon.SellingPartnerAPIAA.AWSAuthenticationCredentials;
import cn.wangdian.amazon.SellingPartnerAPIAA.AWSAuthenticationCredentialsProvider;
import cn.wangdian.amazon.SellingPartnerAPIAA.LWAAuthorizationCredentials;
import cn.wangdian.amazon.spapi.solicitations.api.SolicitationsApi;
import cn.wangdian.amazon.spapi.solicitations.invoker.ApiException;
import cn.wangdian.amazon.spapi.solicitations.model.CreateProductReviewAndSellerFeedbackSolicitationResponse;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpApiApp {

    public static void main(String[] args) throws ApiException {
        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> na = new HashMap<>();
        na.put("region", "us-east-1");
        na.put("endpoint", "https://sellingpartnerapi-na.amazon.com");
        na.put("developerId", "955062355187");
        map.put("na", na);
        Map<String, String> eu = new HashMap<>();
        eu.put("region", "eu-west-1");
        eu.put("endpoint", "https://sellingpartnerapi-eu.amazon.com");
        eu.put("developerId", "220755676401");
        map.put("eu", eu);
        Map<String, String> fe = new HashMap<>();
        fe.put("region", "us-west-2");
        fe.put("endpoint", "https://sellingpartnerapi-fe.amazon.com");
        fe.put("developerId", "481830618952");
        map.put("fe", fe);
        String marketplace = "na";
        String token = "Atzr|IwEBINGdGe0zKx_GrOxi_rkuzJTBewy7ypUVFMUdrIRbCGmZj3nD-mzUbwpPgGgp7a-JZN4bnD7hFuqcG426Inc-uGzTNi4kDL_44-ikCtGUM-F64dl53wD7Wj0N9CVM8ZoA_qlpzU86UUCARj7HLnngVl-mcKvzgRhtryW-rPQblPd5z3DtV-MAQNqAkKg5LLhvgui6hB8yMrclLKgr6OVSV1w8EE1AUdB0d9Q8gt2zL4WQ3t98rfYPNJr2h5dJ1HPSeUq5nIOTIqBODE0VgZj-TCHUegk27lxnjiSYMnStdLzX2Eu1D-zRQSRRpbeLBGQOkhg";
        SolicitationsApi solicitationsApi = getSolicitationsApi(map.get(marketplace), token);
        String orderId = "113-2756925-2397840";
        List<String> marketplaceIdList = List.of("ATVPDKIKX0DER");
        CreateProductReviewAndSellerFeedbackSolicitationResponse response = solicitationsApi.createProductReviewAndSellerFeedbackSolicitation(orderId, marketplaceIdList);
        System.out.println(JSON.toJSONString(response));
    }

    private static SolicitationsApi getSolicitationsApi(Map<String, String> config, String token) {
        AWSAuthenticationCredentials awsAuthenticationCredentials = AWSAuthenticationCredentials.builder()
                .accessKeyId("AKIA4VRFWO37Q32P7YYB")
                .secretKey("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")
                .region(config.get("region"))
                .build();

        AWSAuthenticationCredentialsProvider awsAuthenticationCredentialsProvider = AWSAuthenticationCredentialsProvider
                .builder()
                .roleArn("arn:aws:iam::870883686143:role/wangdiantong")
                .roleSessionName("wdt-sp-api")
                .build();

        LWAAuthorizationCredentials lwaAuthorizationCredentials = LWAAuthorizationCredentials.builder()
                .clientId("amzn1.application-oa2-client.52f1a651754e46018cffa669c8e9b523")
                .clientSecret("30746afb71bffed4218be408fe89a746013fd38ee38f80b9a452fa6074635b49")
                .refreshToken(token)
                .endpoint("https://api.amazon.com/auth/o2/token")
                .build();

        SolicitationsApi sellersApi = new SolicitationsApi.Builder()
                .awsAuthenticationCredentials(awsAuthenticationCredentials)
                .lwaAuthorizationCredentials(lwaAuthorizationCredentials)
                .awsAuthenticationCredentialsProvider(awsAuthenticationCredentialsProvider)
                .endpoint(config.get("endpoint"))
                .build();
        return sellersApi;
    }
}
