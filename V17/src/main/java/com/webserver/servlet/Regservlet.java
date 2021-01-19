package com.webserver.servlet;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 处理用户注册业务
 */
public class Regservlet extends HttpServlet{
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        System.out.println(" Regservlet:开始处理注册了 ");
        //1:获取用户在注册页面上输入的注册信息
          //这里getParameter方法传入的参数值应当与注册页面表单中输入框nam属性指定的值一致！！
        String usename = request.getParameter("usename");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String agestr = request.getParameter("age");
        System.out.println(usename+","+password+","+nickname+","+agestr);

        /*
            添加对用户输入的信息的验证工作
            要求：
            如果用户名，密码，昵称和年龄为null，或者年龄输入的不是一个数字(正则表达式验证)
            则直接设置response响应一个注册失败的提示页面。
            该页面webapps/myweb目录下，名字为reg_error.html
            页面居中显示一行字：注册信息输入有误，请重新注册
            然后加一个超链接返回到注测页面

            只有验证通过了，can进行下面的注册操作
         */


            if(usename==null||password==null||nickname==null||agestr==null||agestr.matches("\\d")){
                File file = new File("./webapps/myweb/reg_error.html");
                response.setEntity(file);
                return;
            }


                int age = Integer.parseInt(agestr);  //将年龄转换为int值

                //2：将该用户信息写入yser。dat文件保存
                try {
                    RandomAccessFile raf = new RandomAccessFile("user.dat", "rw");
                    /*
                        先读取usre.dat文件中现有的所有数据，将每条记录的用户名去处理啊并与当前注册用户的用户名匹配
                        如果以及存在。则直接响应页面：hava_user.html
                        该页面显示一行字 该用户已存在，请重新注册
                        否则才执行下面原有的注册操作
                     */
                    for(int i=0;i<raf.length()/100;i++) {
                        raf.seek(i * 100);
                        byte[] data = new byte[32];
                        raf.read(data);
                       String name = new String(data,"UTF-8").trim();
                        if(name.equals(usename)){
                            File file = new File("./webapps/myweb/hava_user.html");
                            response.setEntity(file);
                            return;
                        }
                    }


                    raf.seek(raf.length());

                    byte[] data = usename.getBytes("UTF-8");
                    data = Arrays.copyOf(data, 32);
                    raf.write(data);

                    data = password.getBytes("UTF-8");
                    data = Arrays.copyOf(data, 32);
                    raf.write(data);

                    data = nickname.getBytes("UTF-8");
                    data = Arrays.copyOf(data, 32);
                    raf.write(data);

                    raf.writeInt(age);
                    System.out.println("注册完毕");


                    File file = new File("./webapps/myweb/reg_success.html");
                    response.setEntity(file);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                //3：响应用户注册结果页面(成功或者失败页面)


                System.out.println(" Regservlet :注册处理完毕！");

    }

}
