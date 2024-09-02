package com.vtiger.crm.contacttest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.vtiger.crm.baseclassutility.BaseClassTest;
import com.vtiger.crm.generic.webdriverutility.UtilityClassObject;
import com.vtiger.crm.objectrepositoryutility.ContactInformationPage;
import com.vtiger.crm.objectrepositoryutility.ContactPage;
import com.vtiger.crm.objectrepositoryutility.ContactsOrganizationsChildWindowTab;
import com.vtiger.crm.objectrepositoryutility.CreateNewContactPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationpage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

/**
 * @author Chetan 
 * 
 * Class involved in Contact Module
 */
@Listeners(com.vtiger.crm.ListenerUtility.ListenerImplimentationClass.class)
public class Working_with_ContactModuleTest extends BaseClassTest {

	@Test (groups = "Smoke Test")
	/**
	 * Details: TestScript involved in creating new contact and verification
	 * @throws IOException
	 * @throws Throwable
	 */
	public void createContactTest() throws IOException, Throwable {
		/*Maximizing the page*/
		wdulib.maximizePage(driver);

		/*Using implicitlywait for page to load*/
		wdulib.waitUntilThePageLoad(driver);

		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String lastName = elib.getDataFromExcelFile("Contact", 1, 2) + jlib.getRandomNumber();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contactlink");
		HomePage hplib = new HomePage(driver);
		hplib.getContactlink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Click on Create New Contact btn");
		ContactPage cplib = new ContactPage(driver);
		cplib.getCreatebtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create a new contact");
		CreateNewContactPage cncplib = new CreateNewContactPage(driver);
		cncplib.createcontact(lastName);

		UtilityClassObject.getTest().log(Status.INFO, "confirmation of creating new org");
		ContactInformationPage ciplib = new ContactInformationPage(driver);

		UtilityClassObject.getTest().log(Status.INFO, "Fetching the actual last name data from the contact information page");
		String actlastname = ciplib.getLastname().getText();


		assertEquals(actlastname, lastName);

	}

	@Test(groups = "Regression Test")
	/**
	 * Details: TestScript involved in creating new contact with new organization and verification
	 * @throws Throwable
	 */
	public void createContactwithOrgTest() throws Throwable {

		wdulib.maximizePage(driver);
		wdulib.waitUntilThePageLoad(driver);
		HomePage hplib = new HomePage(driver);

		UtilityClassObject.getTest().log(Status.INFO, "Reading organization name or contact's lastname data from Excel");
		String orgnamee = elib.getDataFromExcelFile("Contact", 7, 3) + jlib.getRandomNumber();
		String contactlastname = elib.getDataFromExcelFile("Contact", 1, 2) + jlib.getRandomNumber();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization link");
		hplib.getOrglink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Clicking on Create new organization button");
		OrganizationsPage olib = new OrganizationsPage(driver);
		olib.getCreateNewOrgbtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Creating a new organization and saving it");
		CreatingNewOrganizationpage clib = new CreatingNewOrganizationpage(driver);
		clib.createOrg(orgnamee);

		Thread.sleep(1000);

		/*
		 * FluentWait w = new FluentWait(driver); w.pollingEvery(Duration.ofSeconds(2));
		 * w.withTimeout(Duration.ofSeconds(20));
		 * w.until(ExpectedConditions.visibilityOf(hplib.getContactlink()));
		 */


		/*
		 * int i=0; while(i<2) { try { UtilityClassObject.getTest().log(Status.INFO,
		 * "Navigate to Contactlink"); hplib.getContactlink().click(); break;
		 * }catch(Exception e) { i++; }
		 * 
		 * }
		 */

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contactlink");
		hplib.getContactlink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Click on Create New Contact btn");
		ContactPage cplib = new ContactPage(driver);
		cplib.getCreatebtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Sending data to contact's lastname textfield");
		CreateNewContactPage cncplib = new CreateNewContactPage(driver);
		cncplib.getLastnameEtd().sendKeys(contactlastname);

		UtilityClassObject.getTest().log(Status.INFO, "Clicking on the select Organization button");
		cncplib.getSelectOrgbtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Switching to child window");
		wdulib.switchToTabOnUrl(driver, "module+Accounts");

		ContactsOrganizationsChildWindowTab cowctlib= new ContactsOrganizationsChildWindowTab(driver);

		cowctlib.getSearchtxt().sendKeys(orgnamee);
		cowctlib.getSearchbtn().click();
		UtilityClassObject.getTest().log(Status.INFO, "clicking on the required Organization link");
		driver.findElement(By.xpath("//a[text()='" + orgnamee + "']")).click();

		UtilityClassObject.getTest().log(Status.INFO, "Switching to parent window");
		wdulib.switchToTabOnUrl(driver, "Contacts&parent");
		cncplib.getSavebtn().click();

		ContactInformationPage ciplib = new ContactInformationPage(driver);

		UtilityClassObject.getTest().log(Status.INFO, "Fetching the actual lastname and organization name data from the contact information page");
		String lastname = ciplib.getLastname().getText();
		String orgname = ciplib.getOrgNametxt().getText();
		boolean ogname = orgname.trim().contains(orgnamee);

		SoftAssert sa= new SoftAssert();
		sa.assertEquals(lastname, contactlastname);
		sa.assertTrue(ogname);
		sa.assertAll();

	}

	@Test(groups = "Regression Test")
	/**
	 * Details: TestScript involved in creating new contact with support dates and verification
	 * @throws Throwable
	 */
	public void createContactwithDate() throws Throwable {

		wdulib.maximizePage(driver);
		wdulib.waitUntilThePageLoad(driver);

		UtilityClassObject.getTest().log(Status.INFO, "Reading organization name or contact's lastname data from Excel");
		String lastname = elib.getDataFromExcelFile("Contact", 1, 2) + jlib.getRandomNumber();

		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contactlink");
		HomePage hplib = new HomePage(driver);
		hplib.getContactlink().click();

		UtilityClassObject.getTest().log(Status.INFO, "Click on Create New Contact btn");
		ContactPage cplib = new ContactPage(driver);
		cplib.getCreatebtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Sending data to contact's lastname textfield");
		CreateNewContactPage cncplib = new CreateNewContactPage(driver);
		cncplib.getLastnameEtd().sendKeys(lastname);

		UtilityClassObject.getTest().log(Status.INFO, "Entering support data in create contact page");
		String actdate = jlib.getSystemDateYYYYMMDD();
		String daterequired = jlib.getSystemDatewithAdditionalDate(30);

		cncplib.getSupportStartDate().clear();
		cncplib.getSupportStartDate().sendKeys(actdate);

		cncplib.getSupportEndDate().clear();
		cncplib.getSupportEndDate().sendKeys(daterequired);

		cncplib.getSavebtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Fetching the actual support stat and end data from the contact information page");
		ContactInformationPage ciplib = new ContactInformationPage(driver);
		String startdate1 = ciplib.getStartdate().getText();
		String enddate1 = ciplib.getEndate().getText();

		SoftAssert sa= new SoftAssert();
		sa.assertEquals(startdate1, actdate);
		sa.assertEquals(enddate1, daterequired);

		sa.assertAll();

	}

}
