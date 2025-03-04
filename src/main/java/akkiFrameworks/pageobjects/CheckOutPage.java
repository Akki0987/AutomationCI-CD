package akkiFrameworks.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akkiFrameworks.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement dropdown;

	@FindBy(css = ".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")
	WebElement india;

	@FindBy(css = "div.actions a")
	WebElement placeButton;

	By option = By.cssSelector(".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)");

	public void handleDropdown(String country) {
		Actions action = new Actions(driver);
		action.sendKeys(dropdown, country).build().perform();

//		Get all options and click "India"
		waitForElementToAppear(option);
		india.click();

	}

	public ConfirmOrder placeOrder() {
		placeButton.click();

		return new ConfirmOrder(driver);
		
	}
}
