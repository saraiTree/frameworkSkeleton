package com.object.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.object.base.Page;
import com.relevantcodes.extentreports.LogStatus;

public class ListenerUtility extends Page implements ITestListener{

	public void onTestStart(ITestResult result) 
	{
		test = extent.startTest(result.getName().toUpperCase());
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		System.setProperty("org.uncommons.reportng.escape-output","false");
		log.info("on test start for method "+result.getName().toUpperCase());
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log(result.getName().toUpperCase()+" IS PASSED");
		Reporter.log("<br>");
		Reporter.log("<br>");
		test.log(LogStatus.PASS, result.getName().toUpperCase()+" IS PASSED");
		extent.endTest(test);
		extent.flush();
		
	}

	public void onTestFailure(ITestResult result) 
	{
		System.setProperty("org.uncommons.reportng.escape-output","false");
		Utilities.getScreenshot();
		String name = Utilities.name;
		log.info("failed due to "+result.getThrowable());
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+" IS FAILED "+" error message is: "+result.getThrowable());
		//test.log(LogStatus.FAIL, test.addScreencast(System.getProperty("user.dir")+"/test-output/html/"+Utilities.name));
		test.log(LogStatus.FAIL, test.addScreenCapture(System.getProperty("user.dir")+"/test-output/html/"+Utilities.name));
		Reporter.log("Click to see Screenshot ");
		Reporter.log("<a target=\"_blank\" href="+name+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+name+"><img src="+name+" height=200 width=200></img></a>");
		
		extent.endTest(test);
		extent.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
