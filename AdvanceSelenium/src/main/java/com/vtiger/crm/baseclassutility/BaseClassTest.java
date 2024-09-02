package com.vtiger.crm.baseclassutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vtiger.crm.generic.databaseutility.DatabaseUtility;
import com.vtiger.crm.generic.fileutilities.ExcelUtility;
import com.vtiger.crm.generic.fileutilities.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.UtilityClassObject;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;

public class BaseClassTest {
	public WebDriver driver;
	public static WebDriver edriver = null;
	public DatabaseUtility dblib= new DatabaseUtility();
	public FileUtility flib= new FileUtility();
	public WebDriverUtility wdulib= new WebDriverUtility();
	public ExcelUtility elib= new ExcelUtility();
	public JavaUtility jlib= new JavaUtility();

	@BeforeSuite(groups = {"Smoke Test","Regression Test"})
	public void configBS() throws Throwable {
		System.out.println("==connect to DB & Report config");
		dblib.getConnection();
	}
	
	
	/* @Parameters("Browser") */
	@BeforeClass(groups = {"Smoke Test","Regression Test"})
	public void configBC(/* String browsers */) throws Throwable {
		System.out.println("==open the browser==");
		
		String browser = /*browsers;*/
				flib.getDatafromPropertiesFile("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}else {
			driver= new ChromeDriver();
			
		}
		
		wdulib.waitUntilThePageLoad(driver);
		edriver= driver;
		UtilityClassObject.setdriver(driver); 
		
	}

	@BeforeMethod(groups = {"Smoke Test","Regression Test"})
	public void configBM() throws Throwable {
		System.out.println("==login to app==");
		//Thread.sleep(3000);
		LoginPage lplib= new LoginPage(driver);
		String url = flib.getDatafromPropertiesFile("url");
		String username = flib.getDatafromPropertiesFile("username");
		String password = flib.getDatafromPropertiesFile("password");
		lplib.loginToApp(url,username, password);
		
	}

	@AfterMethod(groups = {"Smoke Test","Regression Test"})
	public void configAM(){
		System.out.println("==logout of app==");
		HomePage hplib= new HomePage(driver);
		hplib.logout(driver);
		
	}

	@AfterClass(groups = {"Smoke Test","Regression Test"})
	public void configAC(){
		System.out.println("==close the browser");
		//driver.close();
		wdulib.closeBrowser(driver);
	}

	@AfterSuite(groups = {"Smoke Test","Regression Test"})
	public void configAS(){
		System.out.println("==disconnect to DB & Report backup");
		dblib.closeConnection();
	}
	
	
}
