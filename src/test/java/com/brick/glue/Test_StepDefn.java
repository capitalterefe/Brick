package com.brick.glue;

import com.brick.po.Test_Page;
import com.brick.po.Test_Page2;
import com.brick.po.Test_Page3;

import cucumber.api.java.en.Given;

public class Test_StepDefn {
	
	Test_Page test_Page=new Test_Page();
	Test_Page2 test_Page2=new Test_Page2();
	Test_Page3 test_Page3=new Test_Page3();
	
	@Given("^I am on Test App Home Page$")
	public void onHomePage() {
		test_Page.navigateToTestApp();
	}
	@Given("^I am on Google App Home Page$")
	public void onGoogleHomePage() {
		test_Page3.navigateToGoogleApp();
	}
	@Given("^I login to Test App$")
	public void loginToTestApp() {
		
	}
	@Given("^I See The login Success Message$")
	public void seeSuccessPage() {
		
	}
	@Given("^I am on Mable Test App Home Page$")
	public void secondHomePage() {
		test_Page2.navigateToSecondTestApp();
	}
	@Given("^I login Second to Test App$")
	public void loginToSecondTestApp() {
		
	}
	@Given("^I See Success Message$")
	public void seeSecondSuccessPage() {
		
	}
	
}
