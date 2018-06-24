package com.seleniummaven.appFunctions;

import com.seleniummaven.testBase.TestBase;
import com.seleniummaven.uiActions.Selenium;
import com.seleniummaven.uiActions.VerificationHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.seleniummaven.testBase.Config;
//import com.seleniummaven.testBase.TestBase;

public class AppSpecificFns extends TestBase
{
	private static final Logger logger = Logger.getLogger(AppSpecificFns.class);
	//TestBase testbase = new TestBase();
	//Config conf = new Config(OR);
	//Selenium selenium = new Selenium();
	
	
	/**
	 * @return true if login successful else return false
	 * @throws Exception
	 */
	public boolean loginToApp() throws Exception
	{
		selenium.openURL(config.getURL());
		selenium.clickElement("signin");
		selenium.typeText("email", config.getUserName());
		selenium.typeText("password", config.getPassword());
		selenium.clickElement("signinbtn");
		if(VerificationHelper.verifyElementPresent("signout"))
		{
			logger.info("Signin Successfull");
			return true;
		}
		else 
		{
			logger.info("Signin Unsuccessful");
			return false;
		}
	}
	
	/**
	 * @param locator - Element locator
	 * @param searchText - Text to be searched
	 * @param keyEnter - if pressenterkey, enter key will be pressed. if noenterkey, enter key will not get pressed
	 * @throws Exception
	 */
	public void searchProduct(String locator,String searchText,String keyEnter) throws Exception
	{
		selenium.openURL(config.getURL());
		selenium.typeText(locator,searchText);
		logger.info("Searching for "+searchText);
		if(keyEnter.equalsIgnoreCase("pressenterkey"))
			selenium.pressEnterBySendKeys(locator);
		else
			selenium.clickElement("searchbutton");
	}
	
	public boolean resultDisplay(String locator, String searchText) throws Exception
	{
		boolean isTextPresent=false;
		selenium.openURL(config.getURL());
		selenium.typeText(locator, "Dress");
		//Thread.sleep(4000);
		//selenium.sendKeyAction(locator, Keys.ARROW_DOWN);
		logger.info("Searching for "+searchText);
		String results[] =selenium.getList();	
		for(String s : results)
		{
			if(s.contains(searchText))
			{
				isTextPresent=true;
				logger.info("Searched text <"+searchText+"> is present in result : "+s);
			}
			else
			{
				isTextPresent=false;
				logger.info("Searched text <"+searchText+"> is not present in result : "+s);
				break;
			}
		}
		return isTextPresent;
	}
	
/*	public static void main(String args[]) throws Exception
	{
		AppSpecificFns as = new AppSpecificFns();
		as.loginToApp();
	}*/
}
