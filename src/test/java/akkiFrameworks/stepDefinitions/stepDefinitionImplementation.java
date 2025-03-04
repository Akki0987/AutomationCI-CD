package akkiFrameworks.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import akkiFrameworks.TestComponents.BaseTest;
import akkiFrameworks.pageobjects.CartPage;
import akkiFrameworks.pageobjects.CheckOutPage;
import akkiFrameworks.pageobjects.ConfirmOrder;
import akkiFrameworks.pageobjects.LandingPage;
import akkiFrameworks.pageobjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinitionImplementation extends BaseTest {

//	Initializing objects globally so that they can be used outside their method too
	public LandingPage lp;
	public ProductCatalog PC;
	public CartPage cartPage;
	public CheckOutPage checkoutpage;
	public ConfirmOrder confirmorder;
	@Given ("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException
	{
		lp = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password_(String username, String password)
	{
		PC = lp.loginApplication(username, password);
	}
	
	@When ("^Add the product (.+) to cart$")
	public void add_the_product_to_cart(String productName)
	{
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_order(String productName)
	{
		cartPage = PC.goToCartPage();
		Boolean match = cartPage.getAllCartItems(productName);
		Assert.assertTrue(match);
		checkoutpage = cartPage.clickCheckout();
		String country = "India";
		checkoutpage.handleDropdown(country);
		confirmorder = checkoutpage.placeOrder();
	}
	
	@Then ("Verify {string} message displayed on confirmationPage")
	public void message_displayed_confirmationPage(String string)
	{
		String actual = confirmorder.getConfirmMessage();
		Assert.assertTrue(actual.equalsIgnoreCase(string));
		
		driver.close();
	}
	
	@Then("{string} wrong credentials message is displayed")
	public void wrong_credentials_message_is_displayed(String string)
	{
		Assert.assertEquals(string, lp.getErrorMessage());
		driver.close();
	}
}
