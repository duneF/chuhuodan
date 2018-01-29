<%--
  Created by IntelliJ IDEA.
  User: zhangfeng
  Date: 2018/1/10
  Time: 下午8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理登陆页面</title>
</head>
<body>

<form action="${pageContext.request.contextPath }/login.do" method="post">
    <table border="1" align="center" width="600" height="200">
        <tr>
            <th colspan="2">后台登陆管理</th>
        </tr>
        <tr>
            <td>账号</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="reset" value="重新输入">
                <input type="submit" value="登陆账号">
                <span style="font-weight: 900; color: red">${msg}</span>
            </td>

        </tr>




    </table>
</form>

</body>
</html>
