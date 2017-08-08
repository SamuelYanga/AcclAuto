package com.objectivasoftware.accl.action.pay;

import org.junit.Assert;

import com.objectivasoftware.accl.core.component.HeaderComponent;
import com.objectivasoftware.accl.core.page.CartPage;
import com.objectivasoftware.accl.core.page.ProductDetailPage;
import com.objectivasoftware.accl.core.page.PromotionListPage;
import com.objectivasoftware.accl.core.page.PromotionPage;

import cucumber.api.java.en.And;

public class PromotionAction {

	@And("Select a province for stock. provinceName=\"(.*)\"")
	public void selectProvince(String provinceName) {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.selectProvince(provinceName);
	}

	@And("Navigate to promotion zone, and select a promotion. promotionName=\"(.*)\"")
	public void selectPromotion(String promotionName) {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.navigateToPromotionZone();

		PromotionListPage promotionListPage = new PromotionListPage();
		promotionListPage.selectPromotion(promotionName);
	}

	@And("Select a promotion product and navigate to pdp, then add to card. productName=\"(.*)\"")
	public void selectProduct(String productName) {
		PromotionPage promotionPage = new PromotionPage();
		promotionPage.selectProductToPdp(productName);

		ProductDetailPage productDetailPage = new ProductDetailPage();
		productDetailPage.addToCard();

		productDetailPage.navigateToCartPageByMiniCart();
	}

	@And("Verify the promotion gift is displayed correctly. promotionName=\"(.*)\" giftName=\"(.*)\"")
	public void verifyGiftIsCorrectly(String promotionName, String giftName) {
		CartPage cartPage = new CartPage();
		Assert.assertTrue(cartPage.verifyPromotion(promotionName, giftName));
	}

}
