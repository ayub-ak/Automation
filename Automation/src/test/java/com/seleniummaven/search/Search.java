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

public class Search extends TestBase
{
	private final static Logger logger = LoggerHelper.getLogger(Search.class);
	
	@Test
	public void validateSearch() throws Exception //"Printed dress" and "5 results have been found" are hardcode that needs to be changed based on given input
	{
		fns.searchProduct("searchbox", "Printed dress");
		logger.info("Results Label WebElement appearance status : "+
				selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
						"5 results have been found"));
		Assert.assertTrue("Expected result is matched",
			selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"), 
						"5 results have been found"));
	}
	
	@Test
	public void OpenProdPageBySearch() throws Exception
	{
		fns.searchProduct("searchbox", "Printed dress");
		selenium.clickElement("searchresultitem");
		Assert.assertTrue("Expected result is matched", 
				selenium.waitForTextToAppear(15, OR.getProperty("searchresultstext"),
						"Printed Summer Dress"));
	}
	
}
