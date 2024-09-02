package com.vtiger.crm.contacttest;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.vtiger.crm.generic.fileutilities.ExcelUtility;
import com.vtiger.crm.generic.fileutilities.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;

public class Working_with_VerificationOfNewContactWithOrgNameTest {

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
		
		String orgnamee = elib.getDataFromExcelFile("Contact", 7, 3)+jlib.getRandomNumber();
		String contactlastname = elib.getDataFromExcelFile("Contact", 1, 2)+jlib.getRandomNumber();
				
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

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgnamee);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(contactlastname);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windid = it.next();
			driver.switchTo().window(windid);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains("module+Accounts"))
				break;
		}
		driver.findElement(By.name("search_text")).sendKeys(orgnamee);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgnamee+"']")).click();
		
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set1.iterator();
		while(it1.hasNext()) {
			String windid = it1.next();
			driver.switchTo().window(windid);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains("Contacts&parent"))
				break;
		}
		
		
		//try {
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			String lastname = driver.findElement(By.id("dtlview_Last Name")).getText();
			String orgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();

			if(lastname.contains(contactlastname)) {
				System.out.println("new contact with "+contactlastname+" is created ==== pass");
			}else
			{
				System.out.println("new contact with"+contactlastname+"not created ==== failed");
			}
			
			if(orgname.trim().contains(orgnamee)) {
				System.out.println("new contact with "+orgname+" is created ==== pass");
			}else
			{
				System.out.println("new contact with "+orgname+" is not created ==== failed");
			}
			
//		}catch (Exception e) {
//			Alert alertText = driver.switchTo().alert();
//			String text1 = alertText.getText();
//			alertText.accept();
//			System.out.println("Please handle the exception");
//		}

		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		driver.quit();

	}
}
