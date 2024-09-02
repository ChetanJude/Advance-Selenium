package com.vtiger.crm.orgtest;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.vtiger.crm.generic.fileutilities.ExcelUtility;
import com.vtiger.crm.generic.fileutilities.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationpage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

public class Working_with_CreationAndVerificationOfOrgNameTest {

	@Test
	public void orgName() throws Throwable {

		/*create object*/
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		WebDriverUtility wlib= new WebDriverUtility();


		//Extracting data from property file
		String browser = flib.getDatafromPropertiesFile("browser");
		String url = flib.getDatafromPropertiesFile("url");
		String username = flib.getDatafromPropertiesFile("username");
		String password = flib.getDatafromPropertiesFile("password"); 

		//Extracting OrgName from excel
		JavaUtility jlib= new JavaUtility();
		String orgName = elib.getDataFromExcelFile("NewOrg", 1, 2)+jlib.getRandomNumber();

		//Opening the browser
		WebDriver driver;
		if(browser.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}else {
			driver= new ChromeDriver();
		}

		//Login to App
		LoginPage llib= new LoginPage(driver);
		HomePage hlib = new HomePage(driver);
		driver.get(url);
		wlib.waitUntilThePageLoad(driver);
		wlib.maximizePage(driver);
		llib.loginToApp(url, username, password);

		//Navigate to Organization module
		hlib.getOrglink().click();
		//driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();

		//Click on "createOrganization" Button
		OrganizationsPage olib= new OrganizationsPage(driver);
		olib.getCreateNewOrgbtn().click();
		//driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//Enter all the details & create new Organization
		CreatingNewOrganizationpage clib= new CreatingNewOrganizationpage(driver);
		clib.createOrg(orgName);
		//driver.findElement(By.name("accountname")).sendKeys(data);
		//driver.findElement(By.name("notify_owner")).click(); checkbox


		//try {
		//clib.getSavebtn().click();
		//Verify Header Msg Expected Result
		OrganizationInformationPage oiplib = new OrganizationInformationPage(driver);
		String orgnamehd = oiplib.getHeadermsg().getText();
		String orgnam = oiplib.getOrgNo().getText();

		if(orgnamehd.trim().contains(orgName)) {
			System.out.println(" new org "+orgnamehd+" is create ==== pass");
		}else
		{
			System.out.println(" new org "+orgnamehd+" is not create ==== failed");
		}
		if(orgnam.contains(orgName)) {
			System.out.println(" new org "+orgnam+" is created ==== pass");
		}
		else
		{
			System.out.println(" new org "+orgnam+" is not create ==== failed");
		}
		//		}catch (Exception e) {
		//			Alert alertText = driver.switchTo().alert();
		//			String text1 = alertText.getText();
		//			alertText.accept();
		//			System.out.println("New organsiaction is not created as " + text1 + " -Please enter different Organsiaction Name-");
		//		}

		//Logout
		hlib.logout(driver);
		//		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		driver.quit();
	}

}
