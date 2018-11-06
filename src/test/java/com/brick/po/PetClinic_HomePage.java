package com.brick.po;

import java.util.List;

import org.openqa.selenium.By;

import com.brick.reusablecomponents.CucumberParams;
import com.brick.reusablecomponents.WebDriverHelper;

public class PetClinic_HomePage extends WebDriverHelper{
	 By ownersLink=By.xpath("//a[contains(text(),'Owners')]");
	 By registerLink=By.xpath("//span[contains(text(),'Register')]");
	 By firstNameElement=By.xpath("//input[@name='firstName']");
	 By lastNameElement=By.xpath("//input[@name='lastName']");
	 By addressElement=By.xpath("//input[@name='address']");
	 By cityElement=By.xpath("//input[@name='city']");
	 By telephoneElement=By.xpath("//input[@name='telephone']");
	 By submitElement=By.xpath("//button[text()='Submit']");
	 By searchBox=By.xpath("//input[@placeholder='Search Filter']");
	 By owner_1=By.xpath("//tr[@class='ng-scope'][1]//a");
	public void navigateToPetsClinicApp() {
		driver.navigate().to(properties.getProperty("petClinicUrl"));
	}

	public void navigateToOwnerRegistrationPage() {
		clickOn(ownersLink);
		clickOn(registerLink);
		
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
		typeInto(searchBox,ownerInformation.get(0).getFirstName());
	}
	public void openOwnerInfo() {
		clickOn(owner_1);
	}

}
