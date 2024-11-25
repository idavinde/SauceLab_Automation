package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	static final String file = "./src/main/resources/Data/config.properties";
	Properties props;

	public ReadConfig() throws IOException {

		props = new Properties();
		File f = new File(file);
		FileInputStream fis = new FileInputStream(f);
		props.load(fis);
	}

	public String getUsername() {
		return props.getProperty("username");
	}

	public String getPassword() {
		return props.getProperty("password");
	}
	
	public String getProblemUsername() {
		return props.getProperty("problemUsername");
	}
	
	public String getProblemUser() {
		return props.getProperty("problemUser");
	}

	public String getInvalidUser() {

		return props.getProperty("invalidUser");
	}

	public String getInvalidPass() {

		return props.getProperty("invalidPass");
	}

	public String getUserAndPassError() {
		return props.getProperty("userAndPassNotMatch");
	}

	public String getPassRequireError() {

		return props.getProperty("passRequire");
	}

	public String getUserRequireError() {

		return props.getProperty("userRequire");
	}

	public String getLockedUsername() {

		return props.getProperty("lockedUsername");
	}

	public String getLockedUsernameError() {

		return props.getProperty("lockedUserError");
	}

	public String getItemName(String s) {

		return props.getProperty(s);
	}

	public String getItemPrice(String s) {

		return props.getProperty("p" + s);
	}

	public String getNameAtoZ() {

		return props.getProperty("NameAToZ");
	}

	public String getNameZtoA() {

		return props.getProperty("NameZToA");
	}

	public String getPriceHtoL() {

		return props.getProperty("PriceHToL");
	}

	public String getPriceLtoH() {

		return props.getProperty("PriceLToH");
	}

	public String getFirstName() {

		return props.getProperty("firstName");
	}

	public String getLastName() {

		return props.getProperty("lastName");
	}

	public String getPostalCode() {

		return props.getProperty("postalCode");
	}

	public String getProductTitle() {

		return props.getProperty("productPageTitle");
	}

	public String getValid() {

		return props.getProperty("valid");
	}

	public String getInValid() {

		return props.getProperty("invalid");
	}

	public String getBlank() {

		return props.getProperty("blank");
	}
	
	public String getBlankTextFeild() {
		
		return props.getProperty("blankTextField");
	}
	
	public String getlocked() {
		
		return props.getProperty("lockedUser");
	}

}
