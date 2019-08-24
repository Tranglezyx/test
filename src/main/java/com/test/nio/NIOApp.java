package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author trangle
 */
public class NIOApp {

    private static ByteBuffer readBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);//调整缓存的大小可以看到打印输出的变化

    private static String str;

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start() throws IOException {
        // 打开服务器套接字通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        channel.configureBlocking(false);
        // 进行服务的绑定
        channel.bind(new InetSocketAddress("localhost", 8001));

        // 获取一个接收器
        Selector selector = Selector.open();
        // 将服务器套接字通道注册到接收器上
        channel.register(selector, SelectionKey.OP_ACCEPT);

        while (!Thread.currentThread().isInterrupted()) {
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.keys();
            Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (!key.isValid()) {
                    continue;
                }
                if (key.isReadable()) {

                }
                if (key.isAcceptable()) {
                    accept(key, selector);
                }
                if (key.isWritable()) {
                    write(key, selector);
                }
//                keyIterator.remove();
            }
        }
    }

    private static void accept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected " + clientChannel.getRemoteAddress());
    }

    public static void read(SelectionKey selectionKey, Selector selector) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

        // Clear out our read buffer so it's ready for new data
        readBuffer.clear();
//        readBuffer.flip();
        // Attempt to read off the channel
        int numRead;
        try {
            numRead = socketChannel.read(readBuffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel
            // the selection key and close the channel.
            selectionKey.cancel();
            socketChannel.close();

            return;
        }

        str = new String(readBuffer.array(), 0, numRead);
        System.out.println(str);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    public static void write(SelectionKey selectionKey, Selector selector) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        System.out.println("write: " + str);
        sendBuffer.clear();
        sendBuffer.put(str.getBytes());
        socketChannel.write(sendBuffer);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }
}
