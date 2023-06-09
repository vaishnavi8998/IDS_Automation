package com.ids.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReadConfig {
	public Properties pro;
	
public ReadConfig () 
{
	
	
	File src= new File("./Configuration/Config.properties");
	try {
		FileInputStream fis;
	
		fis = new FileInputStream(src);
	
	pro=new Properties();
	pro.load(fis);
	}
	catch (Exception e) {

	System.out.println(	"Exception is "+e.getMessage());
	}
}

 public String geturl()
 {
	String url= pro.getProperty("baseUrl");
	return url;
 }
 
 public String getuserName()
 {
	 String userName=pro.getProperty("userName") ;
	 return userName;
			 
 }
 
 public String getPassword()
 {
	 String password=pro.getProperty("password") ;
	 return password;
			 
 }
 
 public  String getChromePath()
 {
	 String chromePath=pro.getProperty("chromedriverpath") ;
	 return chromePath;
			 
 }
 
 public String getEdgePath()
 {
	 String edgePath=pro.getProperty("edgedriverpath") ;
	 return edgePath;
			 
 }
/* public static void main (String Args[])
	{
		System.setProperty("webdriver.chrome.driver","E:\\automation\\Workspace\\com.netbanking.project\\Drivers\\chromedriver.exe");
		driver=new ChromeDriver();
	}	*/
 
}
