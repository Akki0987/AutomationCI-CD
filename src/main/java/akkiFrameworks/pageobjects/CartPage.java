 package akkiFrameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akkiFrameworks.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div.cartSection h3")
	WebElement products;

	@FindBy(css = "div.cartSection h3")
	List<WebElement> productTitles;

	@FindBy(css = ".totalRow button")
	WebElement checkOutButton;

	public Boolean getAllCartItems(String productName) {
		Boolean match = productTitles.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));

		return match;
	}

	public CheckOutPage clickCheckout() {
		checkOutButton.click();

		return new CheckOutPage(driver);
		

	}

}
