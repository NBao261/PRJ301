<%-- 
    Document   : footer
    Created on : Feb 17, 2025, 2:24:20 PM
    Author     : cbao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer>
    <style>
        footer {
            width: 100%;
            background: #34495e;
            color: white;
            padding: 40px 0;
            text-align: center;
            margin-top: 50px;
            font-size: 14px;
        }
        .footer-container {
            width: 90%;
            max-width: 1200px;
            margin: auto;
        }
        .footer-container .footer-row {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap;
            margin-bottom: 20px;
        }
        .footer-container .footer-column {
            flex: 1;
            margin: 10px;
            min-width: 200px;
        }
        .footer-container .footer-column h4 {
            font-size: 16px;
            margin-bottom: 10px;
            font-weight: bold;
            color: #ff7e5f;
        }
        .footer-container .footer-column p, .footer-container .footer-column a {
            color: #bdc3c7;
            margin: 5px 0;
            text-decoration: none;
            font-size: 14px;
            transition: color 0.3s ease;
        }
        .footer-container .footer-column p:hover, .footer-container .footer-column a:hover {
            color: white;
        }
        .footer-container .social-icons {
            margin-top: 10px;
        }
        .footer-container .social-icons a {
            color: white;
            font-size: 24px;
            margin: 0 15px;
            transition: transform 0.3s ease;
        }
        .footer-container .social-icons a:hover {
            transform: scale(1.1);
        }
        .footer-bottom {
            text-align: center;
            margin-top: 30px;
            font-size: 13px;
            color: #bdc3c7;
        }
    </style>

    <div class="footer-container">
        <!-- Footer Row 1 -->
        <div class="footer-row">
            <div class="footer-column">
                <h4>Giới thiệu</h4>
                <p>Chúng tôi cung cấp các dịch vụ homestay chất lượng với giá cả hợp lý.</p>
                <p>Khám phá những địa điểm tuyệt vời cho chuyến đi của bạn.</p>
            </div>

            <div class="footer-column">
                <h4>Liên hệ</h4>
                <p>📍 123 Đường ABC, TP. Hồ Chí Minh</p>
                <p>📞 0123 456 789</p>
                <p>✉️ contact@homestay.com</p>
            </div>

            <div class="footer-column">
                <h4>Thông tin</h4>
                <a href="#">Chính sách bảo mật</a>
                <a href="#">Điều khoản sử dụng</a>
                <a href="#">Giới thiệu về chúng tôi</a>
            </div>
        </div>

        <!-- Footer Row 2 (Social Media) -->
        <div class="footer-row">
            <div class="footer-column">
                <h4>Kết nối với chúng tôi</h4>
                <div class="social-icons">
                    <a href="#" class="fa fa-facebook"></a>
                    <a href="#" class="fa fa-instagram"></a>
                    <a href="#" class="fa fa-twitter"></a>
                    <a href="#" class="fa fa-youtube"></a>
                </div>
            </div>
        </div>

        <!-- Footer Bottom -->
        <div class="footer-bottom">
            <p>© 2025 Homestay Booking. Tất cả quyền được bảo vệ.</p>
        </div>
    </div>
</footer>

