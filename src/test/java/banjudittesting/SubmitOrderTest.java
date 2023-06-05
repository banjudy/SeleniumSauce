package banjudittesting;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckOutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductCatalog;
import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	//String productName = "";
	
	@Test(dataProvider="getData")
	public void submitOrderWithJson(HashMap<String,String> inputData) {
		ProductCatalog pc = landingPage.loginApplication(inputData.get("username"), inputData.get("password"));
		List<WebElement> productList = pc.getProductList();
		pc.addProductToCart(inputData.get("wantedProduct"));
		CartPage cart = pc.clickOnCart();
		
		Assert.assertTrue(cart.checkWantedProductInCart(inputData.get("wantedProduct")));
		
		CheckOutPage order = cart.checkOut();
		order.enterDetails("Judit", "Ban", "2222");
		
		ConfirmationPage confPage = order.checkOut();
		
		confPage.finishOrder();
		
		Assert.assertTrue(confPage.isSuccessful());
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + 
					"\\src\\test\\java\\testdata\\Order.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}

}
