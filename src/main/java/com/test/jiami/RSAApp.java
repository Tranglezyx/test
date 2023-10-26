package com.test.jiami;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;

@Slf4j
public class RSAApp {

    public static void main(String[] args) {
        decode();
    }

    public static void decode(){
        String secretKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANnOPp+W3MhQXdXm\n" +
                "nrbj/EpgUB4FYbbuNzL+5Fjf4bfpedPO/bdf34M88IIi1RRiM9RtV6mMNXCu3/lo\n" +
                "AL31wp5cUgoVl97XpPe74OYwbqdqrZYoyu0yCjKlturjPvYxSW5fV732Vrxk2v03\n" +
                "CjQ6BT0dNigpERN0IIOiqVTMSKT3AgMBAAECgYA2eHQE6W/R0PeGtSRgTOgdmbSv\n" +
                "rVzgqPa5FDH7YBJNMmC7bfrZz2pQof7sLNXpQR4BNE9GIcCDq9hrwWzeFQZUqlid\n" +
                "9a62YwtNtAdxTwan1I89wFkJFkDD/5eG2+kr9SVK6kq3bqYP7PwBUFFkhkuRrbME\n" +
                "Dlzl2uOKw55MXJKMoQJBAO50HP6MslX5DsFWiIVdbNSrgyo9hE/HSojbprORjW8t\n" +
                "MBq6bJxqjkFdY/xOt9vvn8kweLQO8HvxMo5U/fgyHUkCQQDp1Syv4igTD4SmnaaC\n" +
                "XTAKIuasWff3sXy9fhsdxvbjhBxJdlYcBAGbeqBQxpf0vx2K7cKtsQG0KBskvozz\n" +
                "h/A/AkEAjuaA9b+FpGYkhJMCL8JkVTMP4QGKyHCrZ0cTKOvica0H9gygyEBHTj7U\n" +
                "i8D1CFYbUOu+elG1puFOJeQja1lFGQJANr44kwjAVw4IATXhWJ3WdFkNZgY+HlgI\n" +
                "Gxjd4k/FVyiDstMof7CUa8D+rUqBIsRndzIIpL9O5c2J5UVTHGST4wJBAJgL2bMq\n" +
                "xI1os7Y/+erBQJN8qG+Gll9hIJaeRalPJNUdyjuLelsOtmgLxazjghYniSoEa+Dv\n" +
                "LhBDhNMek6pUsng=";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZzj6fltzIUF3V5p624/xKYFAe\n" +
                "BWG27jcy/uRY3+G36XnTzv23X9+DPPCCItUUYjPUbVepjDVwrt/5aAC99cKeXFIK\n" +
                "FZfe16T3u+DmMG6naq2WKMrtMgoypbbq4z72MUluX1e99la8ZNr9Nwo0OgU9HTYo\n" +
                "KRETdCCDoqlUzEik9wIDAQAB";
        publicKey = publicKey.replaceAll("\n", "");
        secretKey = secretKey.replaceAll("\n", "");
        String randomCode = "GGoK2ndldwBh/OFRnwEs9EhzHTYFdAOQjQxXcDk8FXeeWr8I2H4FgNnJbVrPD+ABCJRqH2FoDN/7b8xHRXtYE3NzX0Y95ZJkX/KXcWZGl6pS2NhKwOzDnrHGDoEKnsrBDZ66xX/6ojpFvbJj6PyL4sF4jLBpNTlTokU65h3yMFQ=";
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), secretKey, publicKey);
        String decryptStr = rsa.decryptStr(randomCode, KeyType.PrivateKey);
        log.info("解密结果：{}", decryptStr);
    }

    public static void test() {
        String secretKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANnOPp+W3MhQXdXm\n" +
                "nrbj/EpgUB4FYbbuNzL+5Fjf4bfpedPO/bdf34M88IIi1RRiM9RtV6mMNXCu3/lo\n" +
                "AL31wp5cUgoVl97XpPe74OYwbqdqrZYoyu0yCjKlturjPvYxSW5fV732Vrxk2v03\n" +
                "CjQ6BT0dNigpERN0IIOiqVTMSKT3AgMBAAECgYA2eHQE6W/R0PeGtSRgTOgdmbSv\n" +
                "rVzgqPa5FDH7YBJNMmC7bfrZz2pQof7sLNXpQR4BNE9GIcCDq9hrwWzeFQZUqlid\n" +
                "9a62YwtNtAdxTwan1I89wFkJFkDD/5eG2+kr9SVK6kq3bqYP7PwBUFFkhkuRrbME\n" +
                "Dlzl2uOKw55MXJKMoQJBAO50HP6MslX5DsFWiIVdbNSrgyo9hE/HSojbprORjW8t\n" +
                "MBq6bJxqjkFdY/xOt9vvn8kweLQO8HvxMo5U/fgyHUkCQQDp1Syv4igTD4SmnaaC\n" +
                "XTAKIuasWff3sXy9fhsdxvbjhBxJdlYcBAGbeqBQxpf0vx2K7cKtsQG0KBskvozz\n" +
                "h/A/AkEAjuaA9b+FpGYkhJMCL8JkVTMP4QGKyHCrZ0cTKOvica0H9gygyEBHTj7U\n" +
                "i8D1CFYbUOu+elG1puFOJeQja1lFGQJANr44kwjAVw4IATXhWJ3WdFkNZgY+HlgI\n" +
                "Gxjd4k/FVyiDstMof7CUa8D+rUqBIsRndzIIpL9O5c2J5UVTHGST4wJBAJgL2bMq\n" +
                "xI1os7Y/+erBQJN8qG+Gll9hIJaeRalPJNUdyjuLelsOtmgLxazjghYniSoEa+Dv\n" +
                "LhBDhNMek6pUsng=";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZzj6fltzIUF3V5p624/xKYFAe\n" +
                "BWG27jcy/uRY3+G36XnTzv23X9+DPPCCItUUYjPUbVepjDVwrt/5aAC99cKeXFIK\n" +
                "FZfe16T3u+DmMG6naq2WKMrtMgoypbbq4z72MUluX1e99la8ZNr9Nwo0OgU9HTYo\n" +
                "KRETdCCDoqlUzEik9wIDAQAB";
        publicKey = publicKey.replaceAll("\n", "");
        secretKey = secretKey.replaceAll("\n", "");
        log.info("publicKey:{}", publicKey);
        log.info("secretKey:{}", secretKey);
//        String randomKeyAes = "6530cee41cfac872";
        String randomKeyAes = "18b6680ba3170451";
//        String randomCode = "QegpuKn9EdeaolqHSFbqbbJQaZnNRuNhOB51Ka1HtTomOqk2Hdm8ULz8EyLiHRLWu+Vhz5Llt4W/WO7w0artd3BEFweFiSyHByQ3CWC44dm4SjZJdtzuReFAooWDN9mH7ByQZFJ72U6ySQHqlktIpWUwfvYLA19uzzeZshBQvC8=";
        String randomCode = "HswnUpuIOCIEiarG3mFLBIYgLAh/SGtmtmdEMYsmCp1o2Ip7NdqsxElHS+AanNqGIqMLB33VUKpgeaJdev0IbX7GOxPsaL0vznRL24y5cBu7Hnnos6NxHvXeV19Rq2euCu6+7jImaR9dZPkhsMqZPYsWS4I8tkMtcoxNRWJjRg0=";
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), secretKey, publicKey);
        String decryptStr = rsa.decryptStr(randomCode, KeyType.PrivateKey);
        log.info("解密结果：{},{}", decryptStr, decryptStr.equals(randomKeyAes));

//        String phoneSecret = "W7HWMp0VDQoUYi/c17+Asg==";
        String phoneSecret = "nBykB/yg3+Ft3DXVDvD5+A==";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, new SecretKeySpec(randomKeyAes.getBytes(), "AES"));

        String decrypt = new String(aes.decrypt(phoneSecret), StandardCharsets.UTF_8);
        log.info("解密结果：{}", decrypt);

        String newRandomKey = "18b5fc5b89595412";
        String encryptBase64 = rsa.encryptBase64(newRandomKey, KeyType.PublicKey);
        log.info("{}", encryptBase64);
    }
}
