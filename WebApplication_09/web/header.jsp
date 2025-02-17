<%-- 
    Document   : header
    Created on : Feb 17, 2025, 2:22:41 PM
    Author     : cbao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Arial', sans-serif;
        }
        header {
            width: 100%;
            background: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            z-index: 1000;
        }
        .container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            width: 90%;
            margin: auto;
            padding: 15px 0;
        }
        .logo a {
            color: #2c3e50;
            font-size: 28px;
            font-weight: bold;
            text-decoration: none;
            display: flex;
            align-items: center;
        }
        .logo img {
            width: 40px;
            height: 40px;
            margin-right: 10px;
        }
        nav ul {
            display: flex;
            list-style: none;
        }
        nav ul li {
            margin: 0 15px;
        }
        nav ul li a {
            color: #2c3e50;
            text-decoration: none;
            font-size: 16px;
            font-weight: 500;
            transition: 0.3s;
            padding: 8px 12px;
            border-radius: 5px;
        }
        nav ul li a:hover {
            background: #ff7e5f;
            color: white;
        }
        .cta-button {
            background: #ff7e5f;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: bold;
            transition: 0.3s;
        }
        .cta-button:hover {
            background: #e74c3c;
        }
    </style>

    <div class="container">
        <div class="logo">
            <img src="logo.png" alt="Homestay Logo">
            <a href="#">Homestay Booking</a>
        </div>
        <nav>
            <ul>
                <li><a href="#">Trang chủ</a></li>
                <li><a href="#">Homestay</a></li>
                <li><a href="#">Ưu đãi</a></li>
                <li><a href="#">Liên hệ</a></li>
                <li><a href="#" class="cta-button">Đặt ngay</a></li>
            </ul>
        </nav>
    </div>
</header>

