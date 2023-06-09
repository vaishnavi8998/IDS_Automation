package com.ids.automation.TestCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ids.automation.pageObjects.LoginPage;
import com.ids.automation.utilities.XLUtils;



public class TC_LoginDDT_001 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void LoginDDT_Test(String userName,String pwd) throws IOException, InterruptedException

	{

		LoginPage lp=new LoginPage(driver);
		logger.info("URL is lounched");
		//Thread.sleep(1000);		
		lp.setUserName(userName);
		logger.info("DDTUserName is provided");
		lp.setPassword(pwd);
		logger.info("DDT password is provided");
		//lp.chooseBranch(2);
		lp.clickbtn();
		
	
		WebElement welcomestring= driver.findElement(By.xpath("//label[@id='lblWelcome']")); 
		//driver.wait(1000);
	
	if(welcomestring.isDisplayed())
	{
		Assert.assertTrue(true);
	logger.info("DDT Login Test case is pass");
	lp.logout();
		
	}
	
	else
		{
			Assert.assertTrue(false);
			logger.warn("DDT Login  Test case is fail");
			captureScreen(driver,"Login");
			driver.switchTo().defaultContent();		
			
		}
	
	//lp.logout();
	

	}
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path="./src/test/java/com/ids/automation/testData/LoginData.xlsx";
		int rowCount=XLUtils.getRowCount(path,"Login");
		int clmCount=XLUtils.getCellCount(path, "Login", 1);
		String loginData[][]=new String[rowCount][clmCount];
		
		for(int i=1;i<=rowCount;i++)
		{
			for(int j=0;j<clmCount;j++)
			{
				loginData[i-1][j]=XLUtils.getCellData(path,"Login", i, j);
			}
		}
		return loginData;
		
	}
	
	

}
