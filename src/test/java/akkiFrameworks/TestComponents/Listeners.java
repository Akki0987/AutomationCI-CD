package akkiFrameworks.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import akkiFrameworks.resources.extentreportertestNG;

public class Listeners extends BaseTest implements ITestListener { 
	ExtentTest test;
	ExtentReports extent = extentreportertestNG.getReportObject();
	@Override
	public void onTestStart(ITestResult result)
	{
//		Here the result variable holds info about the test which is going to get executed
//		Create entry for test in extent report 
//		Here everytime we have to provide the test name 
//		Using the result variable we will smartly get the test name 
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{
//		test.log(Status.FAIL, "Test Failed");
		test.fail(result.getThrowable());
//		Take screenshot 
		
//		Get driver from result 
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		String filePath = null;
		String testName = result.getMethod().getMethodName();
		try {
			filePath = getScreenshot(testName, driver);
//			Attaches the screenshot taken from his path to the report 
		} catch (IOException e) {
			e.printStackTrace();
		}
//		This method takes the screenshot path from our local system and attaches it to the report with the test name
		test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result)
	{
		test.log(Status.SKIP, "Test Skipped");
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
	
	
}
