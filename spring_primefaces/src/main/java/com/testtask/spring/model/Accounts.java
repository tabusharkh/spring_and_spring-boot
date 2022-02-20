package com.testtask.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Accounts {
	
	@Id
	private Long ID;
	private String account_type;
	private String account_number;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

}
