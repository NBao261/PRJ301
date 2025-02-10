<%-- 
    Document   : example04
    Created on : Feb 10, 2025, 1:36:02 PM
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
        <!-- In ra bang cuu chuong -->
        <%
            for (int i = 1; i <= 10; i++) {
        %>
        Bang cuu chuong <%= i%>: <br/>
        <%
            for (int j = 1; j <= 10; j++) {
        %> 
        <%=i%> * <%=j%> = <%= i * j%> <br/> 
        <%
            }
        %>
        <hr>
        <%
            }
        %>
    </body>
</html>
