package com.objectivasoftware.accl.core.desk.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.Configurations;
import com.objectivasoftware.accl.base.Constants;
import com.objectivasoftware.accl.base.browser.DriverFactory;
import com.objectivasoftware.accl.base.browser.MyDriver;
import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.desk.component.AecLocationComponent;
import com.objectivasoftware.accl.core.desk.component.HeaderComponent;
import com.objectivasoftware.accl.core.util.CommonConstant;

public class HomePage extends BasePage {

	private final static Logger LOGGER = Logger.getLogger(HomePage.class);

	// 是否修改location popup
	public static final String CHANGE_LOCATION_WRAPPER = "#cboxLoadedContent .change-location-wrapper .change-location-content";

	// 是否修改location 否
	public static final String CHANGE_LOCATION_WRAPPER_AMBTN = "#cboxLoadedContent .change-location-wrapper .change-location-content .ambtn-sm";

	public static boolean firstOpen = false;

	/**
	 * 打开主页
	 * @return
	 */
	public static HomePage open() {
		MyDriver browser = DriverFactory.getBrowser();
		LOGGER.info("#############HomePage.openHomePage start.");
		browser.get(Configurations.getConfiguration(Constants.SELENIUM_TARGETURL));
		browser.skipSSLValidation();
		// browser.switchToAlert();
		browser.manage().window().maximize();
		
		try {
			WaitUtil.waitOn(browser, CommonConstant.WAIT_TIME_LEVEL5).untilHidden(By.cssSelector(".page-overlay span.loader"));
		} catch (TimeoutException e) {
			browser.navigate().refresh();
		}

		if (firstOpen) {
			WaitUtil.waitOn(browser).waitTime(CommonConstant.WAIT_TIME_LEVEL3);
		}

		// 关闭AEC网络pop-up
		try {
			WaitUtil.waitOn(browser, CommonConstant.WAIT_TIME_LEVEL3).untilShown(By.cssSelector(AecLocationComponent.CHANGE_AEC_LOCATION_POPUP));
			LOGGER.info("The [change aec location popup] is displayed");
			browser.get(Configurations.getConfiguration(Constants.SELENIUM_TARGETURL));
			WaitUtil.waitOn(browser).untilRemoved(By.cssSelector(AecLocationComponent.CHANGE_AEC_LOCATION_POPUP));
		} catch (TimeoutException e) {
			LOGGER.info("The [change aec location popup] is not displayed");
		}

		// 关闭更新本地地址pop-up
		try {
			WaitUtil.waitOn(browser, CommonConstant.WAIT_TIME_LEVEL1).untilShown(By.cssSelector(CHANGE_LOCATION_WRAPPER));
			LOGGER.info("The [change location popup] is displayed");
			WebElement ambtn = browser.findElement(By.cssSelector(CHANGE_LOCATION_WRAPPER_AMBTN));
			ambtn.click();
			WaitUtil.waitOn(browser).untilPageDown();
			WaitUtil.waitOn(browser).untilRemoved(By.cssSelector(CHANGE_LOCATION_WRAPPER));
		} catch (TimeoutException e) {
			LOGGER.info("The [change location popup] is not displayed");
		}

		WaitUtil.waitOn(browser, CommonConstant.WAIT_TIME_LEVEL4).untilHidden(By.cssSelector(HeaderComponent.HEADER_BANNER_CLOSE_CSS));
		WaitUtil.waitOn(browser).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
		firstOpen = true;
		
		return new HomePage();
	}

	
	// 主广告位
	public static final String KV_BILLBOARD_CSS = "#homepage_slider";
	@FindBy(css = KV_BILLBOARD_CSS)
	private WebElement kvBillboard;

	// 文章广告位
	public static final String ARTICLE_BANNERS_CSS = ".article-banner-wrapper";
	@FindBy(css = ARTICLE_BANNERS_CSS)
	private WebElement articleBanners;

	// 产品分类展示
	public static final String CATEGORY_PRODUCT_SHOW_CASES_CSS = ".homepage-container";
	@FindBy(css = CATEGORY_PRODUCT_SHOW_CASES_CSS)
	private WebElement categoryProductShowCases;

	// 悦享荟礼品展示
	public static final String AMPLUS_GIFT_SHOW_CASE_CSS = ".amplus-gifts-container";
	@FindBy(css = AMPLUS_GIFT_SHOW_CASE_CSS)
	private WebElement amplusGiftShowCase;
	
	// 热销产品展示
	public static final String HOT_PRODUCT_SHOW_CASE_CSS = ".hot-product-showcase";
	@FindBy(css = HOT_PRODUCT_SHOW_CASE_CSS)
	private WebElement hotProductShowCase;
	
	// 标准版/快捷版切换 
	public static final String BUSINESS_VIEW_BTN_CSS = ".business-view-btn";
	@FindBy(css = BUSINESS_VIEW_BTN_CSS)
	private WebElement businessViewBtn;

	/**
	 * 主广告位是否显示
	 * @return
	 */
	public boolean kvBillboardIsDisplayed() {
		boolean result = elementIsShown(By.cssSelector(KV_BILLBOARD_CSS));
		if (result) {
			super.scrollMoveToElement(kvBillboard);
		}

		return result;
	}

	/**
	 * 文章广告位是否显示
	 * @return
	 */
	public boolean articleBannersIsDisplayed() {
		boolean result = elementIsShown(By.cssSelector(ARTICLE_BANNERS_CSS));
		if (result) {
			super.scrollMoveToElement(articleBanners);
		}
		return result;
	}

	/**
	 * 产品分类展示是否显示
	 * @return
	 */
	public boolean categoryProductShowIsDisplayed() {
		boolean result = elementIsShown(By.cssSelector(CATEGORY_PRODUCT_SHOW_CASES_CSS));
		if (result) {
			super.scrollMoveToElement(categoryProductShowCases);
		}
		return result;
	}

	/**
	 * 悦享荟礼品展示是否显示
	 * @return
	 */
	public boolean amplusGiftShowIsDisplayed() {
		boolean result = elementIsShown(By.cssSelector(AMPLUS_GIFT_SHOW_CASE_CSS));
		if (result) {
			super.scrollMoveToElement(amplusGiftShowCase);
		}
		return result;
	}

	/**
	 * 标准版/快捷版 切换是否显示
	 * @return
	 */
	public boolean businessViewIsDisplayed() {
		boolean result = elementIsShown(By.cssSelector(BUSINESS_VIEW_BTN_CSS));
		if (result) {
			super.scrollMoveToElement(businessViewBtn);
		}
		return result;
	}

	/**
	 * 热销产品展示是否显示
	 * @return
	 */
	public boolean hotProductShowIsDisplayed() {
		boolean result = elementIsShown(By.cssSelector(HOT_PRODUCT_SHOW_CASE_CSS));
		if (result) {
			super.scrollMoveToElement(hotProductShowCase);
		}
		return result;
	}

	/**
	 * 切换 标准版/快捷版
	 * @return
	 */
	public String changeBusinessView() {
		String currentValue0 = businessViewBtn.getText().trim();
		businessViewBtn.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver, new UntilEvent() {

			@Override
			public boolean excute() {
				String newValue = businessViewBtn.getText().trim();
				return !newValue.contains(currentValue0);
			}
		}).untilEventHappened();

		return businessViewBtn.getText().trim();
	}
	
	/**
	 * 返回当前显示 快捷版/标准版
	 * @return
	 */
	public String getCurrentBusinessView() {
		return businessViewBtn.getText().trim();
	}

	/**
	 * 还原到初始显示 快捷版/标准版
	 */
	public void returnToDefaultBusinessView() {
		String currentBusinessView = getCurrentBusinessView();
		if (currentBusinessView.contains("快捷版")) {
			return;
		}
		changeBusinessView();
	}
	
	/**
	 * logout
	 */
	public void logout() {
		HeaderComponent headerComponent = new HeaderComponent();
		if (!headerComponent.isLogin()) {
			return;
		}
		headerComponent.logout();

		try {
			WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL3)
					.untilShown(By.cssSelector(AecLocationComponent.CHANGE_AEC_LOCATION_POPUP));
			LOGGER.info("The [change aec location popup] is displayed");
			myDriver.get(Configurations.getConfiguration(Constants.SELENIUM_TARGETURL));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(AecLocationComponent.CHANGE_AEC_LOCATION_POPUP));
		} catch (TimeoutException e) {
			LOGGER.info("The [change aec location popup] is not displayed");
		}

		try {
			WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL1)
					.untilShown(By.cssSelector(CHANGE_LOCATION_WRAPPER));
			LOGGER.info("The [change location popup] is displayed");
			WebElement ambtn = myDriver.findElement(By.cssSelector(CHANGE_LOCATION_WRAPPER_AMBTN));
			ambtn.click();
			WaitUtil.waitOn(myDriver).untilPageDown();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CHANGE_LOCATION_WRAPPER));
		} catch (TimeoutException e) {
			LOGGER.info("The [change location popup] is not displayed");
		}

		WaitUtil.waitOn(myDriver).untilHidden(By.cssSelector(HeaderComponent.HEADER_BANNER_CLOSE_CSS));
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL1);
	}

}
