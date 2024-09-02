package com.vtiger.crm.orgtest;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.vtiger.crm.ListenerUtility.ListenerImplimentationClass;
import com.vtiger.crm.baseclassutility.BaseClassTest;
import com.vtiger.crm.generic.webdriverutility.UtilityClassObject;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationpage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationsPage;

//@Listeners(com.vtiger.crm.ListenerUtility.ListenerImplimentationClass.class)
/**
 * @author Chetan
 * 
 * Class involved in Organization Module
 */
public class Working_with_OrganizationModuleTest extends BaseClassTest {

	@Test(groups = "Smoke Test")
	/* (, retryAnalyzer = com.vtiger.crm.ListenerUtility.RetryListenerImp.class) */
	                                   /*to repeat the excution multiple times*/
	public void createOrgTest() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgName = elib.getDataFromExcelFile("NewOrg", 1, 2) + jlib.getRandomNumber();
		wdulib.waitUntilThePageLoad(driver);
		wdulib.maximizePage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to orglink");
		HomePage hplib = new HomePage(driver);
		hplib.getOrglink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create New Orgbtn");
		OrganizationsPage olib = new OrganizationsPage(driver);
		olib.getCreateNewOrgbtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Create a new org");
		CreatingNewOrganizationpage clib = new CreatingNewOrganizationpage(driver);
		clib.createOrg(orgName);
		
		UtilityClassObject.getTest().log(Status.INFO, "confirmation of Creating a new org");
		OrganizationInformationPage oiplib = new OrganizationInformationPage(driver);
		String orgnamehd = oiplib.getHeadermsg().getText();
		String orgnam = oiplib.getOrgNo().getText();
		
		boolean hd=orgnamehd.contains(orgName);
		assertTrue(hd);
		boolean organname = orgnam.contains(orgName);
		assertTrue(organname);

	}
	
	@Test(groups = "Regression Test")
	public void createOrgWithNumber() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgname = elib.getDataFromExcelFile("NewOrg", 4, 2)+jlib.getRandomNumber();
		wdulib.maximizePage(driver);
		wdulib.waitUntilThePageLoad(driver);
		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to orglink");
		HomePage hplib= new HomePage(driver);
		hplib.getOrglink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Click on Create New Orgbtn");
		OrganizationsPage oplib= new OrganizationsPage(driver);
		oplib.getCreateNewOrgbtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Create a new org with phone");
		CreatingNewOrganizationpage cnoplib= new CreatingNewOrganizationpage(driver);
		String phone = elib.getDataFromExcelFile("NewOrg", 7, 5);
		cnoplib.createOrg(orgname, phone);
		
		UtilityClassObject.getTest().log(Status.INFO, "confirmation of new org with phonen");
		OrganizationInformationPage oiplib= new OrganizationInformationPage(driver);
		String phone1 = oiplib.getPhoneEdt().getText();
		
		SoftAssert sa= new SoftAssert();
		sa.assertEquals(phone1, phone);
		sa.assertAll();
	}
	
	@Test(groups = "Regression Test")
	public void createOrgWithIndustryAndType() throws Throwable {
		
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgname = elib.getDataFromExcelFile("NewOrg", 4, 2)+jlib.getRandomNumber();
		wdulib.maximizePage(driver);
		wdulib.waitUntilThePageLoad(driver);
		
		/*Creation of object*/
		CreatingNewOrganizationpage cnop = new CreatingNewOrganizationpage(driver);
		OrganizationsPage oplib = new OrganizationsPage(driver);
		OrganizationInformationPage oiplib= new OrganizationInformationPage(driver);
		HomePage hlib= new HomePage(driver);
		
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to orglink");
		hlib.getOrglink().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Clicking on Create Org");
		oplib.getCreateNewOrgbtn().click();
		
		UtilityClassObject.getTest().log(Status.INFO, "Extracting data from excel for industry and type dropdown");
		String industry = elib.getDataFromExcelFile("NewOrg", 4, 3);
		String type = elib.getDataFromExcelFile("NewOrg", 4, 4);
		
		UtilityClassObject.getTest().log(Status.INFO, "Creating org with industry and type dropdown");
		cnop.createOrg(orgname,industry,type);	
		
		UtilityClassObject.getTest().log(Status.INFO, "Gather organziation name, industry and type dropdown for confirmation");
		String industry1 = oiplib.getIndustryEdt().getText();
		String type1 = oiplib.getTypeEdt().getText();

		SoftAssert sa= new SoftAssert();
		sa.assertEquals(industry1, industry);
		sa.assertEquals(type1, type);
		sa.assertAll();
	}
}
