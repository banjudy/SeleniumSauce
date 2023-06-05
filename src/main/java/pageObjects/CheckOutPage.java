package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="input[name='firstName']")
	WebElement firstName;
	
	@FindBy(css="input[id='last-name']")
	WebElement lastName;
	
	@FindBy(css="input[name='postalCode']")
	WebElement zipCode;
	
	@FindBy(css="input[class*='submit-button']")
	WebElement submitOrder;
	
	public void enterDetails(String first, String last, String postal) {
		firstName.sendKeys(first);
		lastName.sendKeys(last);
		zipCode.sendKeys(postal);
	}
	
	public ConfirmationPage checkOut() {
		submitOrder.click();
		return new ConfirmationPage(driver);
	}

}
