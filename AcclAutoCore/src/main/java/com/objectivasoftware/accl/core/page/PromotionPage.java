package com.objectivasoftware.accl.core.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;

public class PromotionPage extends BasePage {

	public static final String PROMOTION_PRODUCT_LIST_CSS = ".product-listing .pictureimg";
	@FindBy(css = PROMOTION_PRODUCT_LIST_CSS)
	private List<WebElement> promotionProductList;
	
	public void selectProductToPdp(String promotionName) {
		for (WebElement element : promotionProductList) {
			String value = element.getAttribute("title").trim();
			if (promotionName.equals(value)) {
				super.scrollMoveToElement(element);
				element.click();
				WaitUtil.waitOn(myDriver).untilPageDown();
				return;
			}
		}
	}
}
