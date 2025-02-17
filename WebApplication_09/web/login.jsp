<%-- 
    Document   : login
    Created on : Feb 13, 2025, 1:55:04 PM
    Author     : cbao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <style>
            body {
                display: flex;
                flex-direction: column;
                align-items: center;
                min-height: 100vh;
                background: #f3f3f3;
            }
            .login-container {
                background: white;
                padding: 30px;
                width: 400px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                text-align: center;
                margin: 40px 0;
            }
            h2 {
                margin-bottom: 20px;
                color: #333;
            }
            .input-group {
                margin-bottom: 15px;
                text-align: left;
            }
            .input-group label {
                display: block;
                font-size: 14px;
                margin-bottom: 5px;
                color: #555;
            }
            .input-group input {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                transition: 0.3s;
            }
            .input-group input:focus {
                border-color: #ff7e5f;
                outline: none;
            }
            button {
                width: 100%;
                padding: 12px;
                background: linear-gradient(135deg, #ff7e5f, #feb47b);
                color: white;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                transition: 0.3s;
            }
            button:hover {
                background: linear-gradient(135deg, #e74c3c, #c0392b);
            }
        </style>
    </head>
    <body>

        <!-- Nhúng Header -->
        <%@include file="header.jsp" %>

        <!-- Form đăng nhập -->
        <div class="login-container">
            <h2>Đăng nhập</h2>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="login"/>
                
                <div class="input-group">
                    <label for="username">Tên đăng nhập</label>
                    <input type="text" id="username" name="txtUsername" required>
                </div>
                
                <div class="input-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" name="txtPassword" required>
                </div>
                
                <button type="submit">Đăng nhập</button>
            </form>
        </div>

        <!-- Nhúng Footer -->
        <%@include file="footer.jsp" %>

    </body>
</html>
