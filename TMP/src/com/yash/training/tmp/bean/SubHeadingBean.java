package com.yash.training.tmp.bean;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.service.CourseServiceLocal;

@ManagedBean
@SessionScoped
public class SubHeadingBean {

	private int headingId;

	private String courseTitle;
	private String headingText;
	private String subHeadingText;
	private String status;
	private List<String> headings;
	private int subHeadingId;

	

	public int getSubHeadingId() {
		return subHeadingId;
	}

	public void setSubHeadingId(int subHeadingId) {
		this.subHeadingId = subHeadingId;
	}

	@EJB
	CourseServiceLocal courseServiceLocal;

	public int getHeadingId() {
		return headingId;
	}

	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}

	public List<String> getHeadings() throws Exception {
		List<String> headings = courseServiceLocal.getHeadings();
		return headings;
	}

	public void setHeadings(List<String> headings) {
		this.headings = headings;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getHeadingText() {
		return headingText;
	}

	public void setHeadingText(String headingText) {
		this.headingText = headingText;
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

	@Inject
	SubHeading subHeading;

	public String saveSubHeading() throws Exception {
		System.out.println("--------------method call-----------------" + headingText);
		int headingId = courseServiceLocal.getHeadingId(headingText);
		subHeading.setHeadingId(headingId);
		subHeading.setSubHeadingText(subHeadingText);
		subHeading.setStatus(status);
		courseServiceLocal.saveSubHeading(subHeading);
		return "CreateCourses.xhtml";

	}

	public String saveSubHeadingMore() throws Exception {

		int headingId = courseServiceLocal.getHeadingId(headingText);
		subHeading.setHeadingId(headingId);
		subHeading.setSubHeadingText(subHeadingText);
		subHeading.setStatus(status);
		courseServiceLocal.saveSubHeading(subHeading);
		return null;

	}

	
	
	
	
}
