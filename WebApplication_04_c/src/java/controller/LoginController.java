/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cbao
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    public boolean isValidLogin(String username, String password) {
        return (username.equals("admin") && password.equals("12345678"));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userName = request.getParameter("txtUsername");
        String passWord = request.getParameter("txtPassword");

        if (userName == null || userName.trim().length() == 0) {
            out.println("Pls enter username");
            return;
        }

        if (passWord.trim().length() < 8 || passWord == null) {
            out.println("Pls enter password (at least 8 characters)");
            return;
        }

        //login process
        if (isValidLogin(userName, passWord)) {
            //forward, redirect
            //foward
            RequestDispatcher rd = request.getRequestDispatcher("search.html");
            rd.forward(request, response);
        } else {
            //forwawrd, redirect
            //forwawrd
            //RequestDispatcher rd = request.getRequestDispatcher("Invalid.html");

            //redirect
            response.sendRedirect("Invalid.html");

            //compare rd.forward vs response.sendRedirect
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

}
