package com.yash.training.tmp.domain;

import java.io.Serializable;

public class SubHeading implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int subHeading_Id;
	private int headingId;
	private String subHeadingText;
	private String status;
	public int getSubHeading_Id() {
		return subHeading_Id;
	}
	public void setSubHeading_Id(int subHeading_Id) {
		this.subHeading_Id = subHeading_Id;
	}
	public int getHeadingId() {
		return headingId;
	}
	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}
	public String getSubHeadingText() {
		return subHeadingText;
	}
	public void setSubHeadingText(String subHeadingText) {
		this.subHeadingText = subHeadingText;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SubHeading [subHeading_Id=" + subHeading_Id + ", headingId=" + headingId + ", subHeadingText="
				+ subHeadingText + ", status=" + status + "]";
	}
	
	
	

}
