package com.objectivasoftware.accl.core.desk.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;

public class SearchPage extends BasePage {

	// product items
	public static final String PRODUCT_LIST_CSS = ".product-listing .am-product-tout";
	@FindBy(css = PRODUCT_LIST_CSS)
	private List<WebElement> productList;

	public static final String PRODUCT_ITEM_CODE_CSS = ".productCodePost";
	public static final String PRODUCT_ITEM_IMG_CSS = ".pictureimg";

	private WebElement getProductById(String product) {
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(PRODUCT_LIST_CSS));
		for (WebElement element : productList) {
			WebElement proIdElement = element.findElement(By.cssSelector(PRODUCT_ITEM_CODE_CSS));
			String value = proIdElement.getAttribute("value");
			if (product.equals(value.trim())) {
				return element;
			}
		}
		return null;
	}

	public void navigateToPdp(String product) {
		WebElement productElement = getProductById(product);
		WebElement proImgElement = productElement.findElement(By.cssSelector(PRODUCT_ITEM_IMG_CSS));
		try {
			proImgElement.click();
		} catch (WebDriverException e) {
			proImgElement.click();
		}
	}

}
