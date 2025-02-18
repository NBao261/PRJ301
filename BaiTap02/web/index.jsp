<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tính phương trình bậc 2</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f4f6f9; 
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                flex-direction: column;
            }

            .header {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                background-color: #2e7d32; 
                padding: 20px 0;
                text-align: center;
                box-shadow: 0px 4px 6px rgba(0,0,0,0.2);
                z-index: 1000;
            }

            .header img {
                width: 120px;
                height: 120px;
                object-fit: contain;
            }

            .container {
                width: 420px;
                background-color: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                text-align: center;
                margin-top: 150px;
                border-top: 5px solid #2e7d32; 

            h1 {
                color: #2e7d32; 
                font-size: 24px;
                margin-bottom: 20px;
            }

            input[type="text"] {
                width: 90%;
                padding: 12px;
                margin: 10px 0;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #2e7d32;
                color: white;
                border: none;
                padding: 15px 0;
                font-size: 16px;
                margin-top: 15px;
                cursor: pointer;
                border-radius: 5px;
                width: 100%;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #388e3c; 
            }

            .result {
                margin-top: 20px;
                padding: 15px;
                background-color: #e8f5e9; 
                border: 1px solid #81c784; 
                border-radius: 5px;
                color: #388e3c;
                font-weight: bold;
                font-size: 18px;
            }

        </style>
    </head>
    <body>
        <div class="header">
            <img src="https://img.icons8.com/color/512/apple-calculator.png" alt="Logo">
        </div>
        <div class="container">
            <h1>Tính phương trình bậc 2</h1>
            <form action="bac2" method="post">
                <label for="a">Nhập a </label>
                <input type="text" id="a" name="a" value="" />
                <br>
                <label for="b">Nhập b </label>
                <input type="text" id="b" name="b" value="" />
                <br>
                <label for="c">Nhập c </label>
                <input type="text" id="c" name="c" value="" />
                <br>
                <input type="submit" value="Tính toán" />
            </form>
            <%
                String result = (String) request.getAttribute("result");
                if (result != null) {
            %>
            <div class="result"><%= result%></div>
            <% }%>
        </div>
    </body>
</html>
