package com.vtiger.crm.orgtest;

import java.time.Duration;

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

public class Working_with_CreationAndVerificationOfNumberWithNewOrgTest {

	@Test
	public void number() throws Throwable, Throwable {
		
		/*create object*/
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		
		String browser = flib.getDatafromPropertiesFile("browser");
		String url = flib.getDatafromPropertiesFile("url");
		String username = flib.getDatafromPropertiesFile("username");
		String password = flib.getDatafromPropertiesFile("password");

		//Get date from excelutility or TestScript
		String orgname = elib.getDataFromExcelFile("NewOrg", 4, 2)+jlib.getRandomNumber();

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

		WebDriverUtility wbulib= new WebDriverUtility();
		LoginPage lplib= new LoginPage(driver);
		HomePage hplib= new HomePage(driver);
		OrganizationsPage oplib= new OrganizationsPage(driver);
		CreatingNewOrganizationpage cnoplib= new CreatingNewOrganizationpage(driver);
		OrganizationInformationPage oiplib= new OrganizationInformationPage(driver);
		
		wbulib.maximizePage(driver);
		wbulib.waitUntilThePageLoad(driver);
		driver.get(url);
		lplib.loginToApp(url, username, password);
		hplib.getOrglink().click();
		oplib.getCreateNewOrgbtn().click();
		
		String phone = elib.getDataFromExcelFile("NewOrg", 7, 5);
		cnoplib.createOrg(orgname, phone);
		

		//try {
			//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			String phone1 = oiplib.getPhoneEdt().getText();

			if(phone1.contains(phone)) {
				System.out.println(phone +"=== Org with phone number is created ==== pass");
			}else
			{
				System.out.println(phone +"==== Org with phone number is not created ==== failed");
			}
//		}catch (Exception e) {
//			Alert alertText = driver.switchTo().alert();
//			String text1 = alertText.getText();
//			alertText.accept();
//			System.out.println("New organsiaction is not created as " + text1 + " -Please enter different Organsiaction Name-");
//		}
		
			hplib.logout(driver);
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		driver.quit();
		
	}
}
