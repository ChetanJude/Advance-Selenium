package practice;

import java.io.FileReader;
import java.time.Duration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Working_with_json_ReadDataFromJsonFile {

	@Test
	public void jsonfile()throws Throwable {
		
		// To read data from the jsonfile
		JSONParser parser= new JSONParser();
		
		//here the jsonphysical file is converted to object
		Object obj = parser.parse(new FileReader("./TestData/appcommondata.json"));
		
		//here the java obj is converted to json object
		JSONObject map=(JSONObject) obj;
		
		//here the converting the jsonobject to string
		String browser = (String) map.get("browser");
		String url = (String) map.get("url");
		String username = (String) map.get("username");
		String password = (String) map.get("password");
		
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
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
