<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO" %>
<%@ page import="dto.StartupProjectDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                max-width: 1200px;
                margin-left: auto;
                margin-right: auto;
            }
            h2, h3 {
                color: #333;
            }
            a {
                color: #0066cc;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }
            th, td {
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
            }
            th {
                background-color: #f5f5f5;
                color: #333;
            }
            tr:nth-child(even) {
                background-color: #fafafa;
            }
            tr:hover {
                background-color: #f0f0f0;
            }
            form {
                margin: 10px 0;
            }
            input[type="text"], select {
                padding: 5px;
                margin: 5px 0;
            }
            input[type="submit"] {
                padding: 5px 15px;
                background-color: #0066cc;
                color: white;
                border: none;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #0052a3;
            }
            .logout {
                float: right;
            }
            .message {
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
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
        %>

        <h2>Welcome, <%= user.getName()%></h2>
        <a href="logout" class="logout">Logout</a>

        <h3>Search Projects</h3>
        <form action="projects" method="get">
            <input type="hidden" name="action" value="search">
            Project Name: <input type="text" name="searchName" value="<%= session.getAttribute("searchTerm") != null ? session.getAttribute("searchTerm") : ""%>"><br>
            <input type="submit" value="Search">
        </form>

        <h3>Startup Projects</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Launch Date</th>
                <th>Owner</th>
                    <% if ("Founder".equals(user.getRole())) { %>
                <th>Actions</th>
                    <% } %>
            </tr>
            <%
                List<StartupProjectDTO> projects = (List<StartupProjectDTO>) request.getAttribute("projects");
                if (projects != null) {
                    for (StartupProjectDTO project : projects) {
            %>
            <tr>
                <td><%= project.getProjectId()%></td>
                <td><%= project.getProjectName()%></td>
                <td><%= project.getDescription() != null ? project.getDescription() : ""%></td>
                <td><%= project.getStatus()%></td>
                <td><%= project.getEstimatedLaunch()%></td>
                <td><%= project.getUsername()%></td>
                <% if ("Founder".equals(user.getRole())) {%>
                <td>
                    <form action="projects" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="projectId" value="<%= project.getProjectId()%>">
                        <select name="newStatus">
                            <option value="Ideation" <%= "Ideation".equals(project.getStatus()) ? "selected" : ""%>>Ideation</option>
                            <option value="Development" <%= "Development".equals(project.getStatus()) ? "selected" : ""%>>Development</option>
                            <option value="Launch" <%= "Launch".equals(project.getStatus()) ? "selected" : ""%>>Launch</option>
                            <option value="Scaling" <%= "Scaling".equals(project.getStatus()) ? "selected" : ""%>>Scaling</option>
                        </select>
                        <input type="submit" value="Update">
                    </form>
                    <form action="projects" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="projectId" value="<%= project.getProjectId()%>">
                        <input type="submit" value="Delete" onclick="return confirm('Confirm Delete?');">
                    </form>
                </td>
                <% } %>
            </tr>
            <%
                    }
                }
            %>
        </table>

        <% if ("Founder".equals(user.getRole())) {%>
        <p><a href="add.jsp">Create New Project</a></p>
        <% } %>

        <%
            String message = (String) session.getAttribute("message");
            if (message != null) {
        %>
        <p class="message" style="color:green"><%= message%></p>
        <%
                session.removeAttribute("message");
            }
            String error = (String) request.getAttribute("error") != null ? (String) request.getAttribute("error") : (String) session.getAttribute("error");
            if (error != null) {
        %>
        <p class="message" style="color:red"><%= error%></p>
        <%
                session.removeAttribute("error");
            }
        %>
    </body>
</html>