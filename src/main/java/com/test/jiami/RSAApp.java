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
        String randomKeyAes = "6530cee41cfac872";
        String randomCode = "QegpuKn9EdeaolqHSFbqbbJQaZnNRuNhOB51Ka1HtTomOqk2Hdm8ULz8EyLiHRLWu+Vhz5Llt4W/WO7w0artd3BEFweFiSyHByQ3CWC44dm4SjZJdtzuReFAooWDN9mH7ByQZFJ72U6ySQHqlktIpWUwfvYLA19uzzeZshBQvC8=";
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), secretKey, publicKey);
        String decryptStr = rsa.decryptStr(randomCode, KeyType.PrivateKey);
        log.info("解密结果：{},{}", decryptStr,decryptStr.equals(randomKeyAes));

        String phoneSecret = "W7HWMp0VDQoUYi/c17+Asg==";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, new SecretKeySpec(randomKeyAes.getBytes(), "AES"));

        String decrypt = new String(aes.decrypt(phoneSecret), StandardCharsets.UTF_8);
        log.info("解密结果：{}", decrypt);

        String newRandomKey = "18b5fc5b89595412";
        String encryptBase64 = rsa.encryptBase64(newRandomKey, KeyType.PublicKey);
        log.info("{}",encryptBase64);
    }

    public static void test() {
        try {
            String text = "17602175650,13111111111";
            String secretKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOc9mHuN3vZR4iK6\n" +
                    "QSKzFfQrUYIt4AWV9/6t476eVtn9+nwbyy7mcw9AX2P7takqE+6x3n9cKCupFlxJ\n" +
                    "voB9UvFDlfsD2HnsI22cVvHvyU3GF+/RTEFEn8gwlOPoOf6Qky3ilmNfwAS7w9nM\n" +
                    "FIEr9/L1Up5XghD2PfxZhDDzsbclAgMBAAECgYBdyrgMdVDYLAJC/7TntCfVb7SR\n" +
                    "zrWszlVWcYWMjYDAzqlIqaVSEprctsl6LmBSiwSmrnKGygRpIR42tJqWVz7KY3b9\n" +
                    "wr1MISirJkoCkMuOoYtP85Z3FjKOAfMF0dPwnk6qIkfYRPBWRBGQKjFjKnn9gAY3\n" +
                    "CLVZlm54+NtETnZxiQJBAP+8dvsvtxXpkkfL1Xzt9jFgffjXdK2OHR7HxByfpS53\n" +
                    "81E9WtAycck2buGQa0nP47krqT0RfmdFWEvQohQ1Xb8CQQDneql65/yxZBfvlaOE\n" +
                    "9gspTDQV6iGy4rUjZWLHBWX6RsE2oMG170Y1Bjiqg/Tyuqz25E279cegBEyw/sL/\n" +
                    "DCwbAkAmoqVjC3FgiRoFaMEHD7hK+2XsqzhAZCD4Hnv9Sw0ceRi0ZE2M+KICIQwd\n" +
                    "XcEntJWVywfVZwgut+ZABfQGk41nAkEA3ZqDqANx2g1+EMLMAd1fABFRB+3WhyAc\n" +
                    "058bOndDkp+sF3U9HcTNDLRBCZU8qifmsJIOjTQWAW48UXIEEhGAIwJBAI1CA0tZ\n" +
                    "s8xMLDm1pTvGEvm6daeEZ9YkG7TJjKTgyCcfuoXSQFXrjSGY5e/8rp3FUdXnP/63\n" +
                    "Ig6bopnvSlym0Aw=";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDnPZh7jd72UeIiukEisxX0K1GC\n" +
                    "LeAFlff+reO+nlbZ/fp8G8su5nMPQF9j+7WpKhPusd5/XCgrqRZcSb6AfVLxQ5X7\n" +
                    "A9h57CNtnFbx78lNxhfv0UxBRJ/IMJTj6Dn+kJMt4pZjX8AEu8PZzBSBK/fy9VKe\n" +
                    "V4IQ9j38WYQw87G3JQIDAQAB";
//            KeyPair keyPair = SecureUtil.generateKeyPair("RSA");
//            String secretKey = Base64.encode(keyPair.getPrivate().getEncoded());
            log.info("密钥：{}", secretKey);
//            String publicKey = Base64.encode(keyPair.getPublic().getEncoded());
            log.info("公钥：{}", publicKey);

            RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), secretKey, publicKey);
            String result = rsa.encryptBase64(text, KeyType.PublicKey);
            log.info("加密结果：{}", result);
            String decryptStr = rsa.decryptStr(result, KeyType.PrivateKey);
            log.info("解密结果：{}", decryptStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
