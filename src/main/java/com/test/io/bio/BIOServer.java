package com.test.io.bio;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author trangle
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);

        while (true) {
            System.out.println("等待连接  >>>>>>  ");
            // 阻塞等待
            Socket client = serverSocket.accept();
            System.out.println("有连接进来了 >>>>> 端口号为 ：" + client.getPort());

            new Thread(() -> {
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    while (true) {
                        // 阻塞，等待客户端发送消息
                        String dataLine = reader.readLine();
                        if (StringUtils.isNotEmpty(dataLine) && !"quit".equals(dataLine)) {
                            System.out.println("客户端发送的消息 >>>> " + dataLine);
                        } else {
                            break;
                        }
                    }
                    reader.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
