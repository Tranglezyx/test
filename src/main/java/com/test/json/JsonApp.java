package com.test.json;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author trangle
 */
public class JsonApp {

    public static void main(String[] args) throws IOException {
        Object json = JSON.parse("{\n" +
                "    \"typeId\":\"typeId\",\n" +
                "    \"typeName\":\"typeName\",\n" +
                "    \"company\":[\n" +
                "        {\n" +
                "            \"companyId\":\"companyId\",\n" +
                "            \"companyName\":\"companyName\",\n" +
                "            \"area\":[\n" +
                "                {\n" +
                "                    \"areaId\":\"areaId\",\n" +
                "                    \"areaName\":\"areaName\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"areaId\":\"areaId2\",\n" +
                "                    \"areaName\":\"areaName2\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"companyId\":\"companyId3\",\n" +
                "            \"companyName\":\"companyName\",\n" +
                "            \"area\":[\n" +
                "                {\n" +
                "                    \"areaId\":\"areaId3\",\n" +
                "                    \"areaName\":\"areaName3\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}");
        String[] levelArray = {"company", "area"};
        List<Map<String, Object>> list = new ArrayList<>();
        for (String level : levelArray) {
            if (json instanceof Map) {
                if (((Map) json).containsKey(level) && ((Map) json).get(level) instanceof List) {
                    for (Object o : ((List) ((Map) json).get(level))) {
                        if (o instanceof Map) {
                            HashMap<String, Object> map = new HashMap<>();
                            map.putAll((Map<? extends String, ?>) o);
                            list.add(map);
                        }
                    }
                }
            }
        }
    }

    public static void handle(List<Map<String, Object>> list) {

    }
}
