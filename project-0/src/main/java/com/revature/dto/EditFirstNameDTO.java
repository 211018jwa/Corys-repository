package com.revature.dto;

import java.util.Objects;

public class EditFirstNameDTO {
	
	private String firstName; 
	
	public EditFirstNameDTO() { 
		
	}
	
	public EditFirstNameDTO(String name) {
		this.firstName = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EditFirstNameDTO other = (EditFirstNameDTO) obj;
		return Objects.equals(firstName, other.firstName);
	}

	@Override
	public String toString() {
		return "EditFirstNameDTO [firstName=" + firstName + "]";
	}
	
	
}
