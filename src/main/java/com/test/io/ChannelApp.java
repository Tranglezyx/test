package com.test.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author trangle
 */
public class ChannelApp {

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/trangle/Documents/xmind/Mysql.md");
        File newFile = new File("/Users/trangle/Documents/xmind/MysqlNew.md");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(newFile);
        FileChannel fileChannel = fileInputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            int flag = fileChannel.read(byteBuffer);
            if (flag == -1) {
                break;
            }
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        fileChannel.close();
        outChannel.close();
        fileInputStream.close();
        outputStream.close();
    }
}
