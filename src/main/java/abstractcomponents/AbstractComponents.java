package abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pagobjects.CartPage;


public class AbstractComponents {
	
	WebDriver driver;
	
	@FindBy(css="a[class='shopping_cart_link']")
	WebElement cart;
	
	//@FindBy(css="") //TODO
	//WebElement orderHeader;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear (WebElement webelement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.invisibilityOf(webelement));		
	}
	
	public CartPage clickOnCart() {
		cart.click();
		CartPage cart = new CartPage(driver);
		return cart;
	}
	
	/*public OrderPage clickOnOrders() {
		orderHeader.click();
		OrderPage orderHeader = new OrderPage(driver);
		return orderHeader;
		
	}*/
	
	/*public void scrollDown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver; 		//cast!
		js.executeScript("window.scrollBy(0,500)");	
		Thread.sleep(5000);
	}
	
	public void enterValue(WebElement ele, String value) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).click().sendKeys(value).build().perform();
	}*/
	
	public void closeDriver() {
		driver.close();
	}
	
	

}
