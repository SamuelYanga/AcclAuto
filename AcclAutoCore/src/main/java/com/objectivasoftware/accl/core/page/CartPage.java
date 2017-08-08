package com.objectivasoftware.accl.core.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.component.ChangeBuyerComponent;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class CartPage extends BasePage {

	public static final String CHECKOUT_BUTTON = "a.checkout-btn.checkout-button";
	@FindBy(css = CHECKOUT_BUTTON)
	private WebElement checkOutButton;

	public static final String NON_PARTICIPATION_BUTTON = "#cboxLoadedContent .newMemberPopUpDisplay .ambtn.ambtn-sm.am-white";
	@FindBy(css = NON_PARTICIPATION_BUTTON)
	private WebElement nonparticipation;

	public static final String CBOX_CLOSE_BUTTON_ID = "cboxClose";

	public void checkout() {
		WaitUtil.waitOn(myDriver).waitTime(1000L);
		checkOutButton.click();
		WaitUtil.waitOn(myDriver).waitTime(1000L);
		WaitUtil.waitOn(myDriver).untilPageDown();
		try {
			WaitUtil.waitOn(myDriver, 3000).untilShown(By.id(CBOX_CLOSE_BUTTON_ID));
			WaitUtil.waitOn(myDriver).waitTime(1000L);
			nonparticipation.click();
			WaitUtil.waitOn(myDriver).untilPageDown();
			WaitUtil.waitOn(myDriver).untilRemoved(By.id(CBOX_CLOSE_BUTTON_ID));
			WaitUtil.waitOn(myDriver).waitTime(3000L);
		} catch (TimeoutException e) {

		}
	}

	public static final String CHANGE_BUYER_LINK_CSS = ".cart-header .change-buyer-link";
	@FindBy(css = CHANGE_BUYER_LINK_CSS)
	private WebElement changeBuyerLink;

	public void changeBuyer(String buyer) {

		changeBuyerLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ChangeBuyerComponent.CHANGE_BUYER_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));

		ChangeBuyerComponent changeBuyerComponent = new ChangeBuyerComponent();
		changeBuyerComponent.selectNewBuyer(buyer);
	}

	public void changeBuyerToSelf() {

		changeBuyerLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ChangeBuyerComponent.CHANGE_BUYER_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));

		ChangeBuyerComponent changeBuyerComponent = new ChangeBuyerComponent();
		changeBuyerComponent.selfShopping();
	}

	public static final String PROMOTION_NAME_CSS = ".promotion-section .promotional-title";
	@FindBy(css = PROMOTION_NAME_CSS)
	private List<WebElement> promotionNameElement;

	public static final String GIFT_PRODUCT_NAME_CSS = ".promotion-section .prodname";
	@FindBy(css = GIFT_PRODUCT_NAME_CSS)
	private List<WebElement> giftProductNameElement;
	
	//send-promotional-show-detail
	public static final String PROMOTION_DETAIL_LINK_CSS = ".send-promotional-show-detail";
	@FindBy(css = PROMOTION_DETAIL_LINK_CSS)
	private WebElement promotionDetailLink;

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
}
