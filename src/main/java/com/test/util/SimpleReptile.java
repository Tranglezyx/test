package com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleReptile {
    /**
     * 获得urlStr对应网络内容
     * @param urlStr
     * @return
     */
    public static String getURLContent(String urlStr, String charset){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(charset)));
            String temp = "";
            while((temp = reader.readLine()) != null)
            {
                sb.append(temp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void doing(){
        String destStr = getURLContent("https://www.taobao.com","utf-8");

//        Pattern p = Pattern.compile("<a[\\s\\S]+?</a>");
        Pattern p = Pattern.compile("href=\"(.+?)\"");
        Matcher m = p.matcher(destStr);

        while(m.find()){
            System.out.println(m.group(1));
        }
    }
}
