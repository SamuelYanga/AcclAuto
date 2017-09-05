package com.objectivasoftware.accl.action.pay;

import org.junit.Assert;

import static org.hamcrest.Matchers.*;

import com.objectivasoftware.accl.core.component.HeaderComponent;
import com.objectivasoftware.accl.core.component.LoginComponent;
import com.objectivasoftware.accl.core.page.CartPage;
import com.objectivasoftware.accl.core.page.CheckOutPage;
import com.objectivasoftware.accl.core.page.HomePage;
import com.objectivasoftware.accl.core.page.OrderDetailPage;
import com.objectivasoftware.accl.core.page.PaymentPage;
import com.objectivasoftware.accl.core.page.ProductDetailPage;
import com.objectivasoftware.accl.core.page.SearchPage;
import com.objectivasoftware.accl.core.page.UnionPayPage;
import com.objectivasoftware.accl.core.page.UnionPayResultPage;
import com.objectivasoftware.accl.core.page.PaymentPage.PayMethod;
import com.objectivasoftware.accl.core.page.PaymentPage.PayType;
import com.objectivasoftware.accl.core.page.PaymentSuccessReceiptPage;
import com.objectivasoftware.accl.core.vo.checkout.UnionPayVO;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class BasePayAction {
	@Given("Open the home page.")
	public void openHomePage() {
		HomePage.open();
	}

	@And("Login with valid user. userName=\"(.*)\" password=\"(.*)\"")
	public void login(String userName, String password) {
		HeaderComponent headerComponent = new HeaderComponent();
		if (!headerComponent.isLogin()) {
			headerComponent.clickLogin();
			LoginComponent loginComponent = new LoginComponent();
			loginComponent.login(userName, password);
		}

		if (!headerComponent.isCartNull()) {
			headerComponent.navigateToCartPageByMiniCart();
			CartPage cartPage = new CartPage();
			cartPage.cleanCard();
			this.openHomePage();
		}

	}

	@And("Search and add a product to card. productId=\"(.*)\"")
	public void addProductToCard(String product) {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.searchProduct(product);

		SearchPage searchPage = new SearchPage();
		searchPage.navigateToPdp(product);

		ProductDetailPage productDetailPage = new ProductDetailPage();
		productDetailPage.addToCard();

		productDetailPage.navigateToCartPageByMiniCart();
	}

	@And("Click checkout button on card page, then navigate to check out page.")
	public void checkOutProduct() {
		CartPage cartPage = new CartPage();
		cartPage.checkout();
	}

	@And("Add a delivery address on check out page.")
	public void addNewAddress() {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.addNewAddress();
	}

	@And("Add and select a delivery address by province on check out page. province=\"(.*)\"")
	public void addAndSelectNewAddress(String provinceName) {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.addNewAddress(provinceName);
		checkOutPage.selectAddress(provinceName);
	}

	@And("Select a delivery address on check out page.")
	public void selectAddress() {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.addNewAddress();
	}

	@And("Click payment button, then navigate to payment page.")
	public void navigateToPayment() {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.checkOutAndNaviToPayment();
	}

	@And("Select Union-pay with multiple partial pay, and click pay now, then the Union-pay page displayed.")
	public void payWithUnionPayMulti() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.payNow(PayType.UNION_PAY, PayMethod.PAY_MORE, "0.2");
	}

	@And("Select Union-pay with one pay, and click pay now, then the Union-pay page displayed.")
	public void payWithUnionPayOne() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.payNow(PayType.UNION_PAY, PayMethod.PAY_ONE, null);
	}

	@And("Pay on the Union-pay page, then back to payment page.")
	public void unionPay() {
		UnionPayPage unionPayPage = new UnionPayPage();
		unionPayPage.pay(UnionPayVO.getDefaultVO());

		UnionPayResultPage unionPayResultPage = new UnionPayResultPage();
		Assert.assertTrue(unionPayResultPage.verifyPaySuccess());
		unionPayResultPage.backToMerchant();

		PaymentSuccessReceiptPage paymentSuccessReceiptPage = new PaymentSuccessReceiptPage();
		Assert.assertTrue(paymentSuccessReceiptPage.verifyPaySuccess());
		paymentSuccessReceiptPage.close();
	}

	@And("Navigate to order detail page from payment.")
	public void navigateToOrderDetail() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.navigateToOrderDetailWithNewHandle();
	}

	@Then("Cancel the order which is pay completed and verify the order status.")
	public void cancelOrderWithPaySuccess() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		String orderStatus = orderDetailPage.getOrderStatus();
		Assert.assertThat(orderStatus, equalToIgnoringWhiteSpace(OrderDetailPage.ORDER_STATUS_PAY_SUCCESS));
		orderDetailPage.cancelOrder();
		orderStatus = orderDetailPage.getOrderStatus();
		Assert.assertThat(orderStatus, equalToIgnoringWhiteSpace(OrderDetailPage.ORDER_STATUS_ORDER_CANCEL));
	}

	@Then("Cancel the order which is in payment and verify the order status.")
	public void cancelOrderWhichInPayment() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		String orderStatus = orderDetailPage.getOrderStatus();
		Assert.assertThat(orderStatus, equalToIgnoringWhiteSpace(OrderDetailPage.ORDER_STATUS_IN_PAYMENT));
		orderDetailPage.cancelOrder();
		orderStatus = orderDetailPage.getOrderStatus();
		Assert.assertThat(orderStatus, equalToIgnoringWhiteSpace(OrderDetailPage.ORDER_STATUS_ORDER_CANCEL));
	}

	@Then("Cancel the order which is pending payment with no pay and verify the order status.")
	public void cancelOrderWithNoPay() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		String orderStatus = orderDetailPage.getOrderStatus();
		Assert.assertThat(orderStatus, equalToIgnoringWhiteSpace(OrderDetailPage.ORDER_STATUS_PENDING_PAYMENT));
		orderDetailPage.waittime(10000);
		orderDetailPage.cancelOrder();
		orderStatus = orderDetailPage.getOrderStatus();
		Assert.assertThat(orderStatus, equalToIgnoringWhiteSpace(OrderDetailPage.ORDER_STATUS_ORDER_CANCEL));
	}

}
