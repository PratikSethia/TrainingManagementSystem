package com.yash.training.tmp.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.service.CourseServiceLocal;

@ManagedBean
@SessionScoped
public class CourseBean {

	private int courseId;
	private int selectedCourseId;
	private String courseTitle;
	private String description;
	private String referenceCode;
	private boolean status = true;
	private List<Course> courses;
	private int activeCourse;
	private int totalCourse;
	private List<Course> activeCourses;
	
	
	
	
	
	public List<Course> getActiveCourses() {
		activeCourses = courseServiceLocal.allActiveCourses();
		return activeCourses;
	}

	public void setActiveCourses(List<Course> activeCourses) {
		this.activeCourses = activeCourses;
	}

	

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	int userId = (int) session.getAttribute("userId");

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getActiveCourse() {
		activeCourse = courseServiceLocal.getActiveCourse(userId);

		return activeCourse;
	}

	public void setActiveCourse(int activeCourse) {
		this.activeCourse = activeCourse;
	}

	public int getTotalCourse() {
		totalCourse = courseServiceLocal.getTotalCourse(userId);
		return totalCourse;
	}

	public void setTotalCourse(int totalCourse) {
		this.totalCourse = totalCourse;
	}

	

	public int getSelectedCourseId() {
		return selectedCourseId;
	}

	public void setSelectedCourseId(int selectedCourseId) {
		this.selectedCourseId = selectedCourseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
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

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Course> getCourses() throws Exception {
		courses = courseServiceLocal.getAllCourses(userId);
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@EJB
	CourseServiceLocal courseServiceLocal;

	@Inject
	Course course;
	
	

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String saveCourse() throws Exception {
		System.out.println(userId);

		course.setCourseTitle(courseTitle);
		course.setDescription(description);
		course.setReferenceCode(referenceCode);
		course.setUserId(userId);

		if (status == true) {
			course.setStatus(1);
		} else {
			course.setStatus(0);
		}

		courseServiceLocal.saveCourse(course);

		return "CreateCourses.xhtml?faces-redirect=true&message=Course Added Successfully";

	}

	public String getAllCourses() throws Exception {

		return "courses";

	}

	public String changeStatusMethod() throws Exception {
		int receivedStatus = courseServiceLocal.getStatusByStatus(selectedCourseId);
		courseServiceLocal.changeStatus(selectedCourseId, receivedStatus);
		return "courses.xhtml?faces-redirect=true";

	}

	public String allDetails() throws Exception {
		int receivedUserId = courseServiceLocal.getUserIdByStatus(selectedCourseId);
		course = courseServiceLocal.getCourseDetails(selectedCourseId, receivedUserId);
		
		
		return "details.xhtml?faces-redirect=true";
	}

	public String deleteCourse() throws Exception {
		courseServiceLocal.deleteCourse(selectedCourseId);
		return "courses.xhtml?faces-redirect=true";

	}
	
	
	public String allActiveCoursesDetails() throws Exception{
		System.out.println(selectedCourseId+"+++++++++++++++");
		int receivedUserId = courseServiceLocal.getUserIdByStatus(selectedCourseId);
		course = courseServiceLocal.getCourseDetails(selectedCourseId, receivedUserId);
		System.out.println(course);
		return "trainerCourseDetails";
		
		
	}
	
	

}
