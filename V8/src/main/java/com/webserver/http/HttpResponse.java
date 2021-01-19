package com.webserver.http;
import com.sun.xml.internal.ws.client.SenderException;
import java.io.*;
import java.net.Socket;
import java.security.PublicKey;
/**
 * 响应对象，当前类的每一个实例用于表示服务端发送给客户端的一个标准的
 * HTTP响应内容
 * 每个响应有三部分,状态行，响应头，响应正文
 *  @author uid
 */
public class HttpResponse {
    //状态行相关信息
        private  int statuCode = 200;            //状态代码，默认值200
        private  String statusReason = "OK";     //状态描述，默认值OK
    //响应头相关信息

    //响应正文相关信息
    private File entity;



    private Socket socket;
    public HttpResponse(Socket socket) {
        this.socket = socket;
    }

    public void flush(){
    SendStatusLine();
    sendHeaders();
    sendContent();
    }

    /**
     * 发送状态行
     */

    public void  SendStatusLine(){
        System.out.println("HttpResqonse:发送状态行.....");
        try {
            OutputStream out = socket.getOutputStream();
            String line = "HTTP/1.1"+" "+statuCode+" "+statusReason;
            out.write(line.getBytes("ISO-8859-1"));
            out.write(13);
            out.write(10);
            System.out.println("HttpResqonse:状态行发送完毕！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *发送响应头
     */
    public void sendHeaders(){
        System.out.println("HttpResqonse:发送响应头.....");
        try {
        OutputStream out = socket.getOutputStream();
        String line = " Content-Type: text/html";
        out.write(line.getBytes("ISO-8859-1"));
        out.write(13);
        out.write(10);

        line = " Content-Length: "+entity.length();
        out.write(line.getBytes("ISO-8859-1"));
        out.write(13);
        out.write(10);

        //单独发送CRLF表示响应头发送完毕
        out.write(13);
        out.write(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HttpResqonse:响应头发送完毕！");

    }

    /**
     * 发送响应正文
     */
    public void  sendContent(){
        try {
            OutputStream out = socket.getOutputStream();
            FileInputStream fis = new FileInputStream(entity);
            System.out.println("HttpResqonse:发送响应正文.....");
            int len;//表示实际读取到的字节数
            byte[] data = new byte[1024*10];
            while ((len = fis.read(data))!=-1) {
                out.write(data,0,len);


            }
            System.out.println("HttpResqonse:响应正文发送完毕！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public int getStatuCode() {
        return statuCode;
    }

    public void setStatuCode(int statuCode) {
        this.statuCode = statuCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public File getEntity(){
        return entity;
    }


    public void setEntity(File entity){
        this.entity = entity;
    }











}
