package com.revature.dto;

import java.util.Objects;

public class AddOrUpdateAccountDTO {

	
	private int clientId;
	private String jobTitle;
	private int annualSalary;
	
	public AddOrUpdateAccountDTO() {
		super();
	
	}

	public AddOrUpdateAccountDTO(int clientId, String jobTitle, int annualSalary) {
		super();
		this.clientId = clientId;
		this.jobTitle = jobTitle;
		this.annualSalary = annualSalary;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(annualSalary, clientId, jobTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddOrUpdateAccountDTO other = (AddOrUpdateAccountDTO) obj;
		return annualSalary == other.annualSalary && clientId == other.clientId
				&& Objects.equals(jobTitle, other.jobTitle);
	}

	@Override
	public String toString() {
		return "AddOrUpdateAccountDTO [clientId=" + clientId + ", jobTitle=" + jobTitle + ", annualSalary="
				+ annualSalary + "]";
	}
	
	
	
	
	
}
