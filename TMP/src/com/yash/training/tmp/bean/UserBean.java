package com.yash.training.tmp.bean;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yash.training.tmp.domain.Designation;
import com.yash.training.tmp.domain.MenuList;
import com.yash.training.tmp.domain.SubMenu;
import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.service.UserServiceLocal;

@ManagedBean
@SessionScoped
public class UserBean {
	
	private String name;
	private String contact;
	private String email;
	private int designationId;
	private String userName;
	private String password;
	private boolean createCookie;
	private String coo;
	private List<MenuList> lists;
	private List<SubMenu> subMenuList;
	
	
	
	 
	
	public List<SubMenu> getSubMenuList() {
		return subMenuList;
	}
	public void setSubMenuList(List<SubMenu> subMenuList) {
		this.subMenuList = subMenuList;
	}
	public List<MenuList> getLists() {
		return lists;
	}
	public void setLists(List<MenuList> lists) {
		this.lists = lists;
	}
	public String getCoo() {
		return coo;
	}
	public void setCoo(String coo) {
		this.coo = coo;
	}
	public boolean isCreateCookie() {
		return createCookie;
	}
	public void setCreateCookie(boolean createCookie) {
		this.createCookie = createCookie;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Inject
	User user;
	
	
	
	@EJB
	UserServiceLocal userServiceLocal;
	
	/*@PostConstruct
	public void getCookie(){
        HttpServletRequest  request= (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

		Cookie[] cookies = request.getCookies(); 
		boolean foundCookie = false;
		if(cookies!=null){
			
			for(int i = 0; i < cookies.length; i++)
			{ 
			    Cookie cookie = cookies[i];
			    if (cookie.getName().equals("details"))
			    {
			        String details= cookie.getValue();
			        String[] info  = details.split(" ");
			        String userName= info[0];
			        String password = info[1];
			        System.out.println(userName+" "+password);
			        setUserName("Hello");
			        setPassword(password);
			        foundCookie = true;
			    }
			}  
			
		}
		
		
	}*/
	

	public String userAuthenticate() throws Exception {
		/*if(createCookie==true){
			String details = userName +" "+ password;
			Cookie cookie = new Cookie("details", details);
			cookie.setMaxAge(2*60);
	        HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	        response.addCookie(cookie);
	  
			
		}*/
		
	
		
		String message = userServiceLocal.userAuthenticate(userName,password);
		if(message.equalsIgnoreCase("success")){
			User user = userServiceLocal.getUserName(userName, password);
			
			setName(user.getName());
			System.out.println(user.getName()+" "+user.getUser_Id());
			 HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		      session.setAttribute("userId", user.getUser_Id());
			
			setDesignationId(user.getDesignation().getDesignationId());
			
			int designationId=  user.getDesignation().getDesignationId();
			System.out.println(designationId);
			
			if(designationId==1){
				
				List<MenuList> list =userServiceLocal.getMainList(designationId);
			
				System.out.println(list);
				setLists(list);
				return "composition.xhtml?faces-redirect=true";
			}else if(designationId==2) {
				return "trainer.xhtml?faces-redirect=true";
				
			}else if(designationId==3){
				return "trainee.xhtml?faces-redirect=true";
				
			}else{
				return null;
			}

		}else{
			return "login.xhtml?faces-redirect=true&error="+message;

		}
		

		


	}
	
	
	public String userRegistration() throws Exception{
		user.setName(name);
		user.setContact(contact);
		Designation designation = new Designation();
		designation.setDesignationId(designationId);
		user.setDesignation(designation);
		user.setEmailId(email);
		user.setUserName(userName);
		user.setPassword(password);
       
		userServiceLocal.userRegistration(user);
		return "login.xhtml?faces-redirect=true&error=Registration Successfully";
		
	}
	
	public String logOut(){
		System.out.println("Hello-------");
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
       // return "WebContent/login.xhtml?faces-redirect=true&error=LogOut Successfully";
		return "login";
		
	}
	
	
	

}
