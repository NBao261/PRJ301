/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.HouseDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.utils.DBUtils;

/**
 *
 * @author cbao
 */
public class HouseDAO {

    public List<HouseDTO> searchHouseByName(String searchName) {
        List<HouseDTO> house = new ArrayList<>();
        String sql = "SELECT * FROM tblHouse WHERE name LIKE ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                house.add(new HouseDTO(rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getFloat("price"),
                        rs.getFloat("size"),
                        rs.getString("status")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return house;
    }

    public boolean delete(String id) {
        String sql = "UPDATE tblHouse SET status = 0 WHERE id = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
