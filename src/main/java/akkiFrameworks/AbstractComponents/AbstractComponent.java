package akkiFrameworks.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import akkiFrameworks.pageobjects.CartPage;
import akkiFrameworks.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartButton;

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement Orders;
	
	public OrdersPage orderHeader()
	{
		Orders.click();
		return new OrdersPage(driver);
	}
	
	
}
