package controller;

import dao.ExamDAO;
import dao.ExamCategoryDAO;
import dto.ExamDTO;
import dto.ExamCategoryDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ExamController", urlPatterns = {"/exams"})
public class ExamController extends HttpServlet {

    private ExamDAO examDAO = new ExamDAO();
    private ExamCategoryDAO categoryDAO = new ExamCategoryDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("loggedUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<ExamCategoryDTO> categories = categoryDAO.getAllCategories();
        request.setAttribute("categories", categories);

        String action = request.getParameter("action");

        if ("showCreate".equals(action) && "Instructor".equals(user.getRole())) {
            request.getRequestDispatcher("createExam.jsp").forward(request, response);
            return;
        }

        if ("create".equals(action) && "Instructor".equals(user.getRole())) {
            String examIdStr = request.getParameter("examId");
            String examTitle = request.getParameter("examTitle");
            String subject = request.getParameter("subject");
            String categoryIdStr = request.getParameter("categoryId");
            String totalMarksStr = request.getParameter("totalMarks");
            String durationStr = request.getParameter("duration");

            // Kiểm tra và giữ lại giá trị khi có lỗi
            if (isEmpty(examIdStr)) {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "ID kỳ thi không được để trống!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
                return;
            }
            if (isEmpty(examTitle)) {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "Tên kỳ thi không được để trống!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
                return;
            }
            if (isEmpty(subject)) {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "Môn học không được để trống!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
                return;
            }
            if (isEmpty(categoryIdStr)) {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "Danh mục không được để trống!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
                return;
            }
            if (isEmpty(totalMarksStr)) {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "Tổng điểm không được để trống!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
                return;
            }
            if (isEmpty(durationStr)) {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "Thời gian không được để trống!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
                return;
            }

            ExamDTO exam = new ExamDTO();
            try {
                int examId = Integer.parseInt(examIdStr);
                int categoryId = Integer.parseInt(categoryIdStr);
                int totalMarks = Integer.parseInt(totalMarksStr);
                int duration = Integer.parseInt(durationStr);

                if (examId <= 0) {
                    setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                    request.setAttribute("error", "ID kỳ thi phải lớn hơn 0!");
                    request.getRequestDispatcher("createExam.jsp").forward(request, response);
                    return;
                }
                if (totalMarks <= 0) {
                    setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                    request.setAttribute("error", "Tổng điểm phải lớn hơn 0!");
                    request.getRequestDispatcher("createExam.jsp").forward(request, response);
                    return;
                }
                if (duration <= 0) {
                    setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                    request.setAttribute("error", "Thời gian phải lớn hơn 0!");
                    request.getRequestDispatcher("createExam.jsp").forward(request, response);
                    return;
                }

                boolean validCategory = false;
                for (ExamCategoryDTO cat : categories) {
                    if (cat.getCategoryId() == categoryId) {
                        validCategory = true;
                        break;
                    }
                }
                if (!validCategory) {
                    setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                    request.setAttribute("error", "Danh mục không hợp lệ!");
                    request.getRequestDispatcher("createExam.jsp").forward(request, response);
                    return;
                }

                exam.setExamId(examId);
                exam.setExamTitle(examTitle);
                exam.setSubject(subject);
                exam.setCategoryId(categoryId);
                exam.setTotalMarks(totalMarks);
                exam.setDuration(duration);
            } catch (NumberFormatException e) {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "ID, danh mục, điểm và thời gian phải là số nguyên!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
                return;
            }

            if (examDAO.createExam(exam)) {
                session.setAttribute("message", "Tạo kỳ thi thành công!");
                response.sendRedirect("exams");
            } else {
                setFormAttributes(request, examIdStr, examTitle, subject, categoryIdStr, totalMarksStr, durationStr);
                request.setAttribute("error", "Tạo kỳ thi thất bại (ID có thể đã tồn tại)!");
                request.getRequestDispatcher("createExam.jsp").forward(request, response);
            }
            return;
        }

        String categoryIdStr = request.getParameter("categoryId");
        List<ExamDTO> exams;
        if (categoryIdStr != null && !categoryIdStr.trim().isEmpty()) {
            try {
                int categoryId = Integer.parseInt(categoryIdStr);
                exams = examDAO.getExamsByCategory(categoryId);
            } catch (NumberFormatException e) {
                exams = examDAO.getAllExams();
            }
        } else {
            exams = examDAO.getAllExams();
        }

        request.setAttribute("exams", exams);
        request.getRequestDispatcher("examDashboard.jsp").forward(request, response);
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Lưu giá trị form khi có lỗi
    private void setFormAttributes(HttpServletRequest request, String examId, String examTitle,
            String subject, String categoryId, String totalMarks, String duration) {
        request.setAttribute("formExamId", examId);
        request.setAttribute("formExamTitle", examTitle);
        request.setAttribute("formSubject", subject);
        request.setAttribute("formCategoryId", categoryId);
        request.setAttribute("formTotalMarks", totalMarks);
        request.setAttribute("formDuration", duration);
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
        return "Exam Controller";
    }
}
