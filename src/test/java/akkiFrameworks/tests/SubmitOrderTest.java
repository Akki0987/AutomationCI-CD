package akkiFrameworks.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import akkiFrameworks.TestComponents.BaseTest;
import akkiFrameworks.pageobjects.CartPage;
import akkiFrameworks.pageobjects.CheckOutPage;
import akkiFrameworks.pageobjects.ConfirmOrder;
import akkiFrameworks.pageobjects.LandingPage;
import akkiFrameworks.pageobjects.OrdersPage;
import akkiFrameworks.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
//		Execute in baseTest as first method	LandingPage lp = launchApplication();

		ProductCatalog PC = lp.loginApplication(input.get("email"), input.get("password"));

//			2.  PRODUCT CATALOG
		List<WebElement> products = PC.getProductList();
		PC.addProductToCart(input.get("productName"));
		CartPage cartPage = PC.goToCartPage();

//			3. CARTPAGE
		Boolean match = cartPage.getAllCartItems(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartPage.clickCheckout();

//		4. CHECKOUT PAGE
		String country = "India";
		checkoutpage.handleDropdown(country);
		ConfirmOrder confirmorder = checkoutpage.placeOrder();

//		5. ORDER CONFIRMATION
		String actual = confirmorder.getConfirmMessage();
		Assert.assertTrue(actual.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//			driver.close(); Go to baseTest

//			Submit order, order history if any fails we will tell both failed so its tedious

	}

//		But if we break the tests into two tests then we can confidently tell that this test is failing
//		To Verify ZARA COAT 3 is displaying in orders page Separated 

//		Depends on submitOrder test -> Will only execute after submitorder Test

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {

		ProductCatalog PC = lp.loginApplication("akki@example.com", "Akki@098");

		OrdersPage orderspage = PC.orderHeader();

		Boolean OrderFound = orderspage.verifyOrderPlaced(productName);
		Assert.assertTrue(OrderFound);

	}

	

//	Extent Reports 
	
	
	
	
	
	
	
	
	
	
	
	
//		Data Type is object because data could be int, string we dont know 
//		So Object is a parent dataType for all this and is a generic datatype which accepts any kind of datatype
	@DataProvider
	public Object[][] getData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\java\\akkiFrameworks\\data\\PurchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToHashMap(filePath);
		return new Object[][] {
//	Now retrieve data from list
				{ data.get(0) }, { data.get(1) }

		};
	}

}
