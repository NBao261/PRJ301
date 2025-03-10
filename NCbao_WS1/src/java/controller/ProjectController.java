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

        // Login
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String searchTerm = request.getParameter("searchName");

        // Search
        if (searchTerm != null) {
            session.setAttribute("searchTerm", searchTerm);
        } else {
            searchTerm = (String) session.getAttribute("searchTerm");
        }

        List<StartupProjectDTO> projects = (searchTerm != null && !searchTerm.trim().isEmpty())
                ? projectDAO.searchProjectsByName(searchTerm)
                : projectDAO.getAllProjects();

        // Show dashboard
        if (action == null || action.equals("search")) {
            request.setAttribute("projects", projects);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
            return;
        }

        // Check (Founder)
        if (action.equals("create") && "Founder".equals(user.getRole())) {
            String projectIdStr = request.getParameter("projectId");
            String projectName = request.getParameter("projectName");
            String description = request.getParameter("description");
            String status = request.getParameter("status");
            String estimatedLaunchStr = request.getParameter("estimatedLaunch");

            // Check
            if (isEmpty(projectIdStr) || isEmpty(projectName) || isEmpty(estimatedLaunchStr)) {
                request.setAttribute("error", "All required fields must be filled");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.getRequestDispatcher("add.jsp").forward(request, response);
                return;
            }

            StartupProjectDTO project = new StartupProjectDTO();
            try {
                int projectId = Integer.parseInt(projectIdStr);
                project.setProjectId(projectId);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Project ID must be a number");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.getRequestDispatcher("add.jsp").forward(request, response);
                return;
            }

            project.setProjectName(projectName);
            project.setDescription(description);
            project.setStatus(status != null ? status : "Ideation");
            project.setUsername(user.getUsername());

            // Check day
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                Date estimatedLaunch = sdf.parse(estimatedLaunchStr);
                Date today = new Date();
                if (estimatedLaunch.before(today)) {
                    request.setAttribute("error", "Estimated Launch date cannot be in the past");
                    setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                    request.getRequestDispatcher("add.jsp").forward(request, response);
                    return;
                }
                project.setEstimatedLaunch(estimatedLaunch);
            } catch (Exception e) {
                request.setAttribute("error", "Invalid date format (use yyyy-MM-dd)");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.getRequestDispatcher("add.jsp").forward(request, response);
                return;
            }

            //Create
            if (projectDAO.createProject(project)) {
                session.setAttribute("message", "Project created successfully");
                response.sendRedirect("projects");
            } else {
                request.setAttribute("error", "Failed to create project (ID may already exist)");
                setFormAttributes(request, projectIdStr, projectName, description, status, estimatedLaunchStr);
                request.getRequestDispatcher("add.jsp").forward(request, response);
            }
            return;
        }

        //Check update (Founder)
        if (action.equals("update") && "Founder".equals(user.getRole())) {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            String newStatus = request.getParameter("newStatus");
            if (projectDAO.updateProjectStatus(projectId, newStatus, user.getUsername())) {
                session.setAttribute("message", "Status updated successfully");
            } else {
                session.setAttribute("error", "Failed to update status");
            }
            response.sendRedirect("projects");
            return;
        }

        // Delete 
        if (action.equals("delete") && "Founder".equals(user.getRole())) {
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            if (projectDAO.deleteProject(projectId)) {
                session.setAttribute("message", "Project deleted successfully");
            } else {
                session.setAttribute("error", "Failed to delete project");
            }
            response.sendRedirect("projects");
            return;
        }

        // return dashboard
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    // Check null
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Save value
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
