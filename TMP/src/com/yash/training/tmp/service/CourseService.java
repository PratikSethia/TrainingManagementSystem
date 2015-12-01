package com.yash.training.tmp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.yash.training.tmp.bean.CourseBean;
import com.yash.training.tmp.bean.HeadingBean;
import com.yash.training.tmp.bean.SubHeadingBean;
import com.yash.training.tmp.domain.Course;
import com.yash.training.tmp.domain.Heading;
import com.yash.training.tmp.domain.SubHeading;
import com.yash.training.tmp.util.DButil;

/**
 * Session Bean implementation class CourseService
 */
@Stateful
@LocalBean
public class CourseService implements CourseServiceLocal {

	@Override
	public void saveCourse(Course course)  {
		String query = "INSERT INTO COURSE(COURSETITLE, DESCRIPTION, REFERENCECODE,STATUS, USERID) VALUES " + "('"
				+ course.getCourseTitle() + "','" + course.getDescription() + "','" + course.getReferenceCode() + "','"
				+ course.getStatus() + "','" + course.getUserId() + "') ";

		try {
			DButil.update(query);
			DButil.disconnectDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<String> getCourses(int userId) {
		String query = "SELECT COURSETITLE FROM COURSE WHERE USERID='" + userId + "'";
		ResultSet resultSet = DButil.select(query);
		List<String> courses = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String courseTitle = resultSet.getString("COURSETITLE");
				courses.add(courseTitle);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;

	}

	@Override
	public void saveHeading(Heading heading)  {

		String query = "INSERT INTO HEADING(HEADINGTEXT,COURSEID) VALUES " + "('" + heading.getHeadingText() + "','"
				+ heading.getCourseId() + "')";
		try {
			DButil.update(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int getCourseId(String courseName, int userId)  {
		String query = "SELECT COURSE_ID FROM COURSE WHERE COURSETITLE='" + courseName + "'AND USERID = '"+userId+"'";
		int courseId = 0;
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				courseId = resultSet.getInt("COURSE_ID");
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseId;
	}

	@Override
	public List<String> getHeadings()  {
		String query = "SELECT * FROM HEADING";
		ResultSet resultSet = DButil.select(query);
		List<String> headings = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String courseTitle = resultSet.getString("HEADINGTEXT");
				headings.add(courseTitle);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headings;
	}

	@Override
	public int getHeadingId(String headingText) {
		String query = "SELECT HEADING_ID FROM HEADING WHERE HEADINGTEXT='" + headingText + "'";
		int headingId = 0;
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				headingId = resultSet.getInt("HEADING_ID");
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return headingId;
	}

	@Override
	public void saveSubHeading(SubHeading subHeading)  {

		String query = "INSERT INTO SUBHEADING (HEADINGID, SUBHEADINGTEXT,STATUS) VALUES " + "('"
				+ subHeading.getHeadingId() + "','" + subHeading.getSubHeadingText() + "','" + subHeading.getStatus()
				+ "')";
		try {
			DButil.update(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Course> getAllCourses(int userId)  {

		String query = "SELECT * FROM COURSE WHERE USERID = '" + userId + "'";
		List<Course> list = new ArrayList<>();
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				Course course = new Course();
				course.setReferenceCode(resultSet.getString("REFERENCECODE"));
				course.setCourseTitle(resultSet.getString("COURSETITLE"));
				course.setCourse_Id(resultSet.getInt("COURSE_ID"));
				int statusId = resultSet.getInt("STATUS");
				if (statusId == 1) {
					course.setStatusValue("Deactivate");
				} else {
					course.setStatusValue("Activate");
				}

				list.add(course);

			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Course getCourseDetails(int selectedCourseId, int receivedCourseId)  {
		List<Heading> headings;
		List<SubHeading> subHeadings;

		Course course = new Course();
		Heading heading;
		SubHeading subHeading;
		String query = "Select * from course where userid=" + receivedCourseId + " and " + "course_id="
				+ selectedCourseId;
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				headings = new ArrayList<>();
				course.setCourse_Id(resultSet.getInt("COURSE_ID"));
				course.setCourseTitle(resultSet.getString("COURSETITLE"));
				course.setDescription(resultSet.getString("DESCRIPTION"));
				course.setReferenceCode(resultSet.getString("REFERENCECODE"));
				int status = resultSet.getInt("STATUS");
				if (status == 0) {
					course.setStatusValue("Deactivate");
				} else {
					course.setStatusValue("Activate");
				}

				query = "select * from heading where courseid=" + course.getCourse_Id();
				ResultSet resultSet3 = DButil.select(query);
				while (resultSet3.next()) {
					subHeadings = new ArrayList<>();
					heading = new Heading();
					heading.setHeading_Id(resultSet3.getInt("HEADING_ID"));
					heading.setHeadingText(resultSet3.getString("HEADINGTEXT"));
					heading.setCourseId(selectedCourseId);
					headings.add(heading);
					query = "select * from subheading where headingid=" + heading.getHeading_Id();
					ResultSet resultSet2 = DButil.select(query);
					while (resultSet2.next()) {
						subHeading = new SubHeading();
						subHeading.setSubHeading_Id(resultSet2.getInt("SUBHEADING_ID"));
						subHeading.setSubHeadingText(resultSet2.getString("SUBHEADINGTEXT"));
						subHeading.setHeadingId(heading.getHeading_Id());
						subHeading.setStatus(resultSet2.getString("STATUS"));
						subHeadings.add(subHeading);

					}
					resultSet2.close();
					heading.setSubHeadings(subHeadings);
				}
				resultSet3.close();

				course.setHeadings(headings);

			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("hello");
		}

		return course;

	}

	@Override
	public int getUserIdByStatus(int selectedCourseId)  {
		String sql = "select userid from course where course_id='" + selectedCourseId + "'";
		int userId = 0;
		ResultSet resultSet = DButil.select(sql);
		try {
			while (resultSet.next()) {
				userId = resultSet.getInt(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userId;

	}

	@Override
	public void changeStatus(int selectedCourseId, int receivedStatus)  {
		String query = "UPDATE COURSE SET STATUS = " + receivedStatus + " WHERE COURSE_ID = " + selectedCourseId;
		try {
			DButil.update(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int getStatusByStatus(int selectedCourseId)  {
		String query = "SELECT STATUS FROM COURSE WHERE COURSE_ID = " + selectedCourseId;
		int status = 0;
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				if (resultSet.getInt(1) == 1) {
				} else {
					status = 1;
				}

			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public void deleteCourse(int selectedCourseId) {

		String query = "DELETE FROM COURSE WHERE COURSE_ID = '" + selectedCourseId + "'";
		try {
			DButil.update(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getTotalCourse(int userId) {
		String query = "SELECT COUNT(*) FROM COURSE WHERE USERID = " + userId;
		int count = 0;
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getActiveCourse(int userId) {
		String query = "SELECT COUNT(*) FROM COURSE WHERE USERID = '" + userId + "' AND STATUS=1";
		ResultSet resultSet = DButil.select(query);
		int count = 0;
		try {
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List getHeadingTextByCourseTitle(String courseTitle) {
		String getCourseId = "SELECT COURSE_ID FROM COURSE WHERE COURSETITLE='" + courseTitle + "'";
		ResultSet resultSet0 = DButil.select(getCourseId);
		int cId = 0;
		try {
			if (resultSet0.next()) {
				cId = resultSet0.getInt("COURSE_ID");
				String query = "SELECT  HEADINGTEXT FROM HEADING WHERE COURSEID=" + cId;
				List<String> checklist = new ArrayList<>();
				ResultSet resultSet = DButil.select(query);
				while (resultSet.next()) {
					checklist.add(resultSet.getString("HEADINGTEXT"));
				}
				resultSet.close();
				return checklist;
			}
			resultSet0.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Course> allActiveCourses() {
		String query = "SELECT * FROM COURSE WHERE STATUS = 1";
		List<Course> list = new ArrayList<>();
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				Course course = new Course();
				course.setReferenceCode(resultSet.getString("REFERENCECODE"));
				course.setCourseTitle(resultSet.getString("COURSETITLE"));
				course.setCourse_Id(resultSet.getInt("COURSE_ID"));
				int statusId = resultSet.getInt("STATUS");
				if (statusId == 1) {
					course.setStatusValue("Deactivate");
				} else {
					course.setStatusValue("Activate");
				}

				list.add(course);

			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
			
	
	}

	@Override
	public void updateSubHeadingStatus(String status, int subHeadingId) {
		
		String query = "UPDATE SUBHEADING SET STATUS = '"+status+"' WHERE SUBHEADING_ID = '"+subHeadingId+"'";
		
		try {
			DButil.update(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*@Override
	public Course allActiveCoursesDetails(int selectedCourseId) {
		List<Heading> headings;
		List<SubHeading> subHeadings;

		Course course = new Course();
		Heading heading;
		SubHeading subHeading;
		String query = "Select * from course where userid=" + "course_id="+ selectedCourseId;
		ResultSet resultSet = DButil.select(query);
		try {
			while (resultSet.next()) {
				headings = new ArrayList<>();
				course.setCourse_Id(resultSet.getInt("COURSE_ID"));
				course.setCourseTitle(resultSet.getString("COURSETITLE"));
				course.setDescription(resultSet.getString("DESCRIPTION"));
				course.setReferenceCode(resultSet.getString("REFERENCECODE"));
				int status = resultSet.getInt("STATUS");
				if (status == 0) {
					course.setStatusValue("Deactivate");
				} else {
					course.setStatusValue("Activate");
				}

				query = "select * from heading where courseid=" + course.getCourse_Id();
				ResultSet resultSet3 = DButil.select(query);
				while (resultSet3.next()) {
					subHeadings = new ArrayList<>();
					heading = new Heading();
					heading.setHeading_Id(resultSet3.getInt("HEADING_ID"));
					heading.setHeadingText(resultSet3.getString("HEADINGTEXT"));
					heading.setCourseId(selectedCourseId);
					headings.add(heading);
					query = "select * from subheading where headingid=" + heading.getHeading_Id();
					ResultSet resultSet2 = DButil.select(query);
					while (resultSet2.next()) {
						subHeading = new SubHeading();
						subHeading.setSubHeading_Id(resultSet2.getInt("SUBHEADING_ID"));
						subHeading.setSubHeadingText(resultSet2.getString("SUBHEADINGTEXT"));
						subHeading.setHeadingId(heading.getHeading_Id());
						subHeading.setStatus(resultSet2.getString("STATUS"));
						subHeadings.add(subHeading);

					}
					resultSet2.close();
					heading.setSubHeadings(subHeadings);
				}
				resultSet3.close();

				course.setHeadings(headings);

			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("hello");
		}

		return course;
	}*/
}
