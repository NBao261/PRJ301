
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Login</h2>
        <form action="login" method="post">
            Tên đăng nhập: <input type="text" name="txtUsername" required><br>
            Mật khẩu: <input type="password" name="txtPassword" required><br>
            <input type="submit" value="Đăng nhập">
        </form>
        <% if (request.getAttribute("error") != null) {%>
        <p style="color:red"><%= request.getAttribute("error")%></p>
        <% }%>
    </body>
</html>
