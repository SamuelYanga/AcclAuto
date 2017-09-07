package com.objectivasoftware.accl.core.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.component.AecLocationComponent;
import com.objectivasoftware.accl.core.component.ChangeBuyerComponent;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class CartPage extends BasePage {

	// [去结算]按钮
	public static final String CHECKOUT_BUTTON = "a.checkout-btn.checkout-button";
	@FindBy(css = CHECKOUT_BUTTON)
	private WebElement checkOutButton;

	// 不参与活动按钮
	public static final String NON_PARTICIPATION_BUTTON = "#cboxLoadedContent .newMemberPopUpDisplay .ambtn.ambtn-sm.am-white";
	@FindBy(css = NON_PARTICIPATION_BUTTON)
	private WebElement nonparticipation;

	// 关闭弹窗
	public static final String CBOX_CLOSE_BUTTON_ID = "cboxClose";

	/**
	 * 点击[去结算]
	 */
	public void checkout() {
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(checkOutButton);
		checkOutButton.click();
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));

		try {
			WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL3)
					.untilShown(By.cssSelector(NON_PARTICIPATION_BUTTON));
			WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
			nonparticipation.click();
			WaitUtil.waitOn(myDriver).untilPageDown();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(NON_PARTICIPATION_BUTTON));
			WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL3);
		} catch (TimeoutException e) {

		} catch (NoSuchElementException e) {

		}

		try {
			WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2)
					.untilShown(By.cssSelector(AecLocationComponent.CHANGE_AEC_LOCATION_POPUP));
			AecLocationComponent aecLocationComponent = new AecLocationComponent();
			aecLocationComponent.closeAecComponent();
		} catch (TimeoutException e) {
		}
	}

	// 更换购买人链接
	public static final String CHANGE_BUYER_LINK_CSS = ".cart-header .change-buyer-link";
	@FindBy(css = CHANGE_BUYER_LINK_CSS)
	private WebElement changeBuyerLink;

	/**
	 * 更换购买人
	 * @param buyer
	 */
	public void changeBuyer(String buyer) {

		changeBuyerLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ChangeBuyerComponent.CHANGE_BUYER_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));

		ChangeBuyerComponent changeBuyerComponent = new ChangeBuyerComponent();
		changeBuyerComponent.selectNewBuyer(buyer);
	}

	/**
	 * 购买人改为自己
	 */
	public void changeBuyerToSelf() {

		changeBuyerLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ChangeBuyerComponent.CHANGE_BUYER_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));

		ChangeBuyerComponent changeBuyerComponent = new ChangeBuyerComponent();
		changeBuyerComponent.selfShopping();
	}

	// 促销活动名称列表
	public static final String PROMOTION_NAME_CSS = ".promotion-section .promotional-title";
	@FindBy(css = PROMOTION_NAME_CSS)
	private List<WebElement> promotionNameElement;

	// 礼品名称列表
	public static final String GIFT_PRODUCT_NAME_CSS = ".promotion-section .prodname";
	@FindBy(css = GIFT_PRODUCT_NAME_CSS)
	private List<WebElement> giftProductNameElement;

	// 促销详情链接
	public static final String PROMOTION_DETAIL_LINK_CSS = ".send-promotional-show-detail";
	@FindBy(css = PROMOTION_DETAIL_LINK_CSS)
	private WebElement promotionDetailLink;

	/**
	 * 验证促销名称，礼品名称是否显示
	 * @param promotionName
	 * @param giftName
	 * @return
	 */
	public boolean verifyPromotion(String promotionName, String giftName) {
		List<String> promotionList = new ArrayList<>();
		for (WebElement promotion : promotionNameElement) {
			promotionList.add(promotion.getText().trim());
		}

		List<String> giftList = new ArrayList<>();
		for (WebElement gift : giftProductNameElement) {
			giftList.add(gift.getText().trim());
		}

		return promotionList.contains(promotionName) && giftList.contains(giftName);
	}

	// 购物车列表
	public static final String CART_LIST_CSS = ".cart-listing";
	@FindBy(css = CART_LIST_CSS)
	private List<WebElement> cartList;

	// 购物车详情链接列表
	public static final String CART_DETAIL_LINKS_CSS = ".cart-listing .cartdetails a";
	@FindBy(css = CART_DETAIL_LINKS_CSS)
	private List<WebElement> cartDetailLinks;
	
	// 删除购物车链接列表
	public static final String CART_DELETE_LINKS_CSS = ".cart-listing .transparent-button";
	@FindBy(css = CART_DELETE_LINKS_CSS)
	private List<WebElement> cartDeleteLinks;
	
	// 删除购物车确认按钮
	public static final String REMOVE_CART_CONFIRM_CSS = "#cboxLoadedContent .remove-modal-container .remove-cart .confirm";
	@FindBy(css = REMOVE_CART_CONFIRM_CSS)
	private WebElement removeCartConfirm;

	/**
	 * 打开购物车详情
	 */
	private void openCartDetails() {
		
		try {
			WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2).untilShown(By.cssSelector(CART_DETAIL_LINKS_CSS));
		} catch (TimeoutException e) {
			return;
		}
		
		if (cartDetailLinks == null || cartDetailLinks.size() == 0) {
			return;
		}
		for (WebElement cartDetailLink : cartDetailLinks) {
			cartDetailLink.click();
			WaitUtil.waitOn(myDriver, new UntilEvent() {
				@Override
				public boolean excute() {
					String linkName = cartDetailLink.getText();
					return linkName.contains("收起");
				}
			}).untilEventHappened();
		}
	}

	/**
	 * 删除购物车
	 */
	private void deleteCart() {

		if (cartDeleteLinks == null || cartDeleteLinks.size() == 0) {
			return;
		}

		int size = cartDeleteLinks.size();

		for (int i = 0; i < size; i++) {
			WebElement cartDeleteLink = cartDeleteLinks.get(0);
			cartDeleteLink.click();
			WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(REMOVE_CART_CONFIRM_CSS));
			removeCartConfirm.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).untilPageDown();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(REMOVE_CART_CONFIRM_CSS));
		}
	}
	
	public void cleanCard() {
		openCartDetails();
		deleteCart();
	}

}
