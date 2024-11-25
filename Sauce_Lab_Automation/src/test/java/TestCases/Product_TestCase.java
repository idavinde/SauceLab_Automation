package TestCases;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Pages.HamburgerMenu;
import Pages.Login_Page;
import Pages.Product_Detail_Page;
import Pages.Product_Page;
import Utility.ReadConfig;
import Utility.Report;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;

public class Product_TestCase extends BrowserLaunch {

	private Login_Page loginPage;
	private ReadConfig readConfig;
	private Product_Page productPage;
	private Login_Testcases loginTest;
	private Product_Detail_Page productDetail;

	@BeforeMethod
	public void FirstPageLogin() throws IOException, InterruptedException {
		
		
		productPage = new Product_Page(driver);
		productDetail = new Product_Detail_Page(driver);
		readConfig = new ReadConfig();
		loginTest = new Login_Testcases();

		loginTest.driver = this.driver;
		loginTest.refresh();
		loginTest.validLoginDetail();

	}

	public void abc() throws IOException {

		productPage = new Product_Page(driver);
		productDetail = new Product_Detail_Page(driver);
		readConfig = new ReadConfig();
		

	}

	@Test
	public void validateItemDetail() throws InterruptedException, IOException {

		int itemSize = productPage.getAllInventoryItemsName().size();

		logger.info("----------- Validate All Item Name and Price =-----------------");

		//SoftAssert softAssert = new SoftAssert();

		for (int i = 0; i < itemSize; i++) {

			String productItemName = productPage.getAllInventoryItemsName().get(i).getText();
			
			String productItemPrice = productPage.getAllInventoryItemsPrice().get(i).getText();

			productPage.clickInventoryItemName(i);

			String productDetailPageItemName = productDetail.getItemNameText();

			String productDetailPageItemPrice = productDetail.getItemPriceText();

			try {
				Assert.assertEquals(productItemName, productDetailPageItemName, "Item name does not match");
			} catch (Exception e) {
				handleFailure( productItemName );
			}

			try {
				Assert.assertEquals(productItemPrice, productDetailPageItemPrice, "Item Price does not match");
			} catch (Exception e) {
				
				handleFailure( productItemPrice);
			}

			productDetail.clickBackToProduct();

		}
		
	}

	@Test
	public void validateNameAtoZ() throws InterruptedException, IOException {

		logger.info("----------- Validate Item Name from dropbox A to Z-----------------");

		List<String> actual = new LinkedList<>();

		List<String> expected = new LinkedList<>();

		logger.info("Select the A to Z Item Name from dropbox");
		productPage.selectDropbox(readConfig.getNameAtoZ());

		logger.info("Get all Item Name");
		actual = productPage.getDropboxInventoryName();

		expected = nameReverseOrder(actual.size(), expected, 0);

		Assert.assertEquals(actual, expected);

		logger.info("Click on Add to cart button");
		checkAddToCartButton();

	}

	@Test
	public void validateNameZtoA() throws InterruptedException, IOException {

		logger.info("----------- Validate Item Name from dropbox Z to A-----------------");

		List<String> actual = new LinkedList<>();

		List<String> expected = new LinkedList<>();

		logger.info("Select the Z to A Item Name from dropbox");
		productPage.selectDropbox(readConfig.getNameZtoA());

		logger.info("Get all Item Name");
		actual = productPage.getDropboxInventoryName();

		expected = nameReverseOrder(actual.size(), expected, 1);

		Assert.assertEquals(actual, expected);

		logger.info("Click on Add to cart button");
		checkAddToCartButton();

	}

	@Test
	public void validatePriceHtoL() throws InterruptedException, IOException {

		logger.info("----------- Validate Item Price from dropbox High to Low-----------------");

		List<String> actual = new LinkedList<>();

		List<String> expected = new LinkedList<>();

		logger.info("Select the High to Low Item Price from dropbox");
		productPage.selectDropbox(readConfig.getPriceHtoL());

		logger.info("Get all Item Price");
		actual = productPage.getDropboxInventoryPrice();

		expected = priceReverseOrder(actual.size(), expected, 0);

		Assert.assertEquals(actual, expected);

		logger.info("Click on Add to cart button");
		checkAddToCartButton();

	}

	@Test
	public void validatePriceLtoH() throws InterruptedException, IOException {

		logger.info("----------- Validate Item Price from dropbox Low to High-----------------");

		List<String> actual = new LinkedList<>();

		List<String> expected = new LinkedList<>();

		logger.info("Select the Low to High Item Price from dropbox");
		productPage.selectDropbox(readConfig.getPriceLtoH());

		logger.info("Get all Item Price");
		actual = productPage.getDropboxInventoryPrice();

		expected = priceReverseOrder(actual.size(), expected, 1);

		Assert.assertEquals(actual, expected);

		logger.info("Click on Add to cart button");
		checkAddToCartButton();
	}

	@Test
	public void checkAddToCartButton() {

		logger.info("---------- Check add to Cart Button-------------");

		logger.info("Add to cart all items");
		productPage.clickAddToCart();
		Assert.assertEquals(productPage.getNoOfItemsAdded(), "6");

		logger.info("Remove all Items");
		productPage.removeMultiple();
		Assert.assertEquals(productPage.getNoOfItemsAdded(), "");
	}

	@Test
	public void addAndRemoveItems() throws InterruptedException {

		logger.info("---------- Add the item and Remove the item-------------");

		logger.info("Add to cart 2 Items");
		productPage.clickAddToCart2Items();
		Assert.assertEquals(productPage.getNoOfItemsAdded(), "2");

		logger.info("Click on shopping cart icon");
		productPage.clickShoppingLink();
		Assert.assertEquals(productPage.getNoOfItemsAdded(), "2");

		logger.info("Click on remove and remove both items");
		productPage.removeItem();
		Assert.assertEquals(productPage.getNoOfItemsAdded(), "");

	}

	@Test
	public void addItem() {
		logger.info("---------- Add to Cart 2 item-------------");

		logger.info("Add to cart 2 Items");
		productPage.clickAddToCart2Items();

		logger.info("click on shopping cart icon");
		productPage.clickShoppingLink();

	}

	@AfterMethod
	public void logout() {
		logger.info("Click on Hamburger");
		new HamburgerMenu(driver).clickHamburgerIcon();

		logger.info("Click one Logout button");
		new HamburgerMenu(driver).clickLogout();
	}
}
