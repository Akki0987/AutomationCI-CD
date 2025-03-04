package akkiFrameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akkiFrameworks.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
//		First serve parent
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List <WebElement> products = driver.findElements(By.cssSelector("div.card-body"));

//	driver.findElement calls
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;
//	Page factory - only for driver.findElements calls

//	By calls
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button:nth-of-type(2)");
	By toastMessage = By.cssSelector("#toast-container");

//	Applying waits
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}

}
