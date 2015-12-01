package com.yash.training.tmp.domain;

import java.io.Serializable;

public class Designation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int designationId;
	private String designationName;
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public String getDesignationName() {
		return designationName;
	}
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
	
	
	

}
