package practice;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Working_with_ReadParameterAtRunTime_CMDLINE {
	@Test
	public void seleniumTest() throws Throwable {
		
		// To read data from the commondline
				String browser = System.getProperty("browser");
				String url = System.getProperty("url");
				String username = System.getProperty("username");
				String password = System.getProperty("password");
				
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
				
				driver.quit();
	}

}
