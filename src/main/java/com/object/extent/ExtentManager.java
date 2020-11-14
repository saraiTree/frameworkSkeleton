package com.object.extent;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager 
{
	public static ExtentReports report;
	
	public static ExtentReports getExtent()
	{
		if(report==null)
		{
			report = new ExtentReports(System.getProperty("user.dir")+"/target/html/extent.html", true, DisplayOrder.OLDEST_FIRST);
			report.loadConfig(new File(System.getProperty("user.dir")+"/extentConfig/ReportsConfig.xml"));
		}
		
		return report;
	}
}
