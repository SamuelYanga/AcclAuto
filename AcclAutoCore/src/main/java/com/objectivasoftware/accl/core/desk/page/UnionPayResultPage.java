package com.objectivasoftware.accl.core.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;

public class UnionPayResultPage extends BasePage {

	public static final String BTN_BACK_ID = "btnBack";
	@FindBy(id = BTN_BACK_ID)
	private WebElement backButton;

	public static final String MAIN_WORD_CSS = ".main_word";
	@FindBy(css = MAIN_WORD_CSS)
	private WebElement mainWord;

	public boolean verifyPaySuccess() {
		String text = mainWord.getText();
		return text.contains("您已成功支付") || text.contains("Payment Succeeded");
	}

	public void backToMerchant() {
		backButton.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

}
