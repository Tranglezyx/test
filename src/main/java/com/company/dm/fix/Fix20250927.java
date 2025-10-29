package com.company.dm.fix;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.LineHandler;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/9/27 11:04
 * @Description:
 */
public class Fix20250927 {

    static int count = 0;


    public static void main(String[] args) throws IOException {
//        assembleAllDrDTOData();
//        filterAllDrDTO();
        String drDTODataPath = "C:\\Users\\Trangle\\Downloads\\drDTO_test.txt";
        List<String> allDataList = Files.readAllLines(Paths.get(drDTODataPath));
        for (String string : allDataList) {
            sendKafka(string);
        }
    }

    public static void sendKafka(String str){
        System.out.println(str);
    }

    private static void filterAllDrDTO() throws IOException {
        String allDataPath = "C:\\Users\\Trangle\\Downloads\\allDrDTO.txt";
        String drDTODataPath = "C:\\Users\\Trangle\\Downloads\\drDTO.txt";

        List<String> smsIdList = Files.readAllLines(Paths.get("C:\\Users\\Trangle\\Downloads\\sms_id.tsv"));
        List<String> smsIdList0924 = Files.readAllLines(Paths.get("C:\\Users\\Trangle\\Downloads\\smsg_prod_t_sms_send_20250924.tsv"));
        Set<String> smsIdSet = new HashSet<>(smsIdList);
        smsIdSet.addAll(smsIdList0924);

        List<String> allDataList = Files.readAllLines(Paths.get(allDataPath));
        for (String data : allDataList) {
            DrDTO drDTO = JSONObject.parseObject(data, DrDTO.class);
            String smsId = drDTO.getSmsId();
            if (smsIdSet.contains(smsId)) {
                String value = StrUtil.format("{}\n", data);
                count++;
                FileUtil.appendUtf8String(value, drDTODataPath);
                System.out.println(StrUtil.format("最终过滤数据：{}", count));
            }
        }
    }

    private static void assembleAllDrDTOData() throws IOException {
        Map<String, JSONObject> map = new HashMap<>(1024000);
        map.putAll(getJsonMap("C:\\Users\\Trangle\\Downloads\\20250925.txt"));
        map.putAll(getJsonMap("C:\\Users\\Trangle\\Downloads\\20250926.txt"));
        map.putAll(getJsonMap("C:\\Users\\Trangle\\Downloads\\20250927.txt"));
        String fixData = "C:\\Users\\Trangle\\Downloads\\fixData.txt";
        String drDTODataPath = "C:\\Users\\Trangle\\Downloads\\allDrDTO.txt";
        List<String> list = Files.readAllLines(Paths.get(fixData));

        List<String> smsIdList = Files.readAllLines(Paths.get("C:\\Users\\Trangle\\Downloads\\sms_id.tsv"));
        Set<String> smsIdSet = new HashSet<>(smsIdList);


        List<DrDTO> drDTOList = new ArrayList<>();
        for (String str : list) {
            String[] split = str.split("\\|");
            String smsId = split[0];
            String accountId = split[1];
            String sign = split[2];
            String channelId = split[3];
            String phone = split[4];
            String key = split[5];

            JSONObject jsonObject = map.get(key);
            String deliverReq = jsonObject.getString("deliverReq");
            Long deliverTime = jsonObject.getLong("deliverTime");
            String msgId = jsonObject.getString("msgId");
            String protocolCode = jsonObject.getString("protocolCode");
            String protocolDesc = jsonObject.getString("protocolDesc");
            Integer status = jsonObject.getInteger("status");
            String toPort = jsonObject.getString("toPort");

            DrDTO drDTO = DrDTO.builder()
                    .accountId(Long.valueOf(accountId))
                    .channelId(channelId)
                    .deliverReq(deliverReq)
                    .msgId(msgId)
                    .phone(phone)
                    .protocolDesc(protocolDesc)
                    .protocolCode(protocolCode)
                    .reportTime(deliverTime)
                    .sendTime(IdUtil.getSnowflake().getGenerateDateTime(Long.valueOf(smsId)) + 1000L)
                    .sign(sign)
                    .smsId(smsId)
                    .status(status)
                    .toPort(toPort)
                    .build();

            drDTOList.add(drDTO);
        }
        Map<String, List<DrDTO>> collect = drDTOList.stream().collect(Collectors.groupingBy(DrDTO::getSmsId));
        Set<Map.Entry<String, List<DrDTO>>> entrySet = collect.entrySet();
        for (Map.Entry<String, List<DrDTO>> entry : entrySet) {
            List<DrDTO> valueList = entry.getValue();
            for (int i = 0; i < valueList.size(); i++) {
                DrDTO drDTO = valueList.get(i);
                drDTO.setPkTotal(valueList.size());
                drDTO.setPkNumber(i + 1);
            }
        }
        for (DrDTO drDTO : drDTOList) {
            String value = StrUtil.format("{}\n", JSONObject.toJSONString(drDTO));
            count++;
            FileUtil.appendUtf8String(value, drDTODataPath);
            System.out.println(StrUtil.format("最终过滤数据：{}", count));
        }
    }

    private static void showLogs() {
        FileUtil.readUtf8Lines(new File("C:\\Users\\Trangle\\Downloads\\deliverKeyAndResponseKey.txt"), (LineHandler) line -> {
            String start = "设置deliverKey=";
            String key = line.substring(line.indexOf(start) + start.length());
            String[] split = line.split("\\|");
            String smsId = split[3];
            String accountId = split[4];
            String sign = split[5];
            String channelId = split[6];
            String phone = split[7];
            String value = StrUtil.format("{}|{}|{}|{}|{}|{}\n", smsId, accountId, sign, channelId, phone, key);
            if (count < 10) {
                count++;
                System.out.println(StrUtil.format("最终过滤数据：{}", line));
            }
        });
    }

    private static void filterLogs(Set<String> keySet) {
        FileUtil.readUtf8Lines(new File("C:\\Users\\Trangle\\Downloads\\deliverKeyAndResponseKey.txt"), (LineHandler) line -> {
            String start = "设置deliverKey=";
            String key = line.substring(line.indexOf(start) + start.length());
            String[] split = line.split("\\|");
            String smsId = split[3];
            String accountId = split[4];
            String sign = split[5];
            String channelId = split[6];
            String phone = split[7];
            String value = StrUtil.format("{}|{}|{}|{}|{}|{}\n", smsId, accountId, sign, channelId, phone, key);
            if (keySet.contains(key)) {
                count++;
                FileUtil.appendUtf8String(value, "C:\\Users\\Trangle\\Downloads\\fixData.txt");
                System.out.println(StrUtil.format("最终过滤数据：{}", count));
            }
        });
    }

    private static Map<String, JSONObject> getJsonMap(String path) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(path));
        String start = "默认丢弃: ";
        Map<String, JSONObject> map = new HashMap<>(10240);
        for (String s : list) {
            String str = s.substring(s.indexOf(start) + start.length());
            JSONObject jsonObject = JSONObject.parseObject(str);
            String deliverKey = jsonObject.getString("deliverKey");
            map.put(deliverKey, jsonObject);
        }
        return map;
    }

    private static Set<String> getKeySet(String path) throws IOException {
        List<String> list = Files.readAllLines(Paths.get(path));
        String start = "默认丢弃: ";
        Set<String> keySet = new HashSet<>(10240);
        for (String s : list) {
            String str = s.substring(s.indexOf(start) + start.length());
            JSONObject jsonObject = JSONObject.parseObject(str);
            String deliverKey = jsonObject.getString("deliverKey");
            keySet.add(deliverKey);
        }
        return keySet;
    }
}
