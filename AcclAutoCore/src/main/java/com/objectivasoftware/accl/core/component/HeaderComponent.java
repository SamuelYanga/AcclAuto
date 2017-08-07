package com.objectivasoftware.accl.core.component;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class HeaderComponent extends BaseComponent {

	public static final String LOGIN_LINK_CSS = ".login-link.loginDesktop";
	@FindBy(css = LOGIN_LINK_CSS)
	private WebElement loginLink;

	// search input
	public static final String SEARCH_INPUT_CSS = ".search-form-SearchBox #js-site-search-input";
	@FindBy(css = SEARCH_INPUT_CSS)
	private WebElement searchInput;

	// search button
	public static final String SEARCH_BUTTON_CSS = "searchBtn";
	@FindBy(id = SEARCH_BUTTON_CSS)
	private WebElement searchButton;

	public void searchProduct(String product) {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(SEARCH_INPUT_CSS));
		WaitUtil.waitOn(myDriver).waitTime(500);
		searchInput.clear();
		searchInput.sendKeys(product);
		searchButton.click();
	}

	public void clickLogin() {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(LOGIN_LINK_CSS));
		try {
			loginLink.click();
		} catch (WebDriverException e) {
			System.out.println("*******************************************login error first");
			loginLink.click();
		}
		WaitUtil.waitOn(myDriver).untilShown(By.id(LoginComponent.LOGIN_PAGE_ID));
	}
	
	public boolean isLogin() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilShown(By.cssSelector(LOGIN_LINK_CSS));
		} catch (TimeoutException e) {
			return true;
		}
		return false;
	}
	
	
	
	public static final String PURCHASER_SECTION_CSS = ".purchaser-section";
	@FindBy(css = PURCHASER_SECTION_CSS)
	private WebElement purchaserSection;
	
	public static final String CHANGE_BUYER_LINK_CSS = ".purchaser-section .change-buyer-link";
	@FindBy(css = CHANGE_BUYER_LINK_CSS)
	private WebElement changeBuyerLink;

	
	//.purchaser-block-wrapper .purchases-buyer .change-buyer-link
	
	private void moveToPurchaserSection() {
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(purchaserSection).perform();
		WaitUtil.waitOn(myDriver, new UntilEvent() {
			@Override
			public boolean excute() {
				String classValue = purchaserSection.getAttribute("class");
				return classValue.contains("dropdown-active");
			}
		}).untilEventHappened();
	}
	
	public void changeBuyer(String buyer) {
		moveToPurchaserSection();

		changeBuyerLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ChangeBuyerComponent.CHANGE_BUYER_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));

		ChangeBuyerComponent changeBuyerComponent = new ChangeBuyerComponent();
		changeBuyerComponent.selectNewBuyer(buyer);
	}

}
