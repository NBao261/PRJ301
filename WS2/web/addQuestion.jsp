<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Thêm câu hỏi</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("loggedUser");
            if (user == null || !"Instructor".equals(user.getRole())) {
                response.sendRedirect("login.jsp");
                return;
            }
            String examId = (String) request.getAttribute("examId");
        %>
        <h2>Thêm câu hỏi cho kỳ thi ID: <%= examId%></h2>
        <p><a href="exams">Quay lại Dashboard</a></p>

        <form action="questions" method="post">
            <input type="hidden" name="action" value="addQuestion">
            <input type="hidden" name="examId" value="<%= examId%>">
            <label>ID câu hỏi:</label> 
            <input type="number" name="questionId" value="<%= request.getAttribute("formQuestionId") != null ? request.getAttribute("formQuestionId") : ""%>" required><br>
            <label>Nội dung câu hỏi:</label> 
            <textarea name="questionText" required><%= request.getAttribute("formQuestionText") != null ? request.getAttribute("formQuestionText") : ""%></textarea><br>
            <label>Lựa chọn A:</label> 
            <input type="text" name="optionA" value="<%= request.getAttribute("formOptionA") != null ? request.getAttribute("formOptionA") : ""%>" required><br>
            <label>Lựa chọn B:</label> 
            <input type="text" name="optionB" value="<%= request.getAttribute("formOptionB") != null ? request.getAttribute("formOptionB") : ""%>" required><br>
            <label>Lựa chọn C:</label> 
            <input type="text" name="optionC" value="<%= request.getAttribute("formOptionC") != null ? request.getAttribute("formOptionC") : ""%>" required><br>
            <label>Lựa chọn D:</label> 
            <input type="text" name="optionD" value="<%= request.getAttribute("formOptionD") != null ? request.getAttribute("formOptionD") : ""%>" required><br>
            <label>Đáp án đúng:</label> 
            <select name="correctOption" required>
                <option value="A" <%= "A".equals(request.getAttribute("formCorrectOption")) ? "selected" : ""%>>A</option>
                <option value="B" <%= "B".equals(request.getAttribute("formCorrectOption")) ? "selected" : ""%>>B</option>
                <option value="C" <%= "C".equals(request.getAttribute("formCorrectOption")) ? "selected" : ""%>>C</option>
                <option value="D" <%= "D".equals(request.getAttribute("formCorrectOption")) ? "selected" : ""%>>D</option>
            </select><br>
            <input type="submit" value="Thêm câu hỏi">
        </form>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <p style="color:red"><%= error%></p>
        <% }%>
    </body>
</html>