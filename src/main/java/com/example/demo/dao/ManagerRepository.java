package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Manager;

@Repository
public class ManagerRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public void addManager(Manager manager) {
		String sql = "INSERT INTO manager(manager_id, name) VALUES(?,?)";
		
		jdbctemplate.update(sql,
				manager.getManager_id(),
				manager.getName()
	    );
	}

	public List<Manager> getManager() {
		String query = "select * from manager";
		List<Manager> managerList = jdbctemplate.query(query, new BeanPropertyRowMapper<>(Manager.class));
		return managerList;
	}

	public void updateManagerById(int manager_id, String name) {
        String sql = "UPDATE manager SET name = ? WHERE manager_id = ?";
        jdbctemplate.update(sql, name, manager_id);
    }
	
	public void deleteManagerById(int manager_id) {
	    String sql = "DELETE FROM manager WHERE manager_id = ?";
	    jdbctemplate.update(sql, manager_id);
	}
}
