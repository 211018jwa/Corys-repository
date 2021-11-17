package com.revature.controller;

import java.util.List;

import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.dto.EditFirstNameDTO;
import com.revature.dto.ExceptionMessageDTO;
import com.revature.service.ClientService;
import com.revature.model.Client;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.ClientNotFoundException;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ClientController {
	
	// The Controller layer will be directly communicating with the Service layer, and the service layer will be 
	// communicating with the Data access layer.
	
	//Our dependency here for our Controller
	private ClientService clientService; // ClientController instance HAS-A ClientService 
	
	public ClientController() {
		this.clientService = new ClientService();
		
	}
//	
//	private Handler editClientFirstName = (ctx) -> {
//		
//		//Extract the id from URI path 
//		String clientId = ctx.pathParam("id");
//		
//		int id = Integer.parseInt(clientId);
//		
//		//Extract the information in the form of JSON from the body and place it into an object of the type EditFirstNameDTO
//		EditFirstNameDTO dto = ctx.bodyAsClass(EditFirstNameDTO.class);
//		
//		this.clientService.editFirstNameOfClient(id, dto.getFirstName());
//		
//		try {
//			// When we invoke the editFirstNameOfClient method (service layer), it will then contact the updateClient method in the DAO
//			// layer, which will then return the Client object representation of the record that was newly updated
//			Client clientThatWasJustEdited = this.clientService.editFirstNameOfClient(id, dto.getFirstName());
//			
//			// We then take that object and convert it into JSON 
//			// This JSON is then sent back to the client that sent the request (in our case, Postman)
//			ctx.json(clientThatWasJustEdited); // For this to work, we need to have getter methods that exist for that object
//		} catch(ClientNotFoundException e) {
//			ctx.status(404);
//			ctx.json(e);
//		}
//		
//		
//	};
	
	private Handler addClient = (ctx) -> {
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		Client c = this.clientService.addClient(dto);
		
		ctx.json(c);
		ctx.status(201); // 201 CREATED
	};
	
	private Handler getAllClients = (ctx) -> {
		List<Client> clients = this.clientService.getAllClients();
		
		ctx.json(clients);
		
	};
	
	private Handler getClientById = (ctx) -> {
		String id = ctx.pathParam("id");
		
		Client c = this.clientService.getClientById(id);
		
		ctx.json(c);
	};
	
	private Handler editClientById = ctx -> {
		AddOrUpdateClientDTO dto = ctx.bodyAsClass(AddOrUpdateClientDTO.class);
		
		String id = ctx.pathParam("id");
		int clientId = Integer.parseInt(id); 
		Client clientToBeUpdated = this.clientService.editClientById(clientId, dto);

		ctx.json(clientToBeUpdated);
	};
	
	private Handler deleteClientById = (ctx) -> {
		String id = ctx.pathParam("id");
		
		this.clientService.deleteClientById(id);
		ctx.result("Client with id of " + id + " has been deleted!");
		
	};
	
	private Handler deleteAllClients = (ctx) -> {
		
	};
	
	
	public void registerEndpoints(Javalin app) {
//		app.patch("/clients/{id}", editClientById);
		
		app.post("/clients", addClient);
		app.get("/clients", getAllClients);
		app.get("/clients/{id}", getClientById);
		app.put("/clients/{id}", editClientById);
		app.delete("/clients/{id}", deleteClientById);
		app.delete("/clients", deleteAllClients);
	}
	

	
		
}
	
	
	
	

