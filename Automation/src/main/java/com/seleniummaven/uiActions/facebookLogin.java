package com.seleniummaven.uiActions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class facebookLogin {

	public static void main(String[] args) 
	{
		WebDriver driver = null;
		WebDriverWait wait=null;
		
		//Change the USERNAME and PASSWORD with working test account to perform login 
		String uname="testuser",pword="testpassword";
		
		try
		{
			//Change the path where chromedriver.exe file is located
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			//Launch browser
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
			//Navigate to facebook
			driver.get("https://www.facebook.com/");
			
			wait = new WebDriverWait(driver, 20);
			//Check if username textbox is available  
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"email\"]")));
			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(uname);
			//Check if password textbox is available 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pass\"]")));
			driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys(pword);
			//click on login button
			driver.findElement(By.xpath("//input[contains(@id,'u_0')]")).click();

			//Check if Home is available after login. If available, login is successful else login is unsuccessful 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Home')]")));
			if(driver.findElement(By.xpath("//a[contains(text(),'Home')]")).isDisplayed())
			{
				System.out.println("Login successful");
				Assert.assertTrue(true);
			}
			else
			{
				System.out.println("Login unsuccessful");
				Assert.assertTrue(false);
			}
			
			JavascriptExecutor script = (JavascriptExecutor)driver;
			//Display popup with message "Hello World"
			script.executeScript("alert('Hello World')");	
		
		}catch(Exception e)
		{
			Assert.assertTrue(false);
			e.printStackTrace();
		}

	}

}
