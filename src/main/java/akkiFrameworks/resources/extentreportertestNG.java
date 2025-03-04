package akkiFrameworks.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentreportertestNG {

	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+"//report//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "AKKI");
		extent.setSystemInfo("OS", "Windows");
		
		return extent;
	}
	
	
}
