package TestCases;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

	@Test(priority = 6, enabled = true, groups = "E2E")
	public void validLoginDetail() throws IOException, InterruptedException {

		login(readConfig.getUsername(), readConfig.getPassword(), readConfig.getValid(), readConfig.getValid());

		Assert.assertEquals(productPage.getTitle(), readConfig.getProductTitle());

	}

	@Test(priority = 1)
	public void invalidUserAndPass() throws IOException {

		login(readConfig.getInvalidUser(), readConfig.getInvalidPass(), readConfig.getInValid(), readConfig.getInValid());

		Assert.assertEquals(readConfig.getUserAndPassError(), loginPage.getError());

	}

	@Test(priority = 2, enabled = true)
	public void blankPassword() throws IOException {

		login(readConfig.getUsername(), readConfig.getBlankTextFeild(), readConfig.getValid(), readConfig.getBlank());

		Assert.assertEquals(readConfig.getPassRequireError(), loginPage.getError());

	}

	@Test(priority = 3, enabled = true)
	public void blankUsername() throws IOException {

		login(readConfig.getBlankTextFeild(), readConfig.getPassword(), readConfig.getBlank(), readConfig.getValid());

		Assert.assertEquals(readConfig.getUserRequireError(), loginPage.getError());

	}

	@Test(priority = 4, enabled = true)
	public void blankUserAndPass() throws IOException {

		login(readConfig.getBlankTextFeild(), readConfig.getBlankTextFeild(), readConfig.getBlank(), readConfig.getBlank());

		Assert.assertEquals(readConfig.getUserRequireError(), loginPage.getError());

	}

	@Test(priority = 5)
	public void lockedUser() throws IOException {

		login(readConfig.getLockedUsername(), readConfig.getPassword(), readConfig.getlocked(), readConfig.getValid());

		Reporter.getCurrentTestResult().getTestContext().setAttribute("expected", readConfig.getLockedUsernameError());
		Reporter.getCurrentTestResult().getTestContext().setAttribute("actual", loginPage.getError());

		Assert.assertEquals(readConfig.getLockedUsernameError(), loginPage.getError());

	}
	
	@Test
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
			}
		} catch (NoSuchElementException e) {

		}
	}

}
