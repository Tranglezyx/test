package com.test.util.encryption;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HutoolAESApp {

    public static void main(String[] args) {
        decode();
    }

    public static void decode(){
        String secretKey = "18b69a9b9dc66603";
        String content = "IUzGi8ngwN+T2r1YTVq8XQ==";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, new SecretKeySpec(secretKey.getBytes(), "AES"));
        String decrypt = new String(aes.decrypt(content), StandardCharsets.UTF_8);
        log.info("解密结果：{}", decrypt);
    }

    public static void test(){
        SecretKey key = SecureUtil.generateKey("AES", 128);
        String format = bytesToHexString(key.getEncoded());
        log.info("{},{}",format,key.getEncoded().length);
//        String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
//        String secretKey = "F2D03D75AF64BFEC0F922C84D25CB26E";
        String secretKey = "6530cee41cfac872";
//        String secretKey = "1234123412341234";
        log.info("密钥：{},bytes:{}", secretKey,secretKey.getBytes().length);
        String text = "17602175650,13713826456,18942979785";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, new SecretKeySpec(secretKey.getBytes(), "AES"));
        String encrypt = aes.encryptBase64(text);
        log.info("加密结果：{}", encrypt);
//        String encrypt = "N5tU8QsN6dDtOXbBAOv8cyxu8aoJLes0chnLr0c7Xu+I0IiEnbh8Dl+hm2oLees4/iZjq1mpqm9Dt+8a/DCQ1kTQ2zRScoj1FNPunKEsXvuFpoFQxp3ekC8e6FKx7f5tWjDRDDni1V0Gui6NxvE78lPKw1/srMfDIyp0+9tdEDfaWlx3VjI0vPyzw1wbIJts";
        String decrypt = new String(aes.decrypt(encrypt), StandardCharsets.UTF_8);
        log.info("解密结果：{}", decrypt);
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%2X", b));
        }
        return result.toString();
    }
}
