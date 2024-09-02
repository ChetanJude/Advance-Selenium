
package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MockTask_NavigateAndTakeScreenshotOfAnElement {

	public static void main(String[] args) {
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver= new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://via.com");
		driver.findElement(By.xpath("//span[text()='Hotels ']")).click();
		driver.findElement(By.id("destination")).sendKeys("chikamagalur");
		driver.findElement(By.xpath("//span[text()='Chikmagalur,Karnataka,India']")).click();
		driver.findElement(By.xpath("//div[@class='calendar-icon'][1]")).click();
		driver.findElement(By.xpath("(//div[text()='30'])[2]")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Search Hotels')]")).click();
		driver.findElement(By.xpath("//p[text()='The Grand Krishna']/../following-sibling::div[1]/div[3]/div[1]")).click();
	}

}
