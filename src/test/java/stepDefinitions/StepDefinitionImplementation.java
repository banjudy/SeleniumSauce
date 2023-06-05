package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalog;
import testComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cart;
	public CheckOutPage order;
	public ConfirmationPage confirmationPage;
	
	
	//Scenario: Login to website
	
	@Given("Landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password (String name, String password) {
		productCatalog = landingPage.loginApplication(name, password);
	}
	
	@When("^I add product (.+) from Catalog$")
	public void add_product_from_catalog(String wantedProductName) {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(wantedProductName);
	}
	
	@Then("^(.+) message is displayed$")
	public void error_message_is_displayed(String error) {
		Assert.assertEquals(error, landingPage.getErrorMessage());
		driver.close();
	}
		
	@Then("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_order (String wantedProductName) {
		cart = productCatalog.clickOnCart();
		Assert.assertTrue(cart.checkWantedProductInCart(wantedProductName));
		order = cart.checkOut();
		order.enterDetails("Judit", "Ban", "2222");
		confirmationPage = order.checkOut();
		confirmationPage.finishOrder();
	}
	@Then("^(.+) message is displayed on ConfirmationPage$")
	public void message_is_displayed_on_confirmationpage(String message) {
		Assert.assertEquals(confirmationPage.getSuccessMessage(), message);
		driver.close();
	}
	

}
