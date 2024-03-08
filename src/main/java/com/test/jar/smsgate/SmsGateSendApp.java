package com.test.jar.smsgate;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.chinamobile.cmos.MessageReceiver;
import com.chinamobile.cmos.SmsClient;
import com.chinamobile.cmos.SmsClientBuilder;
import com.zx.sms.BaseMessage;
import com.zx.sms.codec.cmpp.msg.CmppSubmitRequestMessage;
import com.zx.sms.codec.cmpp.msg.CmppSubmitResponseMessage;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author: Trangle
 * @Date: 2024/3/8 15:04
 * @Description:
 */
@Slf4j
public class SmsGateSendApp {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            10,
            30,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(10000000));

    public static void main(String[] args) throws Exception {
        String uri = "cmpp://192.168.11.131:30393?username=pcdG01&password=NV3pxt6v&version=32&spcode=10086&msgsrc=test01&serviceid=000000&window=1024&maxchannel=1";

        //通过builder创建一个Client对象，同一个通道账号只用保持一个smsClient实例。可以使用Spring注册为单例Bean。或者单例模式
        SmsClientBuilder builder = new SmsClientBuilder();
//		EndpointEntity client =  builder.createEndpointEntity(uri);
        final SmsClient smsClient = builder.uri(uri) // 保持空闲连接，以便能接收上行或者状态报告消息
                .receiver(new MessageReceiver() {
                    public void receive(BaseMessage message) {
                        log.info("收到回执:{}", JSONObject.toJSONString(message));
                    }
                }).build();


        Future future = null;
        int size = 3;
        //发送5000条短信
        for (int i = 0; i < size; i++) {
            future = executor.submit(new Runnable() {

                public void run() {
                    //new 一个短信 Request对象
                    CmppSubmitRequestMessage msg = new CmppSubmitRequestMessage();
                    msg.setDestterminalId(String.valueOf(17602175650L + RandomUtil.randomInt(9)));
//                    msg.setSrcId(String.valueOf(10699802323));
                    msg.setLinkID("0000");
                    msg.setMsgContent("【庄点科技】我是1536测试短信内容");
                    msg.setRegisteredDelivery((short) 1);
                    msg.setServiceId("ssss");
                    CmppSubmitResponseMessage response;
                    try {
                        //调用send方法发送
                        response = (CmppSubmitResponseMessage) smsClient.send(msg);
                        log.info("发送结果:{}", JSONObject.toJSONString(response));
                        //收到Response消息
                    } catch (Exception e) {
                        log.info("send error:", e);
                    }
                }

            });
            log.info("发送完成 ---,第 {} 条", i);
        }
        while (true) {
            int count = executor.getQueue().size();
            log.info("容量:{}", count);
            Thread.sleep(1000L);
            if (count == 0) {
                return;
            }
        }
    }
}
