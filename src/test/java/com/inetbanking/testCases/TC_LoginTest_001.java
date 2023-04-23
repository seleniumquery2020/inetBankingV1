package com.inetbanking.testCases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
		
		@Test
		public void loginTest() throws IOException {
			
			
			logger.info("URL is Opened");
			
			LoginPage lp = new LoginPage(driver);
			lp.setUserName(userName);
			logger.info("UserName Entered");
			lp.setPassword(passWord);
			logger.info("Password Entered");
			
			lp.clicksubmit();
			
			if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
			{
				Assert.assertTrue(true);
				logger.info("Login Test Pass");
			}
			else
			{
				
				Assert.assertTrue(false);
				logger.info("Login Test Fail");
			}
		}
		

	}



