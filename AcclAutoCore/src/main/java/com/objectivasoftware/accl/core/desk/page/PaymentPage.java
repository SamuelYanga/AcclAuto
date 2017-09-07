package com.objectivasoftware.accl.core.desk.page;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.util.UrlConstant;

public class PaymentPage extends BasePage {

	public enum PayType {
		ALI_PAY("ali-pay"), UNION_PAY("union-pay"), ALI_PAY_ONE_KEY("ali-pay-one-key"), WE_CHAT("we-chat"), ICBC(
				"icbc"), BANK_OF_CHINA("bank-of-china");

		PayType(String name) {
			this.name = name;
		}

		private String name;

		public String getName() {
			return name;
		}
	}

	public enum PayMethod {
		PAY_ONE, PAY_MORE
	}

	public static final String PAY_NOW_BUTTON_CSS = ".pay-now-button";
	@FindBy(css = PAY_NOW_BUTTON_CSS)
	private WebElement payNowButton;

	public static final String PAY_TYPES_CSS = ".pay-other-common";
	@FindBy(css = PAY_TYPES_CSS)
	private List<WebElement> payTypes;

	public static final String ORDER_SUCCESS_CSS = ".order-sucess p";
	@FindBy(css = ORDER_SUCCESS_CSS)
	private WebElement orderSuccess;

	public static final String ORDER_DETAIL_CSS = ".order-detail a[href^=\"/my-account/order\"]";
	@FindBy(css = ORDER_DETAIL_CSS)
	private WebElement orderDetailLink;

	public static final String PAY_METHODS_RADIO_CSS = ".custom-radio";
	@FindBy(css = PAY_METHODS_RADIO_CSS)
	private List<WebElement> payMethods;

	public static final String PAYMENT_AMOUNT2_ID = "paymentAmount2";
	public static final String PAYMENT_AMOUNT1_ID = "paymentAmount1";
	@FindBy(id = PAYMENT_AMOUNT1_ID)
	private WebElement paymentAmountInput;

	public static final String PAY_METHOD_CSS = "input.payment-method-radio";
	public static final String PAY_ICON_CSS = ".xradio";

	public void selectPayType(PayType type) {
		if (type == null) {
			type = PayType.UNION_PAY;
		}
		String typeName = type.getName();
		WaitUtil.waitOn(myDriver).untilListShown(By.cssSelector(PAY_TYPES_CSS));
		for (WebElement element : payTypes) {
			String value = element.getAttribute("data-payment-button");
			if (typeName.equals(value)) {
				WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
				element.click();
				WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
				WaitUtil.waitOn(myDriver, new UntilEvent() {
					@Override
					public boolean excute() {
						String classValue = element.getAttribute("class");
						if (classValue.contains("active")) {
							return true;
						}
						return false;
					}
				}).untilEventHappened();
				break;
			}
		}
	}

	public void selectPayType() {
		selectPayType(PayType.UNION_PAY);
	}

	private WebElement getPayMethod(PayMethod payMethod) {
		String temp;
		if (payMethod.name().equals("PAY_MORE")) {
			temp = "payment-more";
		} else {
			temp = "payment-one";
		}

		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(PAY_METHODS_RADIO_CSS));
		WaitUtil.waitOn(myDriver).untilListShown(By.cssSelector(PAY_METHODS_RADIO_CSS));
		for (WebElement element : payMethods) {
			WebElement method = element.findElement(By.cssSelector(PAY_METHOD_CSS));
			String value = method.getAttribute("value");
			if (temp.equals(value)) {
				return element.findElement(By.cssSelector(PAY_ICON_CSS));
			}
		}
		return null;
	}

	private void selectPayMethod(PayMethod payMethod) {
		WebElement method = getPayMethod(payMethod);
		if (payMethod.name().equals("PAY_MORE")) {
			try {
				WaitUtil.waitOn(myDriver).untilElementToBeClickable(method);
				method.click();
				WaitUtil.waitOn(myDriver,CommonConstant.WAIT_TIME_LEVEL4).untilShown(By.id(PAYMENT_AMOUNT2_ID));
			} catch (TimeoutException e) {
				WebElement method0 = getPayMethod(PayMethod.PAY_ONE);
				method0.click();
				WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
				method.click();
				WaitUtil.waitOn(myDriver).untilShown(By.id(PAYMENT_AMOUNT2_ID));
			}
		} else {
			method.click();
			WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
		}
	}

	public void payNow(PayType type) {
		payNow(type, null, null);
	}

	public void payNow(PayType type, PayMethod method, String amount) {
		if (method != null) {
			selectPayMethod(method);
		}
		selectPayType(type);
		if (method != null && method.name().equals("PAY_MORE")) {
			if (type.name().equals("UNION_PAY")) {
				paymentAmountInput.clear();
				paymentAmountInput.sendKeys(amount);
				WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
			}
		}

		int handleNum = myDriver.getWindowHandles().size();
		payNowButton.click();
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL4);
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver, new UntilEvent() {
			@Override
			public boolean excute() {
				int newHandleNum = myDriver.getWindowHandles().size();
				return newHandleNum == handleNum + 1;
			}
		}).untilEventHappened();

		String currentHandle = myDriver.getWindowHandle();
		Set<String> handles0 = myDriver.getWindowHandles();
		for (String s : handles0) {
			if (!currentHandle.equals(s)) {
				myDriver.switchTo().window(s);
			}
		}

		WaitUtil.waitOn(myDriver, new UntilEvent() {
			@Override
			public boolean excute() {
				String currentUrl = myDriver.getCurrentUrl();
				return currentUrl.contains(UrlConstant.UNION_PAY_DOMAIN);
			}
		}).untilEventHappened();
		WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL7).untilPageDown();
	}

	public boolean verifyOrderSuccess() {
		String text = orderSuccess.getText();
		return text.contains("您已成功支付");
	}

	public void navigateToOrderDetailWithNewHandle() {
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(ORDER_DETAIL_CSS));

		int handles = myDriver.getWindowHandles().size();
		orderDetailLink.click();

		WaitUtil.waitOn(myDriver, new UntilEvent() {
			@Override
			public boolean excute() {
				int newHandles = myDriver.getWindowHandles().size();
				return newHandles == handles + 1;
			}
		}).untilEventHappened();

		WaitUtil.waitOn(myDriver).untilPageDown();

		super.switchToWindowByUrl(UrlConstant.ORDER_DETAIL);

		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
	}

	public static final String VIEW_DETAIL_LINK_CSS = ".order-detail-link";
	@FindBy(css = VIEW_DETAIL_LINK_CSS)
	private WebElement viewDetailLink;

	public void navigateToOrderDetail() {
		viewDetailLink.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilListShown(By.cssSelector(OrderDetailPage.ORDER_DETAIL_HEAD_NUM_CSS));
	}

	public static final String APPLY_REFUND_CSS = ".am-dark-indigo";
	@FindBy(css = APPLY_REFUND_CSS)
	private WebElement applyForRefundLink;

	public static final String APPLY_REFUND_CONFIRM_CSS = "#cboxLoadedContent .remove-cart .confirm-refund";
	@FindBy(css = APPLY_REFUND_CONFIRM_CSS)
	private WebElement applyForRefundConfirm;

	public static final String REFUND_CONFIRM_CSS = "#cboxLoadedContent .remove-cart a.confirm";
	@FindBy(css = REFUND_CONFIRM_CSS)
	private WebElement refundConfirm;

	public static final String PAYMENT_STATUS_CSS = ".payment-detail-list .payment-status";
	@FindBy(css = PAYMENT_STATUS_CSS)
	private WebElement paymentStatus;
	
	public void applyRefund() {
		super.scrollMoveToElement(applyForRefundLink);
		applyForRefundLink.click();
		WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2)
				.untilElementToBeClickable(By.cssSelector(APPLY_REFUND_CONFIRM_CSS));

		applyForRefundConfirm.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2)
				.untilElementToBeClickable(By.cssSelector(REFUND_CONFIRM_CSS));

		refundConfirm.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL3).untilRemoved(By.cssSelector(REFUND_CONFIRM_CSS));
	}
}
