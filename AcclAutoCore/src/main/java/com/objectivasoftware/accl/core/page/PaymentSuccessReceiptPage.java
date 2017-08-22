package com.objectivasoftware.accl.core.page;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.util.UrlConstant;

public class PaymentSuccessReceiptPage extends BasePage {

	public static final String SUCCESS_CONTENT = ".order-sucess p";
	@FindBy(css = SUCCESS_CONTENT)
	private WebElement successContent;

	public static final String CLOSE_BUTTON_CSS = "a.ambtn-white";
	@FindBy(css = CLOSE_BUTTON_CSS)
	private WebElement closeButton;

	public static final String COFFEE_COUPON_INFO_CSS = ".coupon-box p";
	@FindBy(css = COFFEE_COUPON_INFO_CSS)
	private List<WebElement> couponInfos;

	public boolean verifyPaySuccess() {
		String text = successContent.getText();
		return text.contains("您已成功支付");
	}

	public void close() {
		int handleSize = myDriver.getWindowHandles().size();
		closeButton.click();
		WaitUtil.waitOn(myDriver, new UntilEvent() {
			@Override
			public boolean excute() {
				int newHandles = myDriver.getWindowHandles().size();
				return newHandles == handleSize - 1;
			}
		}).untilEventHappened();
		WaitUtil.waitOn(myDriver).waitTime(2000L);

		
		Set<String> handles = myDriver.getWindowHandles();
		
		myDriver.switchTo().window(handles.iterator().next());
		super.switchToWindowByUrl(UrlConstant.PAYMENT);
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		
		try {
			WaitUtil.waitOn(myDriver, 2000).untilShown(By.cssSelector(PAY_SUCCESS_CSS));
			paySuccessCss.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).untilPageDown();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(PAY_SUCCESS_CSS));
		} catch (TimeoutException e) {
			
		}

	}

	//#cboxLoadedContent .ambtn.ambtn-xs.success
	public static final String PAY_SUCCESS_CSS = "#cboxLoadedContent .ambtn.ambtn-xs.success";
	@FindBy(css = PAY_SUCCESS_CSS)
	private WebElement paySuccessCss;

	public String exchangeCoffeeCouponSuccessful() {
		try {
			WaitUtil.waitOn(myDriver, 5000).untilShown(By.cssSelector(COFFEE_COUPON_INFO_CSS));
		} catch (TimeoutException e) {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&fresh");
			myDriver.navigate().refresh();
			WaitUtil.waitOn(myDriver, 5000).untilShown(By.cssSelector(COFFEE_COUPON_INFO_CSS));
		}
		String value = couponInfos.get(1).getText().trim();
		value = value.substring(value.indexOf("：") + 1);
		return value;
	}
}
