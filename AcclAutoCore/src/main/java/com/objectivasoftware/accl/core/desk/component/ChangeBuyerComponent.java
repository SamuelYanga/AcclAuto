package com.objectivasoftware.accl.core.desk.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class ChangeBuyerComponent extends BaseComponent {

	public static final String SELECT_PURCHASE_LINK_CSS = ".select-purchase span";
	@FindBy(css = SELECT_PURCHASE_LINK_CSS)
	private WebElement selectPurchaseLink;

	public static final String SELECT_FREQUENT_BUYER_LIST_ID = "selectFrequentBuyerList";
	public static final String CHANGE_BUYER_FORM_WRAPPER_CSS = ".changeBuyerFormWrapper";

	public static final String SELECT_BUYER_LIST_CSS = "#selectFrequentBuyerList .custom-radio";
	@FindBy(css = SELECT_BUYER_LIST_CSS)
	private List<WebElement> selectBuyerList;

	public static final String SELECT_BUYER_INFO_CSS = "input.sr-only";
	public static final String SELECT_BUYER_ICON_CSS = "i.xradio+span";

	public static final String CHANGE_BUYER_CLOSE_ID = "cboxClose";

	public static final String SELECT_UPDATE_CSS = ".btn.ambtn.ambtn-sm.update";
	@FindBy(css = SELECT_UPDATE_CSS)
	private WebElement updateButton;

	public static final String ABO_CODE_ID = "ABOCode";
	@FindBy(id = ABO_CODE_ID)
	private WebElement aboCode;

	public static final String SELECT_CONFIRM_CSS = ".buyer-info .confirm";
	@FindBy(css = SELECT_CONFIRM_CSS)
	private WebElement confirmButton;

	public static final String POPUP_CONFIRM_CSS = "#cboxLoadedContent .change-buyer-purchases-confirm-wrapper .cofirm";
	@FindBy(css = POPUP_CONFIRM_CSS)
	private WebElement popupConfirmButton;

	public static final String SELF_SHOPPING_CSS = ".buyer-info .purchase-btn";
	@FindBy(css = SELF_SHOPPING_CSS)
	private WebElement selfShoppingButton;

	public static final String CHANGE_BUYER_WRAPPER_CSS = ".change-buyer-purchases-wrapper";

	public void selectNewBuyer(final String user) {
		selectPurchaseLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(SELECT_BUYER_LIST_CSS));

		WebElement selectedBuyer = getSelectBuyer(user);
		selectedBuyer.click();
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);

		updateButton.click();
		WaitUtil.waitOn(myDriver).untilHidden(By.id(SELECT_FREQUENT_BUYER_LIST_ID));
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(CHANGE_BUYER_FORM_WRAPPER_CSS));
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(SELECT_CONFIRM_CSS));

		confirmButton.click();
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(POPUP_CONFIRM_CSS));
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);

		popupConfirmButton.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
	}

	private WebElement getSelectBuyer(String user) {
		WaitUtil.waitOn(myDriver).untilShown(By.id(SELECT_FREQUENT_BUYER_LIST_ID));
		for (WebElement selectBuyer : selectBuyerList) {
			WebElement buyerInfo = selectBuyer.findElement(By.cssSelector(SELECT_BUYER_INFO_CSS));
			String value = buyerInfo.getAttribute("value");
			if (user.equals(value)) {
				WebElement selectedBuyer = selectBuyer.findElement(By.cssSelector(SELECT_BUYER_ICON_CSS));
				return selectedBuyer;
			}
		}
		return null;
	}

	public void selfShopping() {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(selfShoppingButton);
		selfShoppingButton.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CHANGE_BUYER_WRAPPER_CSS));
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
	}

}
