package com.qc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

public class TestUtil {

	public Properties readProp() throws IOException {
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop;
	}
	
	public Object[][] readExcelData(String sheetName) throws IOException{
		FileInputStream fis = new FileInputStream("src/test/resources/TestData.xls");
		DataFormatter data = new DataFormatter();
		HSSFWorkbook book  = new HSSFWorkbook(fis);
		HSSFSheet sheet = book.getSheet(sheetName);
		HSSFRow hrow = sheet.getRow(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int columns = hrow.getLastCellNum();
		
		Object testData[][] = new Object[rows-1][columns];
		
		for(int i=0; i<rows-1; i++) {
			HSSFRow row = sheet.getRow(i + 1);
			for(int j =0; j<columns; j++) {
				if(row == null) {
					testData[i][j] = "";
				}else {
					HSSFCell cell = row.getCell(j);
					if(cell == null) {
						testData[i][j] = "";
					}else {
						String value = data.formatCellValue(cell);
						testData[i][j] = value;
					}
				}
			}
		}
		return testData;
	}
}
