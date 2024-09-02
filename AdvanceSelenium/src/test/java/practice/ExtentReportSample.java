package practice;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

public class ExtentReportSample {

	@Test
	public void createOrgTest() {
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Script Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		ExtentReports report= new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("Browser", "Chrome-128");
		
		
		WebDriver driver= new ChromeDriver();
		driver.get("https://amazon.in");
		
		TakesScreenshot ts= (TakesScreenshot) driver;
		String scr =ts.getScreenshotAs(OutputType.BASE64);
		
		ExtentTest test= report.createTest("createOrgTest");
		test.log(Status.INFO,"login to app");
		test.log(Status.INFO,"Click on orglink");
		test.log(Status.INFO,"Create a new org");
		if("hdfa".equals("hdfc")) {
			test.log(Status.PASS,"created the org");
		}else {
			test.addScreenCaptureFromBase64String(scr, "Error Screenshot");
			
		}
		
		report.flush();
	}
}
