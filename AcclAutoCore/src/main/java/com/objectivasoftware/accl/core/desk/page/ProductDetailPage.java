package com.objectivasoftware.accl.core.desk.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.desk.component.AecLocationComponent;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.util.enu.AmplusPriceType;

public class ProductDetailPage extends BasePage {

	public static final String ADD_TO_CARD_BUTTON_ID = "addToCartButton";
	@FindBy(id = ADD_TO_CARD_BUTTON_ID)
	private WebElement addToCardButton;

	public static final String MINI_CART_LINK_CSS = ".site-mini-cart .miniCartSlot .mini-cart-link";
	@FindBy(css = MINI_CART_LINK_CSS)
	private WebElement miniCartLink;

	public static final String ADD_SUCCESS_WRAPPER = ".pdp-online-service-wrapper";

	public static final String AMPLUS_PRICE_TYPE_1 = "悦享分：";
	public static final String AMPLUS_PRICE_TYPE_2 = "悦享分 + 现金：";
	public static final String AMPLUS_PRICE_TYPE_SELECTED = "pink-bordered";

	public static final String AMPLUS_PRICE_TYPE = ".ampluspriceblock span";
	@FindBy(css = AMPLUS_PRICE_TYPE)
	private List<WebElement> amplusPriceTypes;

	public void addToCard() {
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.id(ADD_TO_CARD_BUTTON_ID));
		addToCardButton.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	public void navigateToCartPageByMiniCart() {
		miniCartLink.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	public String selectAmplusPriceType(AmplusPriceType type) {
		String filter;
		if ("INTEGRAL".equals(type.name())) {
			filter = AMPLUS_PRICE_TYPE_1;
		} else {
			filter = AMPLUS_PRICE_TYPE_2;
		}

		for (WebElement element : amplusPriceTypes) {
			String value = element.getText().trim();
			if (value.contains(filter)) {
				WaitUtil.waitOn(myDriver).untilElementToBeClickable(element);
				element.click();
				try {
					WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2, new UntilEvent() {
						@Override
						public boolean excute() {
							String classValue = element.getAttribute("class");
							return classValue.contains(AMPLUS_PRICE_TYPE_SELECTED);
						}
					}).untilEventHappened();
				} catch (TimeoutException e) {
					element.click();
					WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2, new UntilEvent() {
						@Override
						public boolean excute() {
							String classValue = element.getAttribute("class");
							return classValue.contains(AMPLUS_PRICE_TYPE_SELECTED);
						}
					}).untilEventHappened();
				}

				value = value.replace(filter, "").trim();
				value = value.split("\\+")[0].trim();
				value = value.substring(0, value.indexOf("."));
				return value;
			}
		}
		return null;
	}

	public static final String AMPLUS_CHECK_OUT_CSS = ".checkoutnow.amplus-gift";
	@FindBy(css = AMPLUS_CHECK_OUT_CSS)
	private WebElement amplusCheckOut;

	public static final String NON_PARTICIPATION_BUTTON = "#cboxLoadedContent .newMemberPopUpDisplay .ambtn.ambtn-sm.am-white";
	@FindBy(css = NON_PARTICIPATION_BUTTON)
	private WebElement nonparticipation;

	public void checkOutAmplusGift() {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(AMPLUS_CHECK_OUT_CSS));
		amplusCheckOut.click();
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

}
