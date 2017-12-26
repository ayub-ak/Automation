package com.seleniummaven.testBase;

import java.util.Properties;

public class Config //extends TestBase
{
	private Properties OR;
	public Config(Properties OR)
	{
		this.OR = OR;
	}
	
	public String getURL()
	{
		return OR.getProperty("URL");
	}
	
	public String getFirefoxDriver()
	{
		return System.getProperty("user.dir")+OR.getProperty("ffdriverpartialpath");
	}
	
	public String getChromeDriver()
	{
		return System.getProperty("user.dir")+OR.getProperty("chromedriverpartialpath");
	}
	
	public String getIEDriver()
	{
		return System.getProperty("user.dir")+OR.getProperty("iedriverpartialpath");
	}
	
	public String getUserName()
	{
		return OR.getProperty("Username");
	}
	
	public String getPassword()
	{
		return OR.getProperty("Password");
	}

	public String getBrowser()
	{
		return OR.getProperty("Browser");
	}

	public String getPageLoadTimeOut()
	{
		return OR.getProperty("PageLoadTimeOut");
	}

	public String getImplcitWait()
	{
		return OR.getProperty("ImplcitWait");
	}

	public String getExplicitWait()
	{
		return OR.getProperty("ExplicitWait");
	}

	public String getLoggerLevel()
	{
		return OR.getProperty("Logger.Level");
	}
}
