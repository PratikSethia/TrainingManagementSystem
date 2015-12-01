package com.yash.training.tmp.domain;

import java.io.Serializable;

public class MenuList implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String role;
	private int menuListId;
	
	

	public int getMenuListId() {
		return menuListId;
	}

	public void setMenuListId(int menuListId) {
		this.menuListId = menuListId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "MenuList [role=" + role + ", menuListId=" + menuListId + "]";
	}

	
	
	

}
