package com.brick.glue;

import com.brick.po.Test_Page;

import cucumber.api.java.en.Given;

public class Test_StepDefn {
	
	Test_Page test_Page=new Test_Page();
	
	@Given("^I am on Test App Home Page$")
	public void onHomePage() {
		test_Page.navigateToTestApp();
	}
	@Given("^I login to Test App$")
	public void loginToTestApp() {
		
	}
	@Given("^I See The login Success Message$")
	public void seeSuccessPage() {
		
	}
	
}
