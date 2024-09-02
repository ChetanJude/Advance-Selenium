package com.vtiger.crm.orgtest;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.vtiger.crm.baseclassutility.*;
import com.vtiger.crm.generic.fileutilities.ExcelUtility;
import com.vtiger.crm.generic.fileutilities.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationpage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

public class Working_with_CreationAndVerificationOfIndustryAndTypeDropdownWithNewOrgTest{

	public static void main(String[] args) {
		System.out.println("i am main method");
	}
	@Test
	public void industryAndType() throws Throwable {
		
		/*create object*/
		
		FileUtility flib= new FileUtility();
		ExcelUtility elib= new ExcelUtility();
		JavaUtility jlib= new JavaUtility();
	
		
        //Getting data from properties or file utility
		String browser = flib.getDatafromPropertiesFile("browser");
		String url = flib.getDatafromPropertiesFile("url");
		String username = flib.getDatafromPropertiesFile("username");
		String password = flib.getDatafromPropertiesFile("password");
		
		//Getting data from Excel or Excel utility
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

		WebDriverUtility wdulib= new WebDriverUtility();
		LoginPage llib= new LoginPage(driver);
		HomePage hlib= new HomePage(driver);
		CreatingNewOrganizationpage cnop = new CreatingNewOrganizationpage(driver);
		OrganizationsPage oplib = new OrganizationsPage(driver);
		OrganizationInformationPage oiplib= new OrganizationInformationPage(driver);
		
		wdulib.maximizePage(driver);
		wdulib.waitUntilThePageLoad(driver);
		driver.get(url);
		llib.loginToApp(url, username, password);
		hlib.getOrglink().click();;
		oplib.getCreateNewOrgbtn().click();
		
		//Getting data from Excel or Excel utility
		String industry = elib.getDataFromExcelFile("NewOrg", 4, 3);
		String type = elib.getDataFromExcelFile("NewOrg", 4, 4);
		cnop.createOrg(orgname,industry,type);	

		
		//try {
			//driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			String industry1 = oiplib.getIndustryEdt().getText();
			String type1 = oiplib.getTypeEdt().getText();

			if(industry1.trim().contains(industry)) {
				System.out.println("new org with industry "+industry+" is create ==== pass");
			}else
			{
				System.out.println("new org with industry "+industry+" not create ==== failed");
			}
			if(type1.contains(type)) {
				System.out.println("new org with type "+type+" created ==== pass");
			}
			else
			{
				System.out.println("new org with type "+type+" not create ==== failed");
			}
			
//		}catch (Exception e) {
//			Alert alertText = driver.switchTo().alert();
//			String text1 = alertText.getText();
//			alertText.accept();
//			System.out.println("New organsiaction is not created as " + text1 + " -Please enter different Organsiaction Name-");
//		}
		
		hlib.logout(driver);

		driver.quit();
		

	}


}
