package akkiFrameworks.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import akkiFrameworks.resources.ConfigureExtentReport;

public class testNGListeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ConfigureExtentReport.getExtentObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "Test executed Successfully");
	}
	
	
	@Override
	public void onTestFailure(ITestResult result)
	{
		extentTest.get().fail(result.getThrowable());
		
		
//		Get the driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		
//		Take screenshot
		String path = null;
		try {
			  path = takeScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		 Attach Screenshot
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	}
	
	
	@Override
	public void onFinish(ITestContext result)
	{
		extent.flush(); 
	}
}
