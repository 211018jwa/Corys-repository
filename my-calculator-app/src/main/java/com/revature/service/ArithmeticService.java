package com.revature.service;

public class ArithmeticService {

public String doAddition(String number1String, String number2String) {
		
		double number1 = Double.parseDouble(number1String);
		double number2 = Double.parseDouble(number2String);
		
		double sum = number1 + number2;
		
		String result = "" + sum; // Convert from double representation of a number to a String representation 
		
		return result;
	}
}
