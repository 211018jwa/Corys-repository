package com.revature.service;

import java.sql.SQLException;
import java.util.List;



import com.revature.dao.AccountDAO;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.AccountNotFoundException;
import com.revature.model.Account;

import io.javalin.http.Context;

public class AccountService {
	
	private AccountDAO accountDao;
	private ClientDAO clientDao;
	
	public AccountService() {
		this.accountDao = new AccountDAO();
		this.clientDao = new ClientDAO();
		
	}
	
	//For mock object (Mockito)
	public AccountService(AccountDAO accountDao, ClientDAO clientDao) {
		this.accountDao = accountDao;
		this.clientDao = clientDao; 	
		
	}
	
	// Business logic we want to make sure that this client acutally exist 
	// 		If the client does not exist, throw a ClientNotFoundException 
	public List<Account>getAllAccountsByClientId(String clientId, Context ctx) throws InvalidParameterException, ClientNotFoundException, SQLException {
		
		List<Account> accounts; 
		
		int id = Integer.parseInt(clientId);
		
		if (ctx.queryParam("greaterThan") != null && ctx.queryParam("lessThan") != null) { // Using both
			int greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));
			int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
			
			accounts = this.accountDao.getAllAccountsByClientId(id, greaterThan, lessThan);
		} else if (ctx.queryParam("lessThan") != null) { // using only lessThan
			
			int lessThan = Integer.parseInt(ctx.queryParam("lessThan"));
			
			
			accounts = this.accountDao.getAllAccountsByClientId(id, 0, lessThan);
		} else if (ctx.queryParam("greaterThan") != null) {
			
			int greaterThan = Integer.parseInt(ctx.queryParam("greaterThan"));
			
			accounts = this.accountDao.getAllAccountsByClientId(id, greaterThan, 100);
			
		} else {
				accounts = this.accountDao.getAllAccountsByClientId(id, 0, 1000000);
		}
		
		return accounts;
	
	}

	public Account addAccount(AddOrUpdateAccountDTO dto) throws InvalidParameterException, SQLException {
		try {
			
		
		if (dto.getJobTitle().trim().equals("")) {
			throw new InvalidParameterException("Job Title cannot be blank");
		}	
		if (dto.getAnnualSalary() <=0 ) {
			throw new InvalidParameterException("Annual Salary must be more then 0!");
		}
		Account instertedAccount = this.accountDao.addAccount(dto);
		
		return instertedAccount;
	
	}catch(InvalidParameterException e) {
		throw new InvalidParameterException("Client cannot be blank!");
	 }
	}
	

	public Account editAccount(String accountId, int clientId, String jobTitle, int annualSalary) throws InvalidParameterException, SQLException {
		try {
			int id = Integer.parseInt(accountId);
//			int cId = Integer.parseInt(clientId);
			Account accountToEdit = this.accountDao.getAccountById(id);
		
			
			if (accountToEdit == null) {
				throw new AccountNotFoundException("Account with an id of " + clientId + " was not found");
				
			}
			
			AddOrUpdateAccountDTO dto = new AddOrUpdateAccountDTO(clientId, jobTitle, annualSalary);
			Account updatedAccount = this.accountDao.updatedAccount(id, dto);
			
			return updatedAccount;
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("Account Id provided is not an int convertable value");
			
		}

	}

	public Account getAccountById(String clientId, String accountId) throws SQLException, InvalidParameterException, AccountNotFoundException {
		try {
			int id = Integer.parseInt(accountId);
			
			Account a =  this.accountDao.getAccountById(id);
			
			if (a == null) {
				throw new AccountNotFoundException("Account with id " + accountId + " was not found");
			}
			return a;
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("Id provided was not an Int convertable value");
		}
		
		
	}

	public void deleteAccountById(String accountId, String clientId) throws InvalidParameterException, SQLException, AccountNotFoundException {
		try {
			int cId = Integer.parseInt(clientId);
			int id = Integer.parseInt(accountId);
	
		Account account = this.accountDao.getAccountById(id);
		if (account == null) {
			throw new AccountNotFoundException("Account with id " + id + " was not found");
		}
		
		this.accountDao.deleteAccountById(id);
	} catch(NumberFormatException e) {
		throw new InvalidParameterException("Id supplied was not an int convertable value");
	
		}
	
	}
}
