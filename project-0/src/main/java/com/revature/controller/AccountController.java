package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.service.AccountService;

import io.javalin.Javalin;
import io.javalin.http.Handler;


public class AccountController {
	
	private AccountService accountService;
	
	public AccountController() {
		this.accountService = new AccountService();
		
	}
	
	private Handler getAllAccountsByClientId = (ctx) -> {
		String clientId = ctx.pathParam("id");
		
		// Using query parameters, you can have 4 situations:
		// 1. No lessThan or greaterThan at all
		// 2. Using ONLY lessThan
		// 3. Using ONLY greaterThan
		// 4. Using BOTH
		
		// We are dealing with these 4 situatioins in our service layer by passing the ctx object, so that our service layer 
		// can examine the query parameters
		List<Account> accounts = this.accountService.getAllAccountsByClientId(clientId, ctx);
		
		ctx.json(accounts);
		
	};
	
	private Handler addAccount = (ctx) -> {
		String clientId = ctx.pathParam("id");
		
		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);
	
		Account a = this.accountService.addAccount(dto);
	
		ctx.json(a);
		ctx.status(201); // 201 CREATED
	};
	
	private Handler getAccountById = (ctx) -> {
		String clientId = ctx.pathParam("id");
		String accountId = ctx.pathParam("account_id");
		
		Account a = this.accountService.getAccountById(clientId, accountId);
		
		ctx.json(a);
		
		
	};
	
	private Handler editAccountById = (ctx) -> {
//		String clientId = ctx.pathParam("id");
		String accountId = ctx.pathParam("account_id");
		
		AddOrUpdateAccountDTO dto = ctx.bodyAsClass(AddOrUpdateAccountDTO.class);
		
		Account a = this.accountService.editAccount(accountId, dto.getClientId(), dto.getJobTitle(), dto.getAnnualSalary());
		
		ctx.json(a);
		ctx.status(201); // 201 CREATED
	};
	
	private Handler deleteAccountById = (ctx) -> {
		String clientId = ctx.pathParam("id");
		String accountId = ctx.pathParam("account_id");
		
		this.accountService.deleteAccountById(accountId, clientId);
		ctx.result("Account with id of " + accountId + "has been deleted!");
		
	};
	
	
	public void registerEndpoints(Javalin app) {
		app.post("/clients/{id}/accounts", addAccount);
		app.get("/clients/{id}/accounts", getAllAccountsByClientId);
		app.get("/clients/{id}/accounts/{account_id}", getAccountById);
		app.put("/clients/{id}/accounts/{account_id}", editAccountById);
		app.delete("/clients/{id}/accounts/{account_id}", deleteAccountById);
		

	}
}
