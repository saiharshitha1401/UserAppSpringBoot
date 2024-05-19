package com.example.demo.models;

import java.time.LocalDate;
import java.util.UUID;

public class User {
	
	UUID id;
	String full_name;
	String mob_num;
	String pan_num;
	int manager_id;
	boolean is_active;
	LocalDate created_at;
	LocalDate updated_at;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public LocalDate getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}
	public LocalDate getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDate updated_at) {
		this.updated_at = updated_at;
	}
	
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getMob_num() {
		return mob_num;
	}
	public void setMob_num(String mob_num) {
		this.mob_num = mob_num;
	}
	public String getPan_num() {
		return pan_num;
	}
	public void setPan_num(String pan_num) {
		this.pan_num = pan_num;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public boolean isIs_active() {
		return is_active;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

}
