package com.testtask.spring.bean;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testtask.spring.model.Accounts;
import com.testtask.spring.model.Statement;
import com.testtask.spring.service.UserService;
import com.testtask.spring.utils.SessionUtils;

@Component("homeBean")
public class HomeBean {
	
	private BigDecimal accountId;
	private Date fromDate;
	private Date toDate;
	private BigDecimal fromAmount;
	private BigDecimal toAmount;
	private List<Statement> records = null;
	private Accounts accountInfo = null;
	
	@Autowired
	private UserService userService;
	
	public void fetchRecords() {
		System.out.println(accountId);
		System.out.println(fromDate);
		System.out.println(toDate);
		System.out.println(fromAmount);
		System.out.println(toAmount);
		
		try {
			records = userService.fetchRecordsByAccountId(Long.parseLong(accountId.toString()), fromDate, toDate,
					fromAmount, toAmount);	
		
		System.out.println(records.size());
		
		accountInfo = userService.findAccountByAccountId(Long.parseLong(accountId.toString()));
		if(accountInfo != null) {
			String temp = getHashedNo(accountInfo.getAccount_number());
			accountInfo.setAccount_number(temp);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateUser(FacesContext context, UIComponent comp, Object value) {
		try {
			
			if (SessionUtils.getUserRole().equals("USER")) {
				HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance()
						.getExternalContext().getResponse();
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not allowed to use this parameter.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getHashedNo(String no) {
		
		String first = no.substring(0, 3);
		String last = no.substring(no.length() - 3);
		int rest = no.length() - 6;
		String hashed = "";
		for(int i = 0; i < rest; i++) {
			hashed = hashed + "X";
		}
		return first + hashed +last;
	}
	
	public BigDecimal getAccountId() {
		return accountId;
	}
	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public BigDecimal getFromAmount() {
		return fromAmount;
	}
	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}
	public BigDecimal getToAmount() {
		return toAmount;
	}
	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}

	public List<Statement> getRecords() {
		return records;
	}

	public void setRecords(List<Statement> records) {
		this.records = records;
	}

	public Accounts getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(Accounts accountInfo) {
		this.accountInfo = accountInfo;
	}

}
