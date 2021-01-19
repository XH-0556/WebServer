package com.webserver.servlet;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;
import sun.misc.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LoginServlet {
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println("开始登录了");
        String usename = request.getParameter("usename");
        String password = request.getParameter("password");
        if(usename==null||password==null){
            response.setEntity(new File("./webapps/myweb/login_fail.html"));
            return;
        }


        try( RandomAccessFile raf = new RandomAccessFile("user.dat", "r")){
        for(int i=0;i<raf.length()/100;i++){
            raf.seek(i*100);
            byte[] data = new byte[32];
            raf.read(data);
            String name = new String(data,"UTF-8").trim();
            if(name.equals(usename)){
                raf.read(data);
                String pwd = new String(data,"UTF-8").trim();
                if(pwd.equals(password)){
                    response.setEntity(new File("./webapps/myweb/login_success.html"));
                    return;
                }
                break;
            }else{
                response.setEntity( new File("./webapps/myweb/login_fail.html"));
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("登录完毕");

    }
}
