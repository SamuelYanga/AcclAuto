package com.objectivasoftware.accl.core.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import com.objectivasoftware.accl.base.Configurations;
import com.objectivasoftware.accl.base.Constants;
import com.objectivasoftware.accl.base.browser.DriverFactory;
import com.objectivasoftware.accl.base.browser.MyDriver;
import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.component.AecLocationComponent;
import com.objectivasoftware.accl.core.component.HeaderComponent;

public class HomePage extends BasePage {

	private final static Logger LOGGER = Logger.getLogger(HomePage.class);

	public static final String CHANGE_LOCALTION_POPUP = ".change-location-popup";
	public static final String CHANGE_LOCATION_WRAPPER = "#cboxLoadedContent .change-location-wrapper .change-location-content";
	public static final String CHANGE_LOCATION_WRAPPER_AMBTN = "#cboxLoadedContent .change-location-wrapper .change-location-content .ambtn-sm";

	public static HomePage open() {
		MyDriver browser = DriverFactory.getBrowser();
		LOGGER.info("#############HomePage.openHomePage start.");
		browser.get(Configurations.getConfiguration(Constants.SELENIUM_TARGETURL));
		browser.skipSSLValidation();
		// browser.switchToAlert();
		browser.manage().window().maximize();

		try {
			WaitUtil.waitOn(browser, 2000).untilShown(By.cssSelector(AecLocationComponent.CHANGE_AEC_LOCATION_POPUP));
			LOGGER.info("The [change aec location popup] is displayed");
			browser.get(Configurations.getConfiguration(Constants.SELENIUM_TARGETURL));
			WaitUtil.waitOn(browser).untilRemoved(By.cssSelector(AecLocationComponent.CHANGE_AEC_LOCATION_POPUP));
		} catch (TimeoutException e) {
			LOGGER.info("The [change aec location popup] is not displayed");
		}

		try {
			WaitUtil.waitOn(browser, 1000).untilShown(By.cssSelector(CHANGE_LOCATION_WRAPPER));
			LOGGER.info("The [change location popup] is displayed");
			WebElement ambtn = browser.findElement(By.cssSelector(CHANGE_LOCATION_WRAPPER_AMBTN));
			ambtn.click();
			WaitUtil.waitOn(browser).untilPageDown();
			WaitUtil.waitOn(browser).untilRemoved(By.cssSelector(CHANGE_LOCATION_WRAPPER));
		} catch (TimeoutException e) {
			LOGGER.info("The [change location popup] is not displayed");
		}

		WaitUtil.waitOn(browser).untilHidden(By.cssSelector(HeaderComponent.HEADER_BANNER_CLOSE_CSS));
		WaitUtil.waitOn(browser).waitTime(1000);
		
		return new HomePage();
	}

}
