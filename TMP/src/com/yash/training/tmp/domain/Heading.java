package com.yash.training.tmp.domain;

import java.io.Serializable;
import java.util.List;

public class Heading implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int heading_Id;
	private String headingText;
	private int courseId;
	private List subHeadings;
	
	
	public int getHeading_Id() {
		return heading_Id;
	}
	public void setHeading_Id(int heading_Id) {
		this.heading_Id = heading_Id;
	}
	public String getHeadingText() {
		return headingText;
	}
	public void setHeadingText(String headingText) {
		this.headingText = headingText;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Heading [heading_Id=" + heading_Id + ", headingText=" + headingText + ", courseId=" + courseId
				+ ", subHeadings=" + subHeadings + "]";
	}
	public List getSubHeadings() {
		return subHeadings;
	}
	public void setSubHeadings(List subHeadings) {
		this.subHeadings = subHeadings;
	}
	
	
	
	

}
