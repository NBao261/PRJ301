<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>

        <form action="cong" method="post">
            Number 1: <input type="text" name="num1" value="" /> <br>
            Number 2: <input type="text" name="num2" value="" />
            <input type="submit" value="submit" />
        </form>
        <%
            String result = (String) request.getAttribute("result");
            if (result != null) {
        %>
        <%= result%>
        <% }%>
    </body>
</html>
