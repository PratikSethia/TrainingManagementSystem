package com.yash.training.tmp.domain;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	private int course_Id;
	private String courseTitle;
	private String description;
	private String referenceCode;
	private int status;
	private String statusValue;
	private List headings;
	
	
	
	public void setHeadings(List headings) {
		this.headings = headings;
	}
	public List getHeadings() {
		return headings;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	public int getCourse_Id() {
		return course_Id;
	}
	public void setCourse_Id(int course_Id) {
		this.course_Id = course_Id;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReferenceCode() {
		return referenceCode;
	}
	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Course [userId=" + userId + ", course_Id=" + course_Id + ", courseTitle=" + courseTitle
				+ ", description=" + description + ", referenceCode=" + referenceCode + ", status=" + status
				+ ", statusValue=" + statusValue + ", headings=" + headings + "]";
	}

	
	
	
	
}
