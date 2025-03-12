<%-- 
    Document   : createExam
    Created on : Mar 12, 2025, 4:54:23 PM
    Author     : cbao
--%>

<%@page import="dto.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.ExamCategoryDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo kỳ thi mới</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("loggedUser");
            if (user == null || !"Instructor".equals(user.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h2>Tạo kỳ thi mới</h2>
        <p><a href="exams">Quay lại Dashboard</a></p>

        <form action="exams" method="post">
            <input type="hidden" name="action" value="create">
            <label>ID kỳ thi:</label> 
            <input type="number" name="examId" value="<%= request.getAttribute("formExamId") != null ? request.getAttribute("formExamId") : ""%>" required><br>
            <label>Tên kỳ thi:</label> 
            <input type="text" name="examTitle" value="<%= request.getAttribute("formExamTitle") != null ? request.getAttribute("formExamTitle") : ""%>" required><br>
            <label>Môn học:</label> 
            <input type="text" name="subject" value="<%= request.getAttribute("formSubject") != null ? request.getAttribute("formSubject") : ""%>" required><br>
            <label>Danh mục:</label>
            <select name="categoryId" required>
                <%
                    List<ExamCategoryDTO> categories = (List<ExamCategoryDTO>) request.getAttribute("categories");
                    String formCategoryId = (String) request.getAttribute("formCategoryId");
                    if (categories != null) {
                        for (ExamCategoryDTO cat : categories) {
                            String selected = (formCategoryId != null && formCategoryId.equals(String.valueOf(cat.getCategoryId()))) ? "selected" : "";
                %>
                <option value="<%= cat.getCategoryId()%>" <%= selected%>><%= cat.getCategoryName()%></option>
                <%
                        }
                    }
                %>
            </select><br>
            <label>Tổng điểm:</label>
            <input type="number" name="totalMarks" value="<%= request.getAttribute("formTotalMarks") != null ? request.getAttribute("formTotalMarks") : ""%>" required><br>
            <label>Thời gian (phút):</label> 
            <input type="number" name="duration" value="<%= request.getAttribute("formDuration") != null ? request.getAttribute("formDuration") : ""%>" required><br>
            <input type="submit" value="Tạo kỳ thi"/>
        </form>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <p style="color:red"><%= error%></p>
        <% }%>
    </body>
</html>
