package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testCases.BaseClass;

public class Reporting extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Time satmp
		String repName = "Test-Report-"+timeStamp+".html";
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+repName); //specify location
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "local host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "pavan");
		
		htmlReporter.config().setDocumentTitle("InetBanking Test Project");
		htmlReporter.config().setReportName("Functional Test Report"); // Name of the report
		//htmlReporter.config().setTestViewChartlocation(ChartLocation.TOP); // location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); // to create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));// send the passed info
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName()); // to create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));// send the passed info
		BaseClass.captureScreen(tr.getName());
		String screenshotpath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotpath);
		if(f.exists())
		{
			try {
				logger.fail("Screenshot is below" + logger.addScreenCaptureFromPath(screenshotpath));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));// send the passed info
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		}

}
