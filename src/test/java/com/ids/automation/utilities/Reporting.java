package com.ids.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeTest;
import org.testng.reporters.TestHTMLReporter;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporting extends TestListenerAdapter implements ITestListener
{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	/*public void startReport()
	{
		extentreport=new ExtentReports("./test-output\\ExtentReport.html",true);
		extentreport.addSystemInfo("Host Name", "Localhost");
		extentreport.addSystemInfo("Environment", "Test");
		extentreport.addSystemInfo("User Name", "VJ");
		extentreport.loadConfig(new File("E:\\automation\\Workspace\\com.netbanking.project\\Extent-Config.xml"));
	}*/
	
	/*public static void main(String Args[][])
	{
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter("extent.html");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		ExtentTest logger=extent.createTest(null)
	}*/
	public void onTestSart(ITestContext testContext)
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		//htmlReporter=new ExtentHtmlReporter("E:\\automation\\Workspace\\com.netbanking.project\\test-output\\IDSEXTENT");
		htmlReporter=new ExtentHtmlReporter("./test-output/"+repName);
		htmlReporter.loadXMLConfig("./Extent-Config.xml");

		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Localhost");
		extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("Environment", "Test");
		
		htmlReporter.config().setDocumentTitle("IDS TEST ROKECT");
		htmlReporter.config().setReportName("Regression Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		

		System.out.println("Test is started");
		
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getTestName());
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		System.out.println("Test is passed");
		
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger=extent.createTest(tr.getTestName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		String ssPath="./Screenshots/"+tr.getName()+".png";
		File f=new File(ssPath);
		if(f.exists())
		{
		try {
			logger.fail("Screenhost is below: "+ logger.addScreenCaptureFromPath(ssPath));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		System.out.println("Test is failed");
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		System.out.println("Test is skipped");
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
