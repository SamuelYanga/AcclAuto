package com.objectivasoftware.accl.core.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class LoginComponent extends BaseComponent {

	// page id
	public static final String LOGIN_PAGE_ID = "cboxTitle";

	protected List<String> getLoadIdentifier() {
		WaitUtil.waitOn(myDriver).untilShown(By.id(LOGIN_PAGE_ID));
		return super.getLoadIdentifier();
	}

	// user name input
	public static final String USER_NAME_ID = "j_username";
	@FindBy(id = USER_NAME_ID)
	private WebElement userNameInput;

	// password input
	public static final String USER_PASSWORD_ID = "j_password";
	@FindBy(id = USER_PASSWORD_ID)
	private WebElement passwordInput;

	// login button
	public static final String LOGIN_BTN_CSS = ".loginbtn";
	@FindBy(css = LOGIN_BTN_CSS)
	private WebElement loginBtn;

	public static final String NOTIFICATION_BTN_CSS = ".notification-banner .notification-btn";
	@FindBy(css = NOTIFICATION_BTN_CSS)
	private WebElement notificationButton;

	public void closeNotification() {
		notificationButton.click();
		WaitUtil.waitOn(myDriver).untilHidden(By.cssSelector(NOTIFICATION_BTN_CSS));
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	public void login(String userName, String password) {
		userNameInput.clear();
		userNameInput.sendKeys(userName);
		passwordInput.clear();
		passwordInput.sendKeys(password);
		loginBtn.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(HeaderComponent.LOGIN_LINK_CSS));
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
		WaitUtil.waitOn(myDriver, new UntilEvent() {

			@Override
			public boolean excute() {
				WebElement eVouchersWrapper = myDriver
						.findElement(By.cssSelector(HeaderComponent.E_VOUCHERS_WRAPPER_CSS));
				String classValue = eVouchersWrapper.getAttribute("class");
				return !classValue.contains("dropdown-active");
			}
		}).untilEventHappened();

		try {
			WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2).untilShown(By.cssSelector(NOTIFICATION_BTN_CSS));
			closeNotification();
		} catch (TimeoutException e) {
		}
	}

}
