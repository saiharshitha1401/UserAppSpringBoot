package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.dao.ManagerRepository;
import com.example.demo.models.Manager;


@RestController
@RequestMapping("/api")
public class ManagerController {
	@Autowired
	private ManagerRepository managerRepository;
	
	@PostMapping("/create_manager")
	public ResponseEntity<Manager> createManager(@RequestBody Manager manager){
		managerRepository.addManager(manager);
		return new ResponseEntity(manager, HttpStatus.OK);
	}
	
	 @GetMapping("/get/manager")
	    public ResponseEntity<List<Manager>> getManager() {
	        List<Manager> managerList = managerRepository.getManager();
	        return new ResponseEntity<>(managerList, HttpStatus.OK);
	 }
	 
	 @PutMapping("update/manager/{manager_id}/{name}")
	    public ResponseEntity<String> updateFullName(@PathVariable int manager_id, @PathVariable String name) {
	        managerRepository.updateManagerById(manager_id, name);
	        return ResponseEntity.ok("Manager name updated to: " + name);
	 }
	 
	 @DeleteMapping("/delete/manager/{manager_id}") 
		public ResponseEntity<String> deleteManager(@PathVariable("manager_id") int manager_id) {
		    managerRepository.deleteManagerById(manager_id);
		    return ResponseEntity.ok("Manager deleted successfully");
		}
}
