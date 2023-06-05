package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@name='checkout']")
	WebElement checkOutButton;
	
	@FindBy(css=".inventory_item_name")
	List<WebElement> cartProducts;
	
	public List<WebElement> getProductsInCart() {
		return cartProducts;
	}
	
	public Boolean checkWantedProductInCart(String wantedProduct) {
		Boolean isInCart = getProductsInCart().stream().anyMatch(p->p.getText().equalsIgnoreCase(wantedProduct));
		return isInCart;
	}
	
	public CheckOutPage checkOut() {
		checkOutButton.click();
		return new CheckOutPage(driver);
	}

}
