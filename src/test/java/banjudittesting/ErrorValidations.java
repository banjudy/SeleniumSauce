package banjudittesting;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductCatalog;
import testComponents.BaseTest;
import testComponents.Retry;

public class ErrorValidations extends BaseTest{
	
	//validate if locked user can log in
	@Test(retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {
		ProductCatalog pc = landingPage.loginApplication("locked_out_user", "secret_sauce");
		String errormessage = landingPage.getErrorMessage();
		Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", landingPage.getErrorMessage());
	}
	
	//validate if selected product is in the cart
	@Test
	public void ProductErrorValidation() {
		String wantedProductName = "Sauce Labs Bike Light";
		ProductCatalog pc = landingPage.loginApplication("standard_user", "secret_sauce");
		List<WebElement> availProducts = pc.getProductList();
		pc.addProductToCart(wantedProductName);
		CartPage cart = pc.clickOnCart();
		
		Assert.assertTrue(cart.checkWantedProductInCart(wantedProductName));
	}

}
