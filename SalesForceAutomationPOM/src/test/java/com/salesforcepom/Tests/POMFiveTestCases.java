package com.salesforcepom.Tests;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.pages.homepages.Homepage;
import com.salesforce.pages.loginPages.Loginpage;
import com.salesforce.utility.CommonUtilities;
import com.salesforcepom.base.BaseTestPOM;
//@Listeners(com.salesforce.utility.listener.class)
public class POMFiveTestCases extends BaseTestPOM
{
	static SoftAssert softAssert = new SoftAssert();
@Test(enabled = true)
public static void testCase1() throws IOException, InterruptedException
{
	Loginpage login=new Loginpage(driver);
	String username=CommonUtilities.getPropertyValue("username");
	login.enterUsername(username);
	login.clickLoginBtn();
	takeScreenShot("loginerrormessageTC1.jpg");
	String expectedmsg="Please enter your password.";
	String actualmsg=login.getErrormsg();
	softAssert.assertEquals(actualmsg, expectedmsg);
	report.logTestPassed();
	report.attachScreenshot("loginerrormessageTC1.jpg");
	softAssert.assertAll();
	
}
@Test(enabled = true)
public static void testCase2() throws IOException, InterruptedException
{
	

	String expectedtext="Welcome to free trial";
	Loginpage login=new Loginpage(driver);
	String username=CommonUtilities.getPropertyValue("username");
	//login.enterUsername(username);
	
	String password=CommonUtilities.getPropertyValue("password");
	login.salesforceLogin( username, password);
	
	takeScreenShot("salesforcehomepagetext.jpg");
	Homepage homepage=new Homepage(driver);
	Thread.sleep(5000);
	String actualtext=homepage.gethomepagetext();
	softAssert.assertEquals(actualtext, expectedtext);
	
	
	report.attachScreenshot("loginerrormessageTC1.jpg");
report.logTestFailed();
softAssert.assertAll();
	
}
	
	

@Test(enabled = true)
public static void testCase3() throws IOException, InterruptedException
{
	Loginpage login=new Loginpage(driver);
	String username=CommonUtilities.getPropertyValue("username");
	
	
	String password=CommonUtilities.getPropertyValue("password");
	login.enterUsername(username);
	login.enterPassword(password);
	
	login.clickOnRememberMeCheckBox();
	login.clickLoginBtn();
	Homepage homepage=new Homepage(driver);
	homepage.usermenu();
	homepage.sfcdLogOut();
	String expected=username;
	Loginpage login1=new Loginpage(driver);
	String actual=login1.getusername();
	softAssert.assertEquals(actual, expected);
	report.logTestPassed();
	softAssert.assertAll();
}

}
