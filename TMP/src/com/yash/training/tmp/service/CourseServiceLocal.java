package com.yash.training.tmp.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import com.yash.training.tmp.bean.CourseBean;
import com.yash.training.tmp.bean.HeadingBean;
import com.yash.training.tmp.bean.SubHeadingBean;
import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.Heading;
import com.yash.training.tmp.domain.SubHeading;

@Local
public interface CourseServiceLocal {

	public void saveCourse(Course course) throws Exception;

	public List<String> getCourses(int userId) throws Exception;

	public void saveHeading(Heading heading) throws Exception;

	public int getCourseId(String courseName, int userId) throws Exception;

	public List<String> getHeadings() throws Exception;

	public int getHeadingId(String headingText) throws Exception;

	public void saveSubHeading(SubHeading subHeading) throws Exception;

	public List<Course> getAllCourses(int userId) throws Exception;

	public Course getCourseDetails(int selectedCourseId, int receivedCourseId) throws SQLException;

	public int getUserIdByStatus(int selectedCourseId) throws Exception;

	public void changeStatus(int selectedCourseId, int receivedStatus) throws Exception;

	public int getStatusByStatus(int selectedCourseId) throws SQLException;

	public void deleteCourse(int selectedCourseId) throws Exception;

	public int getActiveCourse(int userId);

	public int getTotalCourse(int userId);

	public List getHeadingTextByCourseTitle(String courseTitle);

	public List<Course> allActiveCourses();

	public void updateSubHeadingStatus(String status, int subHeadingId);

	//public Course allActiveCoursesDetails(int selectedCourseId);

}
