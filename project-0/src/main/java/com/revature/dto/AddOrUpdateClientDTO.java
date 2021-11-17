package com.revature.dto;

import java.util.Objects;

/*
 * DTO (Data Transfer Object): An object that contain properties, getters/setters and possibly an overriden equals + hashCode and toString method
 * That is used to encapsulate data and pass that data around as this single object 
 * 
 * Model - Is a type DTO, but more specifically, it has all of the properties associated with the database representation of a row in a table
 * 
 * For example, the Client class is a model of rows in the clients table
 * 
 *
 */
	
// This is a DTO used to, our case, add or update a client in the clients table
public class AddOrUpdateClientDTO {
		
		// This DTO does not have an ID, because to Add a new client or to update a previous already existing student, 
		// we just need the core properties 
		
		private String firstName;
		private String lastName;
		private String accountType;
		private int balance;
		private int clientId;
		
		
		public AddOrUpdateClientDTO() {
		
		}
		
		public AddOrUpdateClientDTO(String firstName, String lastName, String accountType, int balance) { 
			this.firstName = firstName;
			this.lastName = lastName;
			this.accountType = accountType;
			this.balance = balance;
			
				
		
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getAccountType() {
			return accountType;
		}

		public void setAccountType(String accountType) {
			this.accountType = accountType;
		}

		public int getBalance() {
			return balance;
		}

		public void setBalance(int balance) {
			this.balance = balance;
		}

		@Override
		public int hashCode() {
			return Objects.hash(accountType, balance, firstName, lastName);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AddOrUpdateClientDTO other = (AddOrUpdateClientDTO) obj;
			return Objects.equals(accountType, other.accountType) && balance == other.balance
					&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
		}

		@Override
		public String toString() {
			return "AddOrUpdateClientDTO [firstName=" + firstName + ", lastName=" + lastName + ", accountType="
					+ accountType + ", balance=" + balance + "]";
		}

		
		}
		
		




