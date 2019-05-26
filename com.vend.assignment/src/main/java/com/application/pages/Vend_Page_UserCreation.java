package com.application.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.basesetup.BaseSetUp;

public class Vend_Page_UserCreation extends BaseSetUp{
	static boolean isEventSuccessful=false;

	private static String userText;
	
	public Vend_Page_UserCreation(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(xpath = "//a[@href='/users/']")
	public static WebElement hd_Users;
	
	@FindBy(name = "name")
	public static WebElement txt_UserName;
	
	@FindBy(xpath = "(//input[contains(@class,'vd-input')])[2]")
	public static WebElement txt_DisplayName;
	
	@FindBy(xpath = "//input[@type='email']")
	public static WebElement txt_Email;
	
	@FindBy(xpath = "//input[@placeholder='Select an Outlet']")
	public static WebElement dropDown_Outlet;
	
	@FindBy(xpath = "//input[@class='vd-checkbox-input']")
	public static WebElement dropDown_MainOutlet;
	
	@FindBy(xpath = "//select[contains(@class,'vd-select')]")
	public static WebElement dropDown_Role;
	
	@FindBy(name = "newPassword")
	public static WebElement txt_SecPassword;
	
	@FindBy(name = "confirmPassword")
	public static WebElement txt_RptsePassword;
	
	@FindBy(name = "confirmPassword")
	public static WebElement btn_Save;
	
	@FindBy(xpath = "//div[@class='vd-section-wrap']")
	public static WebElement hd_userName;
	
	//button[@role='button']
	
	
	
	
	
	
	public static boolean verifyUserCreationPage()
	{
		try {
			WebDriverWait wait= new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.elementToBeClickable(hd_Users));
			if(hd_Users.isDisplayed())
				isEventSuccessful=true;
			else
				isEventSuccessful=false;
		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return isEventSuccessful;
	
        	
	}
	
	public static void enterUserDetails(String userType,String inputDataType,String inputData)
	{
		if(userType.equalsIgnoreCase("manager"))
		{
			switch (inputDataType) {
			case "userName":
				txt_UserName.clear();
				txt_UserName.sendKeys(inputData);

				break;
			case "displayName":
				txt_DisplayName.clear();
				txt_DisplayName.sendKeys(inputData);
				break;
				
			case "email":
				txt_Email.clear();
				txt_Email.sendKeys(inputData);
				break;
				
			case "secPassword":
				txt_SecPassword.clear();
				txt_SecPassword.sendKeys(inputData);
				break;
				
			case "secRepeatPassword":
				txt_RptsePassword.clear();
				txt_RptsePassword.sendKeys(inputData);
				break;
			
			}
		}
	}
	
	public static void selectDropDownValue(String dropDownType)
	{
		
			try {
				switch (dropDownType) {
				case "outlet":
					dropDown_Outlet.click();
					dropDown_MainOutlet.click();
					break;
				case "role":
					
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView();", dropDown_Role);
					dropDown_Role.click();
					Select select= new Select(dropDown_Role);
					
					select.selectByVisibleText("Manager");
					
				break;
				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public static void clickSaveBtn() 
	{
		try {
			btn_Save.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getUserText()
	{
		return userText=hd_userName.getText();
	}
	

}
