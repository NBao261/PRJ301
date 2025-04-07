/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.controllers;

import dao.HouseDAO;
import dao.UserDAO;
import dto.HouseDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hd
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private HouseDAO houseDAO = new HouseDAO();
    private UserDAO userDAO = new UserDAO();
    private static final String LOGIN_PAGE = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else if ("login".equals(action)) {
                String userID = request.getParameter("txtUserID");
                String password = request.getParameter("txtPassword");

                UserDTO user = userDAO.login(userID, password);

                if (user != null) {
                    url = "houseList.jsp";
                    session.setAttribute("user", user);
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                } else {
                    request.setAttribute("error", "Incorrect UserID or Password");
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                }
            } else if ("logout".equals(action)) {
                if (session != null) {
                    session.invalidate();
                    response.sendRedirect(url);
                }
            } else if ("search".equals(action)) {
                String searchTerm = request.getParameter("searchName");
                if (searchTerm == null || searchTerm.trim().isEmpty()) {
                    searchTerm = "";
                } else {
                    request.setAttribute("searchTerm", searchTerm);
                }

                List<HouseDTO> house = houseDAO.searchHouseByName(searchTerm);
                url = "houseList.jsp";
                request.setAttribute("house", house);
                request.getRequestDispatcher(url).forward(request, response);
                return;
            } else if ("delete".equals(action)) {
                String id = request.getParameter("id");
                houseDAO.delete(id);
                String searchTerm = request.getParameter("searchName");
                if (searchTerm == null || searchTerm.trim().isEmpty()) {
                    searchTerm = "";
                } else {
                    request.setAttribute("searchTerm", searchTerm);
                }

                List<HouseDTO> house = houseDAO.searchHouseByName(searchTerm);
                request.setAttribute("house", house);
                url = "houseList.jsp";
                request.getRequestDispatcher(url).forward(request, response);
                return;
            }
//            your code here

        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        }
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
