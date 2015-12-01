package com.yash.training.tmp.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.Heading;
import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.service.CourseServiceLocal;

@ManagedBean
@SessionScoped
public class HeadingBean {

	private int headingId;
	private int courseId;
	private String headingText;
	private List<String> courses;
	private String courseTitle;
	
	public String getCourseName() {
		return courseTitle;
	}
	public void setCourseName(String courseName) {
		this.courseTitle = courseName;
	}
	@EJB
	CourseServiceLocal courseServiceLocal;
	
	
	public List<String> getCourses() throws Exception {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		int userId = (int) session.getAttribute("userId");
		List<String> courses = courseServiceLocal.getCourses(userId); 
		
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	public int getHeadingId() {
		return headingId;
	}
	public void setHeadingId(int headingId) {
		this.headingId = headingId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getHeading() {
		return headingText;
	}
	public void setHeading(String headingText) {
		this.headingText = headingText;
	}
	
	@Inject
	UserBean userBean;
	
	@Inject
	Heading heading;  
	
	
	
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	int userId = (int) session.getAttribute("userId");
	
	public String saveHeading() throws Exception{
		int courseId = courseServiceLocal.getCourseId(courseTitle, userId);
		heading.setCourseId(courseId);
		heading.setHeadingText(headingText);
		courseServiceLocal.saveHeading(heading);
		return "CreateCourses.xhtml";
		
		
	}
	
public String saveHeadingMore() throws Exception{
		
		
		int courseId = courseServiceLocal.getCourseId(courseTitle, userId);
		heading.setCourseId(courseId);
		heading.setHeadingText(headingText);
		courseServiceLocal.saveHeading(heading);
		return "heading.xhtml?faces-redirect&message=Heading Added Successfully";
		
		
	}
}
