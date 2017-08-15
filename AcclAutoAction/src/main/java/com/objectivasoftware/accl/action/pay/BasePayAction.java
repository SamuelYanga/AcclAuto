package com.objectivasoftware.accl.action.pay;

import org.junit.Assert;

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

	@And("Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.")
	public void payWithUnionPay() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.payNow(PayType.UNION_PAY, PayMethod.PAY_MORE, "0.2");
	}

	@And("Select Union-pay with payment more, and click pay now, then the Union-pay page displayed.")
	public void payWithUnionPayMore() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.payNow(PayType.UNION_PAY, PayMethod.PAY_MORE, "0.2");
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

	@Then("Cancel the order and verify the order status.")
	public void cancelOrder() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		Assert.assertTrue(orderDetailPage.verifyOrderSuccess());
		orderDetailPage.cancelOrder();
		Assert.assertTrue(orderDetailPage.verifyOrderCancel());
	}

}
