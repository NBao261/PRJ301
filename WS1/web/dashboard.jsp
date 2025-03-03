<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO" %>
<%@ page import="dto.StartupProjectDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Project Dashboard</title>
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
        <a href="logout">Logout</a>

        <h3>Search Projects</h3>
        <form action="projects" method="get">
            <input type="hidden" name="action" value="search">
            Project Name: <input type="text" name="searchName" value="<%= session.getAttribute("searchTerm") != null ? session.getAttribute("searchTerm") : ""%>"><br>
            <input type="submit" value="Search">
        </form>

        <h3>Startup Projects</h3>
        <table border="1">
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
                        <input type="submit" value="Delete" onclick="return confirm('Are you sure?');">
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
        <% } %>

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