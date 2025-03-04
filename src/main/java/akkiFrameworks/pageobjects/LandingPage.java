package akkiFrameworks.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akkiFrameworks.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	//		Bring driver from actual test script , by sending driver to this class using constructor
		public LandingPage(WebDriver driver)
		{
//			send driver to parent sends variables to parent 
			super(driver);
//			initialization
			this.driver = driver;
//			Driver will be assigned using it -> 
			PageFactory.initElements(driver, this);
		}
		
		
//		Landing page locators - pushing locators that belongs to landing page
//		WebElement userEmails = driver.findElement(By.id("userEmail"));
		
//		Page factory
//		At run time it will be constructed like - driver.findElement(By.id("userEmail"))
		
		
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id = "userPassword")
		WebElement userPassword;
		
		@FindBy(id = "login")
		WebElement submit;
		
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		public String getErrorMessage()
		{
//			Error message is taking some time to appear
				waitForWebElementToAppear(errorMessage);
		    	return errorMessage.getText();
		}
		
//		Merging all the login activity into one action
//		Send email here from testcase
		
//		Action method
		public ProductCatalog loginApplication(String email, String password)
		{
			userEmail.sendKeys(email);
			userPassword.sendKeys(password);
			submit.click();
//			After this we are redirecting to productCataLog Page so initialize it here
			
			 return new ProductCatalog(driver);
			
		}
		
		public void gotoWebsite()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
	}

