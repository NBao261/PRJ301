<%-- 
    Document   : examDashboard
    Created on : Mar 12, 2025, 4:15:08 PM
    Author     : cbao
--%>

<%@page import="dto.ExamDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.ExamCategoryDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Dashboard</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("loggedUser");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>Chào mừng, <%= user.getName()%>!</h2>
        <p>Vai trò: <%= user.getRole()%></p>
        <a href="logout">Đăng xuất</a>

        <h3>Danh mục kỳ thi</h3>
        <table border="1">
            <tr>
                <th>Tên danh mục</th>
                <th>Mô tả</th>
            </tr>
            <%
                List<ExamCategoryDTO> categories = (List<ExamCategoryDTO>) request.getAttribute("categories");
                if (categories != null) {
                    for (ExamCategoryDTO cat : categories) {
            %>
            <tr>
                <td><%= cat.getCategoryName()%></td>
                <td><%= cat.getDescription()%></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="2">Không có danh mục nào.</td>
            </tr>
            <%
                }
            %>
        </table>

        <% if ("Instructor".equals(user.getRole())) { %>
        <p><a href="exams?action=showCreate">Tạo kỳ thi mới</a></p>
        <%
            String message = (String) session.getAttribute("message");
            String error = (String) session.getAttribute("error");
            if (message != null) {
        %>
        <p style="color:green"><%= message%></p>
        <%
                session.removeAttribute("message");
            }
            if (error != null) {
        %>
        <p style="color:red"><%= error%></p>
        <%
                session.removeAttribute("error");
            }
        %>
        <% } %>

        <h3>Danh sách kỳ thi</h3>
        <form action="exams" method="get">
            <label>Lọc theo danh mục:</label>
            <select name="categoryId">
                <option value="">Tất cả</option>
                <%
                    String selectedCategoryId = request.getParameter("categoryId");
                    if (categories != null) {
                        for (ExamCategoryDTO cat : categories) {
                            String selected = (selectedCategoryId != null && selectedCategoryId.equals(String.valueOf(cat.getCategoryId()))) ? "selected" : "";
                %>
                <option value="<%= cat.getCategoryId()%>" <%= selected%>><%= cat.getCategoryName()%></option>
                <%
                        }
                    }
                %>
            </select>
            <input type="submit" value="Lọc">
        </form>

        <table border="1">
            <tr>
                <th>Tên kỳ thi</th>
                <th>Môn học</th>
                <th>Tổng điểm</th>
                <th>Thời gian (phút)</th>
                    <% if ("Instructor".equals(user.getRole())) { %>
                <th>Hành động</th>
                    <% } %>
            </tr>
            <%
                List<ExamDTO> exams = (List<ExamDTO>) request.getAttribute("exams");
                if (exams != null && !exams.isEmpty()) {
                    for (ExamDTO exam : exams) {
            %>
            <tr>
                <td><%= exam.getExamTitle()%></td>
                <td><%= exam.getSubject()%></td>
                <td><%= exam.getTotalMarks()%></td>
                <td><%= exam.getDuration()%></td>
                <% if ("Instructor".equals(user.getRole())) {%>
                <td><a href="questions?action=showAddQuestion&examId=<%= exam.getExamId()%>">Thêm câu hỏi</a></td>
                <% } %>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="<%= "Instructor".equals(user.getRole()) ? 5 : 4%>">Không có kỳ thi nào.</td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
