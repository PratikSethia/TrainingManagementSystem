package com.yash.training.tmp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yash.training.tmp.domain.SubMenu;
import com.yash.training.tmp.service.UserService;
import com.yash.training.tmp.service.UserServiceLocal;

/**
 * Servlet implementation class SubMenuListServlet
 */
@WebServlet("/SubMenuListServlet")
public class SubMenuListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
	     //response.setCharacterEncoding("UTF-8");

		//PrintWriter out = response.getWriter();
		UserServiceLocal userServiceLocal = new UserService();
		int menuListId = Integer.parseInt(request.getParameter("menuListId"));
		System.out.println(menuListId);
		
		try {
			List<SubMenu> subMenus = new ArrayList<SubMenu>(userServiceLocal.getSubList(menuListId));
			Gson gson = new Gson();
			String subMenu =  gson.toJson(subMenus);
			System.out.println(subMenu);
			response.getWriter().write(subMenu);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
