package com.test.jiami;

import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class RSAApp {

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        try {
            // 使用RSA算法生成密钥对
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // 设置密钥长度
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // 获取公钥和私钥
            Key publicKey = keyPair.getPublic();
            Key privateKey = keyPair.getPrivate();

//            RSAPublicKey publicKey = RSAPublicKeyImpl.newKey(("MIICXQIBAAKBgQDnPZh7jd72UeIiukEisxX0K1GCLeAFlff+reO+nlbZ/fp8G8su\n" +
//                    "5nMPQF9j+7WpKhPusd5/XCgrqRZcSb6AfVLxQ5X7A9h57CNtnFbx78lNxhfv0UxB\n" +
//                    "RJ/IMJTj6Dn+kJMt4pZjX8AEu8PZzBSBK/fy9VKeV4IQ9j38WYQw87G3JQIDAQAB\n" +
//                    "AoGAXcq4DHVQ2CwCQv+057Qn1W+0kc61rM5VVnGFjI2AwM6pSKmlUhKa3LbJei5g\n" +
//                    "UosEpq5yhsoEaSEeNrSallc+ymN2/cK9TCEoqyZKApDLjqGLT/OWdxYyjgHzBdHT\n" +
//                    "8J5OqiJH2ETwVkQRkCoxYyp5/YAGNwi1WZZuePjbRE52cYkCQQD/vHb7L7cV6ZJH\n" +
//                    "y9V87fYxYH3413Stjh0ex8Qcn6Uud/NRPVrQMnHJNm7hkGtJz+O5K6k9EX5nRVhL\n" +
//                    "0KIUNV2/AkEA53qpeuf8sWQX75WjhPYLKUw0FeohsuK1I2VixwVl+kbBNqDBte9G\n" +
//                    "NQY4qoP08rqs9uRNu/XHoARMsP7C/wwsGwJAJqKlYwtxYIkaBWjBBw+4Svtl7Ks4\n" +
//                    "QGQg+B57/UsNHHkYtGRNjPiiAiEMHV3BJ7SVlcsH1WcILrfmQAX0BpONZwJBAN2a\n" +
//                    "g6gDcdoNfhDCzAHdXwARUQft1ocgHNOfGzp3Q5KfrBd1PR3EzQy0QQmVPKon5rCS\n" +
//                    "Do00FgFuPFFyBBIRgCMCQQCNQgNLWbPMTCw5taU7xhL5unWnhGfWJBu0yYyk4Mgn\n" +
//                    "H7qF0kBV640hmOXv/K6dxVHV5z/+tyIOm6KZ70pcptAM".getBytes()).getBytes());
//
//            RSAPrivateKey privateKey = RSAPrivateCrtKeyImpl.newKey(("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOc9mHuN3vZR4iK6\n" +
//                                "QSKzFfQrUYIt4AWV9/6t476eVtn9+nwbyy7mcw9AX2P7takqE+6x3n9cKCupFlxJ\n" +
//                                "voB9UvFDlfsD2HnsI22cVvHvyU3GF+/RTEFEn8gwlOPoOf6Qky3ilmNfwAS7w9nM\n" +
//                                "FIEr9/L1Up5XghD2PfxZhDDzsbclAgMBAAECgYBdyrgMdVDYLAJC/7TntCfVb7SR\n" +
//                                "zrWszlVWcYWMjYDAzqlIqaVSEprctsl6LmBSiwSmrnKGygRpIR42tJqWVz7KY3b9\n" +
//                                "wr1MISirJkoCkMuOoYtP85Z3FjKOAfMF0dPwnk6qIkfYRPBWRBGQKjFjKnn9gAY3\n" +
//                                "CLVZlm54+NtETnZxiQJBAP+8dvsvtxXpkkfL1Xzt9jFgffjXdK2OHR7HxByfpS53\n" +
//                                "81E9WtAycck2buGQa0nP47krqT0RfmdFWEvQohQ1Xb8CQQDneql65/yxZBfvlaOE\n" +
//                                "9gspTDQV6iGy4rUjZWLHBWX6RsE2oMG170Y1Bjiqg/Tyuqz25E279cegBEyw/sL/\n" +
//                                "DCwbAkAmoqVjC3FgiRoFaMEHD7hK+2XsqzhAZCD4Hnv9Sw0ceRi0ZE2M+KICIQwd\n" +
//                                "XcEntJWVywfVZwgut+ZABfQGk41nAkEA3ZqDqANx2g1+EMLMAd1fABFRB+3WhyAc\n" +
//                                "058bOndDkp+sF3U9HcTNDLRBCZU8qifmsJIOjTQWAW48UXIEEhGAIwJBAI1CA0tZ\n" +
//                                "s8xMLDm1pTvGEvm6daeEZ9YkG7TJjKTgyCcfuoXSQFXrjSGY5e/8rp3FUdXnP/63\n" +
//                                "Ig6bopnvSlym0Aw=".getBytes()).getBytes());

            System.out.println("公钥：" + publicKey);
            System.out.println("私钥：" + privateKey);

            // 创建加密和解密的实例
            Cipher cipher = Cipher.getInstance("RSA");

            // 加密
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedData = cipher.doFinal("Hello, World!".getBytes());
            String result = Base64.getEncoder().encodeToString(encryptedData);
            System.out.println("加密后的数据：" + result);


            // 解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(result));
            System.out.println("解密后的数据：" + new String(decryptedData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateKey(){
        try {
            // 使用RSA算法生成密钥对
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // 设置密钥长度
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            System.out.println("公钥：" + keyPair.getPublic());
            System.out.println("私钥：" + keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
