package pagobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponents;

public class LangingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LangingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@data-test='username']")
	WebElement usernameElement;
	
	@FindBy(id="password")
	WebElement passwordElement;
	
	@FindBy(css="input[name='login-button']")
	WebElement loginElement;
	
	@FindBy(css="h3[data-test='error']")
	WebElement errorLogin;
	
	public ProductCatalog loginApplication(String name, String password) {
		usernameElement.sendKeys("standard_user");
		passwordElement.sendKeys("secret_sauce");
		loginElement.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public void goToLandingPage() {
		driver.get("https://www.saucedemo.com/");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorLogin);
		String errorText = errorLogin.getText();
		return errorText;
	}
	

}
