package com.objectivasoftware.accl.core.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;

public class FloatMenuComponent extends BaseComponent {

	// 当前购买人
	public static final String CURRENT_BUYER_WRAPPER_CSS = ".floating-menu .user-wrapper .main-link.js-change-buyer-link";
	@FindBy(css = CURRENT_BUYER_WRAPPER_CSS)
	private WebElement currentBuyerWrapper;

	public static final String CHANGE_BUYER_WRAPPER_HIDDEN_CSS = ".floating-menu .user-wrapper .hidden-xs .js-change-buyer-link";

	/**
	 * 当前购买人是否显示
	 * @return
	 */
	public boolean currentBuyerWrapperIsDisplayed() {
		return elementIsShown(By.cssSelector(CURRENT_BUYER_WRAPPER_CSS));
	}

	/**
	 * 鼠标悬停到当前购买人
	 * @return
	 */
	public boolean moveToCurrentBuyerWrapper() {
		mouseMoveToElement(currentBuyerWrapper);
		return elementIsShown(By.cssSelector(CHANGE_BUYER_WRAPPER_HIDDEN_CSS));
	}

	// 购物车
	public static final String CART_WRAPPER_CSS = ".floating-menu .cart-wrapper .main-link.mini-cart-link";
	@FindBy(css = CART_WRAPPER_CSS)
	private WebElement cartWrapper;
	public static final String CART_WRAPPER_HIDDEN_CSS = ".floating-menu .cart-wrapper div a[href=\"/cart\"]";

	/**
	 * 购物车是否显示
	 * @return
	 */
	public boolean cartWrapperIsDisplayed() {
		return elementIsShown(By.cssSelector(CART_WRAPPER_CSS));
	}

	/**
	 * 鼠标悬停到购物车
	 * @return
	 */
	public boolean moveToCartWrapper() {
		mouseMoveToElement(cartWrapper);
		return elementIsShown(By.cssSelector(CART_WRAPPER_HIDDEN_CSS));
	}

	// 订单
	public static final String ORDER_WRAPPER_CSS = ".floating-menu .order-wrapper .main-link";
	@FindBy(css = ORDER_WRAPPER_CSS)
	private WebElement orderWrapper;
	public static final String ORDER_WRAPPER_HIDDEN_CSS = ".floating-menu .order-wrapper div a[href=\"/my-account/orders\"]";

	/**
	 * 订单是否显示
	 * @return
	 */
	public boolean orderWrapperIsDisplayed() {
		return elementIsShown(By.cssSelector(ORDER_WRAPPER_CSS));
	}

	/**
	 * 鼠标悬停到订单
	 * @return
	 */
	public boolean moveToOrderWrapper() {
		mouseMoveToElement(orderWrapper);
		return elementIsShown(By.cssSelector(ORDER_WRAPPER_HIDDEN_CSS));
	}

	// 优惠券
	public static final String TICKET_WRAPPER_CSS = ".floating-menu .ticket-wrapper .main-link";
	@FindBy(css = TICKET_WRAPPER_CSS)
	private WebElement ticketWrapper;
	public static final String TICKET_WRAPPER_HIDDEN_CSS = ".floating-menu .ticket-wrapper div a[href=\"/my-account/voucherPage#notUsedEV\"]";

	/**
	 * 优惠券是否显示
	 * @return
	 */
	public boolean ticketWrapperIsDisplayed() {
		return elementIsShown(By.cssSelector(TICKET_WRAPPER_CSS));
	}

	/**
	 * 鼠标悬停到优惠券
	 * @return
	 */
	public boolean moveToTicketWrapper() {
		mouseMoveToElement(ticketWrapper);
		return elementIsShown(By.cssSelector(TICKET_WRAPPER_HIDDEN_CSS));
	}

	// 快速购买
	public static final String QUICK_BUY_WRAPPER_CSS = ".floating-menu .flash-wrapper .main-link";
	@FindBy(css = QUICK_BUY_WRAPPER_CSS)
	private WebElement quickBuyWrapper;
	public static final String QUICK_BUY_WRAPPER_HIDDEN_CSS = ".floating-menu .flash-wrapper .hidden-xs";

	/**
	 * 快速购买是否显示
	 * @return
	 */
	public boolean quickBuyWrapperIsDisplayed() {
		return elementIsShown(By.cssSelector(QUICK_BUY_WRAPPER_CSS));
	}

	/**
	 * 鼠标悬停到快速购买
	 * @return
	 */
	public boolean moveToQuickBuyWrapper() {
		mouseMoveToElement(quickBuyWrapper);
		return elementIsShown(By.cssSelector(QUICK_BUY_WRAPPER_HIDDEN_CSS));
	}

	public static final String RETURN_TO_TOP_WRAPPER_CSS = ".floating-menu .arrow-wrapper";

	public boolean returnToTopWrapperIsDisplayed() {
		return elementIsShown(By.cssSelector(RETURN_TO_TOP_WRAPPER_CSS));
	}

}
