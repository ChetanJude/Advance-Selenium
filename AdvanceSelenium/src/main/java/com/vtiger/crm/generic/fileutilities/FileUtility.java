package com.vtiger.crm.generic.fileutilities;

import java.io.FileInputStream;
import java.util.Properties;


public class FileUtility {

	public String getDatafromPropertiesFile(String key) throws Throwable {
		FileInputStream fis = new FileInputStream(".\\ConfigAppData\\commondata.properties");
		Properties p= new Properties();
		p.load(fis);
		String data = p.getProperty(key);
		return data;
	}
}
