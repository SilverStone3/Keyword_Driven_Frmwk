package com.MOFservice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.MOF.constants.Constant;
import com.MOFutility.ExcelUtilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.executionCore.Engine;
import com.google.common.base.Verify;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MOF_Service {

	static WebDriver driver;
	static ExtentReports extentReport;
	static ExtentSparkReporter htmlReporter;
	static ExtentTest testcase;

	@BeforeSuite
	public static void openBrowser() {
		try {

			extentReport = new ExtentReports();
			htmlReporter = new ExtentSparkReporter("ExtentReport.html");
			extentReport.attachReporter(htmlReporter);

			switch (ExcelUtilities.dataColumnValue) {
			case Constant.CHROME:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			case Constant.FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case Constant.IE:
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;

			default:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				break;
			}

		} catch (Exception e) {
			e.getCause().printStackTrace();
		}
	}

	@Test(priority = 1)
	public static void gotoURL() {
		testcase = extentReport.createTest("Verify URL loaded ");
		driver.get(ExcelUtilities.dataColumnValue);
//		String title = driver.getTitle();
//		if (title.equals("Al Rajhi")) {
//			testcase.log(Status.PASS, "PASS");
//		} else {
//			testcase.log(Status.FAIL, "FAIL");
//		}
	}

	@Test(priority = 2)
	public static void clicklogin() {
		testcase = extentReport.createTest("Verify Login ");
		WebElement Login = driver.findElement(Engine.locator);
		Login.click();

	}

	@Test(priority = 3)
	public static void institutionID() {
		testcase = extentReport.createTest("Verify Instution ID field ");
		WebElement instid = driver.findElement(Engine.locator);
		instid.sendKeys(ExcelUtilities.dataColumnValue);

	}

	@Test(priority = 4)
	public static void instuserID() {
		testcase = extentReport.createTest("Verify instuserID field ");
		WebElement userid = driver.findElement(Engine.locator);
		userid.sendKeys(ExcelUtilities.dataColumnValue);

	}

	@Test(priority = 5)
	public static void instpwdID() {
		testcase = extentReport.createTest("Verify instpwd field ");
		WebElement instpwd = driver.findElement(Engine.locator);
		instpwd.sendKeys(ExcelUtilities.dataColumnValue);

	}

	@Test(priority = 6)
	public static void clicksbt() {
		testcase = extentReport.createTest("Verify submit button ");
		WebElement sbtn = driver.findElement(Engine.locator);
		sbtn.click();
	}

	@Test(priority = 7)
	public static void verify_MyAccount() {
		testcase = extentReport.createTest("Verify My Accounts  ");
		if (driver.getPageSource().contains("My Account  ")) {
			testcase.log(Status.PASS, "PASS");
		} else {
			testcase.log(Status.FAIL, "FAIL");
		}

	}

	@Test(priority = 8)
	public static void clicklogout() {
		testcase = extentReport.createTest("Verify Logout  ");
		WebElement logoutbtn = driver.findElement(Engine.locator);
		logoutbtn.click();
	}

	@Test(priority = 9)
	public static void verify_logout() {
		testcase = extentReport.createTest("Verify You have logged out Successfully text  ");
		if (driver.getPageSource().contains("You have logged out Successfully !")) {
			System.out.println("You have logged out Successfully !");
		} else {
			System.out.println("Fail");
		}
	}

	@Test(priority = 10)
		public static void opentheIEsearch() {
			testcase = extentReport.createTest("Verify IE ");
			driver.get(ExcelUtilities.dataColumnValue);
			WebElement serchbtn = driver.findElement(Engine.locator);
			if (driver.getPageSource().contains("Al Rajhi")) {
				System.out.println("PASS");
			}
	
	}

	@AfterSuite
	public static void closebrowser() {
		driver.quit();
		extentReport.flush();
	}

}
