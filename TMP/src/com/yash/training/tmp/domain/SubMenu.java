package com.yash.training.tmp.domain;

import java.io.Serializable;

public class SubMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int menuListId;
	private int subMenuList;
	private String subMenuRole;
	public int getMenuListId() {
		return menuListId;
	}
	public void setMenuListId(int menuListId) {
		this.menuListId = menuListId;
	}
	public int getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(int subMenuList) {
		this.subMenuList = subMenuList;
	}
	public String getSubMenuRole() {
		return subMenuRole;
	}
	public void setSubMenuRole(String subMenuRole) {
		this.subMenuRole = subMenuRole;
	}
	@Override
	public String toString() {
		return "SubMenu [menuListId=" + menuListId + ", subMenuList=" + subMenuList + ", subMenuRole=" + subMenuRole
				+ "]";
	}
	
	

}
