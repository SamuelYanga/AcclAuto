package com.objectivasoftware.accl.action.pay;

import org.junit.Assert;

import com.objectivasoftware.accl.core.component.HeaderComponent;
import com.objectivasoftware.accl.core.page.AmplusPage;
import com.objectivasoftware.accl.core.page.OrderDetailPage;
import com.objectivasoftware.accl.core.page.ProductDetailPage;
import com.objectivasoftware.accl.core.util.enu.AmplusPriceType;

import cucumber.api.java.en.And;

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

}
