package com.test.io.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/9/9 18:14
 * @Description:
 */
public class TcpServer {

    public static void main(String[] args) {
        int port = 8080; // 监听端口
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("🚀 服务器启动，等待客户端连接...");

            // 接受客户端连接（阻塞等待）
            Socket socket = serverSocket.accept();
            System.out.println("✅ 客户端已连接：" + socket.getInetAddress());

            // 获取输入输出流
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 读取客户端消息
            String message;
            while((message = in.readLine()) != null){
                System.out.println("📩 收到客户端消息: " + message);
                // 回复消息
                out.println("服务器收到: " + message);
            }

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
