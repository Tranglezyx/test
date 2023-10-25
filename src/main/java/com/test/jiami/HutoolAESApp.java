package com.test.jiami;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class HutoolAESApp {

    public static void main(String[] args) {
        SecretKey key = SecureUtil.generateKey("AES", 128);
        String format = bytesToHexString(key.getEncoded());
        log.info("{},{}",format,key.getEncoded().length);
//        String secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
//        String secretKey = "F2D03D75AF64BFEC0F922C84D25CB26E";
        String secretKey = "6530cee41cfac872";
//        String secretKey = "1234123412341234";
        log.info("密钥：{},bytes:{}", secretKey,secretKey.getBytes().length);
        String text = "17602175650";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, new SecretKeySpec(secretKey.getBytes(), "AES"));
        String encrypt = aes.encryptBase64(text);
        log.info("加密结果：{}", encrypt);
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
