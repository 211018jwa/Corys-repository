package com.revature.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.ClientDAO;
import com.revature.dao.AccountDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.exceptions.InvalidParameterException;
import com.revature.model.Client;
import com.revature.model.Account;


public class ClientService {
	
	private Logger logger = LoggerFactory.getLogger(ClientService.class);
	
	private AccountDAO accountDao;
	private ClientDAO clientDao;// HAS-A relationship. ClientService HAS-A ClientDAO
	// In other words, ClientService depends on ClientDAO 
	// ClientDAO is a dependency of ClientService
	
	//This constructor up here will create a real ClientDAO object for our ClientService object being constructed
	public ClientService() {
		this.clientDao = new ClientDAO();
		this.accountDao = new AccountDAO();
		
		
	}
	
	//Create a new constructor for us to pass in a mock ClientDAO object
	public ClientService(ClientDAO clientDao) { 
		this.clientDao = clientDao;
		
	}
	
	//Another constructor for us to pass in a mock 
	public ClientService(ClientDAO clientDao, AccountDAO accountDao) {
		this.clientDao = clientDao;
		this.accountDao = accountDao;
	}
		
	
	
	/*
	 * This is our service layer logic for updating ONLY the firstName of a client
	 * 
	 * What this method does is first grab the Client with that particular client id from the database
	 * 		- If a student does not exist with that clientId, it will throw a ClientNotFoundException
	 * 		- Otherwise, it will go ahead and call the updateClient method in the DAO layer and provide the appropriate arguments.
	 * 
	 */
	public Client editClientById(int clientId, AddOrUpdateClientDTO changedClient) throws SQLException, ClientNotFoundException, InvalidParameterException {
		
	 //Convert the String to an Int
	try {

		
		// First, grab the information about the client with a client id of that value clientId
		Client clientToEdit = this.clientDao.getClientById(clientId);
		
		//How does getClientById work?
		// 1st scenario: If we have a clientId that indeed exists in the database, it will return us a Client object containing the information
		// 2nd scenario: If we have a clientId that does not have corresponding record in the database, it will return null
		// null = absence of an object 
		if (clientToEdit == null) { 
			throw new ClientNotFoundException("Client with an id of " + clientId + " was not found");
		}
		
		// This DTO will contain the first name that will be changed, while everything else stays the same as before
//		AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO(changedClient, clientToEdit.getLastName(), clientToEdit.getAccountType(), clientToEdit.getBalance(), clientToEdit.getId());
//		
		Client updatedClient = this.clientDao.updateClient(clientId, changedClient);
		
		return updatedClient;
	}catch(NumberFormatException e) {
		throw new InvalidParameterException("Id provided is not an int convertable value");
		}

	}
	
	//We know based on our logic, if getAllClients from the DAO layer throws a SQLException, our service layer will also throw a SQLException
	// Because we are not catching it in this method
	// We are using throws SQLException in the method signature
	public List<Client> getAllClients() throws SQLException {
		logger.info("getAllClients() invoked");
		
		List<Client> clients = this.clientDao.getAllClients();
		
		return clients;
	}
	
	public Client getClientById(String clientId) throws SQLException, InvalidParameterException, ClientNotFoundException {
		// convert from a String to an int
		try {
				int id = Integer.parseInt(clientId);
				
				Client c = this.clientDao.getClientById(id);
				
				if (c == null) {
						throw new ClientNotFoundException("Client with id of " + clientId + " was not found");
					
				}
				
				return c;
			}catch(NumberFormatException e) {
				throw new InvalidParameterException("Id provided was not an Int convertable value");
			}
	
		}
	/*
	 * Business logic
	 * 1. We dont want firstName to be blank
	 * 2. We dont want lastName to be blank 
	 * 3. We want accountType to either be Savings or checking
	 */
	
	public Client addClient(AddOrUpdateClientDTO dto) throws InvalidParameterException, SQLException {
		// dto contains firstName, lastName, accountType and clientBalance
		if (dto.getFirstName().trim().equals("") || dto.getLastName().trim().equals("")) {
			throw new InvalidParameterException("First name and/or last name cannot be blank");
		}
		
		Set<String> validAccountType = new HashSet<>();
		validAccountType.add("Savings");
		validAccountType.add("Checking");
		
		if (!validAccountType.contains(dto.getAccountType())) {
			throw new InvalidParameterException("You entered an invalid account type");
			
		}
		
		// Trim the leading and trailing whitespaces in the first and last names
		dto.setFirstName(dto.getFirstName().trim());
		dto.setLastName(dto.getLastName().trim());
		
		Client instertedClient = this.clientDao.addClient(dto);
		
		return instertedClient;
	}
	
	/*
	 * 1. Check to see if the clientId provided in the URI is actually an int, and if not, throw an InvalidParameterException
	 * 2. If the student we are trying to delete does not exist, throw a ClientNotFoundException
	 */
	
	public void deleteClientById(String clientId) throws InvalidParameterException, SQLException, ClientNotFoundException {
		try { 
				int id = Integer.parseInt(clientId);
				
				// Check to see if a client with tha id exist or not 
				Client client = this.clientDao.getClientById(id);
				if (client == null) {
						throw new ClientNotFoundException("Client with id " + id + " was not found, and therefore we cannot delete that Client");
						
				}
				
				// Delete all accounts that belong to that client
				// Get all accounts that belong to the client
				this.accountDao.deleteAllAccountsByClientId(id);
				
				// Deleting all the accounts that belong to that client ensures that we can actually delete the client
				this.clientDao.deleteClientById(id);
		}catch(NumberFormatException e) {
			throw new InvalidParameterException("Id supplied is not an int");
		}
		
	}

}
