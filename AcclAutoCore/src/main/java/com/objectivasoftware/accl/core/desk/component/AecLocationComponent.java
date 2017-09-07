package com.objectivasoftware.accl.core.desk.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;

public class AecLocationComponent extends BaseComponent {

	public static final String CHANGE_AEC_LOCATION_POPUP = ".change-aeclocation-popup .change-location-aec-wrapper";
	public static final String CHANGE_AEC_LOCATION_CLOSE = ".change-aeclocation-popup #cboxClose";
	@FindBy(css = CHANGE_AEC_LOCATION_CLOSE)
	private WebElement aecLocationClose;

	public void closeAecComponent() {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(CHANGE_AEC_LOCATION_CLOSE));
		aecLocationClose.click();
		WaitUtil.waitOn(myDriver).untilHidden(By.cssSelector(CHANGE_AEC_LOCATION_CLOSE));
	}

}
