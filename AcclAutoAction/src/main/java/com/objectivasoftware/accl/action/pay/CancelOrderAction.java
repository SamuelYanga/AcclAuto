package com.objectivasoftware.accl.action.pay;

import static org.hamcrest.Matchers.*;
import org.junit.Assert;

import com.objectivasoftware.accl.core.desk.page.OrderDetailPage;
import com.objectivasoftware.accl.core.util.CommonConstant;

import cucumber.api.java.en.Then;

public class CancelOrderAction {

	String message1 = "";
	String message2 = "";

	@Then("Cancel order multiple times, then verify cancel error after 5 times. productId=\"(.*)\" provinceName=\"(.*)\"")
	public void cancelOrderWithPaySuccess(String product, String provinceName) {

		int counter = 0;
		while (cancelResult()) {
			if (++counter > 5) {
				break;
			}
			createOrderAgain(product, provinceName);
		}

		Assert.assertThat(message1, equalTo(CommonConstant.CANCEL_ORDER_ERROR_5TIMES_MESSAGE1));
		Assert.assertThat(message2, equalTo(CommonConstant.CANCEL_ORDER_ERROR_5TIMES_MESSAGE2));
	}

	private void createOrderAgain(String product, String provinceName) {
		BasePayAction base = new BasePayAction();
		base.addProductToCard(product);
		base.checkOutProduct();
		base.addAndSelectNewAddress(provinceName);
		base.navigateToPayment();
		base.payWithUnionPayOne();
		base.unionPay();
		base.navigateToOrderDetail();
	}

	private boolean cancelResult() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		String[] msgs = new String[2];
		boolean cancelResult = orderDetailPage.verifyCancelOrderErrorMore5(msgs);
		if (!cancelResult) {
			message1 = msgs[0];
			message2 = msgs[1];
		}

		return cancelResult;
	}

}
