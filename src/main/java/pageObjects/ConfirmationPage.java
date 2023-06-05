package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@data-test='finish']")
	WebElement finishButton;
	
	@FindBy(css="h2[class='complete-header']")
	WebElement confirmationText;
	
	public void finishOrder() {
		finishButton.click();
	}
	
	public boolean isSuccessful() {
		return confirmationText.getText().equals("Thank you for your order!");
	}
	
	public String getSuccessMessage() {
		return confirmationText.getText();
	}

}
