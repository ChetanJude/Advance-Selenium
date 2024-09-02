package com.vtiger.crm.generic.fileutilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public String getDataFromExcelFile(String sheetname, int rownum, int cellnum) throws Throwable, IOException {
		FileInputStream fis= new FileInputStream("./TestData/TestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		wb.close();
		return data;
		
	}
	public void setDataintoExcel(String sheetname, int rownum, int cellnum, String data) throws Throwable {
		FileInputStream fis = new FileInputStream("./TestData/TestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetname).getRow(rownum).createCell(cellnum);
		FileOutputStream fos = new FileOutputStream("./TestData/TestScript.xlsx");
		wb.write(fos);
		wb.close();
	}
	public int getRowCount(String sheetname) throws Throwable, IOException {
		FileInputStream fis = new FileInputStream("./TestData/TestScript.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetname).getLastRowNum();
		wb.close();
		return rowcount;
	}

}
