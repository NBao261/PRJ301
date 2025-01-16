/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cbao
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public int GCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtA = request.getParameter("txtA");
        String txtB = request.getParameter("txtB");

        if (txtA == null || txtA.trim().length() == 0) {
            out.println("Pls enter 'a' value!");
            return;
        }

        if (txtB == null || txtB.trim().length() == 0) {
            out.println("Pls enter 'b' value!");
            return;
        }

        int a = 0;
        int b = 0;
        try {
            a = Integer.parseInt(txtA);
            if (a <= 0) {
                out.println("a must be an integer number");
                return;
            }
        } catch (Exception e) {
            out.println("a must be an integer number");
            return;
        }
        try {
            b = Integer.parseInt(txtB);
            if (b <= 0) {
                out.println("b must be an integer number");
                return;
            }
        } catch (Exception e) {
            out.println("b must be an integer number");
            return;
        }
        
        int result = GCD(a, b);
        out.println("GCD("+a+","+b+") = "+result);
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
    }// </editor-fold>

}
