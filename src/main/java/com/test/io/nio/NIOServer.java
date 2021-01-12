package com.test.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author trangle
 */
public class NIOServer {

    private static ByteBuffer readBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化

    private static String str;

    public static void main(String[] args) throws IOException, InterruptedException {
        start();
    }

    public static void start() throws IOException, InterruptedException {
        List<SocketChannel> clientList = new ArrayList<>();
        // 打开服务器套接字通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 设置连接通道为非阻塞
        channel.configureBlocking(false);
        // 进行服务的绑定
        channel.bind(new InetSocketAddress("localhost", 8001));
        // 一直循环监听，此方法也利用了NIO的特性，但没有利用多路复用器
        // 等于一直在不停的调用系统方法，消耗系统资源
        // 利用多路复用器可以改进，这样只需要调用一次系统方法
//        whileTrueListen(clientList, channel);

        // 获取一个接收器 selector是java对底层多路复用器的封装，linux一般是epoll，unix是kqueue
        Selector selector = Selector.open();
        // 将服务器套接字通道注册到接收器上
        channel.register(selector, SelectionKey.OP_ACCEPT);
        // 转用多路复用器处理
        processSelector(selector);
        return;
    }

    private static void processSelector(Selector selector) throws IOException {
        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    processSelectionKey(selector, iterator, key);
                } else if (key.isReadable()) {
                    processSelectionKey(selector, iterator, key);
                } else if (key.isWritable()) {
                    processSelectionKey(selector, iterator, key);
                } else {
                    System.out.println("其他未知连接");
                }
            }
        }
    }

    private static void processSelectionKey(Selector selector, Iterator<SelectionKey> iterator, SelectionKey key) throws IOException {
        // 获取多路复用器封装的通道
        ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
        // 获取通道里接收的客户端
        SocketChannel client = socketChannel.accept();
        // 等待客户端发送消息也设置为非阻塞
        client.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
        // 将客户端注册到多路复用器上
        client.register(selector, SelectionKey.OP_READ, buffer);
        // 根据规则处理消息
        processClientMsg(buffer, iterator, client);
    }

    /**
     * 一直循环监听，此方法也利用了NIO的特性，但没有利用多路复用器
     * 等于一直在不停的调用系统方法，消耗系统资源
     *
     * @param clientList
     * @param channel
     * @throws InterruptedException
     * @throws IOException
     */
    private static void whileTrueListen(List<SocketChannel> clientList, ServerSocketChannel channel) throws InterruptedException, IOException {
        System.out.println("NIO开始等待客户端连接 >>>> ");
        while (true) {
            SocketChannel client = channel.accept();
            if (client == null) {
                // 当前通道会一直收到空连接数据，但并不是阻塞的
            } else {
                // 设置读取客户端数据也为非阻塞
                client.configureBlocking(false);
                System.out.println("客户端连进来了 >>> 端口为 ： " + client.socket().getPort());
                clientList.add(client);
            }
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            Iterator<SocketChannel> iterator = clientList.iterator();
            while (iterator.hasNext()) {
                SocketChannel tmpClient = iterator.next();
                int flag = tmpClient.read(buffer);
                if (flag > 0) { // 0，-1，大于0
                    processClientMsg(buffer, iterator, tmpClient);
                }
            }
        }
    }

    /**
     * 处理消息
     *
     * @param buffer
     * @param iterator
     * @param tmpClient
     * @throws IOException
     */
    private static void processClientMsg(ByteBuffer buffer, Iterator iterator, SocketChannel tmpClient) throws IOException {
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        String str = new String(bytes).trim();
        System.out.println("端口为 : " + tmpClient.socket().getPort() + "客户端传入的数据 >>>> " + str);
        buffer.clear();
        if ("quit".equals(str)) {
            System.out.println("收到退出请求，连接关闭 --- ");
            iterator.remove();
            tmpClient.close();
        }
    }
}
