package com.yash.training.tmp.domain;

import java.io.Serializable;

public class Status implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusId;
	private String statusName;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	

}
