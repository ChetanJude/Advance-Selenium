package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Task_Flipkart {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.flipkart.com/");
		WebElement ele1 = driver.findElement(By.xpath("//span[text()='Electronics']"));
		Actions a= new Actions(driver);
		a.moveToElement(ele1).perform();
		driver.findElement(By.linkText("Cameras & Accessories")).click();
	}
}
