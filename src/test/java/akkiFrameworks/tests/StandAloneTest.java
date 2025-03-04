package akkiFrameworks.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		
//		Added comments to check trigger works correctly
//		1. Get the webpage 
		driver.get("https://rahulshettyacademy.com/client");
		
//		2. Enter login details and login
		driver.findElement(By.id("userEmail")).sendKeys("akki@example.com");
		driver.findElement(By.id("userPassword")).sendKeys("Akki@098");
		driver.findElement(By.id("login")).click();
		
//		3. Add Zara Coat 3 to cart
		
		List <WebElement> products = driver.findElements(By.cssSelector("div.card-body"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		WebElement item = products.stream()
		.filter(product -> product.findElement(By.tagName("h5")).getText()
		.equals(productName))
		.findFirst().orElse(null);
		
		item.findElement(By.cssSelector("button:nth-of-type(2)")).click();
		
//		Waiting for toast to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
//		Waiting until animation disappears
		WebElement toast = driver.findElement(By.cssSelector(".ng-animating"));
		wait.until(ExpectedConditions.invisibilityOf(toast));
		
		
//		4. Click checkout
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		
//		5. Get all the items from cart and match ZARA COAT 3
		
		List <WebElement> cartItems =	driver.findElements(By.cssSelector("div.cartSection h3"));
		Boolean match = cartItems.stream()
		.anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match, "Item not found in the cart");
		
		
//		6. Click checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		7. Handle the dropdown click india
		Actions action = new Actions(driver);
		WebElement dropdown = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		action.sendKeys(dropdown, "India")
		.build()
		.perform();
		
//		Get all options and click "India"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")));
		driver.findElement(By.cssSelector(".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")).click();
		
//		8. Place Order
		driver.findElement(By.cssSelector("div.actions a")).click();
		
//		9. Put assertion to verify successful text displaying
		String actual = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		System.out.println(actual);
		Assert.assertTrue(actual.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "Different message ");
		
		driver.close();
//		driver.quit();
	}

}