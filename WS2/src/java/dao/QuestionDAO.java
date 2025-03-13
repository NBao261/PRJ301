package dao;

import dto.QuestionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class QuestionDAO {

    public boolean addQuestion(QuestionDTO question) {
        String sql = "INSERT INTO tblQuestions (question_id, exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, question.getQuestionId());
            ps.setInt(2, question.getExamId());
            ps.setString(3, question.getQuestionText());
            ps.setString(4, question.getOptionA());
            ps.setString(5, question.getOptionB());
            ps.setString(6, question.getOptionC());
            ps.setString(7, question.getOptionD());
            ps.setString(8, String.valueOf(question.getCorrectOption()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách câu hỏi theo examId
    public List<QuestionDTO> getQuestionsByExamId(int examId) {
        List<QuestionDTO> questions = new ArrayList<>();
        String sql = "SELECT * FROM tblQuestions WHERE exam_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                questions.add(new QuestionDTO(
                        rs.getInt("question_id"),
                        rs.getInt("exam_id"),
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("correct_option").charAt(0)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }
}
