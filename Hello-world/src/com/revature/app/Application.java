package com.revature.app;

import java.util.Scanner;

public class Application {
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		
	String name;
	int Age;
	
	System.out.println("Enter Your Name");
	System.out.println("Enter the integer");
	
	Scanner s = new Scanner(System.in);
	name = s.next();
	System.out.println("Your name is " + name);
	
	Scanner s1 = new Scanner(System.in);
	Age = s1.nextInt();
	System.out.println("Your age is " + Age);
	
	
	
	
	}
		
        
		
}
