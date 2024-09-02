package practice;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class Working_with_Xml_ReadDataFromXmlTest {
	
	@Test
	public void readXmlData(XmlTest t) {
		System.out.println(t.getParameter("browser"));
		System.out.println(t.getParameter("url"));
		System.out.println(t.getParameter("username"));
		System.out.println(t.getParameter("password"));
	}

}
