package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {

	@FindBy (className ="dvHeaderText")
	private WebElement headertxt;
	
	@FindBy (id="dtlview_Last Name")
	private WebElement lastname;
	
	@FindBy(id ="mouseArea_Organization Name")
	private WebElement orgNametxt;
	
	public WebElement getOrgNametxt() {
		return orgNametxt;
	}

	@FindBy (className = "dvtCellInfo")
	private WebElement contactid;
	
	@FindBy (id="dtlview_Support Start Date")
	private WebElement startdate;
	
	@FindBy (id="dtlview_Support End Date")
	private WebElement endate;
	
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeadertxt() {
		return headertxt;
	}

	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getContactid() {
		return contactid;
	}

	public WebElement getStartdate() {
		return startdate;
	}

	public WebElement getEndate() {
		return endate;
	}
	
}
