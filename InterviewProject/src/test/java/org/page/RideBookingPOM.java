package org.page;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RideBookingPOM extends BaseClass {

	public RideBookingPOM() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "body > section > nav > div > div.logo > a")
	private WebElement appLogo;

	public WebElement getAppLogo() {
		return appLogo;
	}

	@FindBy(css = "#txt_source")
	private WebElement pickupLocation;

	public WebElement getPickupLocation() {
		return pickupLocation;
	}

	@FindBy(css = "#txt_destination")
	private WebElement dropLocation;

	public WebElement getDropLocation() {
		return dropLocation;
	}

	@FindBy(css = "#one-way-form > div:nth-child(3) > span > input")
	private WebElement dateSelect;

	public WebElement getDateSelect() {
		return dateSelect;
	}

	@FindBy(css = "#one-way-form > div.form-group.text-center.btn-form--group.mb-0 > button")
	private WebElement searchCabs;

	public WebElement getSearchCabs() {
		return searchCabs;
	}

	@FindBy(css = "body > section > section.abt-pg-con.pt80.pb60")
	private WebElement listOfCabs;

	public WebElement getListOfCabs() {
		return listOfCabs;
	}

	@FindBy(css = "body > section > section.abt-pg-con.pt80.pb60 > div > div > div:nth-child(1) > div > div.text-center.pb40 > form > button")
	private WebElement bookNow;

	public WebElement getBookNow() {
		return bookNow;
	}

	@FindBy(css = "body > section > section.abt-pg-con.pt80.pb80 > div > div > div.col-md-4 > div > table > tbody > tr:nth-child(3) > td:nth-child(2) > strong")
	private WebElement pickupSummary;

	public WebElement getPickupSummary() {
		return pickupSummary;
	}

	@FindBy(css = "body > section > section.abt-pg-con.pt80.pb80 > div > div > div.col-md-4 > div > table > tbody > tr:nth-child(3) > td:nth-child(2) > strong")
	private WebElement dropSummary;

	public WebElement getDropSummary() {
		return dropSummary;
	}

	@FindBy(css = "body > section > section.abt-pg-con.pt80.pb80 > div > div > div.col-md-4 > div > table > tbody > tr:nth-child(6) > td:nth-child(2)")
	private WebElement driverBata;

	public WebElement getDriverBata() {
		return driverBata;
	}

	@FindBy(css = "body > section > section.abt-pg-con.pt80.pb80 > div > div > div.col-md-4 > div > table > tbody > tr:nth-child(7) > td:nth-child(2) > strong")
	private WebElement taxDeduct;

	public WebElement getTaxDeduct() {
		return taxDeduct;
	}

	@FindBy(css = "#total_fare > strong")
	private WebElement totalFare;

	public WebElement getTotalFare() {
		return totalFare;
	}

	@FindBy(css = "#name")
	private WebElement nameCon;

	public WebElement getNameCon() {
		return nameCon;
	}

	@FindBy(css = "#email")
	private WebElement emailCon;

	public WebElement getEmailCon() {
		return emailCon;
	}

	@FindBy(css = "#pickup_addr")
	private WebElement addressCon;

	public WebElement getAddressCon() {
		return addressCon;
	}

	@FindBy(css = "#phone")
	private WebElement phoneNo;

	public WebElement getPhoneNo() {
		return phoneNo;
	}

	@FindBy(css = "#booking-frm > div.tm-form-field > button")
	private WebElement confirmBook;

	public WebElement getConfirmBook() {
		return confirmBook;
	}

	@FindBy(css = "#overlay-backdrop > div > div > div > form > div.relative.flex.grow.flex-col.d\\:grow-0 > div > div.flex.gap-4 > h3")
	private WebElement succesMsg;

	public WebElement getSuccesMsg() {
		return succesMsg;
	}

}
