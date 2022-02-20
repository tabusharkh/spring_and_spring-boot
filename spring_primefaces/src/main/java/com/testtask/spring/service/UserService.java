package com.testtask.spring.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.testtask.spring.model.Accounts;
import com.testtask.spring.model.Statement;
import com.testtask.spring.model.Users;

public interface UserService {
	
	Users findUserByUsernameAndPassword(String username, String password) throws Exception;
	Accounts findAccountByAccountId(Long accountId) throws Exception;
	List<Statement> fetchRecordsByAccountId(Long accountId, Date fromDate, Date toDate, BigDecimal fromAmount, BigDecimal toAmount) throws Exception;

}
