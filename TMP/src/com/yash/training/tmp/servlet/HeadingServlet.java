package com.yash.training.tmp.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;
import com.yash.training.tmp.service.CourseService;
import com.yash.training.tmp.service.CourseServiceLocal;
import com.yash.training.tmp.util.DButil;

/**
 * Servlet implementation class HeadingServlet
 */
@WebServlet("/HeadingServlet")
public class HeadingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");

		String courseTitle =request.getParameter("course");
		System.out.println(courseTitle);
		CourseServiceLocal courseServiceLocal = new CourseService();
		List headings = courseServiceLocal.getHeadingTextByCourseTitle(courseTitle);
		Gson gson = new Gson();
		String heading =  gson.toJson(headings);
		System.out.println(heading);
		response.getWriter().write(heading);
		
			
			
		}
	}


