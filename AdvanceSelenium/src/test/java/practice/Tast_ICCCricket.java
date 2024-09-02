package practice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tast_ICCCricket {

	public static void main(String[] args) {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.icc-cricket.com/");
		driver.findElement(By.linkText("RANKINGS")).click();
		driver.findElement(By.xpath("//div[@id='mens-team-rankings']/"
				+ "descendant::div[@class='si-table swiper-slide']/"
				+ "descendant::div[@class='si-table-head t20']/"
				+ "following-sibling::div[@class='si-table-footer']/a/span")).click();
		List<WebElement> teamNames = driver.findElements(By.xpath("//div[@class='si-component si-raking-detail si-team-ranking']//descendant::span[@class='si-fname si-text']"));
		List<WebElement> teamPoints = driver.findElements(By.xpath("//div[@class='si-component si-raking-detail si-team-ranking']/descendant::div[@class='si-table-body']/descendant::div[@class='si-table-data si-pts']/span[@class='si-text']"));
		for (int i = 0; i < teamNames.size(); i++) {
			if(!(teamNames.get(i).getText().equalsIgnoreCase("PAKISTan"))) {
			System.out.println(teamNames.get(i).getText()+" ===> "+teamPoints.get(i).getText());
			}
		}
	driver.quit();	
	}

}
