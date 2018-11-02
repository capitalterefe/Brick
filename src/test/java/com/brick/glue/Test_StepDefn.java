package com.brick.glue;

import java.util.List;

import com.brick.po.PetClinic_HomePage;
import com.brick.po.Test_Page2;
import com.brick.po.Test_Page3;
import com.brick.reusablecomponents.CucumberParams;

import cucumber.api.java.en.Given;

public class Test_StepDefn {
	
	PetClinic_HomePage petClinic_HomePage=new PetClinic_HomePage();
	Test_Page2 test_Page2=new Test_Page2();
	Test_Page3 test_Page3=new Test_Page3();
	
	@Given("^Admin Is on Pet Clinic Home Page$")
	public void pet_clinic_HomePage() {
		petClinic_HomePage.navigateToPetsClinicApp();
	}
	@Given("^Admin Register A New Owner$")
	public void registerAnewOwner(List<CucumberParams> ownerInformation) {
		petClinic_HomePage.navigateToOwnerRegistrationPage();
		petClinic_HomePage.completeOwnersRegistrationForm(ownerInformation);
	}
	@Given("^I am on Google App Home Page$")
	public void onGoogleHomePage() {
		test_Page3.navigateToGoogleApp();
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
