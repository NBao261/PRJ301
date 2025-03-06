package controller;

import dao.StartupProjectDAO;
import dto.StartupProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProjectController", urlPatterns = {"/projects"})
public class ProjectController extends HttpServlet {
    private StartupProjectDAO projectDAO = new StartupProjectDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String searchTerm = request.getParameter("searchName");
        
        // Lưu searchTerm vào session để giữ lại sau mỗi hành động
        if (searchTerm != null) {
            session.setAttribute("searchTerm", searchTerm);
        } else {
            searchTerm = (String) session.getAttribute("searchTerm");
        }

        // Lấy danh sách project dựa trên searchTerm
        List<StartupProjectDTO> projects;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            projects = projectDAO.searchProjectsByName(searchTerm);
        } else {
            projects = projectDAO.getAllProjects();
        }

        if (action == null ) {
            request.setAttribute("projects", projects);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } 
        else if (action.equals("search")) {
            request.setAttribute("projects", projects);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } 
        else if (action.equals("create") && user.getRole().equals("Founder")) {
            String projectIdStr = request.getParameter("projectId");
            String projectName = request.getParameter("projectName");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String estimatedLaunchStr = request.getParameter("estimatedLaunch");

            // Kiểm tra bat loi
            if (projectIdStr == null || projectIdStr.trim().isEmpty()) {
                request.setAttribute("error", "Project ID is required");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }
            if (projectName == null || projectName.trim().isEmpty()) {
                request.setAttribute("error", "Project Name is required");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }
            if (estimatedLaunchStr == null || estimatedLaunchStr.trim().isEmpty()) {
                request.setAttribute("error", "Estimated Launch Date is required");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }

            StartupProjectDTO project = new StartupProjectDTO();
            try {
                int projectId = Integer.parseInt(projectIdStr);
                project.setProjectId(projectId);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Project ID must be a valid number");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }

            project.setProjectName(projectName);
            project.setDescription(description);
            project.setStatus(status != null ? status : "Ideation");
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false); // Không cho phép parse ngày không hợp lệ
                Date estimatedLaunch = sdf.parse(estimatedLaunchStr);
                project.setEstimatedLaunch(estimatedLaunch);
            } catch (Exception e) {
                request.setAttribute("error", "Invalid date format (use yyyy-MM-dd)");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
                return;
            }
            project.setUsername(user.getUsername()); // Gán project cho Founder

            if (projectDAO.createProject(project)) {
                session.setAttribute("message", "Project created successfully");
                response.sendRedirect("projects");
            } else {
                // Nếu thất bại (có thể do trùng projectId)
                request.setAttribute("error", "Failed to create project. Project ID may already exist.");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            }
        } 
        else if (action.equals("update") && user.getRole().equals("Founder")) {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            String newStatus = request.getParameter("newStatus");
            if (projectDAO.updateProjectStatus(projectId, newStatus, user.getUsername())) {
                session.setAttribute("message", "Status updated successfully");
            } else {
                session.setAttribute("error", "Failed to update status");
            }
            response.sendRedirect("projects");
        } 
        else if (action.equals("delete") && user.getRole().equals("Founder")) {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            if (projectDAO.deleteProject(projectId)) {
                session.setAttribute("message", "Project deleted successfully");
            } else {
                session.setAttribute("error", "Failed to delete project");
            }
            response.sendRedirect("projects");
        }
    }

    // Luu cac gia tri nhap khi 1 truong co loi
    private void setFormAttributes(HttpServletRequest request, String projectId, String projectName, 
                                  String description, String status, String estimatedLaunch) {
        request.setAttribute("formProjectId", projectId);
        request.setAttribute("formProjectName", projectName);
        request.setAttribute("formDescription", description);
        request.setAttribute("formStatus", status);
        request.setAttribute("formEstimatedLaunch", estimatedLaunch);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Project Controller";
    }
}