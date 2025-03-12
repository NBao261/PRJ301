package dao;

import dto.QuestionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
