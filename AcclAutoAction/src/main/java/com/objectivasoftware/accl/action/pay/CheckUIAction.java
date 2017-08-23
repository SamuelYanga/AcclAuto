package com.objectivasoftware.accl.action.pay;

import org.junit.Assert;

import com.objectivasoftware.accl.core.component.FloatMenuComponent;
import com.objectivasoftware.accl.core.component.HeaderComponent;
import com.objectivasoftware.accl.core.page.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CheckUIAction {

	@And("Check home page is displayed correctly with guest.")
	public void checkHomeUIWithGuest() {
		HomePage homePage = new HomePage();
		homePage.windowScrollToBottom();
		Assert.assertTrue(homePage.kvBillboardIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.articleBannersIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.categoryProductShowIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.amplusGiftShowIsDisplayed());
		homePage.screenshot();
	}

	@And("Check home page is displayed correctly when user login with Standard Edition.")
	public void checkHomeUIWithStandardEdition() {
		HomePage homePage = new HomePage();
		homePage.returnToDefaultBusinessView();
		homePage.windowScrollToBottom();
		Assert.assertTrue(homePage.kvBillboardIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.articleBannersIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.categoryProductShowIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.amplusGiftShowIsDisplayed());
		homePage.screenshot();
	}

	@And("Check home page is displayed correctly when user login with Shortcut Edition.")
	public void checkHomeUIWithShortcutEdition() {
		HomePage homePage = new HomePage();
		homePage.windowScrollToBottom();
		Assert.assertTrue(homePage.kvBillboardIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.hotProductShowIsDisplayed());
		homePage.screenshot();
		Assert.assertTrue(homePage.amplusGiftShowIsDisplayed());
		homePage.screenshot();
	}

	String businessView = "";

	@And("Change business view.")
	public void changeBusinessView() {
		HomePage homePage = new HomePage();
		businessView = homePage.changeBusinessView();
	}

	@And("Log out from home page.")
	public void logoutFromHomepage() {
		HomePage homePage = new HomePage();
		homePage.logout();
	}

	@Then("Verify the business view is not changed.")
	public void verifyBusinessIsNotChanged() {
		HomePage homePage = new HomePage();
		String currentBusinessView = homePage.getCurrentBusinessView();
		Assert.assertTrue(businessView.equals(currentBusinessView));
		homePage.returnToDefaultBusinessView();
	}

	@And("Check header is displayed correctly with guest.")
	public void checkHeaderUIWithGuest() {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.screenshot();
		Assert.assertTrue(headerComponent.logoIsDisplayed());
		Assert.assertTrue(headerComponent.welcomeTextIsDisplayed());
		Assert.assertTrue(headerComponent.guestHelpCenterIsDisplayed());
		Assert.assertTrue(headerComponent.homepageLinkIsDisplayed());
		Assert.assertTrue(headerComponent.guestOnlineChatIsDisplayed());
		Assert.assertTrue(headerComponent.currentLocationIsDisplayed());
		Assert.assertTrue(headerComponent.searchInputIsDisplayed());
		Assert.assertTrue(headerComponent.howToBuyIsDisplayed());
		Assert.assertTrue(headerComponent.miniCartLinkIsDisplayed());
		Assert.assertTrue(headerComponent.gategoryNavigationBarIsDisplayed());
		Assert.assertTrue(headerComponent.hotSalesIsDisplayed());
		Assert.assertTrue(headerComponent.promotionsZoneLinkIsDisplayed());
		Assert.assertTrue(headerComponent.amplusGiftsLinkIsDisplayed());
		Assert.assertTrue(headerComponent.crossBorderLinkIsDisplayed());
	}

	@And("Check header is displayed correctly when user login.")
	public void checkHeaderUIWithUser() {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.screenshot();
		Assert.assertTrue(headerComponent.logoIsDisplayed());
		Assert.assertTrue(headerComponent.currentBuyerTextIsDisplayed());
		Assert.assertTrue(headerComponent.changeBuyerLinkIsDisplayed());
		Assert.assertTrue(headerComponent.bussinessViewIsDisplayed());
		Assert.assertTrue(headerComponent.myAccountLinkIsDisplayed());
		Assert.assertTrue(headerComponent.eVouchersLinkIsDisplayed());
		Assert.assertTrue(headerComponent.userHelpCenterIsDisplayed());
		Assert.assertTrue(headerComponent.homepageLinkIsDisplayed());
		Assert.assertTrue(headerComponent.userOnlineChatIsDisplayed());
		Assert.assertTrue(headerComponent.amwayNetIsDisplayed());
		Assert.assertTrue(headerComponent.currentLocationIsDisplayed());
		Assert.assertTrue(headerComponent.searchInputIsDisplayed());
		Assert.assertTrue(headerComponent.quickBuyIsDisplayed());
		Assert.assertTrue(headerComponent.orderQueryIsDisplayed());
		Assert.assertTrue(headerComponent.miniCartLinkIsDisplayed());
		Assert.assertTrue(headerComponent.gategoryNavigationBarIsDisplayed());
		Assert.assertTrue(headerComponent.hotSalesIsDisplayed() || headerComponent.quotaSalesIsDisplayed());
		Assert.assertTrue(headerComponent.promotionsZoneLinkIsDisplayed());
		Assert.assertTrue(headerComponent.amplusGiftsLinkIsDisplayed());
		Assert.assertTrue(headerComponent.crossBorderLinkIsDisplayed());

	}

	@Then("Check header is displayed correctly in cart page.")
	public void checkHeaderUIInCartPage() {
		HeaderComponent headerComponent = new HeaderComponent();
		headerComponent.screenshot();

		// logo图标
		Assert.assertTrue(headerComponent.logoIsDisplayed());
		// 我的账户
		Assert.assertTrue(headerComponent.myAccountLinkIsDisplayed());
		// 电子券
		Assert.assertTrue(headerComponent.eVouchersLinkIsDisplayed());
		// 帮助中心
		Assert.assertTrue(headerComponent.userHelpCenterIsDisplayed());
		// 在线客服
		Assert.assertTrue(headerComponent.userOnlineChatIsDisplayed());
		// 易联网
		Assert.assertTrue(headerComponent.amwayNetIsDisplayed());
		// 搜索框
		Assert.assertTrue(headerComponent.searchInputIsDisplayed());

	}

	@And("Check float menu is displayed correctly with guest.")
	public void checkFloatMenuUIWithGuest() {
		FloatMenuComponent floatMenuComponent = new FloatMenuComponent();
		floatMenuComponent.screenshot();
		Assert.assertTrue(floatMenuComponent.returnToTopWrapperIsDisplayed());
	}

	@And("Check float menu is displayed correctly when user login.")
	public void checkFloatMenuUIWithUser() {
		FloatMenuComponent floatMenuComponent = new FloatMenuComponent();
		floatMenuComponent.screenshot();
		Assert.assertTrue(floatMenuComponent.currentBuyerWrapperIsDisplayed());
		Assert.assertTrue(floatMenuComponent.cartWrapperIsDisplayed());
		Assert.assertTrue(floatMenuComponent.orderWrapperIsDisplayed());
		Assert.assertTrue(floatMenuComponent.ticketWrapperIsDisplayed());
		Assert.assertTrue(floatMenuComponent.quickBuyWrapperIsDisplayed());
		Assert.assertTrue(floatMenuComponent.returnToTopWrapperIsDisplayed());
	}

	@Then("Mouse move to \\[Change buyer\\] of float ment, then change buyer link displayed.")
	public void mouseMoveToChangeBuyerWrapper() {
		FloatMenuComponent floatMenuComponent = new FloatMenuComponent();
		Assert.assertTrue(floatMenuComponent.moveToCurrentBuyerWrapper());
		floatMenuComponent.screenshot();
	}

	@Then("Mouse move to \\[Cart\\] of float ment, then cart link displayed.")
	public void mouseMoveToCartWrapper() {
		FloatMenuComponent floatMenuComponent = new FloatMenuComponent();
		Assert.assertTrue(floatMenuComponent.moveToCartWrapper());
		floatMenuComponent.screenshot();
	}

	@Then("Mouse move to \\[Order history\\] of float ment, then order history link displayed.")
	public void mouseMoveToOrderWrapper() {
		FloatMenuComponent floatMenuComponent = new FloatMenuComponent();
		Assert.assertTrue(floatMenuComponent.moveToOrderWrapper());
		floatMenuComponent.screenshot();
	}

	@Then("Mouse move to \\[E-voucher\\] of float ment, then e-voucher link displayed.")
	public void mouseMoveToTicketWrapper() {
		FloatMenuComponent floatMenuComponent = new FloatMenuComponent();
		Assert.assertTrue(floatMenuComponent.moveToTicketWrapper());
		floatMenuComponent.screenshot();
	}

	@Then("Mouse move to \\[Quick buy\\] of float ment, then Quick buy content displayed.")
	public void mouseMoveToQuickBuyWrapper() {
		FloatMenuComponent floatMenuComponent = new FloatMenuComponent();
		Assert.assertTrue(floatMenuComponent.moveToQuickBuyWrapper());
		floatMenuComponent.screenshot();
	}

}
