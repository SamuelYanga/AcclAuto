package com.objectivasoftware.accl.core.desk.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class AmplusCoffeeCouponComponent extends BaseComponent {

	public static final String MOBILE_NUMBER_CSS = "#cboxLoadedContent #mobileNumber";
	@FindBy(css = MOBILE_NUMBER_CSS)
	private WebElement mobileNumber;

	public static final String MOBILE_NUMBER_CONFIRME_CSS = "#cboxLoadedContent #mobileNumberConfirme";
	@FindBy(css = MOBILE_NUMBER_CONFIRME_CSS)
	private WebElement mobileNumberConfirme;

	public static final String MOBILE_NUMBER_SUBMIT_CSS = "#cboxLoadedContent input[type=submit]";
	@FindBy(css = MOBILE_NUMBER_SUBMIT_CSS)
	private WebElement mobileNumberSubmit;

	public static final String COFFEE_COUPON_CODE_CSS = "#cboxLoadedContent .coupon-box .code";
	@FindBy(css = COFFEE_COUPON_CODE_CSS)
	private WebElement coffeeCouponCode;

	public String exchangeCoffeeCoupon() {
		return exchangeCoffeeCoupon(CommonConstant.DEFAULT_MOBILE_NUMBER_CSS);
	}

	public String exchangeCoffeeCoupon(String phone) {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(mobileNumberSubmit);
		mobileNumber.clear();
		mobileNumber.sendKeys(phone);
		mobileNumberConfirme.clear();
		mobileNumberConfirme.sendKeys(phone);

		mobileNumberSubmit.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilListShown(By.cssSelector(COFFEE_COUPON_CODE_CSS));
		String value = coffeeCouponCode.getText().trim();
		return value;
	}

	public void exchangeCouponWithIntegralCash() {
		exchangeCouponWithIntegralCash(CommonConstant.DEFAULT_MOBILE_NUMBER_CSS);
	}

	public void exchangeCouponWithIntegralCash(String phone) {
		mobileNumber.clear();
		mobileNumber.sendKeys(phone);
		mobileNumberConfirme.clear();
		mobileNumberConfirme.sendKeys(phone);

		mobileNumberSubmit.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
	}

}
