package akkiFrameworks.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import akkiFrameworks.AbstractComponents.AbstractComponent;

public class ConfirmOrder extends AbstractComponent {

	WebDriver driver;

	public ConfirmOrder(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h1.hero-primary")
	WebElement msgBox;

	public String getConfirmMessage() {
		String actual = msgBox.getText();
		return actual;
	}

}
