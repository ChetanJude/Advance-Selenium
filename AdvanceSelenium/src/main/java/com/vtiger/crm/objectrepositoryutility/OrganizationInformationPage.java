package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {

	@FindBy(className = "dvHeaderText")
	private WebElement headermsg;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement orgNo ;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industryEdt;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeEdt;
	
	@FindBy(id="dtlview_Phone")
	private WebElement phoneEdt;
	
	
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}


	public WebElement getHeadermsg() {
		return headermsg;
	}


	public WebElement getOrgNo() {
		return orgNo;
	}


	public WebElement getIndustryEdt() {
		return industryEdt;
	}


	public WebElement getTypeEdt() {
		return typeEdt;
	}


	public WebElement getPhoneEdt() {
		return phoneEdt;
	} 
	
	
}
