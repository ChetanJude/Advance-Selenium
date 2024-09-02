package com.vtiger.crm.contacttest;

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
import com.vtiger.crm.objectrepositoryutility.ContactInformationPage;
import com.vtiger.crm.objectrepositoryutility.ContactPage;
import com.vtiger.crm.objectrepositoryutility.CreateNewContactPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;

public class Working_with_VerificationOfNewContactWithSupportDateTest {

	@Test
	public void contact() throws Throwable {
		
		/*create object*/

		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();

		//Getting data from properties or file utility
		String browser = flib.getDatafromPropertiesFile("browser");
		String url = flib.getDatafromPropertiesFile("url");
		String username = flib.getDatafromPropertiesFile("username");
		String password = flib.getDatafromPropertiesFile("password");

		String lastname = elib.getDataFromExcelFile("Contact", 1, 2)+jlib.getRandomNumber();

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

		WebDriverUtility wdulib = new WebDriverUtility();
		wdulib.maximizePage(driver);
		wdulib.waitUntilThePageLoad(driver);
		driver.get(url);
		
		LoginPage lplib= new LoginPage(driver);
		lplib.loginToApp(url, username, password);
//		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
//		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
//		driver.findElement(By.id("submitButton")).click();
		
		HomePage hplib= new HomePage(driver);
		hplib.getContactlink().click();
//		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		
		ContactPage cplib= new ContactPage(driver);
		cplib.getCreatebtn().click();
//		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		CreateNewContactPage cncplib= new CreateNewContactPage(driver);
		cncplib.getLastnameEtd().sendKeys(lastname);
//		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		String actdate = jlib.getSystemDateYYYYMMDD();
		String daterequired = jlib.getSystemDatewithAdditionalDate(30);
		
		//Support start date
		driver.findElement(By.id("jscal_field_support_start_date")).clear();
		driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(actdate);
		
		Thread.sleep(3000);
		
		//Support end date
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(daterequired);
			
		//try {
		cncplib.getSavebtn().click();
			//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		ContactInformationPage ciplib= new ContactInformationPage(driver);
		
			String startdate1 = ciplib.getStartdate().getText();
			String enddate1 = ciplib.getEndate().getText();

			if(startdate1.contains(actdate)) {
				System.out.println("new contact with support start date "+actdate+" is created ==== pass");
			}else
			{
				System.out.println("new contact with support start date "+actdate+"not created ==== failed");
			}
			
			if(enddate1.contains(daterequired)) {
				System.out.println("new contact with support end date "+daterequired+" is created ==== pass");
			}else
			{
				System.out.println("new contact with support end date "+daterequired+" is not created ==== failed");
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
