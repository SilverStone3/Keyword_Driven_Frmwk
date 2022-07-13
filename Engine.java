package com.executionCore;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;

import com.MOF.constants.Constant;
import com.MOFservice.MOF_Service;
import com.MOFutility.ExcelUtilities;
import com.MOFutility.Locators;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Engine {
	MOF_Service mofService;
	Method[] methods;

	public static By locator;

	public Engine() {

		mofService = new MOF_Service();
		methods = mofService.getClass().getMethods();

	}

	public void executekeywords() throws Exception {

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equalsIgnoreCase(ExcelUtilities.keywordColumnValue)) {
				methods[i].invoke(mofService);
				break;
			}
		}
	}

	public void findWebElementsTobeUsed() {

		switch (ExcelUtilities.locatorName) {
		case Constant.ID:
			locator = Locators.getID(ExcelUtilities.locatorValue);
			break;
		case Constant.CLASS_NAME:
			locator = Locators.getClassName(ExcelUtilities.locatorValue);
			break;
		case Constant.NAME:
			locator = Locators.getName(ExcelUtilities.locatorValue);
			break;
		case Constant.XPATH:
			locator = Locators.getXpath(ExcelUtilities.locatorValue);
			break;
		case Constant.LINKTEXT:
			locator = Locators.getText(ExcelUtilities.locatorValue);
			break;
		case Constant.TAGNAME:
			locator = Locators.getTagName(ExcelUtilities.locatorValue);
			break;
		case Constant.PARTIAL_LINK_TEXT:
			locator = Locators.getPartialLinkText(ExcelUtilities.locatorValue);
			break;
		case Constant.CSS_SELECTOR:
			locator = Locators.getCssselector(ExcelUtilities.locatorValue);
			break;

		default:
			break;
		}

	}

	public static void main(String[] args) throws Exception {

		try {

			ExcelUtilities utilities = new ExcelUtilities();
			utilities.readExcelfile(Constant.EXCEL_LOCATION);
			Engine engine = new Engine();

			for (int row = 1; row <= ExcelUtilities.totalRows; row++) {
				// keyword =
				utilities.getLocatorsKeywordsAndData(row, Constant.LOCATOR_COLUMN, Constant.KEYWORD_COLUMN,
						Constant.DATA_COLUMN, Constant.EXECUTION_COLUMN);
				if (ExcelUtilities.executioncolumnvalue.contains("TRUE")) {
					engine.findWebElementsTobeUsed();
					engine.executekeywords();
				}

			}
			System.out.println("Excuted Test Case :  TRUE");

			// if (keyword.equalsIgnoreCase("openBrowser")) {
			// MOF_Service.openBrowser();
			// } else if (keyword.equalsIgnoreCase("gotoURLLogin")) {
			// MOF_Service.gotoURLLogin();
			// } else if (keyword.equalsIgnoreCase("institutionID")) {
			// MOF_Service.institutionID();
			// } else if (keyword.equalsIgnoreCase("instuserID")) {
			// MOF_Service.instuserID();
			// } else if (keyword.equalsIgnoreCase("instpwdID")) {
			// MOF_Service.instpwdID();
			// } else if (keyword.equalsIgnoreCase("submitbtn")) {
			// MOF_Service.submitbtn();
			// } else if (keyword.equalsIgnoreCase("verify_MyAccount")) {
			// MOF_Service.verify_MyAccount();
			// } else if (keyword.equalsIgnoreCase("logout")) {
			// MOF_Service.logout();
			// } else if (keyword.equalsIgnoreCase("verify_logout")) {
			// MOF_Service.verify_logout();
			// }
			//

		} catch (InvocationTargetException e) {
			e.getCause().printStackTrace();
		}

	}
}