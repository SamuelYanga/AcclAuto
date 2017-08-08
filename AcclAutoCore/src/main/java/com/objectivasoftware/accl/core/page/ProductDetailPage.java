package com.objectivasoftware.accl.core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class ProductDetailPage extends BasePage {

	public static final String ADD_TO_CARD_BUTTON_ID = "addToCartButton";
	@FindBy(id = ADD_TO_CARD_BUTTON_ID)
	private WebElement addToCardButton;

	public static final String MINI_CART_LINK_CSS = ".site-mini-cart .miniCartSlot .mini-cart-link";
	@FindBy(css = MINI_CART_LINK_CSS)
	private WebElement miniCartLink;

	public static final String ADD_SUCCESS_WRAPPER = ".pdp-online-service-wrapper";

	public void addToCard() {
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.id(ADD_TO_CARD_BUTTON_ID));
		addToCardButton.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(ADD_SUCCESS_WRAPPER));
	}

	public void navigateToCartPageByMiniCart() {
		miniCartLink.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

}
