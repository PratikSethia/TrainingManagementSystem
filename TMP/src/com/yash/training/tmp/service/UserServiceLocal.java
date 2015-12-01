package com.yash.training.tmp.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import com.yash.training.tmp.bean.UserBean;
import com.yash.training.tmp.domain.MenuList;
import com.yash.training.tmp.domain.SubMenu;
import com.yash.training.tmp.domain.User;

@Local
public interface UserServiceLocal {
	
	public String userAuthenticate(String userName, String password) throws Exception;
	public void userRegistration(User user) throws Exception;
	public User getUserName(String userName, String password) throws Exception;
	public List<MenuList> getMainList(int designationId) throws Exception;
	public List<SubMenu> getSubList(int menuListId) throws Exception;

}
