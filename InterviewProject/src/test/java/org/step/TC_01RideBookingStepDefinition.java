package org.step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.base.BaseClass;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.page.RideBookingPOM;

import io.cucumber.java.en.*;

public class TC_01RideBookingStepDefinition extends BaseClass {

	RideBookingPOM ride = new RideBookingPOM();
	String capturedPickup;
	String capturedDrop;

	@Given("I launch the ride booking application")
	public void i_launch_the_ride_booking_application() {

		waitForElementToBeVisible(ride.getAppLogo(), 10);
	}

	@When("I enter {string} as the pickup location")
	public void i_enter_as_the_pickup_location(String pickup) {

		WebElement pickupField = waitForElementToBeVisible(ride.getPickupLocation(), 10);
		pickupField.clear();
		pickupField.sendKeys(pickup);
		capturedPickup = pickupField.getAttribute("value");
	}

	@When("I type {string} as the drop location prefix")
	public void i_type_as_the_drop_location_prefix(String dropPrefix) {

		WebElement dropField = waitForElementToBeVisible(ride.getDropLocation(), 10);
		dropField.clear();
		dropField.sendKeys(dropPrefix);
	}

	@When("I select the 2nd location from the dropdown suggestions")
	public void i_select_the_2nd_location_from_the_dropdown_suggestions() throws InterruptedException {
		WebElement dropField = ride.getDropLocation();
		Thread.sleep(1000);
		dropField.sendKeys(Keys.ARROW_DOWN);
		dropField.sendKeys(Keys.ARROW_DOWN);
		dropField.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		capturedDrop = dropField.getAttribute("value");
	}

	@When("I capture the pickup and drop location text")
	public void i_capture_the_pickup_and_drop_location_text() {

		System.out.println("Captured Pickup: " + capturedPickup);
		System.out.println("Captured Drop: " + capturedDrop);
	}

	@When("I select {string} and choose a future date")
	public void i_select_and_choose_a_future_date(String optionText) {

		WebElement dateField = waitForElementToBeClickable(ride.getDateSelect(), 10);
		dateField.click();
		dateField.clear();

		LocalDate tomorrow = LocalDate.now().plusDays(1);
		String formattedDate = tomorrow.format(DateTimeFormatter.ofPattern("19-06-2025"));

		dateField.sendKeys(formattedDate);
		dateField.sendKeys(Keys.ENTER);
	}

	@When("I click on the {string} button")
	public void i_click_on_the_button(String buttonText) {

		WebElement searchBtn = waitForElementToBeClickable(ride.getSearchCabs(), 30);
		searchBtn.click();
	}

	@Then("I should see a list of available cabs with their prices")
	public void i_should_see_a_list_of_available_cabs_with_their_prices() {

		WebElement listOfCabs = ride.getListOfCabs();
		listOfCabs.isDisplayed();
	}

	@When("I click {string} for the specified car")
	public void i_click_for_the_specified_car(String buttonText) {

		WebElement bookNow = ride.getBookNow();
		bookNow.click();
	}

	@Then("the pickup and drop locations in the summary should match the captured values")
	public void the_pickup_and_drop_locations_in_the_summary_should_match() {

		WebElement pickupSummary = waitForElementToBeVisible(ride.getPickupSummary(), 10);
		WebElement dropSummary = waitForElementToBeVisible(ride.getDropSummary(), 10);

		if (!pickupSummary.getText().equalsIgnoreCase(capturedPickup)) {
			throw new AssertionError(
					"Pickup mismatch. Expected: " + capturedPickup + ", Found: " + pickupSummary.getText());
		}

		if (!dropSummary.getText().equalsIgnoreCase(capturedDrop)) {
			throw new AssertionError("Drop mismatch. Expected: " + capturedDrop + ", Found: " + dropSummary.getText());
		}
	}

	@Then("I should see the correct tax and final price calculated")
	public void i_should_see_the_correct_tax_and_final_price_calculated() {

		WebElement taxElement = waitForElementToBeVisible(ride.getTaxDeduct(), 10);
		WebElement finalPriceElement = waitForElementToBeVisible(ride.getTotalFare(), 10);

		String taxText = taxElement.getText().replaceAll("[^0-9.]", "");
		String finalPriceText = finalPriceElement.getText().replaceAll("[^0-9.]", "");

		double tax = Double.parseDouble(taxText);
		double total = Double.parseDouble(finalPriceText);

		if (total < tax) {
			throw new AssertionError("Final price is less than tax. Final: " + total + ", Tax: " + tax);
		}
	}

	@When("I fill in the contact details")
	public void i_fill_in_the_contact_details() {

		WebElement nameField = waitForElementToBeVisible(ride.getNameCon(), 10);
		WebElement phoneField = waitForElementToBeVisible(ride.getPhoneNo(), 10);
		WebElement emailField = waitForElementToBeVisible(ride.getEmailCon(), 10);
		WebElement Add = waitForElementToBeVisible(ride.getAddressCon(), 10);

		nameField.sendKeys("Test User");
		emailField.sendKeys("arunkumardhamodharan08@gmail.com.com");
		phoneField.sendKeys("9876543210");
		Add.sendKeys("Rathinam college");

	}

	@When("I verify the pickup and drop locations again in the final step")
	public void i_verify_the_pickup_and_drop_locations_again_in_the_final_step() {

		the_pickup_and_drop_locations_in_the_summary_should_match();
	}

	@When("I click on {string}")
	public void i_click_on(String buttonText) {

		WebElement confirmBook = ride.getConfirmBook();
		confirmBook.click();

	}

	@Then("the booking should be confirmed successfully")
	public void the_booking_should_be_confirmed_successfully() {

		WebElement succesMsg = ride.getSuccesMsg();
		succesMsg.isDisplayed();
	}
}
