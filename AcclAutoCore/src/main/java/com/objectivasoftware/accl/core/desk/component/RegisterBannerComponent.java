package com.objectivasoftware.accl.core.desk.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;

public class RegisterBannerComponent extends BaseComponent {

	// register-banner
	public static final String REGISTER_BANNER_CLOSE_ID = "close";
	@FindBy(id = REGISTER_BANNER_CLOSE_ID)
	private WebElement registerBannerClose;

	/**
	 * close the register banner
	 */
	public void closeRegisterBanner() {
		registerBannerClose.click();
		WaitUtil.waitOn(myDriver).untilHidden(By.id(REGISTER_BANNER_CLOSE_ID));
	}

}
