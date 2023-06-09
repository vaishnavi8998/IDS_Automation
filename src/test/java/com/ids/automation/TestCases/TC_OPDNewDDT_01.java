package com.ids.automation.TestCases;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ids.automation.pageObjects.LoginPage;
import com.ids.automation.pageObjects.OPDPage;


public class TC_OPDNewDDT_01 extends BaseClass{
	
	//OPDPage opd=new OPDPage(driver);
	@Test
	public void OPDNew_Test() throws Exception
	{
		OPDPage opd=new OPDPage(driver);
		LoginPage lp=new LoginPage(driver);
		//driver.get("http://localhost/Spandan_Imagine_HIS/");
		logger.info("URL is lounched");
		Thread.sleep(1000);		
		lp.setUserName("admin");
		lp.setPassword("adminedp");
		lp.chooseBranch(2);
		lp.clickbtn();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//INPUT[@ID='btnOPD']")).click();		
				opd.goToOPD();
		opd.OPDNew("test");
		//Thread.sleep(2000);
		//opd.searchPatient("test");
		/*opd.SearchButton.click();
		Thread.sleep(500);
		opd.SearchText.sendKeys("test");
		Thread.sleep(200);
		opd.SearchPatientButton.click();
		Thread.sleep(200);
		act.moveToElement(opd.PatientTableRow1).build().perform();
		act.doubleClick(opd.PatientTableRow1).build().perform();*/
		Thread.sleep(1000);
		if((opd.mrn).equals(opd.searchmrn))
		{
		  System.out.println("True");
			Assert.assertTrue(true);
		logger.info("OPD DDT Test case is pass");
		lp.logout();
			
		}
		
		else
			{
				Assert.assertTrue(false);
				logger.warn("DDT OPD  Test case is fail");
				captureScreen(driver,"Login");
				driver.switchTo().defaultContent();		
				
			}
	}
	
	
}
