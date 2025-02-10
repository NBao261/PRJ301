<%-- 
    Document   : output
    Created on : Feb 10, 2025, 2:26:39 PM
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
        <%
            int n = (int) request.getAttribute("n");
        %>
        <%
            if (n % 2 == 0) {
        %>
        <%=n%> là số chẵn
        <%
        } else {
        %>
        <%=n%> là số lẻ
        <%
            }
        %>
    </body>
</html>
