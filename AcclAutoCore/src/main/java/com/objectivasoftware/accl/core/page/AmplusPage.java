package com.objectivasoftware.accl.core.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;

public class AmplusPage extends BasePage {
	
	public static final String PRODUCT_LIST_CSS = ".product-listing .product-title-link";
	@FindBy(css = PRODUCT_LIST_CSS)
	private List<WebElement> productItems;
	
	public void selectProductAndNaviToPdp(String productName) {
		WebElement item = null;
		
		for(WebElement productItem : productItems) {
			String value = productItem.getAttribute("title").trim();
			if (productName.equals(value)) {
				item = productItem;
				break;
			}
		}
		
		super.scrollMoveToElement(item);
		item.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}
}