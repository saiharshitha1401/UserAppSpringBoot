package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepository;
import com.example.demo.models.User;
import com.example.demo.models.UserDTO;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
    private UserRepository userrepository;
	
	@Autowired
	private UserValidator validator;
	
	@PostMapping("/create_user")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
		if(validator.isValid(user).length() != 0) {
			return new ResponseEntity(validator.isValid(user),HttpStatus.BAD_REQUEST);
		}
		userrepository.addUser(user);
		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@GetMapping("/get_users")
    public ResponseEntity<List<User>> getUser() {
        List<User> userList = userrepository.getUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
	
	 @PostMapping("/delete_user")
	    public ResponseEntity<String> deleteUser(@RequestBody Map<String, String> request) {
	        String userId = request.get("user_id");
	        String mobNum = request.get("mob_num");

	        // Check if either user_id or mob_num is provided
	        if (userId == null && mobNum == null) {
	            return new ResponseEntity<>("Please provide either user_id or mob_num.", HttpStatus.BAD_REQUEST);
	        }

	        // Check if user exists and delete accordingly
	        if (userId != null) {
	            boolean deleted = userrepository.deleteUserById(userId);
	            if (deleted) {
	                return new ResponseEntity<>("User with ID " + userId + " deleted successfully.", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("User with ID " + userId + " not found.", HttpStatus.NOT_FOUND);
	            }
	        } else {
	            boolean deleted = userrepository.deleteUserByMobNum(mobNum);
	            if (deleted) {
	                return new ResponseEntity<>("User with mob_num " + mobNum + " deleted successfully.", HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>("User with mob_num " + mobNum + " not found.", HttpStatus.NOT_FOUND);
	            }
	        }
	 }
	        
	        
	        
	        @PostMapping("/update_user")
	        public ResponseEntity<String> updateUser(@RequestBody Map<String, Object> request) {
	            List<String> userIds = (List<String>) request.get("user_ids");
	            Map<String, String> updateData = (Map<String, String>) request.get("update_data");

	            // Perform bulk update if only manager_id is being updated
	            if (updateData.keySet().size() == 1 && updateData.containsKey("manager_id")) {
	                try {
	                	userrepository.bulkUpdate(userIds, updateData);
	                    return new ResponseEntity<>("Users updated successfully.", HttpStatus.OK);
	                } catch (Exception e) {
	                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	                }
	            } else {
	                return new ResponseEntity<>("Bulk update only supports updating manager_id.", HttpStatus.BAD_REQUEST);
	            }
	        }
	    }
    

	
