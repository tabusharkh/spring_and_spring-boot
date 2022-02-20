package com.testtask.spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Statement {
	
	@Id
	private Long ID;
	private Long account_id;
	private String dateField;
	private Double amount;
	
	public Long getId() {
		return ID;
	}
	public void setId(Long id) {
		this.ID = id;
	}
	public Long getAccountId() {
		return account_id;
	}
	public void setAccountId(Long accountId) {
		this.account_id = accountId;
	}
	public String getDateField() {
		return dateField;
	}
	public void setDateField(String dateField) {
		this.dateField = dateField;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
