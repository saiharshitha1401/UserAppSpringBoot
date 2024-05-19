package com.example.demo.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {
	    @Override
	    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        User user = new User();
	        user.setId(UUID.fromString(rs.getString("id")));
	        user.setFull_name(rs.getString("full_name"));
	        user.setMob_num(rs.getString("mob_num"));
	        user.setPan_num(rs.getString("pan_num"));
	        user.setManager_id(rs.getInt("manager_id"));
	        user.setIs_active(rs.getBoolean("is_active"));

	        // Convert Timestamp to LocalDate
	        if (rs.getTimestamp("created_at") != null) {
	            user.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());
	        }
	        if (rs.getTimestamp("updated_at") != null) {
	            user.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime().toLocalDate());
	        }

	        return user;
	    }
	}
