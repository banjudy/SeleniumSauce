package banjudittesting;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceStandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String wantedProduct = "Sauce Labs Bike Light";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@data-test='username']")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("input[name='login-button']")).click();
		
		//list all the products
		List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));
		
		//get the name of products and filter for the wanted one
		WebElement product = products.stream().filter(p->p.findElement(By.cssSelector("div[class='inventory_item_name']")).getText()
				.equals(wantedProduct)).findFirst().orElse(null);
		
		//look for the chosen product's Add To Cart
		product.findElement(By.cssSelector("button[id*='add-to-cart']")).click();
		
		//go to cart
		driver.findElement(By.cssSelector("a[class='shopping_cart_link']")).click();
		
		//check if chosen product(s) is in the cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".inventory_item_name"));
		Boolean isInCart = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(wantedProduct));
		Assert.assertTrue(isInCart);
		
		//checkout
		driver.findElement(By.xpath("//button[@name='checkout']")).click();
		
		//enter name and zip code
		driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("Judit");
		driver.findElement(By.id("last-name")).sendKeys("Ban");
		driver.findElement(By.cssSelector("input[name='postalCode']")).sendKeys("2222");
		driver.findElement(By.cssSelector("input[class*='submit-button']")).click();
		
		//place the order
		driver.findElement(By.xpath("//button[@data-test='finish']")).click();
		
		//validate whether order has been placed
		Assert.assertTrue(driver.findElement(By.cssSelector("h2[class='complete-header']")).
					getText().equalsIgnoreCase("Thank you for your order!"));
		
		driver.close();
		
	}
	
	/*private static String getProductName(WebElement p) {
		String productName = p.findElement(By.cssSelector("div[class='inventory_item_name']")).getText();
		return productName;
	}*/

}
