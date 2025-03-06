
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="post">
            Username <input type="text" name="txtUsername" required />
            Password <input type="password" name="txtPassword" required/>
            <input type="submit" value="Login"/>        
        </form>
        <% if (request.getAttribute("error") != null) {%>
        <p style="color:red"><%= request.getAttribute("error")%></p>
        <% }%>
    </body>
</html>
