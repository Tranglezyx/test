package com.test.jar.redis;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/7/30 16:42
 * @Description:
 */
@Slf4j
public class JedisApp {

    public static final String queueKey = "queue:333";
    public static final Jedis jedis = new Jedis("192.168.11.131", 30479,
            DefaultJedisClientConfig.builder().password("LqYX8XMEMWYN2GTCgcvQ")
                    .database(3)
                    .build());
    public static final String script = "local result = redis.call('ZRANGE', KEYS[1], ARGV[1], ARGV[2]); " +
            "if #result > 0 then " +
            "redis.call('ZREM', KEYS[1], unpack(result)); " +
            "end; " +
            "return result;";


    public static void main(String[] args) {
//        push(100000);
//        String value = "{\"apiId\":1719239193430138882,\"apiSeqNo\":\"10236\",\"batchId\":\"2024073000000084\",\"chatbotId\":1719239193370034176,\"chatbotName\":\"蚣坝软件\",\"chatbotVersion\":\"v1\",\"city\":\"440300\",\"content\":\"\",\"conversationId\":\"c40fc0a8-dd79-4552-936b-0c2d33dbca57\",\"count\":1,\"cspReportList\":[{\"agentId\":1742853079228792834,\"batchId\":\"2024073000000084\",\"cspId\":1742751868122501121,\"providerChatbotApiParam\":\"{\\\"accessTagNo\\\":\\\"1068178520059800\\\",\\\"appId\\\":\\\"\\\",\\\"appKey\\\":\\\"\\\",\\\"chatbotToken\\\":\\\"M2RhNjYzNGRiZTg5ZDc5NDFhM2U1NTQ2NWY0ZmI0Zjg0N2YyYjExMTM3MjNlOTNkMmZkYWNhZWU5MmI4YTczZQ==\\\",\\\"yxAppId\\\":\\\"\\\",\\\"yxAppKey\\\":\\\"\\\"}\",\"providerChatbotNo\":\"1068178520059800\",\"providerCompanyNo\":\"311A311A296119399488\",\"templateReqDTO\":{\"apiId\":1719239193430138882,\"apiSeqNo\":\"10236\",\"id\":1816027831574986752,\"seqNo\":\"10720\",\"templateContentList\":[{\"id\":1816027831579181056,\"templateId\":1816027831574986752,\"textContent\":\"test666\",\"title\":\"\"}],\"templateName\":\"测试文本模板20240313(43)\",\"type\":\"TEXT\"}}],\"fallBack\":0,\"id\":1818276068319170560,\"messageCspReqDTOList\":[{\"agentId\":1742853079228792834,\"cspId\":1742751868122501121,\"cspName\":\"旦米5G消息平台-河北\",\"provider\":\"CMCC\",\"targetNumber\":\"13428945826\"}],\"province\":\"440000\",\"sessionId\":\"6d87ec37-6061-4e6a-9628-cd8c45ae287f\",\"smsFormat\":\"RCS_FORMAT\",\"smsType\":\"TEXT\",\"templateReqDTO\":{\"$ref\":\"$.cspReportList[0].templateReqDTO\"},\"type\":\"SEND\"}";
        String smsValue = "";
        for (int i = 0; i < 1; i++) {
            batchPush(1000000, smsValue, "queue:" + i);
        }
//        pollAll();
//        System.out.println(poll(1000));
//        scan();
    }

    public static void scan() {
        ScanParams scanParams = new ScanParams().match("queue*").count(2);
        // 初始游标
        String cursor = "0";
        do {
            // 执行 SCAN 命令
            ScanResult<String> scanResult = jedis.scan(cursor, scanParams);

            // 获取新的游标
            cursor = scanResult.getCursor();

            // 获取匹配的键列表
            for (String key : scanResult.getResult()) {
                System.out.println(key);
            }
            log.info("循环--");
        } while (!"0".equals(cursor));  // 当游标为 "0" 时，表示扫描结束
    }

    public static void pollAll() {
        int threadNum = 2;
        Executor executorService = Executors.newFixedThreadPool(threadNum);
        log.info("开始拉取---");
        for (int i = 0; i < threadNum; i++) {
            executorService.execute(() -> pollAll(5000));
        }
    }

    public static void pollAll(int size) {
        Jedis jedis = new Jedis("192.168.11.131", 30479, DefaultJedisClientConfig.builder().password("LqYX8XMEMWYN2GTCgcvQ").build());
        while (true) {
            List<String> list = (List<String>) jedis.eval(script, Arrays.asList(queueKey), Arrays.asList("0", String.valueOf(size - 1)));
            if (CollectionUtils.isEmpty(list)) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                log.info("拉取数量:{}", list.size());
            }
        }
    }

    public static List<String> poll(int size, Jedis jedis) {
        List<String> list = (List<String>) jedis.eval(script, Arrays.asList(queueKey), Arrays.asList("0", String.valueOf(size - 1)));
        log.info("拉取数量:{}", list.size());
        return list;
    }

    public static List<String> poll(int size) {
        List<String> list = (List<String>) jedis.eval(script, Arrays.asList(queueKey), Arrays.asList("0", String.valueOf(size - 1)));
        log.info("拉取数量:{}", list.size());
        return list;
    }

    public static void push(int size) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            jedis.zadd(queueKey, RandomUtil.randomInt(10), IdUtil.fastUUID());
        }
        log.info("push redis优先队列成功，耗时:{}ms，size:{}", System.currentTimeMillis() - start, size);
    }

    public static void batchPush(int size, String value, String queueName) {
        long start = System.currentTimeMillis();
        Map<String, Double> doubleMap = new HashMap<>();
        int num = 200;
        for (int i = 0; i < size; i++) {
            doubleMap.put(value + IdUtil.fastSimpleUUID(), new Double(RandomUtil.randomInt(10)));
        }
        log.info("构建value成功，耗时:{}ms，size:{}", System.currentTimeMillis() - start, size);

        start = System.currentTimeMillis();
        jedis.zadd(queueName, doubleMap);
        log.info("push redis优先队列成功，耗时:{}ms，size:{}", System.currentTimeMillis() - start, size);
    }
}
