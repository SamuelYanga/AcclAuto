package com.objectivasoftware.accl.core.component;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;

public class FloatMenuComponent extends BaseComponent {

	public static final String CURRENT_BUYER_WRAPPER_CSS = ".floating-menu .user-wrapper .main-link.js-change-buyer-link";
	@FindBy(css = CURRENT_BUYER_WRAPPER_CSS)
	private WebElement currentBuyerWrapper;

	public static final String CHANGE_BUYER_WRAPPER_HIDDEN_CSS = ".floating-menu .user-wrapper .hidden-xs .js-change-buyer-link";
	@FindBy(css = CHANGE_BUYER_WRAPPER_HIDDEN_CSS)
	private WebElement changeBuyerWrapperHidden;

	public boolean currentBuyerWrapperIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(CURRENT_BUYER_WRAPPER_CSS));
			super.scrollMoveToElement(currentBuyerWrapper);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return currentBuyerWrapper.isDisplayed();
	}

	public boolean moveToCurrentBuyerWrapper() {
		mouseMoveToElement(currentBuyerWrapper);
		try {
			WaitUtil.waitOn(myDriver, 2000).untilListShown(By.cssSelector(CHANGE_BUYER_WRAPPER_HIDDEN_CSS));
		} catch (TimeoutException e) {
			return false;
		}
		return changeBuyerWrapperHidden.isDisplayed();
	}

	public void mouseMoveToElement(WebElement element) {
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(element).perform();
	}

	public static final String CART_WRAPPER_CSS = ".floating-menu .cart-wrapper .main-link.mini-cart-link";
	@FindBy(css = CART_WRAPPER_CSS)
	private WebElement cartWrapper;

	public static final String CART_WRAPPER_HIDDEN_CSS = ".floating-menu .cart-wrapper div a[href=\"/cart\"]";
	@FindBy(css = CART_WRAPPER_HIDDEN_CSS)
	private WebElement cartWrapperHidden;

	public boolean cartWrapperIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(CART_WRAPPER_CSS));
			super.scrollMoveToElement(cartWrapper);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return cartWrapper.isDisplayed();
	}

	public boolean moveToCartWrapper() {
		mouseMoveToElement(cartWrapper);
		try {
			WaitUtil.waitOn(myDriver, 2000).untilListShown(By.cssSelector(CART_WRAPPER_HIDDEN_CSS));
		} catch (TimeoutException e) {
			return false;
		}
		return cartWrapperHidden.isDisplayed();
	}

	public static final String ORDER_WRAPPER_CSS = ".floating-menu .order-wrapper .main-link";
	@FindBy(css = ORDER_WRAPPER_CSS)
	private WebElement orderWrapper;

	public static final String ORDER_WRAPPER_HIDDEN_CSS = ".floating-menu .order-wrapper div a[href=\"/my-account/orders\"]";
	@FindBy(css = ORDER_WRAPPER_HIDDEN_CSS)
	private WebElement orderWrapperHidden;

	public boolean orderWrapperIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(ORDER_WRAPPER_CSS));
			super.scrollMoveToElement(orderWrapper);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return orderWrapper.isDisplayed();
	}

	public boolean moveToOrderWrapper() {
		mouseMoveToElement(orderWrapper);
		try {
			WaitUtil.waitOn(myDriver, 2000).untilListShown(By.cssSelector(ORDER_WRAPPER_HIDDEN_CSS));
		} catch (TimeoutException e) {
			return false;
		}
		return orderWrapperHidden.isDisplayed();
	}

	public static final String TICKET_WRAPPER_CSS = ".floating-menu .ticket-wrapper .main-link";
	@FindBy(css = TICKET_WRAPPER_CSS)
	private WebElement ticketWrapper;

	public static final String TICKET_WRAPPER_HIDDEN_CSS = ".floating-menu .ticket-wrapper div a[href=\"/my-account/voucherPage#notUsedEV\"]";
	@FindBy(css = TICKET_WRAPPER_HIDDEN_CSS)
	private WebElement ticketWrapperHidden;

	public boolean ticketWrapperIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(TICKET_WRAPPER_CSS));
			super.scrollMoveToElement(ticketWrapper);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return ticketWrapper.isDisplayed();
	}

	public boolean moveToTicketWrapper() {
		mouseMoveToElement(ticketWrapper);
		try {
			WaitUtil.waitOn(myDriver, 2000).untilListShown(By.cssSelector(TICKET_WRAPPER_HIDDEN_CSS));
		} catch (TimeoutException e) {
			return false;
		}
		return ticketWrapperHidden.isDisplayed();
	}

	public static final String QUICK_BUY_WRAPPER_CSS = ".floating-menu .flash-wrapper .main-link";
	@FindBy(css = QUICK_BUY_WRAPPER_CSS)
	private WebElement quickBuyWrapper;

	public static final String QUICK_BUY_WRAPPER_HIDDEN_CSS = ".floating-menu .flash-wrapper .hidden-xs";
	@FindBy(css = QUICK_BUY_WRAPPER_HIDDEN_CSS)
	private WebElement quickBuyWrapperHidden;

	public boolean quickBuyWrapperIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(QUICK_BUY_WRAPPER_CSS));
			super.scrollMoveToElement(quickBuyWrapper);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return quickBuyWrapper.isDisplayed();
	}

	public boolean moveToQuickBuyWrapper() {
		mouseMoveToElement(quickBuyWrapper);
		try {
			WaitUtil.waitOn(myDriver, 2000).untilListShown(By.cssSelector(QUICK_BUY_WRAPPER_HIDDEN_CSS));
		} catch (TimeoutException e) {
			return false;
		}
		return quickBuyWrapperHidden.isDisplayed();
	}

	public static final String RETURN_TO_TOP_WRAPPER_CSS = ".floating-menu .arrow-wrapper";
	@FindBy(css = RETURN_TO_TOP_WRAPPER_CSS)
	private WebElement returnToTopWrapper;

	public boolean returnToTopWrapperIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(RETURN_TO_TOP_WRAPPER_CSS));
			super.scrollMoveToElement(returnToTopWrapper);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return returnToTopWrapper.isDisplayed();
	}

}
