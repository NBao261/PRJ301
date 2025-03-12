<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create New Project</title>
        <style>
            body {
                font-family: Arial;
                margin: 20px;
                text-align: center; 
            }
            h2, h3 {
                margin: 10px 0;
            }
            a {
                display: block;
                margin: 10px 0;
            }
            form {
                margin: 20px auto;
                width: 400px; 
                text-align: left; 
            }
            input[type="number"], input[type="text"], textarea, select {
                width: 100%; 
                padding: 5px;
                margin: 5px 0; 
            }
            textarea {
                height: 100px; 
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
        <%
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            if (!"Founder".equals(user.getRole())) {
                response.sendRedirect("projects");
                return;
            }
        %>

        <h2>Welcome, <%= user.getName()%></h2>
        <a href="projects">Back to Dashboard</a>

        <h3>Create New Project</h3>
        <form action="projects" method="post">
            <input type="hidden" name="action" value="create">
            Project ID: <input type="number" name="projectId" value="<%= request.getAttribute("formProjectId") != null ? request.getAttribute("formProjectId") : ""%>" required><br>
            Name: <input type="text" name="projectName" value="<%= request.getAttribute("formProjectName") != null ? request.getAttribute("formProjectName") : ""%>" required><br>
            Description: <textarea name="description"><%= request.getAttribute("formDescription") != null ? request.getAttribute("formDescription") : ""%></textarea><br>
            Status: <select name="status">
                <option value="Ideation" <%= "Ideation".equals(request.getAttribute("formStatus")) ? "selected" : ""%>>Ideation</option>
                <option value="Development" <%= "Development".equals(request.getAttribute("formStatus")) ? "selected" : ""%>>Development</option>
                <option value="Launch" <%= "Launch".equals(request.getAttribute("formStatus")) ? "selected" : ""%>>Launch</option>
                <option value="Scaling" <%= "Scaling".equals(request.getAttribute("formStatus")) ? "selected" : ""%>>Scaling</option>
            </select><br>
            Estimated Launch (yyyy-MM-dd): <input type="text" name="estimatedLaunch" value="<%= request.getAttribute("formEstimatedLaunch") != null ? request.getAttribute("formEstimatedLaunch") : ""%>" required><br>
            <input type="submit" value="Create">
        </form>

        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
        <p style="color:green"><%= message%></p>
        <%
                session.removeAttribute("message");
            }
            String error = (String) request.getAttribute("error") != null ? (String) request.getAttribute("error") : (String) session.getAttribute("error");
            if (error != null) {
        %>
        <p style="color:red"><%= error%></p>
        <%
                session.removeAttribute("error");
            }
        %>
    </body>
</html>