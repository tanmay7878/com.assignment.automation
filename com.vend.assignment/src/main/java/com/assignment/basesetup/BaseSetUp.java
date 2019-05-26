package com.assignment.basesetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseSetUp {
	public static Properties prop;
	private String app_Url;
	private String browserType;
	public static final Logger logger = Logger.getLogger(Test.class.getName());
	public static WebDriver driver;
	
	public BaseSetUp() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//assignment//configuration//configuration.properties");
			prop.load(ip);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initializeTestBaseSetup() {
		app_Url = prop.getProperty("App_URL");
		browserType = prop.getProperty("Browser_Type");
		try {

			if (browserType.contains("Firefox")) {

				//System.setProperty("webdriver.gecko.driver",
						//System.getProperty("user.dir") + "\\" + "Browser Exes/geckodriver.exe");
				logger.info("Creating a object of Firefox Browser");
				logger.info("Navigating to " + app_Url + "for Firefox browser");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.get(app_Url);
				driver.manage().window().maximize();
			}

			else if (browserType.contains("Chrome")) {
				//String currentDir = System.getProperty("user.dir");
				//String chromeDriverLocation = currentDir + "/Browser Exes/chromedriver.exe";
				logger.info("Creating a object of Chrome Browser");
				System.out.println("Launching Chrome browser......");
				//System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
				WebDriverManager.chromedriver().setup();
				logger.info("Navigating to " + app_Url + "for Chrome browser");
				driver = new ChromeDriver();
				driver.get(app_Url);
				driver.manage().window().maximize();

			}

		} catch (Exception e) {
			System.out.println("Error....." + e.getMessage());
		}

	}
	

}
