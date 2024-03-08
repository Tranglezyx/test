package com.test.util.encryption;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RSAApp {

    public static void main(String[] args) {
        decode();
    }

    public static void decode(){
        String secretKey = "";
        String publicKey = "";
        publicKey = publicKey.replaceAll("\n", "");
        secretKey = secretKey.replaceAll("\n", "");
        String randomCode = "GGoK2ndldwBh/OFRnwEs9EhzHTYFdAOQjQxXcDk8FXeeWr8I2H4FgNnJbVrPD+ABCJRqH2FoDN/7b8xHRXtYE3NzX0Y95ZJkX/KXcWZGl6pS2NhKwOzDnrHGDoEKnsrBDZ66xX/6ojpFvbJj6PyL4sF4jLBpNTlTokU65h3yMFQ=";
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), secretKey, publicKey);
        String decryptStr = rsa.decryptStr(randomCode, KeyType.PrivateKey);
        log.info("解密结果：{}", decryptStr);
    }

    public static void test() {
        String secretKey = "";
        String publicKey = "";
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
