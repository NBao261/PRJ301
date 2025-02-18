package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/bac2"})
public class PTBac2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            try {
                double a = Double.parseDouble(request.getParameter("a"));
                double b = Double.parseDouble(request.getParameter("b"));
                double c = Double.parseDouble(request.getParameter("c"));
                String result;
                if (a == 0) {
                    if (b == 0) {
                        result = "Không phải phương trình hợp lệ!";
                    } else {
                        double x = -c / b;
                        result = "Nghiệm: x = " + x;
                    }
                } else {
                    double delta = b * b - 4 * a * c;
                    if (delta > 0) {
                        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                        result = "Phương trình có 2 nghiệm:<br>x1 = " + x1 + "<br>x2 = " + x2;
                    } else if (delta == 0) {
                        double x = -b / (2 * a);
                        result = "Phương trình có nghiệm kép: x = " + x;
                    } else {
                        result = "Phương trình vô nghiệm!";
                    }
                }
                request.setAttribute("result", result);
            } catch (NumberFormatException e) {
                request.setAttribute("result", "Vui lòng nhập số hợp lệ cho a, b, c!");
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
