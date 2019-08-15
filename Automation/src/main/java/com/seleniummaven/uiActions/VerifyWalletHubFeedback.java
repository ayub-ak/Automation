package com.seleniummaven.uiActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class VerifyWalletHubFeedback 
{
	static WebDriver driver = null;
	static WebDriverWait wait=null;
	static Actions action = null;
	
	public static void main(String[] args) 
	{	
		//Change the USERNAME and PASSWORD with working test account to perform login 
		String uname="ayub.ak@gmail.com",pword="Ayubkhan@1",profile="ayub_ak";
		String feedback="This is a sample text to verify feedback functionality. This is a sample text to verify feedback functionality. This is a sample text to verify feedback functionality";
		try
		{
			//Change the path where chromedriver.exe file is located
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			action = new Actions(driver);
			
			wait = new WebDriverWait(driver, 10);
			driver.get("https://wallethub.com/join/login");
			
			verifyElementVisibility("//*[@id=\"join-login\"]/form/div[1]/input");
			/*wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//*[@id=\"join-login\"]/form/div[1]/input")));*/
			driver.findElement(By.xpath("//*[@id=\"join-login\"]/form/div[1]/input")).sendKeys(uname);
			
			verifyElementVisibility("//*[@id=\"join-login\"]/form/div[2]/input");
			/*wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//*[@id=\"join-login\"]/form/div[2]/input")));*/
			driver.findElement(By.xpath("//*[@id=\"join-login\"]/form/div[2]/input")).sendKeys(pword);
			driver.findElement(By.xpath("//*[@id=\"join-login\"]/form/div[4]/button[2]")).click();
			
			//To ensure login is successful and user has landed on the home page
			verifyElementVisibility("//*[@id=\"viewport\"]/header/div/nav[3]/div[1]/a[1]");
			/*wait.until(ExpectedConditions.visibilityOfElementLocated	
					(By.xpath("//*[@id=\"viewport\"]/header/div/nav[3]/div[1]/a[1]")));*/
			
			driver.navigate().to("http://wallethub.com/profile/test_insurance_company/");
			verifyElementVisibility("//*[@id=\"scroller\"]/main/div[1]/nav/div[1]/a[1]");
			/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"scroller\"]/main/div[1]/nav/div[1]/a[1]")));*/
			
			JavascriptExecutor script = (JavascriptExecutor) driver;
			script.executeScript("window.scrollBy(0,400)", "");
			
			System.out.println("Mouse hover to 5th star");
			action.moveToElement(driver.findElement
					(By.cssSelector("#reviews-section > div.review-stat-box > div.review-action.ng-enter-element > review-star > div > svg:nth-child(5)"))).
						perform();
			
			//Check if the 5th star is highlighted
			if(driver.findElement(By.cssSelector("#reviews-section > div.review-stat-box > div.review-action.ng-enter-element > review-star > div > svg:nth-child(5) > g > path:nth-child(2)")).
					isDisplayed())
				System.out.println("5th Star is highlighted");
			else
				Assert.assertTrue("5th star is not highlighted", false);
			
			action.moveToElement(driver.findElement
					(By.cssSelector("#reviews-section > div.review-stat-box > div.review-action.ng-enter-element > review-star > div > svg:nth-child(5)"))).
						click().perform();
			
			verifyElementVisibility("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/ng-dropdown/div");
			/*wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/ng-dropdown/div")));*/
			driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/ng-dropdown/div")).click();
			verifyElementVisibility("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/ng-dropdown/div/ul/li[2]");
			/*wait.until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/ng-dropdown/div/ul/li[2]")));*/
			driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/ng-dropdown/div/ul/li[2]")).click();
			driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/div/div[1]/textarea")).sendKeys(feedback);
			driver.findElement(By.xpath("//*[@id=\"reviews-section\"]/modal-dialog/div/div/write-review/sub-navigation/div/div[2]")).click();
			verifyElementVisibility("//*[@id=\"scroller\"]/main/div/div/div[1]/h4");
			/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"scroller\"]/main/div/div/div[1]/h4")));*/
			String msg=driver.findElement(By.xpath("//*[@id=\"scroller\"]/main/div/div/div[1]/h4")).getText();
			if(msg.contains("Your review has been posted"))
			{
				System.out.println("Review comments has been posted successfully");
				Thread.sleep(7000);
				driver.navigate().to("https://wallethub.com/profile/"+profile);
				driver.findElement(By.xpath("//*[@id=\"wh-body-inner\"]/div[1]/div[1]/div[2]/ul/li[3]/a")).click();
				if(driver.findElement(By.xpath("//*[contains(@id,'review')]/p[2]")).getText().contains(feedback))
				{
					System.out.println("Verified the feedback comments");
					Assert.assertTrue(true);
				}
				else
				{
					System.out.println("Feedback verification failed");
					Assert.assertTrue(false);
				}
			}
			else
			{
				System.out.println("Problem with posting review comments");
				Assert.assertTrue(false);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
	
	public static void verifyElementVisibility(String xpath)
	{
			if(xpath!=null && !xpath.isEmpty())
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			}
			else
				System.out.println("Xpath is empty or null");	
	}

}
