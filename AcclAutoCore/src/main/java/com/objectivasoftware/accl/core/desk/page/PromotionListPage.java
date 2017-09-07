package com.objectivasoftware.accl.core.desk.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class PromotionListPage extends BasePage {

	public static final String PROMOTION_LIST_CSS = ".pro-listing .details .pro-title a";
	@FindBy(css = PROMOTION_LIST_CSS)
	private List<WebElement> promotionList;

	public void selectPromotion(String promotionName) {
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL2);
		for (WebElement element : promotionList) {
			String value = element.getAttribute("title").trim();
			if (promotionName.equals(value)) {
				super.scrollMoveToElement(element);
				element.click();
				WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
				WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
				WaitUtil.waitOn(myDriver).untilPageDown();
				WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".product-listing"));
				return;
			}
		}
	}
}
