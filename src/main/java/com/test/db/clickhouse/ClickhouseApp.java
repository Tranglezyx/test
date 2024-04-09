package com.test.db.clickhouse;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.clickhouse.client.*;
import com.clickhouse.data.ClickHouseFormat;
import com.clickhouse.jdbc.ClickHouseDataSource;
import com.google.common.collect.Lists;
import com.test.db.clickhouse.entity.SmsSend;
import com.test.db.utils.SQLUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Author: zhouyunxiang
 * @Date: 2024/4/8 16:10
 * @Description:
 */
@Slf4j
public class ClickhouseApp {

    private static ClickHouseDataSource dataSource = null;

    static {
        String url = "jdbc:ch://192.168.11.131:31123/default"; // use http protocol and port 8123 by default
        Properties properties = new Properties();
        try {
            dataSource = new ClickHouseDataSource(url, properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Executor executor = Executors.newFixedThreadPool(30);

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        for (int i = 0; i < 30; i++) {
            executor.execute(ClickhouseApp::extracted);
        }
    }

    private static void extracted() {
        long start = System.currentTimeMillis();
        int size = 100000;
        List<SmsSend> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            SmsSend smsSend = buildSmsSend();
            list.add(smsSend);
        }

        try {
            Connection conn = dataSource.getConnection("default", "admin");
            Statement stmt = conn.createStatement();
            List<List<SmsSend>> partition = Lists.partition(list, 200);
            for (int i = 0; i < partition.size(); i++) {
                String insertSQL = SQLUtils.generateBatchInsertSQL(partition.get(i));
                ResultSet rs = stmt.executeQuery(insertSQL);
                while (rs.next()) {
                    System.out.println(rs);
                }
                log.info("第{}批数据insert完毕", i);
            }
        } catch (Exception e) {
            log.error("error:", e);
        }
        log.info("{}数据量全部insert完成，耗时:{}ms", size, System.currentTimeMillis() - start);
    }

    public static SmsSend buildSmsSend() {
        String str = "{  \n" + "  \"id\": 1776788965775044620,  \n" + "  \"businessType\": 1,  \n" + "  \"smsId\": 1775448250313027585,  \n" + "  \"batchId\": \"ia1775448250313027584\",  \n" + "  \"userId\": 1768794118,  \n" + "  \"userSubAccountId\": 100072421159,  \n" + "  \"extensionCode\": \"18\",  \n" + "  \"targetNumber\": \"13112311231\",  \n" + "  \"operatorId\": null,  \n" + "  \"provinceName\": null,  \n" + "  \"cityName\": null,  \n" + "  \"channelId\": null,  \n" + "  \"sendStatus\": 5,  \n" + "  \"statusCode\": \"10216\",  \n" + "  \"splitsTotal\": 1,  \n" + "  \"splitsCurrent\": 1,  \n" + "  \"signContent\": \"【庄点科技】\",  \n" + "  \"splitMessage\": \"【庄点科技】我是cmpp测试短信内容\",  \n" + "  \"isBilling\": 0,  \n" + "  \"submitTime\": 1712486522039,  \n" + "  \"sendTime\": null,  \n" + "  \"receiptTime\": null,  \n" + "  \"submitType\": 1,  \n" + "  \"userMsgId\": \"ia1775448250313027584\",  \n" + "  \"channelMsgId\": null,  \n" + "  \"channelAccessCode\": null,  \n" + "  \"protocolDesc\": \"签名未审核通过\",  \n" + "  \"deliverReq\": null,  \n" + "  \"billingType\": 2,  \n" + "  \"isFeeReturn\": null,  \n" + "  \"isTariffControl\": null,  \n" + "  \"successRateType\": null,  \n" + "  \"params\": null,  \n" + "  \"paramsSymbol\": null,  \n" + "  \"templateId\": \"18\",  \n" + "  \"originalChannelId\": null,  \n" + "  \"outsideCode\": null,  \n" + "  \"productId\": null,  \n" + "  \"auditMode\": null,  \n" + "  \"auditType\": null,  \n" + "  \"partnerId\": 1631559065475743744,  \n" + "  \"partnerBonus\": null,  \n" + "  \"mobilePrice\": 100,  \n" + "  \"telecomPrice\": 300,  \n" + "  \"unicomPrice\": 200,  \n" + "  \"refundReq\": null,  \n" + "  \"refundReps\": null,  \n" + "  \"refundDate\": null,  \n" + "  \"billStatus\": 0,  \n" + "  \"billReps\": null,  \n" + "  \"updateBillTime\": null,  \n" + "  \"userSourceNo\": null,  \n" + "  \"isLeakage\": 0,  \n" + "  \"isThroughLeakage\": 0,  \n" + "  \"timeFlag\": 0,  \n" + "  \"retryChannelListStr\": null,  \n" + "  \"locationBoListStr\": null,  \n" + "  \"historyChannelIdSet\": null,  \n" + "  \"repeatChannelId\": null  \n" + "}";
        SmsSend smsSend = JSONObject.parseObject(str, SmsSend.class);
        smsSend.setId(IdUtil.getSnowflakeNextId());
        smsSend.setSmsId(IdUtil.getSnowflakeNextId());
        smsSend.setTargetNumber("1" + RandomUtil.randomNumbers(2) + RandomUtil.randomNumbers(8));
        smsSend.setSendStatus(RandomUtil.randomInt(1, 4));
        smsSend.setOperatorId(RandomUtil.randomLong(1, 4));
        smsSend.setMobilePrice(RandomUtil.randomLong(300, 501));
        smsSend.setTelecomPrice(RandomUtil.randomLong(300, 501));
        smsSend.setUnicomPrice(RandomUtil.randomLong(300, 501));
        long day = 1000 * 60 * 60 * RandomUtil.randomLong(1,25);

        smsSend.setSubmitTime(System.currentTimeMillis() + RandomUtil.randomLong(-100, 100) * day);
        smsSend.setChannelId(RandomUtil.randomLong(1, 500));
        smsSend.setExtensionCode(RandomUtil.randomNumbers(6));
        smsSend.setUserSubAccountId(RandomUtil.randomLong(10000,20000));

        return smsSend;
    }
}
