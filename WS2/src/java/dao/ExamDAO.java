package dao;

import dto.ExamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class ExamDAO {

    // Lấy tất cả kỳ thi
    public List<ExamDTO> getAllExams() {
        List<ExamDTO> exams = new ArrayList<>();
        String sql = "SELECT * FROM tblExams";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                exams.add(new ExamDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("Subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exams;
    }

    // Lấy kỳ thi theo categoryId
    public List<ExamDTO> getExamsByCategory(int categoryId) {
        List<ExamDTO> exams = new ArrayList<>();
        String sql = "SELECT * FROM tblExams WHERE category_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exams.add(new ExamDTO(
                        rs.getInt("exam_id"),
                        rs.getString("exam_title"),
                        rs.getString("Subject"),
                        rs.getInt("category_id"),
                        rs.getInt("total_marks"),
                        rs.getInt("Duration")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exams;
    }

    // Tạo kỳ thi mới
    public boolean createExam(ExamDTO exam) {
        String sql = "INSERT INTO tblExams (exam_id, exam_title, Subject, category_id, total_marks, Duration) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, exam.getExamId());
            ps.setString(2, exam.getExamTitle());
            ps.setString(3, exam.getSubject());
            ps.setInt(4, exam.getCategoryId());
            ps.setInt(5, exam.getTotalMarks());
            ps.setInt(6, exam.getDuration());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi (ví dụ: trùng exam_id)
        }
    }
}
