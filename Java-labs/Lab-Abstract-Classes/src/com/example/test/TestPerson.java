package com.example.test;

import com.example.Person;
import com.example.Developer;

public class TestPerson {
	
	public static void main(String[] args) {
		Person cory = new Developer();
		
		cory.setName("Cory");
		System.out.println(cory.getName());
	}

}
