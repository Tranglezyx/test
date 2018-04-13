package com.test;

import com.test.util.CommUtils;
import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.StringUtils;

import java.math.BigDecimal;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author Trangle
 * Hello world!
 */
public class App extends WeChatBot {

    public App(Config config){
        super(config);
    }

    public static void main(String[] args) throws InterruptedException, SocketException, UnknownHostException {
//        Doing say = new Say();
//        InvocationHandler handler = new InvocationHandlerUtils(say);
//        Doing proxy = (Doing) Proxy.newProxyInstance(say.getClass().getClassLoader(),say.getClass().getInterfaces(),handler);
//        proxy.say("hello world");

//        new App(Config.me().autoLogin(true).showTerminal(true)).start();
//        Map<String,String> map = System.getenv();
//        System.out.println(CommUtils.getIp());
//        System.out.println(map.get("USERNAME"));//获取用户名
//        System.out.println(map.get("COMPUTERNAME"));//获取计算机名
//        System.out.println(map.get("USERDOMAIN"));//获取计算机域名

        System.out.println(0 % 100);
    }

    @Bind(msgType = MsgType.TEXT)
    public void handleText(WeChatMessage message) {
        if (StringUtils.isNotEmpty(message.getName())) {
            System.out.println("接收到 [{}] 的消息: {}" + "\t" + message.getName() + "\t" +  message.getText());
            this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
        }
    }
}


