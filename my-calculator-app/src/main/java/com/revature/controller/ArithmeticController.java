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
	// Think of it as similar to a method, but it is a method that can be passed
	// around
	public Handler add = (ctx) -> {

		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");

		ctx.result(arithmeticService.doOperation(number1String, number2String, '+')); // Just Strings concatenation and
																						// not
		// our actual number
	};

	public Handler subtract = (ctx) -> {

		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");

		ctx.result(arithmeticService.doOperation(number1String, number2String, '-'));
	};

	public Handler multiply = (ctx) -> {

		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");

		ctx.result(arithmeticService.doOperation(number1String, number2String, '*'));
	};
	public Handler divide = (ctx) -> {

		String number1String = ctx.formParam("number1");
		String number2String = ctx.formParam("number2");

		ctx.result(arithmeticService.doOperation(number1String, number2String, '/'));// Just Strings concatenation and not our
																				// actual number
	};

	public void registerEndpoint(Javalin app) {
		app.post("/add", add);
		app.post("/subtract", subtract);
		app.post("/multiply", multiply);
		app.post("/divide", divide);
	}

}
