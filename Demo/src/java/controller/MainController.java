package controller;

import dao.UserDAO;
import dto.UserDTO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "index.jsp";
    private static final String HOME_PAGE = "home.jsp";

    private UserDTO getUser(String userID) {
        UserDAO userDAO = new UserDAO();
        return userDAO.readById(userID);
    }

    private boolean isValidLogin(String username, String password) {
        UserDTO user = getUser(username);
        return user != null && user.getPassword() != null && user.getPassword().equals(password);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");

            if (action != null) {
                switch (action) {
                    case "login":
                        String username = request.getParameter("userID");
                        String password = request.getParameter("password");

                        if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
                            if (isValidLogin(username, password)) {
                                url = HOME_PAGE;
                                UserDTO user = getUser(username);
                                request.getSession().setAttribute("user", user);
                            } else {
                                request.setAttribute("message", "Incorrect UserID or Password");
                            }
                        } else {
                            request.setAttribute("message", "Please enter both username and password");
                        }
                        break;
                    default:
                        request.setAttribute("message", "Invalid action");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
        return "Short description";
    }
}
