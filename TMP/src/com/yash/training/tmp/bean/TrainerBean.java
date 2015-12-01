package com.yash.training.tmp.bean;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.service.CourseServiceLocal;

@ManagedBean
@SessionScoped
public class TrainerBean {

	private int selectedCourseId;
	
	@Inject
	private Course course; 
	
	@Inject
	private SubHeading subHeading;
	
		
	public SubHeading getSubHeading() {
		return subHeading;
	}

	public void setSubHeading(SubHeading subHeading) {
		this.subHeading = subHeading;
	}

	public Course getCourse() throws Exception {
		System.out.println(selectedCourseId+"+++++++++++++++");
		int receivedUserId = courseServiceLocal.getUserIdByStatus(selectedCourseId);
		course = courseServiceLocal.getCourseDetails(selectedCourseId, receivedUserId);
		System.out.println(course);
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getSelectedCourseId() {
		return selectedCourseId;
	}

	public void setSelectedCourseId(int selectedCourseId) {
		this.selectedCourseId = selectedCourseId;
	}

	@EJB
	CourseServiceLocal courseServiceLocal;



	public String allActiveCoursesDetails() throws Exception{
		System.out.println(selectedCourseId+"+++++++++++++++");
		int receivedUserId = courseServiceLocal.getUserIdByStatus(selectedCourseId);
		course = courseServiceLocal.getCourseDetails(selectedCourseId, receivedUserId);
		System.out.println(course);
		return "trainerCourseDetails";
		
		
	}
	
	public String updateSubHeadingStatus() {
		System.out.println(subHeading.getSubHeading_Id());
		System.out.println(subHeading.getStatus());
		courseServiceLocal.updateSubHeadingStatus(subHeading.getStatus(), subHeading.getSubHeading_Id());
		
		return "trainerCourseDetails.xhtml?faces-redirect=true";
	}
}
