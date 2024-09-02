package com.vtiger.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
 
	
	public int getRandomNumber() {
	Random random= new Random();
	int randomint = random.nextInt(5000);
	return randomint;
	}
	
	public String getSystemDateYYYYMMDD() {
		Date dateobj = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;
	}
	
	public String getSystemDatewithAdditionalDate(int days) {
		
		/*SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String requireddate = sdf.format(cal.getTime());
		return requireddate;*/
		Date dateobj = new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		sdf.format(dateobj);
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String requireddate = sdf.format(cal.getTime());
		return requireddate;
		
		
	}
}
