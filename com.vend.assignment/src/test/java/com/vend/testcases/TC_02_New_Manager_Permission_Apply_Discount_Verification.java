package com.vend.testcases;

import java.util.Map;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.application.pages.Vend_Page_Account_Login;
import com.application.pages.Vend_Page_Home;
import com.assignment.basesetup.BaseSetUp;
import com.vend.utilLibrary.DataProviderRep_HashMap;

public class TC_02_New_Manager_Permission_Apply_Discount_Verification extends BaseSetUp {
	
	Vend_Page_Account_Login signInPgObj;
	Vend_Page_Home obj_home_page;
	private boolean flag;
	private String sellRegHdTxt;
	
	/* 
	 * Method Name - beforeMethod
	 * Test Objective: To initialize configuration file and setting up browser from base setup class before every test method
	 */
	
	@BeforeMethod
	public void beforeMethod() {
		initializeTestBaseSetup();
	}
	
	/** ################################################################################################################################
	 * TC_002_Manager_Apply_Discount_Permission_Verification
	 * Description: To verify the 'Apply Discount' permission for 'Manager' role. For instance, whether manager is allowed to apply 
	 * discount on items or not
	 * Precondition - N/A
	 * Test Case Type: Positive
	 * @param : mgrData (A hash map variable to fetch manager's data from excel test data sheet
	 * @throws N/A
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Manager should be created and added successfully in Vend Application
	 * 
	 * ################################################################################################################################
	 */
	
	@Test(dataProviderClass=DataProviderRep_HashMap.class,dataProvider="newManagerData",priority=1)
	public void TC_002_Manager_Apply_Discount_Permission_Verification(Map <String,String> data)
	{
		signInPgObj = new Vend_Page_Account_Login(driver);
		
		// Step 1: Enter user details and click on sign In button 
		
		flag = signInPgObj.verifyLoginPage();
		if (flag)
			System.out.println("User landed at correct home page");
		else
			System.out.println("User is incorrectly landed at home page");
		
		// Step 2: Enter newly created manager credentials and click on sign in button
		
		signInPgObj.enterSignInDetails("username", data.get("ManagerUserName"));
		signInPgObj.enterSignInDetails("password", data.get("ManagerDisplayName"));
		// click on sign in button
		
		obj_home_page=signInPgObj.clickSignInButton();
		
		// Step 3: verify home page is displayed by checking the 'Home' tab.
		
		flag=Vend_Page_Home.verifyHomeTab();
		Assert.assertTrue("Home page is not displayed",flag);
		
		
		// Step 4: Click on 'Sell' tab
		
		Vend_Page_Home.clickTab("sell");
		
		// Step 5: click on 'Sell' tab under 'Sell' main tab
		
		Vend_Page_Home.clickTab("sellregister");
		
		// Step 6: Check whether manager is routed to Sell Register page or not
		
		sellRegHdTxt=Vend_Page_Home.getSearchProHdText();
		if(sellRegHdTxt.equalsIgnoreCase("Search for products"))
			System.out.println("User is successfully opened sell register");
		else
			System.out.println("User is not successfully in opened sell register");
		
		// Step 7: Click on Product Name tab
		
		Vend_Page_Home.clickTab("prodName");
		
		// Step 8: Select the product series
		
		Vend_Page_Home.clickTab("prodSeries");
		
		// Step 9: Click on Discount link
		
		Vend_Page_Home.clickDisocuntLink();
		
		// Step 10 : Check that manager has the authority/or permission to apply the discount by checking the enability of discount text field
				
		flag=Vend_Page_Home.verifyDiscountFieldEnable();
		Assert.assertTrue("Apply Discount text box is not enabled",flag);
			
		
	}
	
	/*
	 * Method Name - afterMethod Test Objective: To quite the browser after every test method
	 */
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
