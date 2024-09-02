package practice;

import static org.testng.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class Working_with_NinzaHrm_GUI {

	@Test
	public void gui() throws Throwable {
		String expected="xxxx";
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://106.51.90.215:8084/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys("xxxx");
		driver.findElement(By.name("createdBy")).sendKeys("CJ");
		
		Select s= new Select(driver.findElement(By.xpath("(//select[@name='status'])[2]")));
		s.selectByIndex(1);
		
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		//Verify the project in backend [db] using jdbc
		boolean flag=false;
		Driver driverref= new Driver();
		DriverManager.registerDriver(driverref);
		Connection con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		Statement stmt = con.createStatement();
		ResultSet rest = stmt.executeQuery("Select * from project");
		while(rest.next()) {
			String actprojectname = rest.getString(4);
			if(expected.equals(actprojectname)){
				flag=true;
				System.out.println(expected+"is avaliable in DB===pass");
			}
		}
		if(flag==false) {
			System.out.println(expected+"is not available in DB===fail");
			Assert.fail();
		}
		
		con.close();
		
	}
}
