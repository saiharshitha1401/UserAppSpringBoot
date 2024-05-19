package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;
import com.example.demo.models.UserDTO;
import com.example.demo.models.UserRowMapper;

import java.util.UUID;
@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public void addUser(UserDTO user) {
		String sql = "INSERT INTO users(id, full_name, mob_num, pan_num, manager_id, is_active, created_at, updated_at) VALUES(?,?,?,?,?,?,?,?)";
		UUID id = UUID.randomUUID();
		 LocalDate currentDate = LocalDate.now();
		
		jdbctemplate.update(sql,
				id,
				user.getFull_name(),
				user.getMob_num(),
				user.getPan_num(),
				user.getManager_id(),
				1,
				currentDate,
				currentDate
	    );
	}
	
	 public void bulkUpdate(List<String> userIds, Map<String, String> updateData) {
	        // Validate and perform bulk update
	        // Check if manager_id is the only key in updateData
	        if (updateData.keySet().size() != 1 || !updateData.containsKey("manager_id")) {
	            throw new IllegalArgumentException("Bulk update only supports updating manager_id.");
	        }

	        // Perform bulk update
	        for (String userId : userIds) {
	            updateUser(userId, updateData);
	        }
	    }

	    private void updateUser(String userId, Map<String, String> updateData) {
	        // Implement update logic
	        // For simplicity, assuming you have a table named users with columns: user_id, full_name, mob_num, pan_num, manager_id
	        // You need to write the SQL query to update the user based on userId and updateData
	        // Make sure to handle validation, existing manager existence check, and updating timestamps

	        // Example update query
	        String sql = "UPDATE users SET manager_id = ? WHERE id = ?";
	        jdbctemplate.update(sql, updateData.get("manager_id"), userId);
	    }


	public List<User> getUser() {
		String query = "select * from users";
		List<User> userList = jdbctemplate.query(query, new UserRowMapper());
		
		return userList;
	}
    
	public boolean deleteUserById(String userId) {
        int rowsAffected = jdbctemplate.update("DELETE FROM users WHERE id = ?", userId);
        return rowsAffected > 0;
    }

    public boolean deleteUserByMobNum(String mobNum) {
        int rowsAffected = jdbctemplate.update("DELETE FROM users WHERE mob_num = ?", mobNum);
        return rowsAffected > 0;
    }


	 
	
}
