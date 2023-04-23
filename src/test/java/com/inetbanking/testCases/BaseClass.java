package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String userName = readconfig.getUserName();
	public String passWord = readconfig.getPassWord();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("WebDriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("WebDriver.gecko.driver", readconfig.getFireFoxpath());
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(br.equals("msedge"))
		{
			System.setProperty("WebDriver.msedge.driver", readconfig.getMsEdgePath());
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	@AfterClass 
	public void tearDown() {
		
		driver.quit();
		
	}
	public static void captureScreen(String tname){
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\Screenshots\\"+tname+".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot taken");
	}
	public String randomestring()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public String randomeNum()
	{
		String generatedstring2 = RandomStringUtils.randomNumeric(4);
		return(generatedstring2);
	}

	
}
