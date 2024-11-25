package TestCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.Cart_Page;
import Pages.Checkout_Information_Page;
import Pages.Login_Page;
import Pages.Product_Page;
import Utility.ReadConfig;

public class ProblemUser_TestCases extends BrowserLaunch {
	
	
	private Login_Testcases loginTest;
	private Product_TestCase productTest;
	private Product_Page productPage ;
	private Cart_Page cartPage;
	private Checkout_Information_Page checkoutInfo ;
	private ReadConfig readConfig;
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.localStorage.clear();");
		js.executeScript("window.sessionStorage.clear();");
		
		loginTest = new Login_Testcases();
		productTest = new Product_TestCase();
		productPage = new Product_Page(driver);
		cartPage = new Cart_Page(driver);
		checkoutInfo = new Checkout_Information_Page(driver);
		readConfig = new ReadConfig();
		
		loginTest.driver = this.driver;
		productTest.driver=this.driver;
		loginTest.refresh();
		loginTest.problemUser();
		
	}
	@Test
	public void ProductPageItemValdidation() throws IOException, InterruptedException {

		productTest.abc();
		productTest.validateItemDetail();
		
		
	}
	
	@Test
	public void DropBoxValidation() throws InterruptedException, IOException {
		
		productTest.abc();
		productTest.validateNameZtoA();
	}
	
	@Test
	public void ValidateAddToCartButton() {
		
		logger.info("Add to cart 2 Items");
		productPage.clickAddToCart();
		Assert.assertEquals(productPage.getNoOfItemsAdded(), "6");
	}
	
	@Test
	public void validateRemoveButton() {
		
		logger.info("Add to cart 2 Items");
		productPage.clickAddToCart2Items();
		
		logger.info("Remove all Items");
		productPage.removeItem();
		Assert.assertEquals(productPage.getNoOfItemsAdded(), "");
	}
	
	@Test
	public void validateInformation() throws IOException {
		SoftAssert softAssert = new SoftAssert();
		productTest.abc();
		productTest.addItem();
		cartPage.clickCheckout();
		
		try{
		logger.info("Enter First Name");	
		checkoutInfo.enterFirstName(readConfig.getFirstName());
		
		logger.info("Enter Last Name");
		checkoutInfo.enterLastName(readConfig.getLastName());
		
		logger.info("Enter Postal Code");
		checkoutInfo.enterPostalCode(readConfig.getPostalCode());
		
		
		softAssert.assertEquals(checkoutInfo.getFirstNameAttribute(), readConfig.getFirstName(), "First name is not match");
		softAssert.assertEquals(checkoutInfo.getLastNameAttribute(), readConfig.getLastName(), "Last name is not match");
		softAssert.assertEquals(checkoutInfo.getPostalCodeAttribute(), readConfig.getPostalCode(), "Postal Code is not match");
		
		}catch(Exception e){e.printStackTrace();}
		finally{softAssert.assertAll();}
	}
	
	
	@AfterMethod
	public void logoutMethod() {
		productTest.logout();
	}
}
