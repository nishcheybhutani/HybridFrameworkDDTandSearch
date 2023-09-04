package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_002_Search extends BaseClass {

	@Test
	public void test_search() throws IOException, InterruptedException {

		try {
			logger.info("Started TC_003_Search");

			driver.get(rb.getString("searchURL"));
			logger.info("Search URL opened");

			SearchPage sp = new SearchPage(driver);

			sp.inputSearch("iphoneXR"); //-----------> XR
			logger.info("Search Keyword entered");
			sp.clickSearch();
			logger.info("Search button clicked");

			if (sp.isMsgExists()) {
				captureScreen(driver, "test_search");
				logger.info("Searched item does not exist, search failed");
				Assert.assertTrue(false);

			} else {
				if (sp.getProductName().equals("iphoneXR")) { 
					captureScreen(driver, "PassedSearch");
					logger.info("Searched item exist, search passed");
					Assert.assertTrue(true);
				}
			}
		} catch (IOException e) {
			captureScreen(driver, "test_search");
			Thread.sleep(2000);
			Assert.assertTrue(false);
		}
		logger.info("Finished TC_002_Search");

	}

}
