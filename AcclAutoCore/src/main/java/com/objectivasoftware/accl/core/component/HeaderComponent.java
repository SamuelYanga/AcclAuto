package com.objectivasoftware.accl.core.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.Configurations;
import com.objectivasoftware.accl.base.Constants;
import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.util.waitEvent.WaitClassChange;

public class HeaderComponent extends BaseComponent {

	public static final String LOGIN_LINK_CSS = ".login-link.loginDesktop";
	@FindBy(css = LOGIN_LINK_CSS)
	private WebElement loginLink;

	// search button
	public static final String SEARCH_BUTTON_CSS = "searchBtn";
	@FindBy(id = SEARCH_BUTTON_CSS)
	private WebElement searchButton;

	public static final String HEADER_BANNER_CLOSE_CSS = ".header-banner .btn-close";
	public static final String E_VOUCHERS_WRAPPER_CSS = ".nav-top-right-section.login-user .qty-wrapper";

	public void searchProduct(String product) {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(SEARCH_INPUT_CSS));
		searchInput.clear();
		searchInput.sendKeys(product);
		searchButton.click();
	}

	public void clickLogin() {
		WaitUtil.waitOn(myDriver).untilHidden(By.cssSelector(HeaderComponent.HEADER_BANNER_CLOSE_CSS));
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(LOGIN_LINK_CSS));
		try {
			loginLink.click();
		} catch (WebDriverException e) {
			System.out.println("*******************************************login error first");
			WaitUtil.waitOn(myDriver).untilHidden(By.cssSelector(HeaderComponent.HEADER_BANNER_CLOSE_CSS));
			WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(LOGIN_LINK_CSS));
			loginLink.click();
		}
		WaitUtil.waitOn(myDriver).untilShown(By.id(LoginComponent.LOGIN_PAGE_ID));
		WaitUtil.waitOn(myDriver).waitTime(1000);
	}

	public boolean isLogin() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilShown(By.cssSelector(LOGIN_LINK_CSS));
		} catch (TimeoutException e) {
			return true;
		}
		return false;
	}

	public static final String PURCHASER_SECTION_CSS = ".purchaser-section";
	@FindBy(css = PURCHASER_SECTION_CSS)
	private WebElement purchaserSection;

	private void moveToPurchaserSection() {

		String currentUrl = myDriver.getCurrentUrl();
		String domain = Configurations.getConfiguration(Constants.SELENIUM_TARGETURL);
		String str = currentUrl.replace(domain, "");
		if (str.length() > 0 && !str.startsWith("?")) {
			backToHomePage();
		}

		super.scrollMoveToElement(purchaserSection);
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(purchaserSection).perform();
		WaitUtil.waitOn(myDriver, new UntilEvent() {
			@Override
			public boolean excute() {
				String classValue = purchaserSection.getAttribute("class");
				return classValue.contains("dropdown-active");
			}
		}).untilEventHappened();
	}
	
	public static final String LOG_OUT_CSS = ".purchaser-section .logout";
	@FindBy(css = LOG_OUT_CSS)
	private WebElement logoutLink;
	
	public void logout() {
		moveToPurchaserSection();
		logoutLink.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	public void changeBuyer(String buyer) {
		moveToPurchaserSection();

		changeBuyerLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ChangeBuyerComponent.CHANGE_BUYER_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));

		ChangeBuyerComponent changeBuyerComponent = new ChangeBuyerComponent();
		changeBuyerComponent.selectNewBuyer(buyer);
	}

	public static final String PROMOTION_ZONE_STR = "促销专区";
	public static final String AMPLUS_GIFT_ZONE_STR = "悦享荟礼品";

	public static final String RIGHT_NAVIGATIONS_CSS = ".right-navigation a";
	@FindBy(css = RIGHT_NAVIGATIONS_CSS)
	private List<WebElement> rightNavigations;

	public void navigateToPromotionZone() {
		rightNavigation(PROMOTION_ZONE_STR);
	}

	public void navigateToAmplusZone() {
		rightNavigation(AMPLUS_GIFT_ZONE_STR);
	}

	public void rightNavigation(String name) {
		WebElement element = getRightNavigation(name);
		element.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	private WebElement getRightNavigation(String name) {
		for (WebElement element : rightNavigations) {
			String value = element.getText().trim();
			if (name.equals(value)) {
				return element;
			}
		}
		return null;
	}

	public static final String CHANGE_PROVINCE_LINK_CSS = ".header-change-location .change-location-section";
	@FindBy(css = CHANGE_PROVINCE_LINK_CSS)
	private WebElement changeProvinceLink;

	public static final String PROVINCES_LINK_CSS = ".change-location-content .list-group a";
	@FindBy(css = PROVINCES_LINK_CSS)
	private List<WebElement> provinceList;

	private boolean isCurrentLocation(String provinceName) {
		String value = currentLocation.getText().trim();
		return value.contains(provinceName);
	}

	public void selectProvince(String provinceName) {
		if (isCurrentLocation(provinceName)) {
			return;
		}
		changeProvinceLink.click();
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(PROVINCES_LINK_CSS));

		for (WebElement province : provinceList) {
			String value = province.getText();
			if (provinceName.equals(value)) {
				province.click();
				WaitUtil.waitOn(myDriver).untilHidden(By.cssSelector(PROVINCES_LINK_CSS));
				WaitUtil.waitOn(myDriver).untilPageDown();
				return;
			}
		}
	}

	public static final String MINI_CART_DETAIL_CSS = ".site-mini-cart .mini-cart-list .mini-cart-detail";
	private List<WebElement> miniCartDetails;

	public boolean isCartNull() {
		try {
			miniCartDetails = myDriver.findElements(By.cssSelector(MINI_CART_DETAIL_CSS));
		} catch (NoSuchElementException e) {
			return true;
		}

		return miniCartDetails == null || miniCartDetails.size() == 0;
	}

	public static final String RIGHT_CART_LINK_CSS = ".cart-wrapper";
	@FindBy(css = RIGHT_CART_LINK_CSS)
	private WebElement rightCartLink;

	public void navigateToCartPageByMiniCart() {
		rightCartLink.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	public void backToHomePage() {
		logoImg.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilHidden(By.cssSelector(HEADER_BANNER_CLOSE_CSS));
		WaitUtil.waitOn(myDriver).waitTime(1000);
	}
	
	//logo图标Amway Logo
	public static final String LOGO_IMG_CSS = "img[title=\"Amway Logo\"]";
	@FindBy(css = LOGO_IMG_CSS)
	private WebElement logoImg;

	// 欢迎标语
	public static final String WELCOME_TEXT_CSS = ".headInfo .welcom-text";
	@FindBy(css = WELCOME_TEXT_CSS)
	private WebElement welcomeText;

	// 帮助中心_guest 
	public static final String HELP_CENTER_GUEST_CSS = ".log-out-user a[title=\"帮助中心\"]";
	@FindBy(css = HELP_CENTER_GUEST_CSS)
	private WebElement logoutHelpCenter;

	// 帮助中心_user
	public static final String HELP_CENTER_USER_CSS = ".login-user a[title=\"帮助中心\"]";
	@FindBy(css = HELP_CENTER_USER_CSS)
	private WebElement loginHelpCenter;

	// 商城首页 
	public static final String HOME_PAGE_CSS = ".nav.nav-pills.js-offcanvas-links a[title=\"商城首页\"]";
	@FindBy(css = HOME_PAGE_CSS)
	private WebElement homepageCss;

	// 在线客服_guest 
	public static final String ONLINE_CHAT_GUEST_CSS = ".log-out-user a[title=\"在线客服\"]";
	@FindBy(css = ONLINE_CHAT_GUEST_CSS)
	private WebElement logoutOnlineChat;

	// 在线客服_user
	public static final String AONLINE_CHAT_USER_CSS = ".login-user a[title=\"在线客服\"]";
	@FindBy(css = AONLINE_CHAT_USER_CSS)
	private WebElement loginOnlineChat;

	// 易联网
	public static final String AMWAY_NET_CSS = ".login-user a[title=\"易联网\"]";
	@FindBy(css = AMWAY_NET_CSS)
	private WebElement amwayNet;

	// 我的账户 
	public static final String MY_ACCOUNT_CSS = ".cdd-title a[href=\"/my-account/profile\"]";
	@FindBy(css = MY_ACCOUNT_CSS)
	private WebElement myAccountCss;

	// 电子券
	public static final String E_VOUCHERS_CSS = ".qty-wrapper a[href=\"/my-account/voucherPage#notUsedEV\"]";
	@FindBy(css = E_VOUCHERS_CSS)
	private WebElement eVouchersCss;

	// 切换快捷版/标准版 
	public static final String BUSSINESS_VIEW_CSS = ".business-view";
	@FindBy(css = BUSSINESS_VIEW_CSS)
	private WebElement bussinessView;

	// 当前购货人
	public static final String CURRENT_BUYER_TEXT_CSS = ".purchaser-section .purchases-buyer .login-username";
	@FindBy(css = CURRENT_BUYER_TEXT_CSS)
	private WebElement currentBuyerText;

	// 更换购货人
	public static final String CHANGE_BUYER_LINK_CSS = ".purchaser-section .change-buyer-link";
	@FindBy(css = CHANGE_BUYER_LINK_CSS)
	private WebElement changeBuyerLink;

	// 送货至
	public static final String CURRENT_LOCATION_CSS = ".header-change-location .change-location-section .icon-wrapper";
	@FindBy(css = CURRENT_LOCATION_CSS)
	private WebElement currentLocation;

	// 搜索框
	public static final String SEARCH_INPUT_CSS = ".search-form-SearchBox #js-site-search-input";
	@FindBy(css = SEARCH_INPUT_CSS)
	private WebElement searchInput;

	// 快速下单
	public static final String QUICK_BUY_CSS = ".site-mini-cart .login-user a[href=\"/quickBuy\"]";
	@FindBy(css = QUICK_BUY_CSS)
	private WebElement quickBuy;

	// 订单查询
	public static final String ORDER_QUERY_CSS = ".site-mini-cart .login-user a[href=\"/my-account/orders\"]";
	@FindBy(css = ORDER_QUERY_CSS)
	private WebElement orderQuery;

	// 迷你购物车
	public static final String MINI_CART_LINK_CSS = ".site-mini-cart .miniCartSlot .mini-cart-link";
	@FindBy(css = MINI_CART_LINK_CSS)
	private WebElement miniCartLink;

	// 分类导航栏
	public static final String CATEGORY_NAVIGATION_BAR_CSS = ".main-navigation";
	@FindBy(css = CATEGORY_NAVIGATION_BAR_CSS)
	private WebElement gategoryNavigationBar;

	// 热销产品
	public static final String HOT_SALES_CSS = ".right-navigation a[href=\"c/800\"]";
	@FindBy(css = HOT_SALES_CSS)
	private WebElement hotSales;

	// 配售专区
	public static final String QUOTA_SALES_CSS = ".right-navigation a[href=\"/quota/quotaList\"]";
	@FindBy(css = QUOTA_SALES_CSS)
	private WebElement quotaSales;

	// 促销专区
	public static final String PROMOTIONS_ZONE_CSS = ".right-navigation a[href=\"/promotionlisting\"]";
	@FindBy(css = PROMOTIONS_ZONE_CSS)
	private WebElement promotionsZone;

	// 悦享荟礼品
	public static final String AMPLUS_GIFTS_CSS = ".right-navigation a[href=\"c/600\"]";
	@FindBy(css = AMPLUS_GIFTS_CSS)
	private WebElement amplusGifts;

	// 海外购
	public static final String CROSS_BORDER_CSS = ".right-navigation a[title=\"海外购\"]";
	@FindBy(css = CROSS_BORDER_CSS)
	private WebElement crossBorder;

	// 如何购买安利产品
	public static final String HOW_TO_BUY_CSS = ".site-mini-cart .log-out-user-active a[href=\"/howToBuy\"]";
	@FindBy(css = HOW_TO_BUY_CSS)
	private WebElement howToBuy;

	/**
	 * logo图标是否显示
	 * @return
	 */
	public boolean logoIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(LOGO_IMG_CSS));
			super.scrollMoveToElement(logoImg);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return logoImg.isDisplayed();
	}

	/**
	 * 欢迎标语是否显示
	 * @return
	 */
	public boolean welcomeTextIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(WELCOME_TEXT_CSS));
			super.scrollMoveToElement(welcomeText);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return welcomeText.isDisplayed();
	}

	/**
	 * 帮助中心_guest是否显示
	 * @return
	 */
	public boolean guestHelpCenterIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(HELP_CENTER_GUEST_CSS));
			super.scrollMoveToElement(logoutHelpCenter);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return logoutHelpCenter.isDisplayed();
	}

	/**
	 * 帮助中心_user是否显示
	 * @return
	 */
	public boolean userHelpCenterIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(HELP_CENTER_USER_CSS));
			super.scrollMoveToElement(loginHelpCenter);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return loginHelpCenter.isDisplayed();
	}

	/**
	 * 商城首页是否显示
	 * @return
	 */
	public boolean homepageLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(HOME_PAGE_CSS));
			super.scrollMoveToElement(homepageCss);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return homepageCss.isDisplayed();
	}

	/**
	 * 在线客服_guest是否显示
	 * @return
	 */
	public boolean guestOnlineChatIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(ONLINE_CHAT_GUEST_CSS));
			super.scrollMoveToElement(logoutOnlineChat);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return logoutOnlineChat.isDisplayed();
	}

	/**
	 * 在线客服_user是否显示
	 * @return
	 */
	public boolean userOnlineChatIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(AONLINE_CHAT_USER_CSS));
			super.scrollMoveToElement(loginOnlineChat);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return loginOnlineChat.isDisplayed();
	}

	/**
	 * 易联网是否显示
	 * @return
	 */
	public boolean amwayNetIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(AMWAY_NET_CSS));
			super.scrollMoveToElement(amwayNet);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return amwayNet.isDisplayed();
	}

	/**
	 * 我的账户是否显示
	 * @return
	 */
	public boolean myAccountLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(MY_ACCOUNT_CSS));
			super.scrollMoveToElement(myAccountCss);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return myAccountCss.isDisplayed();
	}

	/**
	 * 电子券是否显示
	 * @return
	 */
	public boolean eVouchersLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(E_VOUCHERS_CSS));
			super.scrollMoveToElement(eVouchersCss);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return eVouchersCss.isDisplayed();
	}

	/**
	 * 切换快捷版/标准版 是否显示
	 * @return
	 */
	public boolean bussinessViewIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(BUSSINESS_VIEW_CSS));
			super.scrollMoveToElement(bussinessView);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return bussinessView.isDisplayed();
	}

	/**
	 * 当前购货人是否显示
	 * @return
	 */
	public boolean currentBuyerTextIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(CURRENT_BUYER_TEXT_CSS));
			super.scrollMoveToElement(currentBuyerText);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return currentBuyerText.isDisplayed();
	}

	/**
	 * 更换购货人是否显示
	 * @return
	 */
	public boolean changeBuyerLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(CHANGE_BUYER_LINK_CSS));
			super.scrollMoveToElement(changeBuyerLink);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return changeBuyerLink.isDisplayed();
	}

	/**
	 * 送货至是否显示
	 * @return
	 */
	public boolean currentLocationIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(CURRENT_LOCATION_CSS));
			super.scrollMoveToElement(currentLocation);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return currentLocation.isDisplayed();
	}

	/**
	 * 搜索框是否显示
	 * @return
	 */
	public boolean searchInputIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(SEARCH_INPUT_CSS));
			super.scrollMoveToElement(searchInput);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return searchInput.isDisplayed();
	}

	/**
	 * 快速下单是否显示
	 * @return
	 */
	public boolean quickBuyIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(QUICK_BUY_CSS));
			super.scrollMoveToElement(quickBuy);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return quickBuy.isDisplayed();
	}

	/**
	 * 订单查询是否显示
	 * @return
	 */
	public boolean orderQueryIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(ORDER_QUERY_CSS));
			super.scrollMoveToElement(orderQuery);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return orderQuery.isDisplayed();
	}

	/**
	 * 迷你购物车是否显示
	 * @return
	 */
	public boolean miniCartLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(MINI_CART_LINK_CSS));
			super.scrollMoveToElement(miniCartLink);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return miniCartLink.isDisplayed();
	}

	/**
	 * 分类导航栏是否显示
	 * @return
	 */
	public boolean gategoryNavigationBarIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(CATEGORY_NAVIGATION_BAR_CSS));
			super.scrollMoveToElement(gategoryNavigationBar);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return gategoryNavigationBar.isDisplayed();
	}

	/**
	 * 热销产品是否显示
	 * @return
	 */
	public boolean hotSalesIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(HOT_SALES_CSS));
			super.scrollMoveToElement(hotSales);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return hotSales.isDisplayed();
	}

	/**
	 * 配售专区是否显示
	 * @return
	 */
	public boolean quotaSalesIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(QUOTA_SALES_CSS));
			super.scrollMoveToElement(quotaSales);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return quotaSales.isDisplayed();
	}

	/**
	 * 促销专区是否显示
	 * @return
	 */
	public boolean promotionsZoneLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(PROMOTIONS_ZONE_CSS));
			super.scrollMoveToElement(promotionsZone);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return promotionsZone.isDisplayed();
	}

	/**
	 * 悦享荟礼品是否显示
	 * @return
	 */
	public boolean amplusGiftsLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(AMPLUS_GIFTS_CSS));
			super.scrollMoveToElement(amplusGifts);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return amplusGifts.isDisplayed();
	}

	/**
	 * 海外购是否显示
	 * @return
	 */
	public boolean crossBorderLinkIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(CROSS_BORDER_CSS));
			super.scrollMoveToElement(crossBorder);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return crossBorder.isDisplayed();
	}

	/**
	 * 如何购买安利产品是否显示
	 * @return
	 */
	public boolean howToBuyIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(HOW_TO_BUY_CSS));
			super.scrollMoveToElement(howToBuy);
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return howToBuy.isDisplayed();
	}

	// 纽崔莱
	public static final String FIRST_MENU_NUTRILITE_CSS = ".js-offcanvas-links .nutrilite-cat";
	@FindBy(css = FIRST_MENU_NUTRILITE_CSS)
	private WebElement firstMenuNutrilite;
	// 安利
	public static final String FIRST_MENU_ARTISTRY_CSS = ".js-offcanvas-links .artistry-cat";
	@FindBy(css = FIRST_MENU_ARTISTRY_CSS)
	private WebElement firstMenuArtistry;
	// 家居科技
	public static final String FIRST_MENU_HOME_TECH_CSS = ".js-offcanvas-links .home-tech-cat";
	@FindBy(css = FIRST_MENU_HOME_TECH_CSS)
	private WebElement firstMenuHomeTech;
	// 家居护理
	public static final String FIRST_MENU_HOME_CARE_CSS = ".js-offcanvas-links .home-care-cat";
	@FindBy(css = FIRST_MENU_HOME_CARE_CSS)
	private WebElement firstMenuHomeCare;
	// 个人护理
	public static final String FIRST_MENU_PERSONAL_CARE_CSS = ".js-offcanvas-links .personal-care-cat";
	@FindBy(css = FIRST_MENU_PERSONAL_CARE_CSS)
	private WebElement firstMenuPersonalCare;
	// 辅销产品
	public static final String FIRST_MENU_ACCESSORIES_CSS = ".js-offcanvas-links .accessories-cat";
	@FindBy(css = FIRST_MENU_ACCESSORIES_CSS)
	private WebElement firstMenuAccessories;

	public static final String SECOND_MENU_CSS = ".sub-navigation-section a";

	/**
	 * 鼠标悬停到一级菜单_纽崔莱 二级菜单是否显示
	 * @return
	 */
	public boolean moveToNutriAndSecMenuIsDisplayed() {
		List<WebElement> secondMenu = null;
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(firstMenuNutrilite).perform();
		WaitUtil.waitOn(myDriver, 2000, new WaitClassChange(firstMenuNutrilite, "md-show-sub")).untilEventHappened();

		try {
			secondMenu = firstMenuNutrilite.findElements(By.cssSelector(SECOND_MENU_CSS));
		} catch (NoSuchElementException e) {
			return false;
		}
		return secondMenu != null && secondMenu.size() > 0;
	}

	/**
	 * 鼠标悬停到一级菜单_安利 二级菜单是否显示
	 * @return
	 */
	public boolean moveToArtiAndSecMenuIsDisplayed() {
		List<WebElement> secondMenu = null;
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(firstMenuArtistry).perform();
		WaitUtil.waitOn(myDriver, 2000, new WaitClassChange(firstMenuArtistry, "md-show-sub")).untilEventHappened();

		try {
			secondMenu = firstMenuArtistry.findElements(By.cssSelector(SECOND_MENU_CSS));
		} catch (NoSuchElementException e) {
			return false;
		}
		return secondMenu != null && secondMenu.size() > 0;
	}

	/**
	 * 鼠标悬停到一级菜单_家居科技 二级菜单是否显示
	 * @return
	 */
	public boolean moveToHomeTechAndSecMenuIsDisplayed() {
		List<WebElement> secondMenu = null;
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(firstMenuHomeTech).perform();
		WaitUtil.waitOn(myDriver, 2000, new WaitClassChange(firstMenuHomeTech, "md-show-sub")).untilEventHappened();

		try {
			secondMenu = firstMenuHomeTech.findElements(By.cssSelector(SECOND_MENU_CSS));
		} catch (NoSuchElementException e) {
			return false;
		}
		return secondMenu != null && secondMenu.size() > 0;
	}

	/**
	 * 鼠标悬停到一级菜单_家居护理 二级菜单是否显示
	 * @return
	 */
	public boolean moveToHomeCareAndSecMenuIsDisplayed() {
		List<WebElement> secondMenu = null;
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(firstMenuHomeCare).perform();
		WaitUtil.waitOn(myDriver, 2000, new WaitClassChange(firstMenuHomeCare, "md-show-sub")).untilEventHappened();

		try {
			secondMenu = firstMenuHomeCare.findElements(By.cssSelector(SECOND_MENU_CSS));
		} catch (NoSuchElementException e) {
			return false;
		}
		return secondMenu != null && secondMenu.size() > 0;
	}

	/**
	 * 鼠标悬停到一级菜单_个人护理 二级菜单是否显示
	 * @return
	 */
	public boolean moveToPersonCareAndSecMenuIsDisplayed() {
		List<WebElement> secondMenu = null;
		Actions action = new Actions(myDriver.getDelegate());
		action.moveToElement(firstMenuPersonalCare).perform();
		WaitUtil.waitOn(myDriver, 2000, new WaitClassChange(firstMenuPersonalCare, "md-show-sub")).untilEventHappened();

		try {
			secondMenu = firstMenuPersonalCare.findElements(By.cssSelector(SECOND_MENU_CSS));
		} catch (NoSuchElementException e) {
			return false;
		}
		return secondMenu != null && secondMenu.size() > 0;
	}
	
	/**
	 * 一级菜单_纽崔莱 是否显示
	 * @return
	 */
	public boolean firstMenuNutriliteIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(FIRST_MENU_NUTRILITE_CSS));
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return firstMenuNutrilite.isDisplayed();
	}

	/**
	 * 一级菜单_安利 是否显示
	 * @return
	 */
	public boolean firstMenuArtistryIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(FIRST_MENU_ARTISTRY_CSS));
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return firstMenuArtistry.isDisplayed();
	}

	/**
	 * 一级菜单_家居科技 是否显示
	 * @return
	 */
	public boolean firstMenuHomeTechIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(FIRST_MENU_HOME_TECH_CSS));
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return firstMenuHomeTech.isDisplayed();
	}

	/**
	 * 一级菜单_家居护理 是否显示
	 * @return
	 */
	public boolean firstMenuHomeCareIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(FIRST_MENU_HOME_CARE_CSS));
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return firstMenuHomeCare.isDisplayed();
	}

	/**
	 * 一级菜单_个人护理 是否显示
	 * @return
	 */
	public boolean firstMenuPersonalCareIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(FIRST_MENU_PERSONAL_CARE_CSS));
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return firstMenuPersonalCare.isDisplayed();
	}

	/**
	 * 一级菜单_辅销产品 是否显示
	 * @return
	 */
	public boolean firstMenuAccessoriesIsDisplayed() {
		try {
			WaitUtil.waitOn(myDriver, 2000).untilAdded(By.cssSelector(FIRST_MENU_ACCESSORIES_CSS));
		} catch (TimeoutException e) {
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}
		return firstMenuAccessories.isDisplayed();
	}

}
