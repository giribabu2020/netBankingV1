package com.netBanking.TestCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.netBanking.PageObjects.LoginPage;
import com.netBanking.Utilities.*;

public class TC_LoginTest_001 extends Base{

	
	@Test
	public void LoginTest() throws IOException
	{
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		logger.info("user id and password is provided");
		
		lp.clickSubmit();
		logger.info("submit button is clicked");
		if(driver.getTitle().equals("GTPL Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("login test passed");
		}
		else
		{
			captureScreenshot(driver,"LoginTest");
			Assert.assertTrue(false);
			logger.info("login test failed");
			
			
		}
		
	}
	
}
