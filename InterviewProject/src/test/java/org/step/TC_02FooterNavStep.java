package org.step;

import org.base.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.page.FooterNavPOM;

import io.cucumber.java.en.*;

public class TC_02FooterNavStep extends BaseClass {

	FooterNavPOM footer = new FooterNavPOM();

	@Given("I am on the homepage")
	public void i_am_on_the_homepage() {
		
	}

	@When("I scroll to the footer section of the page")
	public void i_scroll_to_the_footer_section_of_the_page() {
		
		scrollToElement(footer.getContactUsLink());
	}

	@Then("I should see the \"Privacy Policy\" link in the footer")
	public void i_should_see_the_privacy_policy_link_in_the_footer() {
		WebElement privacy = waitForElementToBeVisible(footer.getPrivacyPolicyLink(), 10);
		Assert.assertTrue(privacy.isDisplayed());
	}

	@When("I click on the \"Privacy Policy\" footer link")
	public void i_click_on_the_privacy_policy_footer_link() {
		WebElement privacy = waitForElementToBeClickable(footer.getPrivacyPolicyLink(), 10);
		privacy.click();
	}

	@Then("I should be redirected to the Privacy Policy page")
	public void i_should_be_redirected_to_the_privacy_policy_page() throws InterruptedException {
		Thread.sleep(2000);
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.toLowerCase().contains("privacy"));
	}

	@Then("the Privacy Policy page should load successfully without any error")
	public void the_privacy_policy_page_should_load_successfully_without_any_error() {
		Assert.assertFalse(driver.getPageSource().toLowerCase().contains("error"));
	}
}
