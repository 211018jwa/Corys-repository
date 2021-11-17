package com.revature.model;

import java.util.Objects;

public class Account {
	
	private int id;
	private int annualSalary;
	private String jobTitle;
	private int clientId;
	
	//Constructors
	public Account() {
		
	}
	
	public Account(int id,String jobTitle, int clientId) {
		this.id = id;
		this.annualSalary = annualSalary;
		this.jobTitle = jobTitle;
		this.clientId = clientId;	
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(annualSalary, clientId, id, jobTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return annualSalary == other.annualSalary && clientId == other.clientId && id == other.id
				&& Objects.equals(jobTitle, other.jobTitle);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", annualSalary=" + annualSalary + ", jobTitle=" + jobTitle + ", clientId="
				+ clientId + "]";
	}

	
}
