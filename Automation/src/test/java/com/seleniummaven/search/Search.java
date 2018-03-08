package com.seleniummaven.search;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.seleniummaven.appFunctions.AppSpecificFns;
import com.seleniummaven.helper.Logger.LoggerHelper;
import com.seleniummaven.testBase.Config;
import com.seleniummaven.testBase.TestBase;
import com.seleniummaven.uiActions.Selenium;

import junit.framework.Assert;

/**
 * Search functionality 
 */

public class Search extends TestBase
{
	private final static Logger logger = LoggerHelper.getLogger(Search.class);
	
	/**
	 * @throws Exception
	 * @author AyubKhan
	 * Search - User should be able to perform search operation
	 */
	@Test
	public void validateSearch() throws Exception //"Printed dress" and "5 results have been found" are hardcode that needs to be changed based on given input
	{
		fns.searchProduct("searchbox", "Printed dress","noenterkey");
		logger.info("Results Label WebElement appearance status : "+
				selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
						"5 results have been found"));
		Assert.assertTrue("Expected result is matched",
			selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
						"5 results have been found"));
	}
	
	/**
	 * @throws Exception
	 * Search - User should be able to search an item and open product details page
	 */
	@Test
	public void OpenProdPageBySearch() throws Exception
	{
		fns.searchProduct("searchbox", "Printed dress","noenterkey");
		selenium.clickElement("searchresultitem");
		logger.info("OpenProdPageBySearch status : "+
				selenium.waitForTextToAppear(15, OR.getProperty("searchitemtxt"),
						"Printed Summer Dress"));
		Assert.assertTrue("Expected result is matched", 
				selenium.waitForTextToAppear(15, OR.getProperty("searchitemtxt"),
						"Printed Summer Dress"));
	}
	
	/**
	 * @throws Exception
	 * Search - No results should get displayed when searched item not present  
	 */
	@Test
	public void noResultsFound() throws Exception
	{
		fns.searchProduct("searchbox", "dummy data","noenterkey");
		logger.info("noResultsFound : "+
				selenium.waitForTextToAppear(15, OR.getProperty("noresults"),
						"No results were found for your search \"dummy data\""));		
		Assert.assertTrue("Expected result is matched", 
				selenium.waitForTextToAppear(15, OR.getProperty("noresults"),
						"No results were found for your search \"dummy data\""));
		//selenium.getTextFromWebElement("noresults").contains("No results were found for your search")
	}
	
	/**
	 * Search - User should be able to search by pressing enter after typing the item name
	 * @throws Exception
	 */
	@Test
	public void searchItemByEnterKey() throws Exception
	{
		fns.searchProduct("searchbox", "Printed dress","pressenterkey");
		logger.info("Results Label WebElement appearance status : "+
				selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
						"5 results have been found"));
		Assert.assertTrue("Expected result is matched",
			selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
						"5 results have been found"));
	}
	
	/**
	 * Search - numbers, special characters should be acceptable 
	 * @throws Exception 
	 */
	@Test
	public void searchUsingSpecialChar() throws Exception
	{
		fns.searchProduct("searchbox", "Faded Short Sleeve T-shirts","noenterkey");
		logger.info("searchUsingSpecialChar status : "+
				selenium.waitForTextToAppear(15, OR.getProperty("searchitemspecialchar"), 
						"Faded Short Sleeve T-shirts"));
		Assert.assertTrue("Expected result is matched",
			selenium.waitForTextToAppear(15, OR.getProperty("searchitemspecialchar"), 
						"Faded Short Sleeve T-shirts"));
	}
}
