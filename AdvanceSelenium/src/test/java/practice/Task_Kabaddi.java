package practice;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task_Kabaddi {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.prokabaddi.com/");
		List<WebElement> teamNames = driver.findElements(By.xpath("//div[@class='row-head']//p[@class='name']"));

		for (int i = 0; i < teamNames.size()/* to find the first 5 team*/; i++) {
			System.out.print(i+1+")"+teamNames.get(i).getText());
			String teamName = teamNames.get(i).getText();
			List<WebElement> allForms = driver.findElements(By.xpath("//p[text()='"+teamName+"']//ancestor::div[@class='row-head']//descendant::ul[@class='form-listing']//descendant::p"));
			for (int j = 0; j < allForms.size(); j++) {
				System.out.print(" "+allForms.get(j).getText()); 
			}
			System.out.println();
		}
		driver.quit();
	}

}
