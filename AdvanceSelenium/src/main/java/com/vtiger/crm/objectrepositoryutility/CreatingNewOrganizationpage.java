package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationpage {

	@FindBy(name ="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath ="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(name="industry")
	private WebElement industryDB;
	
	@FindBy(name="accounttype")
	private WebElement typeDB;
	
	@FindBy(name="phone")
	private WebElement phoneEtd;
	
	public CreatingNewOrganizationpage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		savebtn.click();
	}
	
	public void createOrg(String orgName, String industry, String type) {
		
		orgNameEdt.sendKeys(orgName);
		
		Select s= new Select(industryDB);
		s.selectByVisibleText(industry);
		
		Select s1= new Select(typeDB);
		s1.selectByValue(type);
		
		savebtn.click();
	}
	
	public void createOrg(String orgName, String phone) {
		orgNameEdt.sendKeys(orgName);
		phoneEtd.sendKeys(phone);
		savebtn.click();
	}
	
}
