<%-- 
    Document   : admin
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>


<%@page import="java.util.List"%>
<%@page import="dto.HouseDTO"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House List</title>
    </head>
    <body>
        <%
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <h1>
            Welcome <%=user.getName()%>
        </h1>

        <a href="MainController?action=logout"> Logout </a>

        <br>

        <form action="MainController" method="POST">
            <input type="hidden" name="action" value="search"/>
            Search By Name <input type="text" name="searchName" value="<%=request.getAttribute("searchTerm") != null ? request.getAttribute("searchTerm") : ""%>"/>
            <input type="submit" value="Search"/>
        </form>

        <br>

        <table border = "1">
            <tr>
                <th>no</th>
                <th>id</th>
                <th>name</th>
                <th>description</th>
                <th>price</th>
                <th>size</th>
                <th>status</th>
                <th>action</th>
            </tr>

            <%
                int count = 0;
                List<HouseDTO> house = (List<HouseDTO>) request.getAttribute("house");
                if (house != null) {
                    for (HouseDTO houses : house) {
                        count++;
            %>
            <tr>
                <td><%=count%></td>
                <td><%=houses.getId()%></td>
                <td><%=houses.getName()%></td>
                <td><%=houses.getDescription()%></td>
                <td><%=houses.getPrice()%></td>
                <td><%=houses.getSize()%></td>
                <td><%=houses.getStatus()%></td>
                <td>
                    <a href="MainController?action=delete&id=<%= houses.getId()%>&searchName=<%=request.getAttribute("searchTerm") != null ? request.getAttribute("searchTerm") : ""%>">
                        <button>Delete</button>
                    </a>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>
