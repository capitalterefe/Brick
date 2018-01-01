package com.brick.reusablecomponents;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.serenitybdd.core.pages.PageObject;

public class Hook extends PageObject {
	private static WebDriver driver;
	private static Properties properties;
	// private static ReadPropertyFile data;

	public static void setPropertyFiles() {
		try {
			properties=new Properties();
			properties.load(Hook.class.getClassLoader().getResourceAsStream("config.properties"));
			properties.load(new FileInputStream("serenity.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void setUp() {
		setPropertyFiles();
		driver=getDriver();
		WebDriverHelper.driver=driver;
		WebDriverHelper.properties=properties;
	}
	
	@After
	public void tearDown() {
		if(driver!=null) {
			driver.manage().deleteAllCookies();
			driver.close();
			driver.quit();
		}
			
	}

}
