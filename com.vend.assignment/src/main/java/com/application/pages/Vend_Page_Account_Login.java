package com.application.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.assignment.basesetup.BaseSetUp;

public class Vend_Page_Account_Login extends BaseSetUp{
	
static boolean isEventSuccessful=false;
	
	public Vend_Page_Account_Login(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(name = "signin[username]")
	public static WebElement txt_userName;
	
	@FindBy(name = "signin[password]")
	public static WebElement txt_password;
	
	@FindBy(name = "signin_submit")
	public static WebElement btn_SignIn;
	
	public boolean verifyLoginPage()
	{
		try
        {
           
        	if(btn_SignIn.isDisplayed())
        		isEventSuccessful=true;
        	else
        		isEventSuccessful=false;
        }

        catch(Exception e)
        {
         System.out.println("Exception occured" + e.getMessage());
        }
		return isEventSuccessful;
	
    }
	
	public void enterSignInDetails(String dataType, String inputData) {
		{
			try {
				switch (dataType) {
				case "username":
					// txt_emailAdd.clear();
					txt_userName.sendKeys(inputData);

					break;
				case "password":

					//txt_password.clear();
					txt_password.sendKeys(inputData);
					break;
				
				}
			} catch (Exception e) {
				System.out.println("Exception occured" + e.getMessage());
			}
		}
	}
	
	public Vend_Page_Home clickSignInButton() {
		try {
			btn_SignIn.click();
		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return new Vend_Page_Home(driver);
	}
	

}
