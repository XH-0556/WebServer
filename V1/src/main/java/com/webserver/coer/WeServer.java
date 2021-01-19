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



            /*
                如果读取客户端发送过来的内容是乱码，通常是应为浏览器地址栏
                中输入的是http协议！！
                要使用http协议
                http://localhost:8088
             */
            //通过socket获取输入流，读取客户端发送的内容
            InputStream in = socket.getInputStream();
            int d ;
            while ((d = in.read())!=-1){
                System.out.print((char) d);

            }





        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        WeServer server = new WeServer();
        server.start();


    }






}
