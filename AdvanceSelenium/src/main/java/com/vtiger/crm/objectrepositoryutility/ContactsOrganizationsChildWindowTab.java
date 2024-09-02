package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsOrganizationsChildWindowTab {
	@FindBy(id="search_txt")
	private WebElement  searchtxt;
	
	@FindBy(name="search")
	private WebElement searchbtn;
	
	public WebElement getSearchbtn() {
		return searchbtn;
	}

	public ContactsOrganizationsChildWindowTab(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getSearchtxt() {
		return searchtxt;
	}
	
	

}
