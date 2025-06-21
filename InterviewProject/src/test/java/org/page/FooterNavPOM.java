package org.page;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterNavPOM extends BaseClass{
	
	public FooterNavPOM() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Notices']")
	private WebElement noticelink;
	
	public WebElement getNoticelink() {
		return noticelink;
	}

	@FindBy(xpath="//a[text()=' Terms & Conditions ']")
	private WebElement termsandCon;
	
	public WebElement getTermsandCon() {
		return termsandCon;
	}
	
	@FindBy(xpath = "//*[@id=\"contact\"]/div[1]/div/div/div/div[2]/div/ul/li[3]/a")
	private WebElement privacyPolicyLink;

	public WebElement getPrivacyPolicyLink() {
	    return privacyPolicyLink;
	}

	@FindBy(xpath = "//*[@id=\"contact\"]/div[1]/div/div/div/div[2]/div/ul/li[4]/a")
	private WebElement contactUsLink;

	public WebElement getContactUsLink() {
	    return contactUsLink;
	}

}
