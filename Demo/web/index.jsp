<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h2>Login</h2>
        
        <% String errorMessage = (String) request.getAttribute("message"); %>
        <% if (errorMessage != null) { %>
            <p style="color: red;"><%= errorMessage %></p>
        <% } %>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login"/>
            Username <input type="text" name="userID"/> <br/>
            Password <input type="password" name="password"/> <br/>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>
