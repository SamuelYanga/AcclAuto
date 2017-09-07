package com.objectivasoftware.accl.action.pay;

import com.objectivasoftware.accl.core.desk.page.CartPage;

import cucumber.api.java.en.And;

public class ChangeBuyerAction {

	@And("Change buyer in cart page. newBuyer=\"(.*)\"")
	public void changeBuyerInCartPage(String newBuyer) {
		CartPage cartPage = new CartPage();
		cartPage.changeBuyer(newBuyer);
	}

	@And("Change buyer to self in cart page.")
	public void changeToSelfInCartPage() {
		CartPage cartPage = new CartPage();
		cartPage.changeBuyerToSelf();
	}
	
	
}
