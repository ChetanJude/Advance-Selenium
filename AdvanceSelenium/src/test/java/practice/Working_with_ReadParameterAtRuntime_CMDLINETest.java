package practice;

import org.testng.annotations.Test;

public class Working_with_ReadParameterAtRuntime_CMDLINETest {

	@Test
	public void mavenParametertest() {
		
		String url = System.getProperty("Url");
		String browser = System.getProperty("Browser");
		String username = System.getProperty("UserName");
		String password = System.getProperty("Password");
		
		System.out.println("Env data====>URL =====>"+ url);
		System.out.println("Broswer======>"+ browser);
		System.out.println("Username======>"+ username);
		System.out.println("Password====>"+password);
	}
}
