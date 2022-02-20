package com.testtask.spring.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testtask.spring.model.Users;
import com.testtask.spring.service.UserService;
import com.testtask.spring.utils.SessionUtils;

@Component("loginBean")
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String username;
	private String password;
	private String message;
	
	@Autowired
	private UserService userService;
	
	public String validateUser() {
		
		System.out.println(username);
		
		try {
		Users user = userService.findUserByUsernameAndPassword(username, password);
		
		if(user != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userRole", user.getRole());
			session.setAttribute("user", user);
			return "home";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
											"Incorrect username and password", "Please enter correct username and password"));
			return "index";
		}
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
