package com.ids.automation.TestCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ids.automation.utilities.ReadConfig;


public class BaseClass {
	
	ReadConfig config=new ReadConfig();

	//public String baseUrl="//https://demo.guru99.com/v4/";
	public String baseUrl=config.geturl();
	//public String userName=config.getuserName();
	//public String password=config.getPassword();
	public String userName="admin";
	public String password="adminedp";
	public static WebDriver driver;
	public static Logger logger;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentlogger;
	public Actions act;

	
	@Parameters("browser")
	@BeforeClass()
	public void setup(String br) throws InterruptedException 
	{
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-allow-origins=*");
//driver = new ChromeDriver(options);
		logger=logger.getLogger("IDS Automation");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{

		System.setProperty("webdriver.chrome.driver", config.getChromePath());
		driver=new ChromeDriver();
		}
		
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver",config.getEdgePath());
			driver=new EdgeDriver();
		}
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		act=new Actions(driver);
		Thread.sleep(1000);		
		
		
		/*System.setProperty("webdriver.edge.driver", "E:\\automation\\Workspace\\com.netbanking.project\\Drivers\\msedgedriver.exe");
		driver=new EdgeDriver();
		//driver.get("https://mvnrepository.com/artifact/log4j/log4j/1.2.17");*/
		}
	
	//@AfterClass
	public void teardown() 
	{
		//extent.flush();
		driver.close();
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File("./Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	@BeforeSuite
	public void onTestSart(ITestContext testContext)
	{
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
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
		extentlogger=extent.createTest(tr.getTestName());
		extentlogger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		System.out.println("Test is passed");
		
	}
	
	public void onTestFailure(ITestResult tr)
	{
		extentlogger=extent.createTest(tr.getTestName());
		extentlogger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		String ssPath="E:\\automation\\Workspace\\com.netbanking.project\\Screenshots\\"+tr.getName()+".png";
		File f=new File(ssPath);
		if(f.exists())
		{
		try {
			extentlogger.fail("Screenhost is below: "+ extentlogger.addScreenCaptureFromPath(ssPath));
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
		extentlogger=extent.createTest(tr.getName());
		extentlogger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		System.out.println("Test is skipped");
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Finally.......");

	}

}
