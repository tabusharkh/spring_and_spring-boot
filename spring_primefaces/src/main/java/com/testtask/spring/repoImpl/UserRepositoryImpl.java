package com.testtask.spring.repoImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.testtask.spring.model.Accounts;
import com.testtask.spring.model.Statement;
import com.testtask.spring.model.Users;
import com.testtask.spring.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Users findByUsernameAndPassword(String username, String password) throws Exception{
		
		Users user = null;
		try {
			user = (Users) entityManager
					.createQuery("SELECT u FROM Users u WHERE u.username = :username " + "AND u.password = :password")
					.setParameter("username", username).setParameter("password", password).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Statement> fetchRecordsByAccountId(Long accountId, Date fromDate, Date toDate, 
			BigDecimal fromAmount, BigDecimal toAmount) throws Exception{
		//When the from amount value is null, we set the date of beginning date of the last three months.
		if(fromDate == null) fromDate = java.sql.Date.valueOf(LocalDate.now().minus(3, ChronoUnit.MONTHS));
		
		//Setting the date of the current date to insure fetching all date till this date.
		if(toDate == null) toDate = new Date();
		
		//Assuming data doesn't contain negative values.
		if(fromAmount == null) fromAmount = new BigDecimal(-9999);
		
		//setting the maximum value in the column.
		if(toAmount == null) toAmount = new BigDecimal(getMaximumValue());
		List<Statement> records = null;
		
		System.out.println(fromDate);
		System.out.println(toDate);
		System.out.println(fromAmount);
		System.out.println(toAmount);
		
		try {
			Query qstr = entityManager
					.createNativeQuery("SELECT u.ID, u.account_id, u.datefield, u.amount FROM statement u WHERE u.account_id = :accountId AND "
							+ "STR_TO_DATE(u.datefield, '%d.%m.%Y') BETWEEN :fromDate and :toDate AND u.amount BETWEEN :fromAmount and :toAmount", Statement.class)
					.setParameter("accountId", accountId)
					.setParameter("fromDate", fromDate)
					.setParameter("toDate", toDate)
					.setParameter("fromAmount", fromAmount)
					.setParameter("toAmount", toAmount);
			
			records = (List<Statement>) qstr.getResultList(); 
		}catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		
		return records;
		
	}
	
	public double getMaximumValue() throws Exception{
		double maxValue = 0;
		
		Query qstr = entityManager
				.createNativeQuery("SELECT MAX(amount) FROM statement");
		
		maxValue = (double)qstr.getSingleResult();
		
		return maxValue;
		
	}
	
	public Accounts findAccountByAccountId(Long accountId) throws Exception{
		Accounts account = null;
		try {
			account = (Accounts) entityManager.find(Accounts.class, accountId);
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
}
