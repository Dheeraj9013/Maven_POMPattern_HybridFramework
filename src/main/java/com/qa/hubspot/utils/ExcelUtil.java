package com.qa.hubspot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	
	
	public static Object[][]  getTestData(String sheetName , String excelpath) {
		Object data[][] = null;
		File file = new File(excelpath);
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			int totalRowNum = sheet.getLastRowNum() - sheet.getFirstRowNum();
			
			data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int i=0;i<sheet.getLastRowNum();i++) {
				for(int j=0 ; j<sheet.getRow(i).getLastCellNum();j++) {
					
					data[i][j] = sheet.getRow(i+1).getCell(j).getStringCellValue();
					
				}
				
				
				
			}
			
			
			
		
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
		return data;
		
	}

}
