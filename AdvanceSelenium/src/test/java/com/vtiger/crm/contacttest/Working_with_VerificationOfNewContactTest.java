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

public class Working_with_VerificationOfNewContactTest {

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


		String contactname = elib.getDataFromExcelFile("Contact", 1, 2)+jlib.getRandomNumber();

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

		WebDriverUtility wdulib= new WebDriverUtility();
		wdulib.maximizePage(driver);
		wdulib.waitUntilThePageLoad(driver);
		
		driver.get(url);
		
		LoginPage lplib= new LoginPage(driver);
		lplib.loginToApp(url, username, password);
		
		HomePage hplib= new HomePage(driver);
		hplib.getContactlink().click();
		
		ContactPage cplib= new ContactPage(driver);
		cplib.getCreatebtn().click();
		
		CreateNewContactPage cncplib= new CreateNewContactPage(driver);
		
		cncplib.createcontact(contactname);

		//try {
			//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		ContactInformationPage ciplib= new ContactInformationPage(driver);
			String lastname = ciplib.getLastname().getText();

			if(lastname.contains(contactname)) {
				System.out.println("new contact with "+contactname+" is created ==== pass");
			}else
			{
				System.out.println("new contact with"+contactname+"not created ==== failed");
			}
//		}catch (Exception e) {
//			Alert alertText = driver.switchTo().alert();
//			String text = alertText.getText();
//			alertText.accept();
//			System.out.println("Please handle the exception"+text);
//		}

		hplib.logout(driver);
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		driver.quit();

	}
}
