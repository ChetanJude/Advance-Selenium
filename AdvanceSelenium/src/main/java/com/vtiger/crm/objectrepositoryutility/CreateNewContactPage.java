package com.vtiger.crm.objectrepositoryutility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {

	@FindBy (name="lastname")
	private WebElement lastnameEtd;
	
	@FindBy(xpath="//input[@name='account_name']/following-sibling::img")
	private WebElement selectOrgbtn;
	
	@FindBy (xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(id="jscal_field_support_start_date")
	private WebElement supportStartDate;
	
	@FindBy(id="jscal_field_support_end_date")
	private WebElement supportEndDate;
	
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastnameEtd() {
		return lastnameEtd;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void createcontact(String lastName) {
		lastnameEtd.sendKeys(lastName);
		savebtn.click();
	}

	public WebElement getSelectOrgbtn() {
		return selectOrgbtn;
	}

	public WebElement getSupportStartDate() {
		return supportStartDate;
	}

	public WebElement getSupportEndDate() {
		return supportEndDate;
	}
	
//	public void createcontact(String lastName,String startdate,String enddate ) {
//		lastnameEtd.sendKeys(lastName);
//		
//		Date dateobj= new Date();
//		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//		sdf.format(dateobj);
//		Calendar cal= sdf.getCalendar();
//		cal.add(Calendar.DAY_OF_MONTH, 30);
//		String enddate1= sdf.format(cal.getTime());
//		
//		savebtn.click();
//	}
}
