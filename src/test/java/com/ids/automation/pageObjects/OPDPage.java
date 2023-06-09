package com.ids.automation.pageObjects;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;

import com.ids.automation.TestCases.BaseClass;



public class OPDPage extends BaseClass {
	public String searchmrn;
	public String mrn;
	WebDriver ldriver;
	public OPDPage(WebDriver driver)
	{
		ldriver=driver;
		PageFactory.initElements(driver, this);
		act=new Actions(ldriver);
	}
	
	//WebElement OPDButton=ldriver.findElement(By.xpath("//INPUT[@ID='btnOPD']"));
	@FindBy(xpath="//INPUT[@ID='btnOPD']")
	public WebElement OPDButton;
	
	@FindBy(xpath="//li[@ID='btnAdd']")
	public WebElement AddButton;
	
	@FindBy(xpath="//button[@ID='btnSearchPatientOPD']")
	public WebElement SearchButton;
	
	@FindBy(xpath="//input[@ID='FilterText']")
	public WebElement SearchText;
	
	@FindBy(xpath="//button[@ID='btnGetPatients']")
	public WebElement SearchPatientButton;
	
	@FindBy(xpath="//TABLE[@id='tblSearchGrid']/tbody/tr[1]/td[1]")
	//@FindBy(xpath="//table[@id='tblSearchGrid']")
	public WebElement PatientTableRow1;
	
	@FindBy(xpath="//li[@id='btnSave']")
	public WebElement SaveBtn;
	
	public void goToOPD()
	{
		OPDButton.click();
		//driver.findElement(By.xpath("//INPUT[@ID='btnOPD']")).click();
	}
	
	public void OPDNew(String patientName) throws Exception
	{
		AddButton.click();
		SearchButton.click();
		Thread.sleep(500);
		SearchText.sendKeys(patientName);
		Thread.sleep(200);
		SearchPatientButton.click();
		Thread.sleep(1000);
		//PatientTableRow1.click();
		 searchmrn=(driver.findElement(By.xpath("//TABLE[@id='tblSearchGrid']/tbody/tr[1]/td[2]"))).getText();
		act.moveToElement(PatientTableRow1).build().perform();
		act.doubleClick(PatientTableRow1).build().perform();
		SaveBtn.click();
		Thread.sleep(1000);
		driver.navigate().back();
		mrn=(driver.findElement(By.xpath("//TABLE[@id='tblGrid']/tbody/tr[1]/td[1]"))).getText(); 
		
		//Thread.sleep(1000);
		//driver.findElement(By.xpath("//li[@ID='btnAdd']")).click();
	}
	
	/*public void searchPatient(String patientName) throws Exception
	{
		SearchButton.click();
		Thread.sleep(500);
		SearchText.sendKeys(patientName);
		Thread.sleep(200);
		SearchPatientButton.click();
		Thread.sleep(3000);
		//PatientTableRow1.click();
		act.moveToElement(PatientTableRow1).build().perform();
		act.doubleClick(PatientTableRow1).build().perform();
		//Thread.sleep(1000);
		
	}*/

	

}
