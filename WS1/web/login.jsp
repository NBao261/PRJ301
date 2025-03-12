<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            body {
                font-family: Arial;
                margin: 20px;
                text-align: center; 
            }
            form {
                margin: 20px auto; 
                width: 300px;
            }
            input[type="text"], input[type="password"] {
                width: 100%; 
                padding: 5px;
                margin: 5px 0; 
            }
            input[type="submit"] {
                padding: 5px 15px;
                background-color: #007bff; 
                color: white;
                border: none;
            }
            p {
                margin: 10px 0;
            }
        </style>
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