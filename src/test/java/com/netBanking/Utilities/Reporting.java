package com.netBanking.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class Reporting  extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+timeStamp+".html";
		//String repName = "netBankingTest-Report.html";
		htmlReporter= new ExtentHtmlReporter(repName);
		htmlReporter.loadXMLConfig(new File (System.getProperty("user.dir")+"\\extent-config.xml"));
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("User","Giri");
		htmlReporter.config().setDocumentTitle("InternetBanking Test Project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		System.out.println("test started");
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
		System.out.println("test success");
		
	}	
		
	public void onTestFailure(ITestResult tr)	
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f = new File(screenshotPath);
		if(f.exists())
		{
			try 
			{
				logger.fail("screenshot is below: "+logger.addScreenCaptureFromPath(screenshotPath));
			}
		catch(Exception e)
			{
			  e.printStackTrace();
		    }
		
		}
		System.out.println("test failed");
	}
		
		public void onTestSkipped(ITestResult tr)
		{
			logger = extent.createTest(tr.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
			
		}
		
		
		public void onFinish(ITestContext testContext)
		{
			
				extent.flush();
				System.out.println("test completed");
				
		}

		
		
		
	}
	


