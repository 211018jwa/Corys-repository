package com.revature.controller;
import com.revature.service.ArithmeticService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ArithmeticController {

	public ArithmeticService arithmeticService;

	    // Constructor
	    public ArithmeticController() {
	        this.arithmeticService = new ArithmeticService();
	    }

	    // This is what is known as a lambda
	    // Think of it as similar to a method, but it is a method that can be passed around
	    public Handler  add = (ctx) -> {
	        ctx.result("add lamda invoked");

	        // Double class
	        // the Double class has a static method called parseDouble that can take a String and return a double primitive representation of that String

	        String number1String = ctx.formParam("number1");
	        String number2String = ctx.formParam("number2");

	        double number1 = Double.parseDouble(number1String);
	        double number2 = Double.parseDouble(number2String);


	        ctx.result(arithmeticService.doAddition(number1String, number2String)) ; // Just Strings concatenation and not our actual number
	    };

	    // Define an instance method here
	    public void registerEndpoint(Javalin app) {
	        app.post("/add", add); // we are mapping the add lambda, which will be invoked whenever a client sends a post request
	
		
	    }
	

}