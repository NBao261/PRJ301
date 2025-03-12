package controller;

import dao.QuestionDAO;
import dto.QuestionDTO;
import dto.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "QuestionController", urlPatterns = {"/questions"})
public class QuestionController extends HttpServlet {

    private QuestionDAO questionDAO = new QuestionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("loggedUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        // Hiển thị trang thêm câu hỏi
        if ("showAddQuestion".equals(action) && "Instructor".equals(user.getRole())) {
            String examIdStr = request.getParameter("examId");
            request.setAttribute("examId", examIdStr);
            request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
            return;
        }

        // Thêm câu hỏi mới
        if ("addQuestion".equals(action) && "Instructor".equals(user.getRole())) {
            String questionIdStr = request.getParameter("questionId");
            String examIdStr = request.getParameter("examId");
            String questionText = request.getParameter("questionText");
            String optionA = request.getParameter("optionA");
            String optionB = request.getParameter("optionB");
            String optionC = request.getParameter("optionC");
            String optionD = request.getParameter("optionD");
            String correctOptionStr = request.getParameter("correctOption");

            // Kiểm tra dữ liệu
            if (isEmpty(questionIdStr)) {
                setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                request.setAttribute("error", "ID câu hỏi không được để trống!");
                request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                return;
            }
            if (isEmpty(examIdStr)) {
                setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                request.setAttribute("error", "ID kỳ thi không được để trống!");
                request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                return;
            }
            if (isEmpty(questionText)) {
                setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                request.setAttribute("error", "Nội dung câu hỏi không được để trống!");
                request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                return;
            }
            if (isEmpty(optionA) || isEmpty(optionB) || isEmpty(optionC) || isEmpty(optionD)) {
                setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                request.setAttribute("error", "Tất cả các lựa chọn không được để trống!");
                request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                return;
            }
            if (isEmpty(correctOptionStr)) {
                setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                request.setAttribute("error", "Đáp án đúng không được để trống!");
                request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                return;
            }

            QuestionDTO question = new QuestionDTO();
            try {
                int questionId = Integer.parseInt(questionIdStr);
                int examId = Integer.parseInt(examIdStr);
                char correctOption = correctOptionStr.toUpperCase().charAt(0);

                if (questionId <= 0) {
                    setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                    request.setAttribute("error", "ID câu hỏi phải lớn hơn 0!");
                    request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                    return;
                }
                if (examId <= 0) {
                    setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                    request.setAttribute("error", "ID kỳ thi phải lớn hơn 0!");
                    request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                    return;
                }
                if (correctOption != 'A' && correctOption != 'B' && correctOption != 'C' && correctOption != 'D') {
                    setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                    request.setAttribute("error", "Đáp án đúng phải là A, B, C hoặc D!");
                    request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                    return;
                }

                question.setQuestionId(questionId);
                question.setExamId(examId);
                question.setQuestionText(questionText);
                question.setOptionA(optionA);
                question.setOptionB(optionB);
                question.setOptionC(optionC);
                question.setOptionD(optionD);
                question.setCorrectOption(correctOption);
            } catch (NumberFormatException e) {
                setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                request.setAttribute("error", "ID câu hỏi và ID kỳ thi phải là số nguyên!");
                request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
                return;
            }

            if (questionDAO.addQuestion(question)) {
                session.setAttribute("message", "Thêm câu hỏi thành công!");
                response.sendRedirect("exams");
            } else {
                setQuestionFormAttributes(request, questionIdStr, examIdStr, questionText, optionA, optionB, optionC, optionD, correctOptionStr);
                request.setAttribute("error", "Thêm câu hỏi thất bại (ID có thể đã tồn tại)!");
                request.getRequestDispatcher("addQuestion.jsp").forward(request, response);
            }
            return;
        }

        // Nếu không có action hợp lệ, chuyển về dashboard
        response.sendRedirect("exams");
    }

    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private void setQuestionFormAttributes(HttpServletRequest request, String questionId, String examId,
            String questionText, String optionA, String optionB, String optionC, String optionD, String correctOption) {
        request.setAttribute("formQuestionId", questionId);
        request.setAttribute("formExamId", examId);
        request.setAttribute("formQuestionText", questionText);
        request.setAttribute("formOptionA", optionA);
        request.setAttribute("formOptionB", optionB);
        request.setAttribute("formOptionC", optionC);
        request.setAttribute("formOptionD", optionD);
        request.setAttribute("formCorrectOption", correctOption);
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
        return "Question Controller";
    }
}
