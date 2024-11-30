package TestCases;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HamburgerMenu;
import Pages.Login_Page;
import Pages.Product_Page;
import Utility.ReadConfig;


public class Login_Testcases extends BrowserLaunch {

	private Login_Page loginPage;
	private ReadConfig readConfig;
	private Product_Page productPage;

	@BeforeMethod
	public void refresh() throws IOException, InterruptedException {

		loginPage = new Login_Page(driver);
		productPage = new Product_Page(driver);
		readConfig = new ReadConfig();
		pageRefresh();
	}

	public void login(String username, String password, String ls, String lp) {
		logger.info("----------Validating "+ ls +" UserName and "+ lp+" Password -----------");
		logger.info("Enter " + ls + " UserName");
		loginPage.enterusername(username);

		logger.info("Enter " + lp + " Password");
		loginPage.enterpassword(password);

		logger.info("Click Login Button");
		loginPage.clickLogin();
	}

	@Test(priority=6)
	public void validLoginDetail() throws IOException, InterruptedException {

		login(readConfig.getUsername(), readConfig.getPassword(), readConfig.getValid(), readConfig.getValid());

		Assert.assertEquals(productPage.getTitle(), readConfig.getProductTitle());

	}

	@Test(priority=1)
	public void invalidUserAndPass() throws IOException {

		login(readConfig.getInvalidUser(), readConfig.getInvalidPass(), readConfig.getInValid(), readConfig.getInValid());

		Assert.assertEquals(readConfig.getUserAndPassError(), loginPage.getError());

	}

	@Test(priority=2)
	public void blankPassword() throws IOException {

		login(readConfig.getUsername(), readConfig.getBlankTextFeild(), readConfig.getValid(), readConfig.getBlank());

		Assert.assertEquals(readConfig.getPassRequireError(), loginPage.getError());

	}

	@Test(priority=3)
	public void blankUsername() throws IOException {

		login(readConfig.getBlankTextFeild(), readConfig.getPassword(), readConfig.getBlank(), readConfig.getValid());

		Assert.assertEquals(readConfig.getUserRequireError(), loginPage.getError());

	}

	@Test(priority=4)
	public void blankUserAndPass() throws IOException {

		login(readConfig.getBlankTextFeild(), readConfig.getBlankTextFeild(), readConfig.getBlank(), readConfig.getBlank());

		Assert.assertEquals(readConfig.getUserRequireError(), loginPage.getError());

	}

	@Test(priority=5)
	public void lockedUser() throws IOException {

		login(readConfig.getLockedUsername(), readConfig.getPassword(), readConfig.getlocked(), readConfig.getValid());

		Reporter.getCurrentTestResult().getTestContext().setAttribute("expected", readConfig.getLockedUsernameError());
		Reporter.getCurrentTestResult().getTestContext().setAttribute("actual", loginPage.getError());

		Assert.assertEquals(readConfig.getLockedUsernameError(), loginPage.getError());

	}
	
	@Test(priority=7)
	public void problemUser() {
		
		login(readConfig.getProblemUsername(), readConfig.getPassword(), readConfig.getProblemUser(), readConfig.getValid());
		
		Assert.assertEquals(productPage.getTitle(), readConfig.getProductTitle());
		
	}
	
	

	@AfterMethod
	public void clickOncancel() {

		try {

			if (loginPage.isDisplayed()) {
				
				logger.info("Click Cancel Button");
				
				loginPage.clickCancel();
			} else {
				logger.info("SucessFully Login");
				logger.info("Click on Hamburger");
				new HamburgerMenu(driver).clickHamburgerIcon();

				logger.info("Click one Logout button");
				new HamburgerMenu(driver).clickLogout();
			}
		} catch (NoSuchElementException e) {

		}
	}

}
