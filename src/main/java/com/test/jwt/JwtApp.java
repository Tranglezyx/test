package com.test.jwt;


//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;

import java.util.HashMap;
import java.util.Map;

public class JwtApp {

    public static void main(String[] args) {
        Map<String, Object> header = new HashMap<String, Object>() {{
            put("alg", "HS256");
        }};
//        String token = JWT.create()
//                .withHeader(header)
//                .withClaim("iss", "uwKogqS9CydCoTZFkSz9ontUpP9CRQRn")
//                .sign(Algorithm.HMAC256("Qxl3TwNZJ7HRUoBJuTNfp1PG8jhMCF1G"));
//        System.out.println(token);
    }
}