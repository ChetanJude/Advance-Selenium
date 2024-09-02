package com.vtiger.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	
	/*
	 * need to share the extenttest variable(object = test) so using threadlocal and
	 * using extenttest- -specific which kind of object is created
	 */
	
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	
	/* same as above */
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static ExtentTest getTest() {
		return test.get(); 
		/* get() is from the threadlocal which will give multiple access to test */
	}
	
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
		/* Set(actTest) is from the threadlocal which will set the test variable */
	}

	public static WebDriver getdriver() {
		return driver.get(); 
		/* get() is from the threadlocal which will give multiple access to driver */
	}
	
	public static void setdriver(WebDriver actDriver) {
		driver.set(actDriver);
		/* Set(actTest) is from the threadlocal which will set the driver variable */
	}
}
