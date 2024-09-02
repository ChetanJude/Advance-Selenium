package com.vtiger.crm.ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.crm.baseclassutility.BaseClassTest;
import com.vtiger.crm.generic.webdriverutility.UtilityClassObject;

public class ListenerImplimentationClass implements ITestListener, ISuiteListener {
	
	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onStart(suite);
		String time= new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/repor_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Script Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		
		/* test.log(Status.INFO, "Report Started"); */
		System.out.println("====> Isuite is Started <====");
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onFinish(suite);
		
		report.flush();
		
		test.log(Status.INFO, "Report Backup");
		System.out.println("====> Isuite is completed <====");
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		UtilityClassObject.setTest(test); 
		test.log(Status.INFO, result.getMethod().getMethodName()+"======> Started <=======" );
		System.out.println(result.getMethod().getMethodName()+"======> Started <=======");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test.log(Status.PASS, result.getMethod().getMethodName()+"======> Completed <=======" );
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String time= new Date().toString().replace(" ", "_").replace(":", "_");
		
		TakesScreenshot tss= (TakesScreenshot) BaseClassTest.edriver;
		String scr = tss.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(scr, testname+" "+time);
		
		test.log(Status.FAIL, result.getMethod().getMethodName()+"======> Failed <=======" );
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
