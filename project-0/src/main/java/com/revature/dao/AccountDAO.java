package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dto.AddOrUpdateAccountDTO;
import com.revature.model.Account;
import com.revature.util.JDBCUtility;

public class AccountDAO {

	public void deleteAllAccountsByClientId(int clientId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM accounts WHERE client_id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, clientId);

			pstmt.executeUpdate();

		}
	}

	public void deleteAccountById(int accountId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "DELETE FROM accounts WHERE account_id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, accountId);

			pstmt.executeUpdate();
		}
	}

	public List<Account> getAllAccountsByClientId(int clientId, int greaterThan, int lessThan) throws SQLException {
		List<Account> accounts = new ArrayList<>();

		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE client_id = ? AND annual_salary >= ? AND grade <= ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, clientId);
			pstmt.setInt(2, greaterThan);
			pstmt.setInt(3, lessThan);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("account_id");
				int annualSalary = rs.getInt("annual_salary");
				String jobTitle = rs.getString("job_title");
				int refClientId = rs.getInt("client_id");

				Account a = new Account(id, jobTitle, refClientId);

				accounts.add(a);

			}
		}

		return accounts;
	}

	public Account getAccountById(int accountId) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE client_id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, accountId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Account(rs.getInt("account_id"), rs.getString("job_title"),
						rs.getInt("annual_salary"));
			} else {
				return null;
			}

		}

	}

	public Account addAccount(AddOrUpdateAccountDTO account) throws SQLException{
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "INSERT INTO accounts (client_id, job_title, annual_salary) "
					+ "VALUES (?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, account.getClientId());
			pstmt.setString(2, account.getJobTitle());
			pstmt.setInt(3, account.getAnnualSalary());
			
		int	numbersOfRecordsInserted = pstmt.executeUpdate();
		
		if (numbersOfRecordsInserted !=1) {
			throw new SQLException("Adding account was successful!");
		}
		
		ResultSet rs = pstmt.getGeneratedKeys();
		rs.next();
		int automaticallyGeneratedId = rs.getInt(1);
		
		return new Account(automaticallyGeneratedId, account.getJobTitle(), account.getAnnualSalary());
			
		}
		
	}

	public Account updatedAccount(int id, AddOrUpdateAccountDTO account) throws SQLException {
		try (Connection con = JDBCUtility.getConnection()) {
			String sql = "UPDATE accounts" + "client_id = ?, " + "job_title = ?, " + "annual_salary = ?, " + "WHERE account_id = ?; ";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(2, account.getClientId());
			pstmt.setString(3, account.getJobTitle());
			pstmt.setInt(4, account.getAnnualSalary());
			
		int	numbersOfRecordsInserted = pstmt.executeUpdate();
		
		if (numbersOfRecordsInserted != 1) {
			throw new SQLException("Unable to update account record w/ id of " + id);
			
			
		}
			
			
			
		}
		
		
		return new Account(id, account.getJobTitle(), account.getAnnualSalary());
		
	
	}



}
