从本版本开始我们完成对业务的支持.
以用户注册为例,理解和实现业务的处理过程.

用户注册的流程:
1:用户访问注册页面
2:用户在页面上输入注册信息并点击注册按钮提交注册信息
3:服务端通过解析请求request获取用户提交的信息
4:根据请求路径分派给对应的业务处理类完成处理工作
5:发送响应告知处理结果页面

知识点:
1:了解数据是怎么提交给服务端的.对html中form表单的理解
2:数据在服务端是怎么被解析的.理解request中get请求提交数据时抽象路径部分的格式


实现:
1:提供一个注册页面.在webapps/myweb下新建一个页面:reg.html

2:当页面表单提交数据后,HttpRequest要重构解析请求的工作,对uri(请求行中的抽象路径部分)进一步
  解析
  添加三个实行:
  String requestURI 用于保存uri中的请求部分,"?"左侧内容
  String queryString 用于保存uri中的参数部分,"?"右侧内容
  Map parameters    用于保存每一个参数

  然后添加一个新方法parseUri,用于进一步解析uri.这个方法在parseRequestLine方法中拆分出
  uri后调用.

3:为三个属性添加对应的get方法,其中getParameter方法需要手敲,我们不直接将Map返回出去.

4:ClientHandler处理请求的环节做出一个改变,不能再使用request中的uri作为请求路径的判断了,
因为uri中可能含有参数.所以我们改为使用requestURI的值作为请求进行处理.

5:在ClientHandler处理请求的环节新添加一个分支判断,判定请求路径是否为"/myweb/regUser"
  这个值与reg.html页面中form表单里action有关.
  如果是,则处理注册业务.否则再执行以前的处理(判断是否为webapps下的某个文件)

6:创建一个包com.webserver.servlet,这个包将来保存所有处理业务的类
7:在这个包中新建一个类RegServlet并实现service方法完成注册业务.


