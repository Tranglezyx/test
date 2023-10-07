package com.test.jiami;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;

@Slf4j
public class RSAApp {

    public static void main(String[] args) {
        test();
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
