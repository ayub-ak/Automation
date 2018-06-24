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
	public void validateSearch() throws Exception //"Printed dress" and "5 results have been found" are hardcoded that needs to be changed based on given input
	{
		fns.searchProduct("searchbox", "Printed dress","noenterkey");
		if(selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
				"5 results have been found"))
		{
			logger.info("Search successful");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Search failed");
			Assert.assertTrue(false);
		}
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
		if(selenium.waitForTextToAppear(15, OR.getProperty("searchitemtxt"),
				"Printed Summer Dress"))
		{
			logger.info("Able to Search and Open Product details Page");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Search an item and open product details page is failed");
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * @throws Exception
	 * Search - No results should get displayed when searched item not present  
	 */
	@Test
	public void noResultsFound() throws Exception
	{
		fns.searchProduct("searchbox", "dummy data","noenterkey");
		if(selenium.waitForTextToAppear(15, OR.getProperty("noresults"),
						"No results were found for your search \"dummy data\""))
		{
			logger.info("No Results found");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Issue in identifying the msg - No results were found");
			Assert.assertTrue(false); 
		}
	}
	
	/**
	 * Search - User should be able to search by pressing enter after typing the item name
	 * @throws Exception
	 */
	@Test
	public void searchItemByEnterKey() throws Exception
	{
		fns.searchProduct("searchbox", "Printed dress","pressenterkey");
		if(selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
				"5 results have been found"))
		{
			logger.info("Result displayed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Result not displayed");
			Assert.assertTrue(false);
		}
	}
	
	/**
	 * Search - numbers, special characters should be acceptable 
	 * @throws Exception 
	 */
	@Test
	public void searchUsingSpecialChar() throws Exception
	{
		fns.searchProduct("searchbox", "Faded Short Sleeve T-shirts","noenterkey");
		if(selenium.waitForTextToAppear(15, OR.getProperty("searchitemspecialchar"),
				"Faded Short Sleeve T-shirts"))
		{
			logger.info("Successfully performed search by using Special Character");
			Assert.assertTrue(true);
		}
		else		
		{
			logger.info("Search using special charter is unsuccessful");
			Assert.assertTrue(false);
		}
			
	}
	
	@Test
	public void searchResultDisplay() throws Exception
	{
		if(fns.resultDisplay("searchbox", "Dress"))
		{
			logger.info("Searched text is present in search list");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Searched text is not present in search list");
			Assert.assertTrue(false);
		}
		
	}
}
