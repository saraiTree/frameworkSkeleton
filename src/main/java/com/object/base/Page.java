package com.object.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.object.extent.ExtentManager;
import com.object.utilities.ExcelReader;

public class Page 
{
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExtentReports extent = ExtentManager.getExtent();
	public static ExtentTest test;
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties data = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/excels/testdata.xlsx");

	public Page()
	{
		if(driver == null)
		{
			//initializing the webdriver
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver();

			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"/properties/config.properties");
				config.load(fis);
				fis = new FileInputStream(System.getProperty("user.dir")+"/properties/data.properties");
				data.load(fis);
			} catch (Exception e) 
			{
				log.error(e.getMessage());
			}
		}

	}
	public static String attributeValue(String locator, String attribute)
	{
		String val = "";
		if(locator.endsWith("_CSS"))
		{
			val = driver.findElement(By.cssSelector(config.getProperty(locator))).getAttribute(attribute);			
		}
		else if(locator.endsWith("_XPATH"))
		{
			val = driver.findElement(By.xpath(config.getProperty(locator))).getAttribute(attribute);
		}
		else if(locator.endsWith("_ID"))
		{
			val = driver.findElement(By.xpath(config.getProperty(locator))).getAttribute(attribute);
		}

		return val;
	}
	public static void wait(int wait)
	{
		driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
	}
	public static boolean elementEnabled(String locator)
	{
		boolean status = false;
		if(locator.endsWith("_CSS"))
		{
			status = driver.findElement(By.cssSelector(config.getProperty(locator))).isEnabled();
			log.info("element is enabled: "+status);
		}
		else if(locator.endsWith("_XPATH"))
		{
			status = driver.findElement(By.xpath(config.getProperty(locator))).isEnabled();
			log.info("element is enabled: "+status);
		}
		else if(locator.endsWith("_ID"))
		{
			status = driver.findElement(By.id(config.getProperty(locator))).isEnabled();
			log.info("element is enabled: "+status);
		}
		return status;
	}

	public static void quiteBrowser()
	{
		driver.quit();
	}
	public static void click(String locator)
	{
		if(locator.endsWith("_CSS"))
		{
			log.info("locator "+locator+" is css");
			Reporter.log("<br>");
			Reporter.log("locator is "+locator+" is css");
			Reporter.log("<br>");
			driver.findElement(By.cssSelector(config.getProperty(locator))).click();
			log.info("css locator is clicked");
			Reporter.log("<br>");
			Reporter.log("locator is clicked");
			Reporter.log("<br>");
		}
		else if(locator.endsWith("_XPATH"))
		{
			log.info("locator "+locator+" is xpath");
			Reporter.log("<br>");
			Reporter.log("locator is "+locator+" is xpath");
			Reporter.log("<br>");
			driver.findElement(By.xpath(locator)).click();
			log.info("xpath locator is clicked");
			Reporter.log("<br>");
			Reporter.log("xpath locator is clicked");
			Reporter.log("<br>");
		}
		else if(locator.endsWith("_ID"))
		{
			log.info("locator"+locator+" is id");
			Reporter.log("<br>");
			Reporter.log("locator is"+locator+" is id");
			Reporter.log("<br>");
			driver.findElement(By.id(locator)).click();
			log.info("id locator is clicked");
			Reporter.log("<br>");
			Reporter.log("id locator is clicked");
			Reporter.log("<br>");
		}
	}

	public static void enterText(String locator, String text)
	{
		if(locator.endsWith("_CSS"))
		{
			log.info("locator"+locator+" is css and text is "+text);
			Reporter.log("<br>");
			Reporter.log("locator"+locator+" is css and text is "+text);
			Reporter.log("<br>");
			driver.findElement(By.cssSelector(locator)).sendKeys(text);
		}
		else if(locator.endsWith("_PATH"))
		{
			log.info("locator"+locator+" is xpath and text is "+text);
			Reporter.log("<br>");
			Reporter.log("locator"+locator+" is xpath and text is "+text);
			Reporter.log("<br>");
			driver.findElement(By.xpath(locator)).sendKeys(text);
		}
		else if(locator.endsWith("_ID"))
		{
			log.info("locator"+locator+" is id and text is "+text);
			Reporter.log("<br>");
			Reporter.log("locator"+locator+" is id and text is "+text);
			Reporter.log("<br>");
			driver.findElement(By.id(locator)).sendKeys(text);
		}
	}
	public static void verify(String expected, String actual)
	{
		try 
		{
			Assert.assertEquals(actual, expected);

		} catch (Exception e) 
		{
			log.error("both strings are not equal", e);
			Reporter.log("<br>");
			Reporter.log(actual+" and "+expected+" are not same");
			Reporter.log("<br>");
		}
	}

}
