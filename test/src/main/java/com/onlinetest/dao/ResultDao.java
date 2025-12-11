package com.onlinetest.dao;

import com.onlinetest.model.Result;
import com.onlinetest.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultDao {
    public void saveResult(Result result) {
        String sql = "INSERT INTO results(userId, score) VALUES(?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, result.getUserId());
            ps.setInt(2, result.getScore());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
