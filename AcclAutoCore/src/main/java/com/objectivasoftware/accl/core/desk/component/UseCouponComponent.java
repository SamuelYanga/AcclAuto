package com.objectivasoftware.accl.core.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.vo.orderdetail.DigitalTicket;;

public class UseCouponComponent extends BaseComponent {

	//
	public static final String COUPON_LIST_CSS = ".checkout-electronic-sattlement .coupons-listing";
	@FindBy(css = COUPON_LIST_CSS)
	private List<WebElement> couponList;
	
	//check .checkout-electronic-sattlement .coupons-listing .fake-check
	public static final String COUPON_ITEM_DIGICHK_CSS = ".fake-check";
	//编号  .checkout-electronic-sattlement .coupons-listing .check-text
	public static final String COUPON_ITEM_DIGINUM_CSS = "strong.check-text";
	//price .checkout-electronic-sattlement .coupons-listing .price-validation
	public static final String COUPON_ITEM_PRICEINPUT_CSS = ".price-validation";

	public static final String APPLY_COUPON_BUTTON_CSS = ".apply-coupon-btn";
	@FindBy(css = APPLY_COUPON_BUTTON_CSS)
	private WebElement applyCouponButton;
	
	public void selectCoupon(List<DigitalTicket> tickets) {
		for (DigitalTicket ticket : tickets) {
			WebElement couponElement = getCouponByNum(ticket.getDigiNum());
			WebElement chkElement = couponElement.findElement(By.cssSelector(COUPON_ITEM_DIGINUM_CSS));
			WebElement priceInputElement = couponElement.findElement(By.cssSelector(COUPON_ITEM_PRICEINPUT_CSS));

			chkElement.click();
			WaitUtil.waitOn(myDriver).untilElementToBeClickable(priceInputElement);
			
			priceInputElement.clear();
			priceInputElement.sendKeys(ticket.getUseMoney());
		}
		scrollMoveForCoupon(1000);
		applyCouponButton.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(COUPON_LIST_CSS));
	}
	
	private WebElement getCouponByNum(String num) {
		int counter = 0;
		for (WebElement coupon : couponList) {
			counter++;
			WebElement numElement = coupon.findElement(By.cssSelector(COUPON_ITEM_DIGINUM_CSS));
			String value = numElement.getText();
			if (num.equals(value)) {
				scrollMoveForCoupon(counter);
				return coupon;
			}
		}
		return null;
	}
	
	private void scrollMoveForCoupon(int n) {
		if (n <= 8) {
			return;
		}
		int move = 0;
		int size = getElementSize(couponList);
		move = n * size;

		String setscroll = "$('#cboxLoadedContent').scrollTop(" + move + ")";
		myDriver.executeScript(setscroll);
	}
	
	private int getElementSize(List<WebElement> list) {
		if (list == null || list.size() <= 1) {
			return 0;
		}

		try {
			return list.get(1).getLocation().getY() - list.get(0).getLocation().getY();
		} catch (Exception e) {
			return 0;
		}
	}
}
