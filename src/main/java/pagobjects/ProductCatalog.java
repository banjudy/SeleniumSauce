package pagobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractcomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".inventory_item")
	List<WebElement> products;
	
	By productsBy = By.cssSelector(".inventory_item");
	By addToCart = By.cssSelector("button[id*='add-to-cart']");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductElementByName(String wantedProduct) {
		WebElement product = products.stream().filter(p->p.findElement(By.cssSelector("div[class='inventory_item_name']"))
				.getText().equals(wantedProduct)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String wantedProduct) {
		getProductElementByName(wantedProduct).findElement(addToCart).click();
	}

}
