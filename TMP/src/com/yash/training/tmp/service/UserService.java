package com.yash.training.tmp.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.yash.training.tmp.bean.UserBean;
import com.yash.training.tmp.domain.Designation;
import com.yash.training.tmp.domain.MenuList;
import com.yash.training.tmp.domain.SubMenu;
import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.util.DButil;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceLocal {

	@Inject 
	User user;
	
	
	public String userAuthenticate(String userName, String password)  {

		String query = "SELECT * FROM USER WHERE USERNAME='"+userName+"'AND PASSWORD='"+password+"'";
		ResultSet resultSet = DButil.select(query);
		try {
			if(resultSet.next()){
				String userName1 = resultSet.getString("USERNAME");
				String password1 = resultSet.getString("PASSWORD");
				String name = resultSet.getString("NAME");
				user.setName(name);
				user.setUserName(userName);
				user.setPassword(password);
				return "success";
			}else{
				String checkusername = "SELECT USERNAME FROM USER WHERE USERNAME='"+userName+"'";
				String checkpassword = "SELECT PASSWORD FROM USER WHERE PASSWORD='"+password+"'";
				ResultSet userSet = DButil.select(checkusername);
				ResultSet passwordSet = DButil.select(checkpassword);
				if (userSet.next()){
					return "Invalid Password";
				}else{
					
					if (passwordSet.next()){
						return "Invalid UserName";
					}
					else {
						return "Invalid UserName And Password";
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void userRegistration(User user)  {
		
		String query = "INSERT INTO USER(NAME,CONTACT, EMAILID,DESIGNATIONID, USERNAME, PASSWORD,STATUSID, ROLEID) VALUES "
				+ "('"+user.getName()+"','"+user.getContact()+"','"+user.getEmailId()+"','"+user.getDesignation().getDesignationId()+"',"
						+ "'"+user.getUserName()+"','"+user.getPassword()+"',1,2)";
		try {
			DButil.update(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public User getUserName(String userName, String password)  {
		String query = "SELECT * FROM USER WHERE USERNAME = '"+userName+"' AND PASSWORD ='"+password+"'";
		ResultSet resultSet = DButil.select(query);
		try {
			while(resultSet.next()){
				String name = resultSet.getString("NAME");
				int userId = resultSet.getInt("USER_ID");
				int designationId= Integer.parseInt(resultSet.getString("DESIGNATIONID"));
				user.setName(name);
				user.setUser_Id(userId);
				Designation designation = new Designation();
				designation.setDesignationId(designationId);
				user.setDesignation(designation);
				
			}
			resultSet.close();
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public List<MenuList> getMainList(int designationId)  {
		String query ="SELECT MENUROLE,MENULIST_ID FROM MENULIST WHERE DESIGNATIONID='"+designationId+"'";
		ResultSet resultSet = DButil.select(query);
		List<MenuList> list = new ArrayList<>();
		try {
			while(resultSet.next()){
				MenuList menuList = new MenuList();
				String menuRole = resultSet.getString("MENUROLE");
				int menuListId = resultSet.getInt("MENULIST_ID");
				menuList.setRole(menuRole);
				menuList.setMenuListId(menuListId);
				list.add(menuList);
				
				
				
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<SubMenu> getSubList(int menuListId)  {
		String query = "SELECT * FROM SUBMENULIST WHERE MENULIST_ID='"+menuListId+"'";
		ResultSet resultSet = DButil.select(query);
		List<SubMenu> list = new ArrayList<>();
		try {
			while(resultSet.next()){
				SubMenu subMenu = new SubMenu();
				String subMenuRole = resultSet.getString("SUBMENUROLE");
				int menuListId1 = resultSet.getInt("MENULIST_ID");
				int subMenuListId = resultSet.getInt("SUBMENULIST_ID");
				subMenu.setMenuListId(menuListId1);
				subMenu.setSubMenuList(subMenuListId);
				subMenu.setSubMenuRole(subMenuRole);
				
				list.add(subMenu);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

	
   
}
