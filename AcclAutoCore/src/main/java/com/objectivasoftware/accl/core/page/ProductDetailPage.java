package com.objectivasoftware.accl.core.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
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
				element.click();
				WaitUtil.waitOn(myDriver, new UntilEvent() {
					@Override
					public boolean excute() {
						String classValue = element.getAttribute("class");
						return classValue.contains(AMPLUS_PRICE_TYPE_SELECTED);
					}
				}).untilEventHappened();

				value = value.replace(filter, "").trim();
				value = value.split("\\+")[0].trim();
				value = value.substring(0, value.indexOf("."));
				return value;
			}
		}
		return null;
	}

}
