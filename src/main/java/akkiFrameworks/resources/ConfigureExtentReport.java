package akkiFrameworks.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ConfigureExtentReport {

	public static ExtentReports getExtentObject()
	{
		String path = System.getProperty("user.dir")+"//TestReports//report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setDocumentTitle("Test Result");
		reporter.config().setReportName("Error handling report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "AKKI");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("System", "Asus tuf gaming a15");
		
		return extent;
	}
}
