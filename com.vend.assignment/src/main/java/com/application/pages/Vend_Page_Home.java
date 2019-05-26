package com.application.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.basesetup.BaseSetUp;

public class Vend_Page_Home extends BaseSetUp {
	
	static boolean isEventSuccessful=false;
	
	public Vend_Page_Home(WebDriver driver)
    {
    	this.driver = driver;
    	PageFactory.initElements(driver,this);
    }
	
	@FindBy(xpath = "//div[text()='Home']")
	public static WebElement tab_Home;
	
	@FindBy(xpath = "//div[text()='Sell']")
	public static WebElement tab_Sell;
	
	@FindBy(xpath = "//div[text()='Setup']")
	public static WebElement tab_setUp;
	
	@FindBy(xpath = "//a[@href='https://bestquality.vendhq.com/users']")
	public static WebElement tab_Users;
	
	@FindBy(xpath = "//a[@href='/users/add']")
	public static WebElement btn_AddUser;
	
	@FindBy(xpath = "(//a[@href='https://bestquality.vendhq.com/webregister'])[2]")
	public static WebElement tab_Sellregister;
	
	@FindBy(xpath = "//div[contains(text(),'Search for products')]")
	public static WebElement hd_SearchProduct;
	
	@FindBy(xpath = "(//div[@ng-if='vdQuickKeyCtrl.imageUrl'])[1]")
	public static WebElement tab_ProductName;
	
	@FindBy(xpath = "//button[@ng-click='$ctrl.selectProductValue(productValue)']")
	public static WebElement tab_NoOfProd;
	
	@FindBy(xpath = "//span[@ng-if='!saleCtrl.shouldShowDiscount']")
	public static WebElement link_Discount;
	
	@FindBy(xpath = "//input[@ng-model='$ctrl.displayDiscount']")
	public static WebElement txt_DiscountFiled;
	
	
	
	public static boolean verifyHomeTab() {
		
		try
		{
			
			WebDriverWait wait= new WebDriverWait(driver,20);
	          wait.until(ExpectedConditions.elementToBeClickable(tab_Home));
			if(tab_Home.isDisplayed())
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
	
	public static void clickTab(String tabName) {
		
			try {
				switch (tabName) {
				case "setUp":
					tab_setUp.click();

					break;
				case "users":

					tab_Users.click();
					break;
					
				case "sell":

					tab_Sell.click();
					break;
					
				case "sellregister":

					tab_Sellregister.click();
					break;
					
				case "prodName":

					tab_ProductName.click();
					break;
					
				case "prodSeries":

					tab_NoOfProd.click();
					break;
					
					default:
						
				
				}
			} catch (Exception e) {
				System.out.println("Exception occured" + e.getMessage());
			}
		}
	
	public Vend_Page_UserCreation clickAddUserButton()
	{
		try {
			btn_AddUser.click();
		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
		return new Vend_Page_UserCreation(driver);
	}
	
	public static String getSearchProHdText()
	{
		return hd_SearchProduct.getText();
	}
	
	public static void clickDisocuntLink()
	{
		try {
			link_Discount.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void clickAndSelectProd()
	{
		
	}
	
	public static boolean verifyDiscountFieldEnable()
	{
		if(txt_DiscountFiled.isEnabled())
			return true;
			else 
				return false;
	}
	
}
