package com.test.io.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8189);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(inputStream);
            PrintWriter out = new PrintWriter(outputStream);

            out.println("hello ~~~~~~~~~~~~~~~~~~~~~~~~");

            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                System.out.println(line);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
