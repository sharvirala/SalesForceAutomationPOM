package com.salesforcepom.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.salesforce.utility.GenerateReportsPOM;

public class BasePage 
{
	static WebDriver driver;
	protected static  Logger log= Logger.getLogger(BaseTestPOM.class);
	 
	public BasePage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public static String getTiltle()
	
	{	
		String title=driver.getTitle();
		return title;
	}
	
	public static void switchToWindow()
	{
	//	driver.switchTo().w
	}
	
	
	public static void goToUrl(String url)
	 {
		driver.get(url);
		log.info("Url Entered is "+url);
	 }
	
	
	protected static void waitForImplicitWait(int seconds)
	{
		driver.manage().timeouts().implicitlyWait(seconds,TimeUnit.SECONDS );
	}

	
	public static void windowMaximize()
	{
		driver.manage().window().maximize();
		log.info("window maximized");
	}
	
	public static void enterText(WebElement element,String text,String objName)
	{
		//GenerateReportsPOM report = null;
		if(element.isDisplayed())
		{
		clearElement(element,objName);
		element.sendKeys(text);
		log.info("pass:"+objName+" text entered");
		}
		else
		{
			log.info("fail:"+objName+"text not entered");
		}
	}
	public  void salesForcelogin(String username,String password)
	{
		WebElement emailEle=driver.findElement(By.id("username"));
		waitUntilVisibilityOf(emailEle,"user name");
		enterText(emailEle,username,"user name");
		WebElement passwordEle=driver.findElement(By.id("password"));
		enterText(passwordEle,password,"password");
		WebElement login=driver.findElement(By.id("Login"));
		clickElement(login,"login button");
	}
	public  void usermenudropdown()
	{

		WebElement usermenuEle=driver.findElement(By.id("userNavLabel"));
		 waitUntilVisibilityOf(usermenuEle,"usemenu");
		clickElement(usermenuEle,"usermenu");
	}
	public static void pageScrollUp()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
	}
	public  void SFDCLogout()
	{
		usermenudropdown();
		WebElement logoutEle=driver.findElement(By.xpath(" //a[contains(text(),'Logout')]"));
		 waitUntilVisibilityOf(logoutEle,"logout");
		clickElement(logoutEle,"logout");
		log.info("Logout successfully");
	}
	
	public static void selectByValue(WebElement element,String value,String objName)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	
	
	public static void selectByIndex(WebElement element,int index,String objName)
	{
		Select s=new Select(element);
		s.selectByIndex(index);
	}
	
	public static void selectByVisibleText(WebElement element,String text,String objName)
	{
		Select s=new Select(element);
		s.selectByValue(text);
	}
	
	
	public static void clickElement(WebElement element,String objName)
	{
		
		waitforPageLoadTimeOut(80);
		element.click();
		//report.logTestInfo("click operation done on "+objName);
		
	}
	
	
	public static void waitUntilVisibilityOfElementLocated(By locator,String objName)
	{
		 WebDriverWait wait=new WebDriverWait(driver,30);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public static void waitUntilElementToBeClickable(By locator,String objName)
	{
		 WebDriverWait wait=new WebDriverWait(driver,30);
		  wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	
	public static void waitUntilAlertIsPresent()
	{
		 WebDriverWait wait=new WebDriverWait(driver,30);
		  wait.until(ExpectedConditions.alertIsPresent());
	}
	
	
	public static void clearElement(WebElement element,String objName)
	{
		if(element.isDisplayed())
		{
		element.clear();
		log.info("pass: "+objName+ " element cleared");
		}else
		{
			log.info("fail: "+objName+" element is not displayed");
		
		}
	}
	public  void findElement1(By locator,String value)
	{
		
	}
	
	//public static void login()
	public  void mouseOverAndClick(WebElement element,String objName)
	{
		waitUntilVisibilityOf( element, objName);
			Actions action=new Actions(driver);
		   action.moveToElement(element).build().perform();
		   action.click().build().perform();
		   log.info(" cursor moved to "+objName+" and click operation done");
	    
		}
	
	public static Alert switchToAlert()
	{
	return	driver.switchTo().alert();
	}
	
	
	public static void alertAccept(Alert alert,String objName)
	{
		alert.accept();
	}
	
	public static void alertDismiss(Alert alert,String objName)
	{
		alert.dismiss();
	}
	
	public   void waitUntilVisibilityOf(WebElement element,String objName)
	{
		 WebDriverWait wait=new WebDriverWait(driver,30);
		  wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	protected  static void waitforPageLoadTimeOut(int seconds)
	{
		driver .manage().timeouts().pageLoadTimeout(seconds,TimeUnit.SECONDS);
	}
	
	
	public static void findElement(By locator,String text)
	{
		
	}
	protected  String getText(WebElement element ,String objName)
	{
		
		String text=element.getText();
		return text;
	}
	

	public static void closeDriver()
	{
		log.info("driver closed");
		driver.close();
		
		
	}
	public static void closeAllDriver()
	{
		driver.quit();
	}
}
