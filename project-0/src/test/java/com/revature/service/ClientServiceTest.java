package com.revature.service;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.revature.dao.ClientDAO;
import com.revature.dto.AddOrUpdateClientDTO;
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.ClientNotFoundException;
import com.revature.model.Client;


public class ClientServiceTest {
	
	//Define the System under test (SUT): ClientService
	private ClientService sut;
	
	
	/*
	 * ClientService's getAllClients() test
	 */
	
	@Test
	public void testGetAllClientsPositive() throws SQLException {
		
		
		/*
		 * ARRANGE
		 */
		
		
		// ClientService requires a ClientDAO object to funtion properly 
		// Mocking a ClientDAO object
		ClientDAO mockClientDao = mock(ClientDAO.class); // this is a fake object, because we're not using "new<<constructor>())", we are using
		// mock from Mockito that creates a fake object that we can specify scenarios for, whenever we call that object's instance methods
		
		Client client1 = new Client(12, "Joe", "Goldberg", "Savings", 5500);
		Client client2 = new Client(15, "Brandon", "Path", "Checking", 10000);
		Client client3 = new Client(35, "Justin", "Gomez", "Savings", 50000);
		
		List<Client> clientsFromDao = new ArrayList<>();
		clientsFromDao.add(client1);
		clientsFromDao.add(client2);
		clientsFromDao.add(client3);
		
		when(mockClientDao.getAllClients()).thenReturn(clientsFromDao);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		/*
		 * ACT
		 */
		
		List<Client> actual = clientService.getAllClients();
	
		/*
		 * ASSERT
		 */
	
		List<Client> expected = new ArrayList<>();
		expected.add(new Client(12, "Joe", "Goldberg", "Savings", 5500));
		expected.add(new Client(15, "Brandon", "Path", "Checking", 10000));
		expected.add(new Client(35, "Justin", "Gomez", "Savings", 50000));
		
		Assertions.assertEquals(expected, actual);
	
	
	}
	//Negative Test
	@Test 
	public void testGetAllClientsSQLExceptionOccursNegative() throws SQLException {
		/*
		 * ARRANGE 
		 */
		ClientDAO mockClientDao = mock(ClientDAO.class);
		
		when(mockClientDao.getAllClients()).thenThrow(SQLException.class);
		
		ClientService clientService = new ClientService(mockClientDao);
		
		/*
		 *  ACT AND ASSERT
		 */
		
		// Our test will pass, if the code inside the second argument's lambda expression throws a SQLExcpetion
		// If no exception occurs, then the test will fail 
		Assertions.assertThrows(SQLException.class, () -> {
			
			clientService.getAllClients(); // We actually want for SQLException to emanate from our clientService getAllClients
			
			
		});
		
	}
		
		// Positive test (happy path)
		@Test
		public void testGetClientByIdPositive() throws SQLException, InvalidParameterException, ClientNotFoundException {
			/*
			 * ARRANGE
			 */
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			when(mockClientDao.getClientById(eq(4))).thenReturn(new Client(4, "Cory", "Hall", "Savings", 10000));
			
			ClientService clientService = new ClientService(mockClientDao);
			
			/*
			 * ACT 
			 */
			Client actual = clientService.getClientById("4");
			
			/*
			 * ASSERT
			 */
			
			Assertions.assertEquals(new Client(4, "Cory", "Hall", "Savings", 10000), actual);
			
		}
		
		//Negative Test
		// ClientNotFoundException is thrown 
		@Test
		public void testGetClientByIdNotFoundNegative() throws SQLException, InvalidParameterException, ClientNotFoundException {
			/*
			 * ARRANGE 
			 */
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			//By default, any object returned from one of the methods called from the mock client dao will return null 
			//So here we are not specifying any when(...).thenReturn(...);
			
			ClientService clientService = new ClientService(mockClientDao);
			
			/*
			 * ACT & ASSERT 
			 */
			
			Assertions.assertThrows(ClientNotFoundException.class, () -> {
				clientService.getClientById("1"); // ACT 
			
			});
			
			
		}
		//Negative test
		// InvalidParameterException is thrown 
		@Test 
		public void testGetClientByIdAlphabeticalIdNegative() {
			/*
			 * ARRANGE 
			 */
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			//By default, any object returned from one of the methods called from the mock client dao will return null 
			//So here we are not specifying any when(...).thenReturn(...);
			
			ClientService clientService = new ClientService(mockClientDao);
			
			/*
			 * ACT AND ASSERT
			 */
			
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				clientService.getClientById("abc"); //ACT 
			
			});
			
			
			
		}
		
		//Negative Test
		//InvalidParameterException is thrown
		@Test
		public void testGetClientByIdDecimalIdNegative() {
			/*
			 * ARRANGE
			 */
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			//By default, any object returned from one of the methods called from the mock client dao will return null 
			//So here we are not specifying any when(...).thenReturn(...);
			
			ClientService clientService = new ClientService(mockClientDao);
			
			/*
			 * ACT & ASSERT
			 */
			
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				clientService.getClientById("1.0"); // ACT 
				
			});
			
		}
		
		/*
		 *  ClientServices's editFirstName(String clientId, String changedClient
		 */
		
		//Positive (happy path)
		@Test
		public void tesEditFirstNameOfClientPositive() throws SQLException, ClientNotFoundException, InvalidParameterException {
			/*
			 * ARRANGE
			 */
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			when(mockClientDao.getClientById(eq(2))).thenReturn(new Client(2, "Steve", "Jobs", "Savings", 100000));
			
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Steven", "Jake", "Savings", 100000);
			when(mockClientDao.updateClient(eq(2), eq(dto))).thenReturn(new Client(2, "Steven", "Jake", "Savings", 100000));
			
			ClientService clientService = new ClientService(mockClientDao);
			
			/*
			 * ACT
			 */
			
			Client actual = clientService.editClientById(2, dto);
			
			/*
			 * ASSERT 
			 */
			
			Client expected = new Client(2, "Steven", "Jake", "Savings", 100000);
			
			Assertions.assertEquals(expected, actual);
			
			}
		
		//Negative
		// ClientNotFoundException case
		@Test
		public void testEditFirstNameOfClientButWithClientWithId10DoesNotExist() {
			/*
			 * ARRANGE
			 */
			ClientDAO mockClientDao = mock(ClientDAO.class);
			
			//mocked methods that return objects will return null by default
			//so we dont need to worry about when(...).thenReturn(null);
			
			ClientService clientService = new ClientService(mockClientDao);
			
			/*
			 * ACT & ASSERT
			 */
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO();
			Assertions.assertThrows(ClientNotFoundException.class, () -> {
				
				clientService.editClientById(10, dto); //ACT
			
			});
		
		}
		
		//Negative 
		//InvalidParameterException thrown
//		@Test
//		public void testEditFirstNameButIdProvidedIsNotAnInt() { 
//			/*
//			 * ARRANGE 
//			 */
//			ClientDAO mockClientDao = mock(ClientDAO.class);
//			
//			ClientService clientService = new ClientService(mockClientDao);
//			
//			/*
//			 * ACT & ASSERT
//			 */
//			
//			Assertions.assertThrows(InvalidParameterException.class, () -> {	
//				
//				clientService.editClientById("asdjkfhdkj", "Test"); //ACT
//				
//			});
//		}
		
		/*
		 * ClientService's addClient(AddOrUpdateClientDTO) dto method
		 */
		
		//Positive (Happy path)
		@Test 
		public void testAddClientAllInformationCorrectInDTO() throws InvalidParameterException, SQLException {
			/*
			 * ARRANGE 
			 */
			ClientDAO clientDao = mock(ClientDAO.class);
			
			AddOrUpdateClientDTO dtoIntoDao = new AddOrUpdateClientDTO("Micheal", "Jackson", "Savings", 10000);
			
			when(clientDao.addClient(eq(dtoIntoDao))).thenReturn(new Client(13, "Micheal", "Jackson", "Savings", 10000));
			
			ClientService clientService = new ClientService(clientDao);
			
			/*
			 * ACT
			 */
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Micheal", "Jackson", "Savings", 10000);
			Client actual = clientService.addClient(dto);
			
			/*
			 * ASSERT 
			 */
			Client expected = new Client(13, "Micheal", "Jackson", "Savings", 10000);
			Assertions.assertEquals(expected, actual);
		
			
		}
		
		//Negative
		//Scenario: Everything is correct except the firstName was left blank
		@Test 
		public void testAddClientFirstNameBlankEverythingElseValid() throws InvalidParameterException, SQLException {
			/*
			 * ARRANGE
			 */
			ClientDAO clientDao = mock(ClientDAO.class);
			
			ClientService clientService = new ClientService(clientDao);
			
			/*
			 * ACT and ASSERT
			 */
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("    ", "Hall,", "Savings", 10000);
			
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				
				clientService.addClient(dto);
			
			});
			
		}
		
		//Negative 
		//Scenario: everything is correct except the lastName was left blank
		@Test
		public void testAddClientLastNameBlankEverythingElseValid() throws InvalidParameterException, SQLException {
			/*
			 * ARRANGE
			 */
			ClientDAO clientDao = mock(ClientDAO.class);
					
			ClientService clientService = new ClientService(clientDao);
			
			/*
			 * ACT and ASSERT
			 */
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Sam", "     ", "Savings", 10000);
			
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				
				clientService.addClient(dto);
				
			});
		
		}
		
		public void testAddClientFirstNameAndLastNameBlankEverythingElseValid() throws InvalidParameterException, SQLException {
			/*
			 * ARRANGE
			 */
			ClientDAO clientDao = mock(ClientDAO.class);
					
			ClientService clientService = new ClientService(clientDao);
			
			/*
			 * ACT and ASSERT
			 */
			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("       ", "     ", "Savings", 10000);
			
			Assertions.assertThrows(InvalidParameterException.class, () -> {
				
				clientService.addClient(dto);
				
			});
		
		
	}
		
		//Negative 
		//Scenario: everything is correct except accountType was invalidly spelled
		@Test 
		public void testAddClientSavingsSpelledIncorrectlyEverythingElseValid() throws InvalidParameterException, SQLException {
				/*
				 * ARRANGE
				 */
				ClientDAO clientDao = mock(ClientDAO.class);
						
				ClientService clientService = new ClientService(clientDao);
				
				/*
				 * ACT and ASSERT
				 */
				AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Sam", "Smith", "      ", 10000);
				
				Assertions.assertThrows(InvalidParameterException.class, () -> {
					
					clientService.addClient(dto);
					
				});
		
		
		
		//Negative 
		//Scenario: everything is correct except balance is letters
//		@Test
//		public void testAddClientBalanceIsLettersEverythingElseIsValid() throws NumberFormatException, InvalidFormatException, SQLException {
//			 /*
//			 * ARRANGE
//			 */
//			ClientDAO clientDao = mock(ClientDAO.class);
//					
//			ClientService clientService = new ClientService(clientDao);
//			
//			/*
//			 * ACT and ASSERT
//			 */
//			String error = "dslfjk";
//			int value = Integer.parseInt(error);
//			
//			
//			AddOrUpdateClientDTO dto = new AddOrUpdateClientDTO("Sam", "Smith", "Savings", value);
//			
//			Assertions.assertThrows(InvalidFormatException.class, () -> {
//				
//				clientService.addClient(dto);
//				
//			});
	
		
		
	}	
	

	
}

