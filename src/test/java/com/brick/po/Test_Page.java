package com.brick.po;

import com.brick.reusablecomponents.WebDriverHelper;

public class Test_Page extends WebDriverHelper{

	public void navigateToTestApp() {
		driver.navigate().to(properties.getProperty("testAppUrl"));
	}

}
