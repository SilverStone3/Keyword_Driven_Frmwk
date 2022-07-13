package com.MOFutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	static FileInputStream file;
	XSSFWorkbook workBook;
	XSSFSheet sheet;
	XSSFCell cell;
	public static String locatorColumnValue;
	public static String locatorName;
	public static String locatorValue;
	public static String keywordColumnValue;
	public static String dataColumnValue;
	public static String executioncolumnvalue;
	public static int totalRows;

	public void readExcelfile(String location) throws IOException {
		FileInputStream file = new FileInputStream(location);
		workBook = new XSSFWorkbook(file);
		sheet = workBook.getSheet("Sheet1");
		totalRows = sheet.getLastRowNum();
	}

	public void getLocatorsKeywordsAndData(int row, int locatorColumn, int keyWordcolumn, int Datacolumn , int Executioncolumn ) {
		// cell = sheet.getRow(row).getCell(column);
		// String cellvalue = cell.getStringCellValue();
		locatorColumnValue = sheet.getRow(row).getCell(locatorColumn).toString().trim();
		keywordColumnValue = sheet.getRow(row).getCell(keyWordcolumn).toString().trim();
		dataColumnValue = sheet.getRow(row).getCell(Datacolumn).toString().trim();
		executioncolumnvalue = sheet.getRow(row).getCell(Executioncolumn).toString().trim();
		
		if (!locatorColumnValue.contains("NA")) {
			locatorName = locatorColumnValue.split("=")[0].toString().trim();
			locatorValue = locatorColumnValue.split("=")[1].toString().trim();

		} else {
			locatorName = "NA";
			locatorValue = "NA";
		}
		// 
		
		System.out.println(locatorName + " : " +locatorValue + "   Keyword :" +keywordColumnValue + 
				"   Keyword :" + keywordColumnValue + "   Data :" +dataColumnValue +" FLAG STATUS : " + executioncolumnvalue );
	}
}
