package com.webserver.coer;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * WebServer
 */
public class WeServer {
    private ServerSocket serverSocket;

    /**
     * 构造器，用于初始化
     */
    public  WeServer(){
            try {
                System.out.println("正在启用服务端");
                serverSocket = new ServerSocket(8088);
                System.out.println("服务端启动完毕");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * 服务端开始工作的方法
     */
    public void start(){
        try {
            System.out.println("等待客户端连接");
            Socket socket = serverSocket.accept();
            System.out.println("一个客户端连接链接了");

            //启动一个线程来处理客户端的交互工作
            ClientHandler handler = new ClientHandler(socket);
            Thread t = new Thread(handler);
            t.start();







        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        WeServer server = new WeServer();
        server.start();


    }






}
