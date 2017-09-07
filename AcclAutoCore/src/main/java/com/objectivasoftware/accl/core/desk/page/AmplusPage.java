package com.objectivasoftware.accl.core.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class AmplusPage extends BasePage {

	// 产品列表
	public static final String PRODUCT_LIST_CSS = ".product-listing .swiper-slide-active .product-title-link";
	@FindBy(css = PRODUCT_LIST_CSS)
	private List<WebElement> productItems;

	/**
	 * 选择一个产品，点击进入pdp
	 * 
	 * @param productName
	 */
	public void selectProductAndNaviToPdp(String productName) {
		WebElement item = getProduct(productName);
		super.scrollMoveToElement(item);
		item.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	int productNum0 = 0;
	int index = 0;

	private WebElement getProduct(String productName) {
		productNum0 = productItems.size();
		WebElement item = null;

		for (int i = index; i < productNum0; i++, index++) {
			WebElement productItem = productItems.get(i);
			String value = productItem.getAttribute("title").trim();
			if (productName.equals(value)) {
				item = productItem;
				break;
			}
		}

		if (item == null) {
			if (loadMore()) {
				item = getProduct(productName);
			} else {
				return item;
			}
		}
		return item;
	}

	private boolean loadMore() {
		String script = "return document.body.clientHeight;";
		Object obj = myDriver.executeScript(script);
		int webPageViewHeight0 = Integer.valueOf(obj.toString());
		windowScrollToTop(webPageViewHeight0);
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOAD_MORE_LOADER_CSS));
		obj = myDriver.executeScript(script);
		int webPageViewHeight1 = Integer.valueOf(obj.toString());
		return !(webPageViewHeight1 == webPageViewHeight0);
	}
}
