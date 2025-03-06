package dao;

import dto.UserDTO;
import utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    
    public UserDTO login(String username, String password) {
        String sql = "SELECT * FROM tblUsers WHERE Username = ? AND Password = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new UserDTO(
                    rs.getString("Username"),
                    rs.getString("Name"),
                    rs.getString("Password"),
                    rs.getString("Role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}