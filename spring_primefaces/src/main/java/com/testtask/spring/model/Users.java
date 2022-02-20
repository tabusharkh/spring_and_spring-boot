package com.testtask.spring.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@Entity
public class Users implements HttpSessionBindingListener{
	
	private static Map<Users, HttpSession> logins = new HashMap<Users, HttpSession>();
	
	@Id
	private Long id;
	private String username;
	private String password;
	private String role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
    public boolean equals(Object other) {
        return (other instanceof Users) && (id != null) ? id.equals(((Users) other).id) : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = logins.remove(this);
        if (session != null) {
            session.invalidate();
        }
        logins.put(this, event.getSession());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logins.remove(this);
    }
}
