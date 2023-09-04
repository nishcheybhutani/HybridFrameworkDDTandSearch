package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_001_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginDataSauceDemo")
	public void test_Login(String user, String pwd, String exp) throws IOException, InterruptedException {

		try {
			logger.info("Started TC_001_LoginDDT ");
			
			driver.get(rb.getString("saucedemoURL"));
			logger.info("opened sauce demo url");

			LoginPage lp = new LoginPage(driver);

			lp.setUsername(user);
			logger.info("entered username");
			Wait();
			lp.setPassword(pwd);
			logger.info("entered password");
			Wait();
			lp.clickLogin();
			logger.info("clicked Login button");
			Thread.sleep(3000);

			// validation
			if (exp.equals("Valid")) {
				if (driver.getTitle().equals("Swag Labs")) {
					logger.info("test_Login passed");
					Thread.sleep(2000);
					captureScreen(driver, "PassedValid_Login");
					lp.clickSandwichIcon();
					lp.clickLogout();
					Assert.assertTrue(true);

				} else {
					Thread.sleep(2000);
					captureScreen(driver, "test_Login");
					logger.info("test_Login failed");
					Assert.assertTrue(false);

				}
			} else if (exp.equals("Invalid")) {

				// if (driver.getTitle().equals("Swag Labs")) {
				// Assert.assertTrue(false);
				// captureScreen(driver, "test_Login");
				// logger.info("test_Login failed");
				// lp.clickSandwichIcon();
				// logger.info("clicked sandwich icon");
				// lp.clickLogout();
				// logger.info("clicked logout button");
				//
				// } else {
				// Assert.assertTrue(true);
				// logger.info("test_Login passed");
				// }
				if (lp.isInvalidDisplayed()) {
					lp.dispInvalidMsg();
					logger.info("test_Login passed");
					captureScreen(driver, "PassedInvalid_Login");
					Thread.sleep(2000);
					Assert.assertTrue(true);

				} else {
					logger.info("test_Login failed");
					captureScreen(driver, "test_Login");
					Thread.sleep(2000);
					Assert.assertTrue(false);
				}
			}
		} catch (Exception e) {
			captureScreen(driver,"test_Login");
			Thread.sleep(2000);
			Assert.assertTrue(false);
		}
		
		logger.info("Finished TC_001_LoginDDT ");
	}

	@DataProvider(name = "LoginDataSauceDemo")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\LoginDataSauceDemo.xlsx";// taking xl file from testData

		XLUtility xlutil = new XLUtility(path);// creating an object for xlutils

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcols];// created for two dimension array which can store the
																// data user and password

		for (int i = 1; i <= totalrows; i++) // 1 //read the data from xl storing in two deminsional array
		{
			for (int j = 0; j < totalcols; j++) // 0 i is rows j is col
			{
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
			}
		}
		return logindata;// returning two deminsion array

	}

}
