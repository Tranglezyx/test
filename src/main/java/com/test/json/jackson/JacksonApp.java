package com.test.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author trangle
 */
public class JacksonApp {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map = new HashMap<>();
        map.put("name","周云翔");
        map.put("date","2019-10-10");
        map.put("content","测试");
        map.put("title","标题");
        System.out.println(objectMapper.writeValueAsString(map));
    }
}
