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
		
		Thread.sleep(10000);                           
		
		WebElement addInput1 = driver.findElement(By.id("addnumber1"));         
		WebElement addInput2 = driver.findElement(By.id("addnumber2"));         
		WebElement addButton = driver.findElement(By.id("addButton"));                  
		
		
		addInput1.sendKeys("10.5");         
		addInput2.sendKeys("15.3");         
		addButton.click();                   
		
		driver.switchTo().frame("result");                   
		
		WebElement addOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of adding 10.5 and 15.3 is " + addOutput.getText());
		
		driver.switchTo().defaultContent();
		
		
		driver.quit();
	}
}
