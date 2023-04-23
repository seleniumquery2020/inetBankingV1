package com.inetbanking.testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.AddCustomerPage;
import com.inetbanking.pageObject.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	
	@Test
	public void addNewCustomer() throws InterruptedException {
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("username provided");
		lp.setPassword(passWord);
		logger.info("password provided");
		lp.clicksubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		
		logger.info("proving customer details...");
		
		addcust.custName("Akshay");
		addcust.custgender("male");
		addcust.custdob("18", "07", "1990");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("AMRAVATI");
		addcust.custstate("MAHARASHTRA");
		addcust.custpinno("444601");
		addcust.custtelephoneno("8855999509");
		
		String email = randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		logger.info("Validation Started...");
		boolean res = driver.getPageSource().contains("sonali");
				//("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("Test Case is Passed");
		}else
		{
			logger.info("Test Case is Failed");
			Assert.assertTrue(false);
		}
		
	}
	
	


}
