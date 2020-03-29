package com.wallethub.com;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.SendKeysAction;

import com.seleniummaven.uiActions.Selenium;

public class LoginPage extends Selenium 
{
	public boolean loginCheck()
	{
		login();
		return true;
	}
	
	public void login()
	{
		selenium.openURL("https://wallethub.com/join/login");
		driver.findElement(By.xpath("//*[@id=\"join-login\"]/form/div[1]/input")).sendKeys("ayub.ak@gmail.com");;
	}
}
