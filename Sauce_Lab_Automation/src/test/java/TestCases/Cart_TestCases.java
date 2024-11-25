package TestCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Cart_Page;
import Pages.Checkout_Information_Page;
import Pages.HamburgerMenu;
import Pages.Login_Page;
import Pages.Product_Detail_Page;
import Pages.Product_Page;
import Utility.ReadConfig;

public class Cart_TestCases extends BrowserLaunch {

	private Product_TestCase ProductTest;
	private Cart_Page cartPage;
	private Product_Detail_Page productDetail;

	@BeforeMethod
	public void LoginAndAddItems() throws IOException, InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.localStorage.clear();");
		js.executeScript("window.sessionStorage.clear();");
		
		cartPage = new Cart_Page(driver);
		productDetail = new Product_Detail_Page(driver);

		ProductTest = new Product_TestCase();
		ProductTest.driver = this.driver;
		ProductTest.FirstPageLogin();
		ProductTest.addItem();

	}

	@Test
	public void validateItemsNameAndPrice() {

		logger.info("------------- Validate Item Name ans Price--------------");

		int itemsListSize = cartPage.getAllItemsNames().size();

		for (int i = 0; i < itemsListSize; i++) {

			String productPageItemName = cartPage.getAllItemsNames().get(i).getText();

			String productPageItemPrice = cartPage.getAllItemsPrice().get(i).getText();

			cartPage.clickItemName(i);

			String productDetailPageItemName = productDetail.getItemNameText();

			String productDetailPageItemPrice = productDetail.getItemPriceText();

			Assert.assertEquals(productDetailPageItemName, productPageItemName);

			Assert.assertEquals(productDetailPageItemPrice, productPageItemPrice);

			productDetail.clickShoppingCart();
		}

	}

	@Test
	public void validateRemoveButton() {
		logger.info("----------------Validate the remove button-------------");

		logger.info("Click on Remove Button");
		cartPage.clickRemoveBtn();

		Assert.assertEquals(cartPage.getTotalItems(), "");
	}

	@Test
	public void validateCheckoutButton() {
		logger.info("------------ Validate the Checkout Button---------------");

		logger.info("Click On Checkout");
		cartPage.clickCheckout();

		Assert.assertEquals(new Checkout_Information_Page(driver).getTitle(), "Checkout: Your Information");
	}

	@Test
	public void validateContinueShoppingButton() {
		logger.info("-------------Valdiate the Continue Shopping Button------------");

		logger.info("Click On Continue Shopping Button");
		cartPage.clickContinueShopping();

		Assert.assertEquals(new Product_Page(driver).getTitle(), "Products");
	}

	@AfterMethod
	public void logout() {
		logger.info("Click on Hamburger");
		new HamburgerMenu(driver).clickHamburgerIcon();

		logger.info("Click one Logout button");
		new HamburgerMenu(driver).clickLogout();
	}

}
