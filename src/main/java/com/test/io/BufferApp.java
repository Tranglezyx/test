package com.test.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author trangle
 */
public class BufferApp {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/trangle/Documents/xmind/Mysql.md");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        output("init:", byteBuffer);
        while (true) {
            int flag = fileChannel.read(byteBuffer);
            output("read:", byteBuffer);
            if (flag == -1) {
                break;
            }
            byteBuffer.flip();
            output("flip:", byteBuffer);
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);
            output("get:", byteBuffer);
            System.out.println(new String(bytes));
            output("clea:r", byteBuffer);
            byteBuffer.clear();
        }
        fileChannel.close();
        fileInputStream.close();
    }

    public static void output(String string, ByteBuffer buffer) {
        System.out.println(string);
        System.out.println(buffer.capacity() + ":" + buffer.position() + ":" + buffer.limit());
    }
}
