package com.brick.po;

import com.brick.reusablecomponents.WebDriverHelper;

public class Test_Page2 extends WebDriverHelper{

	public void navigateToSecondTestApp() {
		driver.navigate().to(properties.getProperty("second_testAppUrl"));
	}

}
