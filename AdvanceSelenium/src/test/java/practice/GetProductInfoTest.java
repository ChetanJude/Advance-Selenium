package practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ObjectArrays;
import com.vtiger.crm.generic.fileutilities.ExcelUtility;

public class GetProductInfoTest {
	
	@Test(dataProvider = "getExcelData")
	public void getproductinfoTest(String brandname, String productname) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandname+Keys.ENTER);
		
		String x="//span[text()='"+productname+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span[1]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(productname+" price==> "+price);
		
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getExcelData() throws Throwable{
	 ExcelUtility eulib= new ExcelUtility();
	 int rowcount = eulib.getRowCount("product");
	 Object[][] objarr= new Object[rowcount][2];
	 for(int i=0; i<rowcount;i++) {
		 objarr[i][0]= eulib.getDataFromExcelFile("product", i+1, 0);
		 objarr[i][1]= eulib.getDataFromExcelFile("product", i+1, 1);
	 }
		return objarr;
	}

}
