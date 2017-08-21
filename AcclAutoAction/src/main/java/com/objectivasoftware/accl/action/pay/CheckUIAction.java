package com.objectivasoftware.accl.action.pay;

import org.junit.Assert;

import com.objectivasoftware.accl.core.page.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class CheckUIAction {

	@And("Check home page is displayed correctly with guest.")
	public void checkHomeUIWithGuest() {
		HomePage homePage = new HomePage();
		homePage.windowScrollToBottom();
		Assert.assertTrue(homePage.kvBillboardIsDisplayed());
		Assert.assertTrue(homePage.articleBannersIsDisplayed());
		Assert.assertTrue(homePage.categoryProductShowIsDisplayed());
		Assert.assertTrue(homePage.amplusGiftShowIsDisplayed());
	}

	@And("Check home page is displayed correctly when user login with Standard Edition.")
	public void checkHomeUIWithStandardEdition() {
		HomePage homePage = new HomePage();
		homePage.windowScrollToBottom();
		Assert.assertTrue(homePage.kvBillboardIsDisplayed());
		Assert.assertTrue(homePage.articleBannersIsDisplayed());
		Assert.assertTrue(homePage.categoryProductShowIsDisplayed());
		Assert.assertTrue(homePage.amplusGiftShowIsDisplayed());
	}

	@And("Check home page is displayed correctly when user login with Shortcut Edition.")
	public void checkHomeUIWithShortcutEdition() {
		HomePage homePage = new HomePage();
		homePage.windowScrollToBottom();
		Assert.assertTrue(homePage.kvBillboardIsDisplayed());
		Assert.assertTrue(homePage.hotProductShowIsDisplayed());
		Assert.assertTrue(homePage.amplusGiftShowIsDisplayed());
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

}
