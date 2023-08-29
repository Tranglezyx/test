package com.test.sms;

import com.google.common.collect.Lists;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SendSmsApp {

    public static void main(String[] args) throws IOException {
        String fileToString = FileUtils.readFileToString(new File("src/main/resources/phone.txt"), "UTF-8");
        List<String> list = Lists.newArrayList(fileToString.split("\n"));
        List<List<String>> partition = Lists.partition(list, 50);
        for (List<String> stringList : partition) {
            String phone = stringList.stream().collect(Collectors.joining(","));
            phone = phone.replaceAll("\n", "").replaceAll("\r", "");
            sendSMS(phone);
        }
    }

    private static void sendSMS(String phone) {
        String url = "https://openapi.danmi.com/distributor/sendSMS";
        String content = "【建设银行】尊敬的客户，您可在本行申请一笔450000元备用金，期限三年，随用随还。如有需要请及时回复，回复1查利率，回复2办理退订回T";
        String sid = "";
        String token = "";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String accountId = "983697";
        String sig = DigestUtils.md5Hex(sid + token + timestamp);
        // 构建表单数据
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("to", phone)
                .add("smsContent", content)
                .add("accountSid", sid)
                .add("timestamp", timestamp)
                .add("sig", sig)
                .add("accountId", accountId);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .post(formBuilder.build())
                .build();

        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println(responseBody);
        } catch (Exception e) {
            System.out.println("发送失败: " + phone);
        }
    }
}
