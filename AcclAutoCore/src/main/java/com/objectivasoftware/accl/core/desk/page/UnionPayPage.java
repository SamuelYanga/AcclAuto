package com.objectivasoftware.accl.core.desk.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.vo.checkout.UnionPayVO;

public class UnionPayPage extends BasePage {

	public static final String BANK_NUMBER_CSS = ".bankNumber .ipt_txt";
	@FindBy(css = BANK_NUMBER_CSS)
	private WebElement bankNumberInput;

	public static final String NEXT_BTN_ID = "btnNext";
	@FindBy(id = NEXT_BTN_ID)
	private WebElement nextBtn;

	public static final String REAL_NAME_ID = "realName";
	@FindBy(id = REAL_NAME_ID)
	private WebElement realNameInput;

	public static final String CREDENTIAL_NO_ID = "credentialNo";
	@FindBy(id = CREDENTIAL_NO_ID)
	private WebElement credentialNoInput;

	public static final String SMS_CODE_ID = "smsCode";
	@FindBy(id = SMS_CODE_ID)
	private WebElement smsCodeInput;

	public static final String BTN_CARD_PAY_ID = "btnCardPay";
	@FindBy(id = BTN_CARD_PAY_ID)
	private WebElement cardPayButton;

	public void inputBankNumber(String bankNumber) {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(BANK_NUMBER_CSS));
		bankNumberInput.clear();
		bankNumberInput.sendKeys(bankNumber);

		nextBtn.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		
		System.out.println("****************************************inputBankNumber");
	}
	
	public void pay(UnionPayVO vo) {
		inputBankNumber(vo.getBankNumber());
		realNameInput.clear();
		realNameInput.sendKeys(vo.getRealName());
		credentialNoInput.clear();
		credentialNoInput.sendKeys(vo.getCredentialNo());
		smsCodeInput.clear();
		smsCodeInput.sendKeys(vo.getSmsCode());
		
		cardPayButton.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		System.out.println("****************************************pay");
	}
}
