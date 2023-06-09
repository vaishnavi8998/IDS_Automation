 package com.ids.automation.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.*;

import com.ids.automation.TestCases.BaseClass;



//https://demo.guru99.com/v4/
public class LoginPage extends BaseClass {
	
	  WebDriver ldriver;
	public LoginPage(WebDriver driver)
	{
		 ldriver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElement txtUsername=driver.findElement(By.name("uid"));
	//WebElement txtPassword=driver.findElement(By.name("password"));
	//WebElement btnLogin=driver.findElement(By.name("btnLogin"));
	 
	@FindBy(xpath="//input[@id='txtLoginname']")
	 WebElement txtUsername;
	
	@FindBy(xpath="//input[@id='txtPassword']")
	WebElement txtPassword;
	
	@FindBy(xpath="//b[@role='presentation']")
	WebElement barrow;
	
	@FindBy(xpath="//ul[@id='select2-results-2']/li[1]")
	WebElement branch1;
	
	@FindBy(xpath="//ul[@id='select2-results-2']/li[2]")
	WebElement branch2;
	
	@FindBy(xpath="//input[@id='btnSubmit']")
	WebElement btnLogin;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav navbar-left']/li[12]")
	WebElement logoutBtn;
	
	public  void setUserName(String username)
	{
		txtUsername.sendKeys(username);
	}
	
	public  void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void chooseBranch(int branchCode)
	{
		barrow.click();
		if(branchCode==1)
		
			branch1.click();
		else
			branch2.click();
	}
	public void clickbtn()
	{
		btnLogin.click();
	}
	
	public void logout() {
		logoutBtn.click();
	}
	


}
