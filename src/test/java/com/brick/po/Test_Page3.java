package com.brick.po;

import com.brick.reusablecomponents.WebDriverHelper;

public class Test_Page3 extends WebDriverHelper{

	public void navigateToGoogleApp() {
		driver.navigate().to(properties.getProperty("googlePage"));
	}

}
