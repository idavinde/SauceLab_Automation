package TestCases;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Cart_Page;
import Pages.Checkout_Information_Page;
import Pages.Checkout_Overview_Page;
import Pages.HamburgerMenu;
import Pages.Login_Page;
import Pages.Product_Page;
import Utility.ReadConfig;

public class Checkout_Information_TestCases extends BrowserLaunch {

	private Cart_TestCases CartTest;
	private Checkout_Information_Page checkoutInfo;
	private ReadConfig readConfig;

	@BeforeMethod
	public void LoginInform() throws IOException, InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.localStorage.clear();");
		js.executeScript("window.sessionStorage.clear();");

		checkoutInfo = new Checkout_Information_Page(driver);
		readConfig = new ReadConfig();

		CartTest = new Cart_TestCases();
		CartTest.driver = this.driver;
		CartTest.LoginAndAddItems();
		CartTest.validateCheckoutButton();

	}

	public void enterAllDetails(String firstName, String lastName, String postalCode, String lf, String ll, String lp) {

		logger.info("Enter " + lf + " FirstName");
		checkoutInfo.enterFirstName(firstName);

		logger.info("Enter " + ll + " LastName");
		checkoutInfo.enterLastName(lastName);

		logger.info("Enter " + lp + " PostalCode");
		checkoutInfo.enterPostalCode(postalCode);

		logger.info("Click Continue Button");
		checkoutInfo.clickContinueBtn();

	}

	@Test(priority = 5)
	public void enterValidInformation() throws IOException {

		enterAllDetails(readConfig.getFirstName(), readConfig.getLastName(), readConfig.getPostalCode(), readConfig.getValid(),
				readConfig.getValid(), readConfig.getValid());

		Assert.assertEquals(new Checkout_Overview_Page(driver).getTitle(), "Checkout: Overview");
	}

	@Test(priority = 2)
	public void enterBlankFirstName() throws IOException {

		enterAllDetails(readConfig.getBlank(), readConfig.getLastName(), readConfig.getPostalCode(), readConfig.getBlank(), readConfig.getValid(), readConfig.getValid());

		Assert.assertEquals(new Checkout_Information_Page(driver).getErrorText(), "Error: First Name is required");
	}

	@Test(priority = 3)
	public void enterBlankLastName() throws IOException {

		enterAllDetails(readConfig.getFirstName(), readConfig.getBlank(), readConfig.getPostalCode(), readConfig.getValid(), readConfig.getBlank(), readConfig.getValid());

		Assert.assertEquals(new Checkout_Information_Page(driver).getErrorText(), "Error: Last Name is required");

	}

	@Test(priority = 4)
	public void enterBlankPostalCode() throws IOException {

		enterAllDetails(readConfig.getFirstName(), readConfig.getLastName(), readConfig.getBlank(), readConfig.getValid(), readConfig.getValid(), readConfig.getBlank());

		Assert.assertEquals(new Checkout_Information_Page(driver).getErrorText(), "Error: Postal Code is required");

	}

	@Test(priority = 1)
	public void enterBlankAllField() throws IOException {

		enterAllDetails(readConfig.getBlank(), readConfig.getBlank(), readConfig.getBlank(), readConfig.getBlank(), readConfig.getBlank(), readConfig.getBlank());

		Assert.assertEquals(new Checkout_Information_Page(driver).getErrorText(), "Error: First Name is required");

	}

	@Test
	public void validateCancelBtn() {
		logger.info("Click on Canacel Button");
		new Checkout_Information_Page(driver).clickCancelBtn();

		Assert.assertEquals(new Cart_Page(driver).getTitle(), "Your Cart");
	}

	@AfterMethod
	public void logout() {
		logger.info("Click on Hamburger");
		new HamburgerMenu(driver).clickHamburgerIcon();
		
		logger.info("Click one Logout button");
		new HamburgerMenu(driver).clickLogout();
	}

}
