package com.revature.app;

public class Driver {

	public static void main(String[] args) {
		
		// String: an object whose role is to provide a structure for a sequence of characters
		String s1 = "Hello world";
		
		char[] myCharacters = { 'H', 'e', 'l','l', 'o', 'w', 'o', 'r', 'l', 'd'};
		String s2 = new String(myCharacters);
		
		System.out.println("s1:" + s1); //will be false because s1 and s2 do not point to the same object
		System.out.println("s2:" + s2); //true, because these two objects have the same underlying value
		
		
				
				
	}

}
