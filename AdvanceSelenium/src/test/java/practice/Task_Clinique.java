package practice;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task_Clinique {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://www.clinique.in/");
		driver.findElement(By.xpath("//*[local-name()='svg']/following-sibling::span[@class='desktop-header-secondary-links-text' and contains(text(),'Sign In')]")).click();
		WebElement registerbtn = driver.findElement(By.id("new-account-link"));
		
		TakesScreenshot ts= (TakesScreenshot) driver;
		File sc = registerbtn.getScreenshotAs(OutputType.FILE);
		File des = new File("./Screenshot/register.png");
		FileUtils.copyFile(sc, des);
		
		driver.quit();
	}
}
