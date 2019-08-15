package com.seleniummaven.search;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.seleniummaven.appFunctions.AppSpecificFns;
import com.seleniummaven.helper.Logger.LoggerHelper;
import com.seleniummaven.testBase.TestBase;
import com.seleniummaven.uiActions.Selenium;

public class test 
{
	AppSpecificFns as = new AppSpecificFns();
	private final static Logger logger = LoggerHelper.getLogger(test.class);
	
	@Test
	public void testmethod() throws Exception 
	{
		/*logger.info("Test msg 1"); */
		boolean val =as.loginToApp();
		System.out.println("Value : "+val);
	}
	
	@Test
	public void testFacebookLogin()
	{
		Selenium.testLogin();
	}
	
	@Test
	public void addReviewForWalletHub()
	{
		Selenium.addReviewAndVerify();
	}
	
}
