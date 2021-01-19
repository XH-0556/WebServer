package com.webserver.coer;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 每个客户端连接后都会启动一个线程来完成与该客户端的交互
 * 交互过程遵循HTTP协议一问一答要求，分三步进行处理。
 * 1：解析请求
 * 2：处理请求
 * 3：响应客户端
 */
public class ClientHandler implements Runnable{
    private Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try{
            //  1：解析请求
            //http://localhost:8088/myweb/index.html
            //通过socket获取输入流，读取客户端发送的内容
            /*
                读取客户端发送过来的第一行字符串(以CRLF结尾)
             */
            InputStream in = socket.getInputStream();
            int d ;
            //cur表示本次读取字符，pre表示上次读取的字符
            char cur = 'a',pre ='a';
            //记录读取到的一行字符串
            StringBuilder builder = new StringBuilder();
            while ((d = in.read())!=-1){
                cur = (char)d;
                //如果上次读取到的是回车符并且本次读取的是换行符就停止
                if(pre==13 && cur==10){
                    break;
                }
                builder.append(cur);
                pre = cur;
            }
            String line = builder.toString().trim();
            System.out.println("请求行"+line);

            //请求行的请求方式
            String method;
            //抽象路径部分
            String uri;
            //协议版本
            String Protocol;
            //将请求行按照空格拆分为三部分，并复制给上述桑变量
          String[] data = line.split(" ");

            System.out.println("method"+data[0]);
            System.out.println("uri"+data[1]);
            System.out.println("Protocol"+data[2]);
//            System.out.println(uri);
//            System.out.println(Protocol);







            //  2：处理请求

            //  3：响应客户端
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //最终交互完毕后与客户端断开连接
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
