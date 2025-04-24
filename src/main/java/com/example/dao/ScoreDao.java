package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.ScoreDto;
import com.example.util.JdbcUtil;

public class ScoreDao {
    public List<ScoreDto> getAllScores() {
        List<ScoreDto> list = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection()) {
            String sql = "SELECT s.score_id, c.classs_name, st.stu_name, s.grade " +
                         "FROM tb_score s JOIN tb_student st ON s.stu_id = st.stu_id " +
                         "JOIN tb_class c ON st.classs_id = c.classs_id " +
                         "ORDER BY c.classs_id";
            PreparedStatement ps = conn.prepareStatement(sql);
    		ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScoreDto dto = new ScoreDto();
                dto.setScoreId(rs.getInt("score_id"));
                dto.setClassName(rs.getString("classs_name"));
                dto.setStuName(rs.getString("stu_name"));
                dto.setScore(rs.getInt("grade"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ScoreDto getScoreById(int scoreId) {
        ScoreDto dto = null;
        try (Connection conn = JdbcUtil.getConnection()) {
            String sql = "SELECT st.stu_name, s.grade FROM tb_score s " +
                         "JOIN tb_student st ON s.stu_id = st.stu_id WHERE s.score_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, scoreId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dto = new ScoreDto();
                dto.setScoreId(scoreId);
                dto.setStuName(rs.getString("stu_name"));
                dto.setScore(rs.getInt("grade"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    
    public boolean addScore(String stuName, String className, int score) {
        try (Connection conn = JdbcUtil.getConnection()) {
        	String sql = "SELECT classs_id FROM tb_class " +
       			 		 "WHERE classs_name = ?";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.setString(1, className);
        	ResultSet rs = ps.executeQuery();
        	rs.next();
        	int classId = rs.getInt("classs_id");
        
            sql = "BEGIN " +
                  "INSERT INTO tb_student (stu_name, classs_id) " +
            	  "VALUES (?, ?) RETURNING stu_id INTO ?; " +
            	  "END;";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, stuName);
            cs.setInt(2, classId);
            cs.registerOutParameter(3, java.sql.Types.INTEGER);
            cs.executeUpdate();
            int newStuId = cs.getInt(3);
        
            sql = "INSERT INTO tb_score (stu_id, grade) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newStuId);
            ps.setInt(2, score);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<ScoreDto> searchScore(String className, String stuName) {
        List<ScoreDto> list = new ArrayList<>();
        try (Connection conn = JdbcUtil.getConnection()) {
            String sql = "SELECT s.score_id, c.classs_name, st.stu_name, s.grade " +
                         "FROM tb_score s JOIN tb_student st ON s.stu_id = st.stu_id " +
                         "JOIN tb_class c ON st.classs_id = c.classs_id " +
                         "WHERE (c.classs_name = ? OR ? IS NULL) " +
                         "AND st.stu_name LIKE ? " +
                         "ORDER BY c.classs_id";
            PreparedStatement ps = conn.prepareStatement(sql);
            // 指定なし / 全クラス指定 
            if (className == null || className.equals("All")) {
            	ps.setNull(1, Types.VARCHAR);
            	ps.setNull(2, Types.VARCHAR);
            }
            // 特定のクラス指定
            else {
            	ps.setString(1, className);
            	ps.setString(2, className);
            }
            ps.setString(3, "%" + stuName + "%");
    		ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ScoreDto dto = new ScoreDto();
                dto.setScoreId(rs.getInt("score_id"));
                dto.setClassName(rs.getString("classs_name"));
                dto.setStuName(rs.getString("stu_name"));
                dto.setScore(rs.getInt("grade"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateScore(int scoreId, int score) {
        try (Connection conn = JdbcUtil.getConnection()) {
            String sql = "UPDATE tb_score SET grade = ? WHERE score_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, score);
            ps.setInt(2, scoreId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteScore(int scoreId) {
        try (Connection conn = JdbcUtil.getConnection()) {
            String sql = "DELETE FROM tb_score WHERE score_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, scoreId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
