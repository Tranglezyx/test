package com.test.io.nio;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author trangle
 */
public class TalkNIOServer {

    private static int PORT = 9090;
    private static Charset charset = Charset.forName("UTF-8");
    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    //相当于自定义协议格式，与客户端协商好
    private static String SPLIT = "@";
    private Selector selector;
    //用来记录在线人数，以及昵称,使用端口确定用户名
    private static Map<String, String> userMap = new HashMap<>();
    private ServerSocketChannel socketChannel;

    public static void main(String[] args) throws IOException {
        new TalkNIOServer(PORT).listener();
    }

    public TalkNIOServer(int port) throws IOException {
        socketChannel = ServerSocketChannel.open();

        socketChannel.bind(new InetSocketAddress(port));
        socketChannel.configureBlocking(false);

        selector = Selector.open();
        // 将通道注册到多路复用器上，设定接收的事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println(" --- 服务已启动 --- ");
        System.out.println(" 服务端通道地址为： " + socketChannel);
    }

    public void listener() throws IOException {
        while (true) {
            int selectFlag = selector.select();
            if (selectFlag == 0) {
                continue;
            }
            // 获得关注事件的集合
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                // 处理过的请求直接移除
                iterator.remove();
                processKey(selectionKey);
            }
        }
    }

    public void processKey(SelectionKey key) throws IOException {
        // 初次连接
        if (key.isAcceptable()) {
            // 根据处理请求获取服务端的通道
//            ServerSocketChannel server = (ServerSocketChannel) key.channel();
//            System.out.println("客户端第一次连接请求时，服务端通道地址为：" + server);
            System.out.println("客户端第一次连接请求时，服务端通道地址为：" + socketChannel);
            // 根据服务端通道生成客户端请求服务端数据生成的通道
            SocketChannel client = socketChannel.accept();
            System.out.println("客户端第一次连接请求时，客户端通道地址为：" + client);
            //非阻塞模式
            client.configureBlocking(false);
            //注册选择器，并设置为读取模式，收到一个连接请求，然后起一个SocketChannel，并注册到selector上，之后这个连接的数据，就由这个SocketChannel处理
            client.register(selector, SelectionKey.OP_READ);

            //将此对应的channel设置为准备接受其他客户端请求
            key.interestOps(SelectionKey.OP_ACCEPT);
            System.out.println("有客户端连接，IP地址为 :" + client.getRemoteAddress());
            client.write(charset.encode("请输入你的昵称"));
        }
        //处理来自客户端的数据读取请求，后续数据交互
        if (key.isReadable()) {

            SocketChannel client = (SocketChannel) key.channel();
            String portStr = String.valueOf(client.socket().getPort());
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            StringBuilder sb = new StringBuilder();
            while (client.read(byteBuffer) > 0) {
                byteBuffer.flip();
                sb.append(charset.decode(byteBuffer));
            }
            if (StringUtils.isEmpty(sb)) {
                client.write(charset.encode("请输入正确的消息 --- "));
                System.out.println("空消息 >>>> ");
            }
            System.out.println(client.getRemoteAddress() + "IP收到的消息" + sb);
            // 将通道设置为准备接收下一次消息
            key.interestOps(SelectionKey.OP_READ);
            String[] strings = sb.toString().split(SPLIT);
            String nickName = strings[0].trim();
            if (strings.length == 1) {
                if (userMap.containsKey(portStr) && !userMap.get(portStr).equals(nickName)) {
                    client.write(charset.encode(generateReturnMsg("改名成功，你当前的名称为 >>> " + nickName)));
                } else {
                    userMap.put(portStr, nickName);
                    String content = "欢迎进入聊天室，当前用户人数 >>> " + onlineCount();
                    broadCast(null, generateReturnMsg(content));
                }
            } else if (strings.length == 2) {
                String message = sb.substring(nickName.length() + SPLIT.length()).trim();
                if ("quit".equals(message)) {
                    client.write(charset.encode(generateReturnMsg("欢迎下次再来")));
                    client.close();
                }
                message = nickName + "说 : " + message;
                //不回发给发送此内容的客户端
                broadCast(client, generateReturnMsg(message));
            }
        }
    }

    public String generateReturnMsg(String msg) {
        return msg + "\r\n";
    }

    //TODO 要是能检测下线，就不用这么统计了
    public int onlineCount() {
        int res = 0;
        for (SelectionKey key : selector.keys()) {
            Channel target = key.channel();

            if (target instanceof SocketChannel) {
                res++;
            }
        }
        return res;
    }

    public void broadCast(SocketChannel client, String content) throws IOException {
        //广播数据到所有的SocketChannel中
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            //如果client不为空，不回发给发送此内容的客户端
            if (channel instanceof SocketChannel && channel != client) {
                SocketChannel target = (SocketChannel) channel;
                target.write(charset.encode(content));
            }
        }
    }
}
