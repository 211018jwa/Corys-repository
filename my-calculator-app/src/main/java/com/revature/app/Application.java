package com.revature.app;

import com.revature.controller.ArithmeticController;


import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {

	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/", Location.CLASSPATH);
			// here we are using the create() static method that belongs to the Javelin
			// class to create a Javelin object

			// This Javelin object is what gives us a "handle" on the Jetty webserver that
			// will receive HTTP request and send HTTP responses
		});
		// Instantiate our Controller
		ArithmeticController arithmeticController = new ArithmeticController();
		arithmeticController.registerEndpoint(app);
		app.start(8080); // start server on port 8080

	}

}
