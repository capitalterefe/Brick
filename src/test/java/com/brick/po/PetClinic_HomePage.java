package com.brick.po;

import java.util.List;

import org.openqa.selenium.By;

import com.brick.reusablecomponents.CucumberParams;
import com.brick.reusablecomponents.WebDriverHelper;

import net.serenitybdd.core.Serenity;

public class PetClinic_HomePage extends WebDriverHelper{
	 By ownersLink=By.xpath("//a[contains(text(),'Owners')]");
	 By allOwners=By.xpath("//a[@href='#!/owners']");
	 By registerLink=By.xpath("//span[contains(text(),'Register')]");
	 By firstNameElement=By.xpath("//input[@name='firstName']");
	 By lastNameElement=By.xpath("//input[@name='lastName']");
	 By addressElement=By.xpath("//input[@name='address']");
	 By cityElement=By.xpath("//input[@name='city']");
	 By telephoneElement=By.xpath("//input[@name='telephone']");
	 By submitElement=By.xpath("//button[text()='Submit']");
	 By searchBox=By.xpath("//input[@placeholder='Search Filter']");
	 By owner_1=By.xpath("//tr[@class='ng-scope'][1]//a");
	 By add_new_pet=By.xpath("//a[contains(@href,'new-pet')]");
	 By pet_name=By.xpath("//input[@name='name']");
	 By pet_birth_date=By.xpath("//input[@type='date']");
	 By pet_type=By.xpath("//select");
	 By owner_information_title=By.xpath("//h2[text()='Owner Information']");
	 By submit_newPet=By.xpath("//button[@type='submit']");
	 By reasonForVisit=By.xpath("//textarea");
	 String add_visit_link="(//a[text()='%s']/ancestor::td/following-sibling::td//a[text()='Add Visit'])[1]";
	 String visit_discription="(//td[text()='%s'])[1]";
	public void navigateToPetsClinicApp() {
		driver.navigate().to(properties.getProperty("petClinicUrl"));
	}

	public boolean navigateToOwnerRegistrationPage() {
		clickOn(ownersLink);
		clickOn(registerLink);
		return isUserOnRegistrationPage();
		
	}
	public boolean navigateToOwnerSearchPage() {
		clickOn(ownersLink);
		clickOn(allOwners);
		return isUserOnOwnersSearchPage();
		
	}
	private boolean isUserOnRegistrationPage() {
				sleep(2000);
				return isDisplayed(firstNameElement);
	}
	private boolean isUserOnOwnersSearchPage() {
		sleep(2000);
		return isDisplayed(searchBox);
	}

	public void completeOwnersRegistrationForm(List<CucumberParams> ownerInformation) {
		typeInto(firstNameElement,ownerInformation.get(0).getFirstName());
		typeInto(lastNameElement,ownerInformation.get(0).getLastName());
		typeInto(addressElement,ownerInformation.get(0).getAddress());
		typeInto(cityElement,ownerInformation.get(0).getCity());
		typeInto(telephoneElement,ownerInformation.get(0).getTelephone());
		clickOn(submitElement);
		
	}
	
	public void searchByFirst_name(List<CucumberParams>ownerInformation) {
		navigateToOwnerSearchPage();
		typeInto(searchBox,ownerInformation.get(0).getFirstName());
		Serenity.takeScreenshot();
	}
	public void searchByLast_name(List<CucumberParams>ownerInformation) {
		navigateToOwnerSearchPage();
		typeInto(searchBox,ownerInformation.get(0).getLastName());
		Serenity.takeScreenshot();
	}
	public void searchByAddress	(List<CucumberParams>ownerInformation) {
		navigateToOwnerSearchPage();
		typeInto(searchBox,ownerInformation.get(0).getAddress());
		Serenity.takeScreenshot();
	}
	public void searchByCity(List<CucumberParams>ownerInformation) {
		navigateToOwnerSearchPage();
		typeInto(searchBox,ownerInformation.get(0).getCity());
		Serenity.takeScreenshot();
	}
	public void searchByTelephone(List<CucumberParams>ownerInformation) {
		navigateToOwnerSearchPage();
		typeInto(searchBox,ownerInformation.get(0).getTelephone());
		Serenity.takeScreenshot();
	}
	public boolean verifyRegistrationSucceed() {
		return isDisplayed(owner_1);
	}
	public boolean verifyOwnerInfoSearch() {
		if(isDisplayed(owner_1)) {
			clicksOn(owner_1);
			sleep(2000);
		   return	isDisplayed(owner_information_title);
			
		}
		else {
			return false;
		}
				
	}
	
	public void addNewPet(List<CucumberParams>ownerInformation) {
		clickOn(add_new_pet);
		typeInto(pet_name,ownerInformation.get(0).getPetName());
		typeInto(pet_birth_date,ownerInformation.get(0).getPetBirthday());
		selectOneByText(pet_type, ownerInformation.get(0).getPetType());
		clickOn(submit_newPet);
	}
	public boolean verifyNewPetAdded(List<CucumberParams>ownerInformation) {
		String newPet=String.format(add_visit_link, ownerInformation.get(0).getPetName());
		sleep(2000);
		return isDisplayed(By.xpath(newPet));
	}
	public void addNewVisit(List<CucumberParams>ownerInformation) {
		String newPet=String.format(add_visit_link, ownerInformation.get(0).getPetName());
		if(isDisplayed(By.xpath(newPet))) {
		clickOn(By.xpath(newPet));
		typeInto(reasonForVisit,ownerInformation.get(0).getVisitDiscription());
		clickOn(submit_newPet);
		}
	}
	public boolean verifyVisitAdded(List<CucumberParams>ownerInformation) {
		String visit_detail=String.format(visit_discription, ownerInformation.get(0).getVisitDiscription());
		sleep(2000);
		return isDisplayed(By.xpath(visit_detail));
	}
	

}
