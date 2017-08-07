package com.objectivasoftware.accl.core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.util.UrlConstant;

public class PaymentSuccessReceiptPage extends BasePage {

	public static final String SUCCESS_CONTENT = ".order-sucess p";
	@FindBy(css = SUCCESS_CONTENT)
	private WebElement successContent;

	public static final String CLOSE_BUTTON_CSS = "a.ambtn-white";
	@FindBy(css = CLOSE_BUTTON_CSS)
	private WebElement closeButton;

	public boolean verifyPaySuccess() {
		String text = successContent.getText();
		return text.contains("您已成功支付");
	}

	public void close() {
		closeButton.click();
		WaitUtil.waitOn(myDriver).waitTime(3000L);
		super.switchToWindowByUrl(UrlConstant.PAYMENT);
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
	}
}
