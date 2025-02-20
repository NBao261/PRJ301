<%-- 
    Document   : search
    Created on : Feb 13, 2025, 1:55:36 PM
    Author     : cbao
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 20px">

            <%
                UserDTO user = (UserDTO) request.getAttribute("user");
            %>
            Welcome <b> <%=user.getFullName()%> </b>
            <form action="MainController">
                <input type="hidden" name="action" value="logout" />
                <input type="submit" value="Logout"/>
            </form>
            <hr/>
            <form action="#">
                Search Value <input type="text" name="txtSearchValue" />
                <input type="submit" value="Login"/>
            </form>
        </div>
        <%@include file="footer.jsp" %>
</html>
