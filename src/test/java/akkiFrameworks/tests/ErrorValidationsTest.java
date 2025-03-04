package akkiFrameworks.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import akkiFrameworks.TestComponents.BaseTest;
import akkiFrameworks.TestComponents.Retry;
import akkiFrameworks.pageobjects.CartPage;
import akkiFrameworks.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
//	No need to create obj of landing page because baseTest is parent 	
		lp.loginApplication("akki@example.com", "IMAkki@098");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException
	{
		String productName = "ZARA COAT 3";		
		
		ProductCatalog PC = lp.loginApplication("akki@example.com", "Akki@098");

//		2.  PRODUCT CATALOG
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(productName);
		CartPage cartPage = PC.goToCartPage();

//		3. CARTPAGE
		Boolean match = cartPage.getAllCartItems("ZARA COAT 33");
//		Error check 
		Assert.assertFalse(match);

	}

}
