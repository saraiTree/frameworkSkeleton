package com.object.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.object.base.Page;

public class Utilities extends Page
{
	public static String name;

	public static void getScreenshot()
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date date = new Date();
		name = date.toString().replace(":", "_").replaceFirst(" ", "_")+".jpg";
		try 
		{
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/target/html/"+name));
		} 
		catch (IOException e) 
		{
		log.error(e.getMessage());
		}
	}
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}

}
