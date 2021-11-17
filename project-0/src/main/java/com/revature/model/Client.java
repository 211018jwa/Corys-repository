package com.revature.model;

import java.util.Objects;

//We will follow the conventions of Java bean
// 1. No-args constructor 
// 2. All-args constructor
// 3. All private variables
// 4. getters and setters

public class Client {
	
	private int id;
	private String firstName;
	private String lastName;
	private String accountType;
	private int balance;
	
	public Client() { 
		// super() is inserted implicitly. What this means is parent constructor are always called
		// Because Client is not explicitly inheriting any class, it is extending directly the Object class
		// Hence, the implicit super() is invoking the Object class constructor before running the rest of the code inside of this constructor
		
		
	}
	
	public Client(int id, String firstName, String lastName, String accountType, int balance) {
		//super(); (implicit) 
		this.id = id;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.accountType = accountType;
		this.balance = balance;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	/*
	 *  Object class methods: override toString(), equals(), and hashCode()
	 */
	
	@Override
	public int hashCode() {
		return Objects.hash(accountType, balance, firstName, id, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(accountType, other.accountType) && balance == other.balance
				&& Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", accountType="
				+ accountType + ", balance=" + balance + "]";
	}
	
}
