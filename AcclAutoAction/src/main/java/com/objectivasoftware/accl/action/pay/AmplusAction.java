package com.objectivasoftware.accl.action.pay;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import com.objectivasoftware.accl.core.component.AmplusCoffeeCouponComponent;
import com.objectivasoftware.accl.core.component.HeaderComponent;
import com.objectivasoftware.accl.core.page.AmplusPage;
import com.objectivasoftware.accl.core.page.CheckOutPage;
import com.objectivasoftware.accl.core.page.OrderDetailPage;
import com.objectivasoftware.accl.core.page.PaymentPage;
import com.objectivasoftware.accl.core.page.PaymentSuccessReceiptPage;
import com.objectivasoftware.accl.core.page.ProductDetailPage;
import com.objectivasoftware.accl.core.page.SearchPage;
import com.objectivasoftware.accl.core.page.UnionPayPage;
import com.objectivasoftware.accl.core.page.UnionPayResultPage;
import com.objectivasoftware.accl.core.page.PaymentPage.PayType;
import com.objectivasoftware.accl.core.util.enu.AmplusPriceType;
import com.objectivasoftware.accl.core.vo.checkout.UnionPayVO;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class AmplusAction {

	String integral = null;
	
	@And("Navigate to Amplus gift zone, and select a product, then navigate to pdp. productName=\"(.*)\"")
	public void selectAmplusProduct(String productName) {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.navigateToAmplusZone();

		AmplusPage amplusPage = new AmplusPage();
		amplusPage.selectProductAndNaviToPdp(productName);
	}

	@And("Select Amplus price type with integral, then add to cart.")
	public void selectIntegralAndAddToCart() {
		ProductDetailPage productDetailPage = new ProductDetailPage();
		integral = productDetailPage.selectAmplusPriceType(AmplusPriceType.INTEGRAL);

		productDetailPage.addToCard();
		productDetailPage.navigateToCartPageByMiniCart();
	}

	@And("Select Amplus price type with integral and cash, then add to cart.")
	public void selectIntegralCashAndAddToCart() {
		ProductDetailPage productDetailPage = new ProductDetailPage();
		integral = productDetailPage.selectAmplusPriceType(AmplusPriceType.INTEGRAL_CASH);

		productDetailPage.addToCard();
		productDetailPage.navigateToCartPageByMiniCart();
	}

	@And("Verify the deduct amplus point in order detail page.")
	public void verifyDeductAmplusPoint() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		Assert.assertTrue(orderDetailPage.verifyDeductAmplusPoint(integral));
	}

	@And("Search coffee coupon and navigate to pdp. productId=\"(.*)\"")
	public void searchCoffeeCouponAndNaviToPdp(String product) {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.searchProduct(product);

		SearchPage searchPage = new SearchPage();
		searchPage.navigateToPdp(product);
	}

	@Then("Select exchange type with integral, then exchange successful.")
	public void selectIntegralAndExchange() {
		ProductDetailPage productDetailPage = new ProductDetailPage();
		integral = productDetailPage.selectAmplusPriceType(AmplusPriceType.INTEGRAL);

		productDetailPage.addToCard();

		AmplusCoffeeCouponComponent amplusCoffeeCouponComponent = new AmplusCoffeeCouponComponent();
		String codeNumber = amplusCoffeeCouponComponent.exchangeCoffeeCoupon();
		Assert.assertTrue(StringUtils.isNotEmpty(codeNumber));
	}

	@And("Select exchange type with integral and cash, then click exchange, payment page is displayed.")
	public void selectIntegralCashAndExchange() {
		ProductDetailPage productDetailPage = new ProductDetailPage();
		integral = productDetailPage.selectAmplusPriceType(AmplusPriceType.INTEGRAL_CASH);

		productDetailPage.addToCard();

		AmplusCoffeeCouponComponent amplusCoffeeCouponComponent = new AmplusCoffeeCouponComponent();
		amplusCoffeeCouponComponent.exchangeCouponWithIntegralCash();
	}

	@And("Select Union-pay, and click pay now, then the Union-pay page displayed.")
	public void payWithUnionPay() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.payNow(PayType.UNION_PAY);
	}

	@And("Pay on the Union-pay page, then back to payment result.")
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

	@Then("Verify exchange Amplus Coffee Coupon successfully on payment result page.")
	public void verifyExchangeSuccessful() {
		PaymentSuccessReceiptPage paymentSuccessReceiptPage = new PaymentSuccessReceiptPage();
		String coponCode = paymentSuccessReceiptPage.exchangeCoffeeCouponSuccessful();
		Assert.assertTrue(StringUtils.isNotEmpty(coponCode));
	}

	@And("Search store product and navigate to pdp. productId=\"(.*)\"")
	public void searchStoreProductAndNaviToPdp(String product) {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.searchProduct(product);

		SearchPage searchPage = new SearchPage();
		searchPage.navigateToPdp(product);
	}

	@And("Select exchange type with integral, then exchange and navigate to check out page.")
	public void selectIntegralAndExchangeToCheckout() {
		ProductDetailPage productDetailPage = new ProductDetailPage();
		integral = productDetailPage.selectAmplusPriceType(AmplusPriceType.INTEGRAL);

		productDetailPage.checkOutAmplusGift();
	}

	@And("Select exchange type with integral and cash, then exchange and navigate to check out page.")
	public void selectIntegralCashAndExchangeToCheckout() {
		ProductDetailPage productDetailPage = new ProductDetailPage();
		integral = productDetailPage.selectAmplusPriceType(AmplusPriceType.INTEGRAL_CASH);

		productDetailPage.checkOutAmplusGift();
	}

	@And("Click payment button, then pay success and navigate to payment page.")
	public void navigateToPayment() {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.checkOutAndNaviToPayment();
		PaymentPage paymentPage = new PaymentPage();
		Assert.assertTrue(paymentPage.verifyOrderSuccess());
	}

	@And("Navigate to order detail page by click View Detail Link from payment.")
	public void navigateToOrderDetail() {
		PaymentPage paymentPage = new PaymentPage();
		paymentPage.navigateToOrderDetail();
	}

}
