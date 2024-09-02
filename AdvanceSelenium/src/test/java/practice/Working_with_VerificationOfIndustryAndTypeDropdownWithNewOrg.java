package practice;

import java.io.FileInputStream;
import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Working_with_VerificationOfIndustryAndTypeDropdownWithNewOrg{

	@Test
	public void industryAndType() throws Throwable {
		
		FileInputStream fis = new FileInputStream("./TestData/commondata.properties");
		Properties p= new Properties();
		p.load(fis);
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");

		Random random = new Random();
		int randomint = random.nextInt(1000);

		FileInputStream fis1 = new FileInputStream("./TestData/TestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("NewOrg");
		Row row = sheet.getRow(4);
		Cell cell = row.getCell(2);
		//System.out.println(cell.getStringCellValue()+ randomint);
		String data = cell.getStringCellValue()+ randomint;

		WebDriver driver;
		if(browser.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}else {
			driver= new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(data);
		
		WebElement we = driver.findElement(By.name("industry"));
		Select s= new Select(we);
		
		Row row1 = sheet.getRow(4);
		Cell cell1 = row1.getCell(3);
		String data1 = cell1.getStringCellValue();
		
		s.selectByVisibleText(data1);
		
		WebElement we1 = driver.findElement(By.name("accounttype"));
		Select s1= new Select(we1);
		
		Row row2 = sheet.getRow(4);
		Cell cell2 = row2.getCell(4);
		String data2 = cell2.getStringCellValue();
		
		s1.selectByVisibleText(data2);
		
		
		try {
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			String industry = driver.findElement(By.id("mouseArea_Industry")).getText();
			String type = driver.findElement(By.id("dtlview_Type")).getText();

			if(industry.trim().contains(data1)) {
				System.out.println("new org with industry "+industry+" is create ==== pass");
			}else
			{
				System.out.println("new org with industry "+industry+" not create ==== failed");
			}
			if(type.contains(data2)) {
				System.out.println("new org with type "+type+" created ==== pass");
			}
			else
			{
				System.out.println("new org with type "+type+" not create ==== failed");
			}
		}catch (Exception e) {
			Alert alertText = driver.switchTo().alert();
			String text1 = alertText.getText();
			alertText.accept();
			System.out.println("New organsiaction is not created as " + text1 + " -Please enter different Organsiaction Name-");
		}
		
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		driver.quit();
		

	}


}
