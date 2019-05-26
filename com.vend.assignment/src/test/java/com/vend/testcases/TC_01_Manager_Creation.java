package com.vend.testcases;

import java.text.ParseException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.application.pages.Vend_Page_Account_Login;
import com.application.pages.Vend_Page_Home;
import com.application.pages.Vend_Page_UserCreation;
import com.assignment.basesetup.BaseSetUp;
import com.vend.utilLibrary.DataProviderRep_HashMap;

public class TC_01_Manager_Creation extends BaseSetUp {

	Vend_Page_Account_Login signInPgObj;
	boolean flag = false;
	Vend_Page_Home obj_home_page;
	Vend_Page_UserCreation obj_usercreation_page;
	private String userName;
	private String mgrUserName = "testmgr001";
	private String emailAddress;
	private String displayName;

	/*
	 * Method Name - beforeMethod Test Objective: To initialize configuration file
	 * and setting up browser from base setup class before every test method
	 */
	@BeforeMethod
	public void beforeMethod() {
		initializeTestBaseSetup();
	}
	

	/** ################################################################################################################################
	 * TC_001_Create_Manager Valid data
	 * Description: To verify the addition of new manager in Vend application
	 * Precondition - N/A
	 * Test Case Type: Positive
	 * @param : mgrData (A hash map variable to fetch manager's data from excel test data sheet
	 * @throws ParseException 
	 * @Author: Hitesh Ghai
	 * @Expected Result: New Manager should be created and added successfully in Vend Application
	 * 
	 * ################################################################################################################################
	 */

	@Test(dataProviderClass = DataProviderRep_HashMap.class, dataProvider = "newManagerData", priority = 1)
	public void TC_001_Create_Manager(Map<String, String> mgrData) {
		// userName = prop.getProperty("UserName");
		userName = mgrData.get("ManagerUserName");

		// password = prop.getProperty("Password");
		displayName = mgrData.get("ManagerDisplayName");
		emailAddress = mgrData.get("ManagerEmailAddress");
		
		// Step 1: Verify user is on Vend Account Login Page or not
		
		signInPgObj = new Vend_Page_Account_Login(driver);
		flag = signInPgObj.verifyLoginPage();
		if (flag)
			System.out.println("User landed at correct home page");
		else
			System.out.println("User is incorrectly landed at home page");
		
		// Step 2: Enter user details and click on sign In button 
		
		signInPgObj.enterSignInDetails("username", userName);
		signInPgObj.enterSignInDetails("password", "Pyramid123#");
		obj_home_page = signInPgObj.clickSignInButton();

		// Step 3: verify user is on home page checking the 'Home' tab.

		flag = Vend_Page_Home.verifyHomeTab();
		Assert.assertTrue(flag, "Home page is not displayed");

		// Step 4: click on 'Set Up' tab

		Vend_Page_Home.clickTab("setUp");

		// Step 5: Click on 'Users' tab

		Vend_Page_Home.clickTab("users");
		obj_usercreation_page = obj_home_page.clickAddUserButton();

		// Step 6: Verify user is routed to User Creation page

		flag = Vend_Page_UserCreation.verifyUserCreationPage();
		Assert.assertTrue(flag, "User creation page is displayed");

		// Step 7 : Manager User creation steps

		// Step 7.1 Enter Manager's User name

		Vend_Page_UserCreation.enterUserDetails("manager", "userName", mgrUserName);

		// Step 7.2 Enter Manager's Display name

		Vend_Page_UserCreation.enterUserDetails("manager", "displayName", displayName);
		
		// Step 7.3 Enter Manager's Email Address

		Vend_Page_UserCreation.enterUserDetails("manager", "email", emailAddress);

		// Step 7.4 Select Manager's outlet

		Vend_Page_UserCreation.selectDropDownValue("outlet");

		// Step 7.5 Enter Manager's Role 
		
		Vend_Page_UserCreation.selectDropDownValue("role");

		// Step 7.6 Enter Manager's Security password
		Vend_Page_UserCreation.enterUserDetails("manager", "secPassword", mgrUserName);
		
		// Step 7.7 Enter Manager's Confirm security password

		Vend_Page_UserCreation.enterUserDetails("manager", "secRepeatPassword", mgrUserName);
			
		// Step 7.8 Save Manager details by clicking on Save button
		
		Vend_Page_UserCreation.clickSaveBtn();
		
		// Step 8:  Verify Manager is created or saved successfully or not by getting and comparing the manager name
		
		String userName = Vend_Page_UserCreation.getUserText();
		if (mgrUserName.equalsIgnoreCase(userName))
			System.out.println("Manager created successfully");
		else
			System.out.println("Manager could not be created successfully");

	}

	/*
	 * Method Name - afterMethod Test Objective: To quite the browser after every
	 * test method
	 */
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
