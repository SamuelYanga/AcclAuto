package com.objectivasoftware.accl.core.util;

import com.objectivasoftware.accl.base.browser.MyDriver;
import com.objectivasoftware.accl.base.browser.DriverFactory;

public class UrlConstant {

	public static final String PAYMENT = "payment";
	public static final String UNION_PAY_DOMAIN = "cashier.test.95516.com";
	public static final String ORDER_DETAIL = "my-account/order";
	
	
	
	public static final String HOME = "/tacobellstorefront";
	public static final String LANGUAGE = "/en";
	public static final String SEARCH = "/search";
	public static final String CART = "/cart";
	//public static final String FAVORITE_PRODUCTS = "/my-account/getFavoriteProducts";
	public static final String CLP = "/Tacobell-Root-Category/Food/Sellable-Food";
	public static final String CLP_NEW = "/Tacobell-Root-Category/Food/Sellable-Food/New/new/c/New";
	public static final String CLP_FAV = "/Tacobell-Root-Category/Food/Sellable-Food/Favorites/favorites/c/favorites";
	public static final String MY_ACCOUNT = "/my-account";
	public static final String OFFER = "/offer";
	public static final String ORDERS = "/orders";
	public static final String PAYMENT_DETAIL = "/payment-details";
	public static final String PROFILE = "/profile";
	public static final String OFFER_DETAIL = "/promotion";
	public static final String NOTIFICATIONS = "/notification-details";
	public static final String NUTRITION = "/nutrition/calculator";
	public static final String ORDER_CONFIRMATION = "/checkout/orderConfirmation";
	public static final String ORDER_PLACE = "/checkout/orderPlace";
	public static final String MY_ACCOUNT_PAYMENTS = "/my-account/payment-details";
	public static final String CHECKOUT_SINGLE = "/checkout/single";
	public static final String REGISTER = "/register";
	public static final String PAGE_404 = "/AutomationTest404";

	public static void openPage(String target) {
		MyDriver browser = DriverFactory.getBrowser();
		String currentUrl = browser.getCurrentUrl();
		String domain = currentUrl.substring(0, currentUrl.indexOf(UrlConstant.HOME));
		String targetUrl = domain + HOME + target;
		browser.get(targetUrl);
	}
	
	public static boolean isThePage(String url) {
		MyDriver browser = DriverFactory.getBrowser();
		String currentUrl = browser.getCurrentUrl();
		return currentUrl.contains(url);
	}

}
