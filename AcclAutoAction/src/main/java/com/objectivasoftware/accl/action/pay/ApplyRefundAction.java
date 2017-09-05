package com.objectivasoftware.accl.action.pay;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;

import com.objectivasoftware.accl.core.page.OrderDetailPage;
import com.objectivasoftware.accl.core.page.PaymentPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ApplyRefundAction {

	@And("Apply for refund on payment page.")
	public void applyRefund() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.applyRefund();
	}

	@Then("Verify the order status and refund status on order detail page.")
	public void verifyRefundStatus() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		Assert.assertThat(orderDetailPage.getOrderStatus(), equalTo(OrderDetailPage.ORDER_STATUS_ORDER_CANCEL));
		Assert.assertThat(orderDetailPage.getRefundStatus(), equalTo(OrderDetailPage.REFUND_STATUS_SUCCESS));
	}

}
