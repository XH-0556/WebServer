package com.webserver.coer;

import com.webserver.http.EmptyRequetException;
import com.webserver.http.HttpContext;
import com.webserver.http.HttpResponse;
import com.webserver.http.HttpRequest;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 每个客户端连接后都会启动一个线程来完成与该客户端的交互
 * 交互过程遵循HTTP协议一问一答要求，分三步进行处理。
 * 1：解析请求
 * 2：处理请求
 * 3：响应客户端
 * @author uid
 */
public class ClientHandler implements Runnable{
    private Socket socket;
    public ClientHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        try {
            //  1：解析请求
            HttpRequest request = new HttpRequest(socket);
            HttpResponse response = new HttpResponse(socket);
            //  2：处理请求
            String path = request.getUri();
            File file = new File("./webapps"+path);

            //判断用户请求的资源是否存在并且还要求定位的是文件
            if(file.exists()&&file.isFile()){
                response.setEntity(file);
            }else{
                File notFoundpage = new File("./webapps/root/404.html");
                response.setStatuCode(404);
                response.setStatusReason("NotFound");
                response.setEntity(notFoundpage);
            }
            //告诉服务端我是
            response.putHeader("Serber","WebServer");
            //响应客户端
            response.flush();

            //单独捕获空请求异常，不需要做任何处理，目的仅是忽略处理工作
        }catch (EmptyRequetException e){

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
