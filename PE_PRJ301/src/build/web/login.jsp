<%-- 
    Document   : login
    Created on : Mar 11, 2022, 9:02:11 PM
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->

        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="login"/>
            UserID <input type="text" name="txtUserID"/>
            Password <input type="password" name="txtPassword"/>
            <input type="submit" value="Login"/>
        </form>

        <%
            if (request.getAttribute("error") != null) {

        %>
        <p>
            <%=request.getAttribute("error")%>
        </p>
        <%
            }
        %>
    </body>

</html>

