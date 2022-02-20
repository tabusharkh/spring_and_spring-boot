package com.testtask.spring.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testtask.spring.model.Accounts;
import com.testtask.spring.model.Statement;
import com.testtask.spring.model.Users;
import com.testtask.spring.repository.UserRepository;
import com.testtask.spring.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Users findUserByUsernameAndPassword(String username, String password) throws Exception{
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<Statement> fetchRecordsByAccountId(Long accountId, Date fromDate, Date toDate, BigDecimal fromAmount, BigDecimal toAmount) throws Exception {
		return userRepository.fetchRecordsByAccountId(accountId, fromDate, toDate, fromAmount, toAmount);
	}

	@Override
	public Accounts findAccountByAccountId(Long accountId) throws Exception{
		return userRepository.findAccountByAccountId(accountId);
	}
}
