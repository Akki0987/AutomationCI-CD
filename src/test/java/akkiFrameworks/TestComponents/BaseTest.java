package akkiFrameworks.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import akkiFrameworks.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
//	At start driver is null and as it executes any condition the respected driver will be assigned to it 
	public WebDriver driver;
	public LandingPage lp;
	public WebDriver initializeDriver() throws IOException {

//		We want to maintain a global properties file where we store which browser we want to execute 
//		Based upon properties we set ex if set browser chrome then chrome browser code will be executed

//		For setting global properties there is a class in java called properties which can read the global properties

//		Properties class setup to get globalproperties from a file 

		Properties prop = new Properties();
//		Converts file to inputStream object
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\akkiFrameworks\\resources\\GlobalData.properties");
		prop.load(fis);

//		Using java ternary operator
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//		prop.getProperty("browser");

//		If value contains headless then headless argument will be sent to chrome to run the execution in headless mode
		if (browserName.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless"))
			{
				options.addArguments("--headless");
			}
			driver = new ChromeDriver(options);
//			Make sure browser runs in maximized mode 
			driver.manage().window().setSize(new Dimension(1440,900));
			
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
//			Firefox code here
			WebDriverManager.firefoxdriver().setup(); // ✅ Setup for Firefox
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
//			Edge code here
			WebDriverManager.edgedriver().setup(); // ✅ Setup for Edge
			driver = new EdgeDriver();
		} else {
			System.out.println("❌ Invalid browser name provided.");
			System.exit(0);
		}
		// ✅ Common Setup for All Browsers

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
//		1. Read json to string 
		String jsonContent = FileUtils.readFileToString(new File(filePath), 
            StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String , String>>>() {
			
		});
		return data;

	}

//	Land on the page
//	Now after globalresource setup i want that this should be executed before we touch any method

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		lp = new LandingPage(driver);
//		Scope is inside this block so we will make it global so that child classes xan use it directly
		lp.gotoWebsite();
//		return back so that we can use it in our testcase
		return lp;
	}
	
//	Screenshot utility
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser()
	{
		driver.close();
	}
}
