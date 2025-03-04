package akkiFrameworks.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akkiFrameworks.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".table.table-bordered.table-hover.ng-star-inserted tr td:nth-of-type(2)")
	List <WebElement> orderProduct;
	
	public Boolean verifyOrderPlaced(String productName)
	{
		Boolean OrderFound = orderProduct.stream().anyMatch(order -> order.getText().equalsIgnoreCase(productName));
		return OrderFound;
	}
}
