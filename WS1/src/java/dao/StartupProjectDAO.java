package dao;

import dto.StartupProjectDTO;
import utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class StartupProjectDAO {

    public List<StartupProjectDTO> getAllProjects() {
        List<StartupProjectDTO> projects = new ArrayList<>();
        String sql = "SELECT * FROM tblStartupProjects";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                projects.add(new StartupProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch"),
                        rs.getString("Username")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    public boolean createProject(StartupProjectDTO project) {
        String sql = "INSERT INTO tblStartupProjects (project_id, project_name, Description, Status, estimated_launch, Username) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, project.getProjectId());
            ps.setString(2, project.getProjectName());
            ps.setString(3, project.getDescription());
            ps.setString(4, project.getStatus());
            ps.setDate(5, new java.sql.Date(project.getEstimatedLaunch().getTime()));
            ps.setString(6, project.getUsername());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProjectStatus(int projectId, String newStatus, String username) {
        String sql = "UPDATE tblStartupProjects SET Status = ? WHERE project_id = ? AND Username = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, projectId);
            ps.setString(3, username);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<StartupProjectDTO> searchProjectsByName(String searchName) {
        List<StartupProjectDTO> projects = new ArrayList<>();
        String sql = "SELECT project_id, project_name, Description, Status, estimated_launch, Username "
                + "FROM tblStartupProjects WHERE project_name LIKE ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projects.add(new StartupProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch"),
                        rs.getString("Username")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

    public boolean deleteProject(int projectId) {
        String sql = "DELETE FROM tblStartupProjects WHERE project_id = ?";
        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, projectId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
