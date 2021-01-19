<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
<body>
<center>
    <h1>用户列表</h1>
    <table border="1">
        <tr>
            <td>用户名</td>
            <td>密码</td>
            <td>昵称</td>
            <td>年龄</td>
        </tr>
        <tr>
           <%
               List<User> list = findUser();
               for(User user : list){
           %>

            <td><%=user.getUsername()%></td>

        </tr>
        <% }%>
        <tr>
            <td>李四</td>
            <td>12345</td>
            <td>四哥</td>
            <td>26</td>
        </tr>
    </table>
</center>

</body>
</html>