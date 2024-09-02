package com.vtiger.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitUntilThePageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	public void WaitUntilTheElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		//wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToTabOnUrl(WebDriver driver, String partailURL) {
		Set<String> url = driver.getWindowHandles();
		Iterator<String> it = url.iterator();
		while(it.hasNext()) {
			String windowUrl = it.next();
			driver.switchTo().window(windowUrl);
			String acturl = driver.getCurrentUrl();
			if(acturl.contains(partailURL)) {
				break;
			}
		}
	}
	
	public void switchToTabOnTitle(WebDriver driver, String PartailTitle) {
		Set<String> url = driver.getWindowHandles();
		Iterator<String> it = url.iterator();
		while(it.hasNext()) {
			String windowUrl = it.next();
			driver.switchTo().window(windowUrl);
			String acttitle = driver.getTitle();
			if(acttitle.contains(PartailTitle)) {
				break;
			}
		}
	}
	
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameId) {
		driver.switchTo().frame(nameId);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void selectbytext(WebElement element, String text) {
		Select sel= new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void selectbyindex(WebElement element, int index) {
		Select sel= new Select(element);
		sel.selectByIndex(index);
	}
	
	public void selectByvalue(WebElement element, String value) {
		Select sel= new Select(element);
		sel.selectByValue(value);
	}
	
	public void mouseMoveOnElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	
	public void doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick().perform();
		
	}
	
	public void maximizePage(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void minimizePage(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	//add more method and implement those methos in testcase
	
	public void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	
}
