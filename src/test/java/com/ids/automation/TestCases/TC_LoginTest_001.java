package com.ids.automation.TestCases;



import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.ids.automation.pageObjects.LoginPage; 



public class TC_LoginTest_001  extends BaseClass{ 
	
	@Test
	public void loginTest() throws Exception 
	{
		LoginPage lp=new LoginPage(driver);
		//driver.get("http://localhost/Spandan_Imagine_HIS/");
		logger.info("URL is lounched");
		//Thread.sleep(1000);		
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickbtn();
		
		/*//Thread.sleep(500);
		(driver.findElement(By.xpath("//input[@id='btnOPD']"))).click();
		Thread.sleep(500);
		(driver.findElement(By.xpath("//li[@id='btnAdd']"))).click();
		//Thread.sleep(500);
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='ddlVisitType']")));
		List<WebElement> visitType = dropdown.getOptions();
		(driver.findElement(By.xpath("//span[@id='select2-chosen-10']"))).click();
		//(driver.findElement(By.xpath("//b[@role='presentation'][2]"))).click();
		//System.out.println((driver.findElement(By.xpath("//ul[@id='select2-results-10']/li[1]/div/span"))).getText();
		//List <WebElement> visitType=driver.findElements(By.xpath("//ul[@id='select2-results-10']/li"));
		System.out.println(visitType.size());
		for (int i = 0; i < visitType.size(); i++) {
	        System.out.println(visitType.get(i).getText());

	    }
		
		*/
		
	
		WebElement welcomestring= driver.findElement(By.xpath("//label[@id='lblWelcome']")); 
		
	
	if(welcomestring.isDisplayed())
	{
		Assert.assertTrue(true);
	logger.info("Login Test case is pass");
		
	}
	
	else
		{
			Assert.assertTrue(false);
			logger.info("Login Test case is fail");
			captureScreen(driver,"Login");
		}
		/*if(driver.getTitle().equals("Home"))
		{
			Assert.assertTrue(true);
			logger.info("Login Test case is pass");
		}
		else
		{
			Assert.assertTrue(false);
			logger.info("Login Test case is fail");
		}*/
		
		
	}
	

}
