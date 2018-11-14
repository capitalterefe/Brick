package com.brick.glue;

import static org.junit.Assert.assertTrue;

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
		assertTrue("Failed To Navigate To Registration Page",petClinic_HomePage.navigateToOwnerRegistrationPage());
		petClinic_HomePage.completeOwnersRegistrationForm(ownerInformation);
	}
	@Given("^Admin Verify Newly Registered User Exist in the Database$")
	public void verifyRegistration(List<CucumberParams> ownerInformation) {
		petClinic_HomePage.searchByFirst_name(ownerInformation);
		assertTrue("Registration Failed",petClinic_HomePage.verifyRegistrationSucceed());
	}
	@Given("^Admin Should Able To Search A Registered Owner with FirstName$")
	public void searchByFirstName(List<CucumberParams> ownerInformation) {
		petClinic_HomePage.searchByFirst_name(ownerInformation);
		assertTrue("Search Owner By First Name Failed",petClinic_HomePage.verifyOwnerInfoSearch());
	}
	@Given("^Admin Should Able To Search A Registered Owner with LastName$")
	public void searchByLastName(List<CucumberParams> ownerInformation) {
		petClinic_HomePage.searchByLast_name(ownerInformation);
		assertTrue("Search Owner By Last Name Failed",petClinic_HomePage.verifyOwnerInfoSearch());
	}
	@Given("^Admin Should Able To Search A Registered Owner with Address$")
	public void searchByAddress(List<CucumberParams> ownerInformation) {
		petClinic_HomePage.searchByAddress(ownerInformation);
		assertTrue("Search Owner By Address Failed",petClinic_HomePage.verifyOwnerInfoSearch());
	}
	@Given("^Admin Should Able To Search A Registered Owner with Telephone$")
	public void searchByTelephone(List<CucumberParams> ownerInformation) {
		petClinic_HomePage.searchByTelephone(ownerInformation);
		assertTrue("Search Owner By Telephone Failed",petClinic_HomePage.verifyOwnerInfoSearch());
	}
	@Given("^Admin Should Able To Search A Registered Owner with City$")
	public void searchByCity(List<CucumberParams> ownerInformation) {
		petClinic_HomePage.searchByCity(ownerInformation);
		assertTrue("Search Owner By City Failed",petClinic_HomePage.verifyOwnerInfoSearch());
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
