package com.test.io.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/9/9 18:14
 * @Description:
 */
public class TcpClient {

    public static void main(String[] args) {
        String host = "127.0.0.1"; // 服务器地址
        int port = 8080;           // 服务器端口
        Scanner scanner = new Scanner(System.in);
        try (Socket socket = new Socket(host, port)) {
            System.out.println("✅ 已连接到服务器: " + host + ":" + port);

            // 获取输入输出流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while(scanner.hasNext()){
                String nextLine = scanner.nextLine();
                // 向服务器发送消息
                out.println(nextLine);
                System.out.println("📤 发送消息: " + nextLine);

                // 接收服务器回复
                String response = in.readLine();
                System.out.println("📩 服务器回复: " + response);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
