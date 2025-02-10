<%-- 
    Document   : example01
    Created on : Feb 10, 2025, 12:55:05 PM
    Author     : cbao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%! int a = 9; %>
        <%
            double b;
            b = Math.sqrt(a);
        %>
        Result sqrt(<%=a%>) = <span style="color: red"> <%=b%></span>
    </body>
</html>
