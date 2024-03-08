package com.test.util.encryption;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class AESApp {

    public static void main(String[] args) throws Exception {
        String key = "797E59288350012A4B47B2C160936568";
        String phone = "18942979785";
        System.out.println(encrypt(phone,key));
    }

    private static Cipher CIPHER = null;

    static {
        try {
            CIPHER = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 随机生成密钥
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        // 设置密钥长度为128位
        keyGen.init(128);
        return bytesToHexString(keyGen.generateKey().getEncoded());
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

    /**
     * 加密
     *
     * @param plainText 需要加密的文本
     * @param secretKey 密钥
     * @return 加密后的内容
     * @throws Exception
     */
    public static String encrypt(String plainText, String secretKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        CIPHER.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = CIPHER.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 解密
     *
     * @param encryptedText 被加密的文本
     * @param secretKey     密钥
     * @return 解密后的文本
     * @throws Exception
     */
    public static String decrypt(String encryptedText, String secretKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        CIPHER.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = CIPHER.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
