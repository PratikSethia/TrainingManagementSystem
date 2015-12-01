package com.yash.training.tmp.resource;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yash.training.tmp.util.DButil;

@Path("/heading")
public class HeadingHelper {

	

	@GET
	@Path("/getvalue/{course}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getHeading(@PathParam("course") String course,@PathParam("id") int userId) throws Exception {
		String getCourseId = "SELECT COURSE_ID FROM COURSE WHERE COURSETITLE='" + course + "'AND USERID = '"+userId+"'";
		ResultSet resultSet0 = DButil.select(getCourseId);
		int cId = 0;
		if (resultSet0.next()) {
			cId = resultSet0.getInt("COURSE_ID");
		}
		String query = "SELECT  HEADINGTEXT FROM HEADING WHERE COURSEID=" + cId;
		List<String> checklist = new ArrayList<>();
		ResultSet resultSet = DButil.select(query);
		while (resultSet.next()) {
			checklist.add(resultSet.getString("HEADINGTEXT"));
		}
		return checklist;
	}

}
