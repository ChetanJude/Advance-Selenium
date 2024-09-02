package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Working_With_RandomNumber {

	public static void main(String[] args) throws Throwable {

		// To retrive data from the properties file
		FileInputStream fis = new FileInputStream(".\\TestData\\commondata.properties");
		Properties p = new Properties();
		p.load(fis);
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String un = p.getProperty("un");
		String pw = p.getProperty("pw");

		//Creating a random number
		Random random= new Random();
		int randomint = random.nextInt(1000);

		// To get the org name from the excel file
		FileInputStream fis1 = new FileInputStream("./TestData/TestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("NewOrgWithRandomNumber");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(2);
		String data = cell.getStringCellValue()+randomint;


		// To choose and execute the program in specified browser
		WebDriver driver;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(un);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(pw);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		//Create a New OrgName with Random
		driver.findElement(By.name("accountname")).sendKeys(data);

		// selecting from dropdown
		WebElement industry = driver.findElement(By.name("industry"));
		Select s = new Select(industry);
		s.selectByIndex(1);

		// radio button
		driver.findElement(By.xpath("//input[@name='assigntype'][2]")).click();

		// checkbox
		driver.findElement(By.name("notify_owner")).click();

		// creating org and confirmation
		try {
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			String text = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			System.out.println("New organsiation " + text + " is created");

			FileInputStream fis2 = new FileInputStream("./TestData/TestScript.xlsx");
			Workbook wb1 = WorkbookFactory.create(fis2);
			FileOutputStream fos1 = new FileOutputStream("./TestData/TestScript.xlsx");
			Row row1 = wb1.getSheet("NewOrgWithRandomNumber").getRow(1);
			Cell cell1 = row.createCell(3);
			cell1.setCellValue(text);
			wb1.write(fos1);
			wb1.close();

		} catch (Exception e) {
			Alert alertText = driver.switchTo().alert();
			String text1 = alertText.getText();
			alertText.accept();
			System.out.println("New organsiaction is not created as " + text1 + " -Please enter different Organsiaction Name-");
		}

		// logout of the application
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		driver.quit();
	}

}
