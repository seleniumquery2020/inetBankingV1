package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

//keyword Class
public class AutomationUtils {

	public static WebDriver driver;
	static Actions actions;

	public static WebElement findElement(By by) {

		WebElement ele = driver.findElement(by);
		return ele;
	}

	public static List<WebElement> findElements(By by) {

		List<WebElement> elements = driver.findElements(by);
		return elements;
	}

	public static void clickOnElement(WebElement element) {

		element.click();
	}

	public static void sendTextOnElement(WebElement element, String text) {

		element.sendKeys(text);
	}

	public static void moveToElement(WebElement element) {

		actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public static String readExelData(String sheetName) {

		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(
					"C:\\CountersignTechnology\\StargateMavenProject\\src\\test\\resources\\TestData\\TestData.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = wb.getSheet(sheetName);

		DataFormatter format = new DataFormatter();

		String s = format.formatCellValue(sheet.getRow(1).getCell(0));

		return s;

	}

	public static String readPropertiesData(String key) {

		File file = new File("C:\\CountersignTechnology\\CoreJavaBatchJune\\Files\\TestData.properties");

		Properties properties = new Properties();

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String value = properties.getProperty(key);
		return value;

	}

	public static void assertEquals(boolean actual) {
		
	    Assert.assertEquals(actual, true,"Element is not Present");
	    
	}
	
	
	public static String takeScreenshot() {

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String path = "C:\\CountersignTechnology\\StargateMavenProject\\Screenshot\\screenshotInterface" + timeStamp()
				+ ".png";
		File destFile = new File(path);

		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return path;
	}

	public static String timeStamp() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String timeStamp = simpleDateFormat.format(date);
		return timeStamp;
	}
}
