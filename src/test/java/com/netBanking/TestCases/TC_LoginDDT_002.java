package com.netBanking.TestCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netBanking.PageObjects.LoginPage;
import com.netBanking.Utilities.XLUtils;

public class TC_LoginDDT_002 extends Base{
	
	@Test(dataProvider="LoginData")
	public void LoginDDT(String uname,String pwd) throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		logger.info("ddt user name provided");
		lp.setUserName(uname);
		logger.info("password provided");
		lp.setPassword(pwd);
		logger.info("submit button clicked");
		lp.clickSubmit();
		
		if( isAlertPresent()==true)
		{
			logger.warn("login failed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			captureScreenshot(driver,"DDT");
		}
		else
		{
			logger.info("success");
			Assert.assertTrue(true);
			lp.clickNewCustomer();
			//driver.switchTo().frame("flow_close_btn_iframe");
			//driver.findElement(By.id("closeBtn")).click();
			Thread.sleep(2000);
			lp.clicklogOut();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		    }
		catch(Exception e)
		{
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/netBanking/TestData/LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		System.out.println("rowcolumns"+rownum+colcount);
		String logindata[][] = new String[3][colcount];
		for(int i=1;i<=rownum;i++) 
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return logindata;
	}

}
