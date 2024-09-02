package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(linkText ="Organizations")
	private WebElement orglink;
	
	@FindBy(linkText ="Contacts")
	private WebElement contactlink;
	
	@FindBy(linkText ="More")
	private WebElement morelink;
	
	@FindBy(linkText ="Campaigns")
	private WebElement campaignslink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutlink;
	
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getOrglink() {
		return orglink;
	}



	public WebElement getContactlink() {
		return contactlink;
	}



	public WebElement getMorelink() {
		return morelink;
	}



	public WebElement getCampaignslink() {
		return campaignslink;
	}



	public WebElement getadminImg() {
		return adminImg;
	}

	public WebElement getSignOutlink() {
		return signOutlink;
	}

	public void navigateToCampaignPage(WebDriver driver) {
		Actions a= new Actions(driver);
		a.moveToElement(morelink).perform();
		campaignslink.click();
	}
	
	public void logout(WebDriver driver) {
		Actions a = new Actions(driver);
		a.moveToElement(adminImg).perform();
		signOutlink.click();
	}
	
}
