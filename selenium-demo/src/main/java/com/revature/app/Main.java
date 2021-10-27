package com.revature.app;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.chrome.ChromeDriver;  

public class Main {      
	public static void main(String[] args) throws InterruptedException {         
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");                 
		
		WebDriver driver = new ChromeDriver();         
		driver.get("http://localhost:8080/");
		                          
		
		WebElement addInput1 = driver.findElement(By.id("addnumber1"));         
		WebElement addInput2 = driver.findElement(By.id("addnumber2"));         
		WebElement addButton = driver.findElement(By.id("addButton"));                  
		
		
		addInput1.sendKeys("10.5");         
		addInput2.sendKeys("15.3");         
		addButton.click();                   
		
		driver.switchTo().frame("addresult");                   
		
		WebElement addOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of adding 10.5 and 15.3 is " + addOutput.getText());
		
		driver.switchTo().defaultContent();
		//Subtraction
		WebElement subInput1 = driver.findElement(By.id("subnumber1"));         
		WebElement subInput2 = driver.findElement(By.id("subnumber2"));         
		WebElement addsubButton = driver.findElement(By.id("subButton"));                  
		
		
		subInput1.sendKeys("10.5");         
		subInput2.sendKeys("15.3");         
		addsubButton.click();                   
		
		driver.switchTo().frame("subresult");                   
		
		WebElement subOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of adding 10.5 and 15.3 is " + subOutput.getText());
		
		driver.switchTo().defaultContent();
		//Multiplication
		WebElement multInput1 = driver.findElement(By.id("multnumber1"));         
		WebElement multInput2 = driver.findElement(By.id("multnumber2"));         
		WebElement addmultButton = driver.findElement(By.id("multButton"));                  
		
		
		multInput1.sendKeys("10.5");         
		multInput2.sendKeys("15.3");         
		addmultButton.click();                   
		
		driver.switchTo().frame("multResult");                   
		
		WebElement multOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of adding 10.5 and 15.3 is " + multOutput.getText());
		
		driver.switchTo().defaultContent();
		//Division
		WebElement divInput1 = driver.findElement(By.id("divnumber1"));         
		WebElement divInput2 = driver.findElement(By.id("divnumber2"));         
		WebElement divButton = driver.findElement(By.id("divButton"));                  
		
		
		divInput1.sendKeys("10.5");         
		divInput2.sendKeys("15.3");         
		divButton.click();                   
		
		driver.switchTo().frame("divResult");                   
		
		WebElement divOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of adding 10.5 and 15.3 is " + divOutput.getText());
		
		driver.switchTo().defaultContent();


		Thread.sleep(5000);
		
		driver.quit();
	}
}
