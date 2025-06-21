package org.hooks;

import org.base.BaseClass;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class HooksClass extends BaseClass {

	@Before
	public void setUp() throws InterruptedException {
		try {
			getDriver("chrome");
			getUrl("https://droptaxie.in/");
			windMax();
			
//			Thread.sleep(10000);

			System.out.println("Browser Launched and Url Opened Successfull");
		} catch (Exception e) {
			System.out.println("Browser Launched and Url Opened unSuccessfull" + "Error: " + e.getMessage());
		}
	}

	@After
	public void signOff() {
//		try {
//			// Take a screenshot in case of failure
//			takeScreenshot("failure_screenshot");
//
//			// Close all browser windows
//			if (driver != null) {
//				driver.quit();
//			}
//			System.out.println("Browser closed successfully.");
//		} catch (Exception e) {
//			System.err.println("Error during browser close or screenshot capture: " + e.getMessage());
//		}
	}
}