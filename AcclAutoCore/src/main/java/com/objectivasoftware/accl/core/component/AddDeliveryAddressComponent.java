package com.objectivasoftware.accl.core.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.vo.checkout.DeliveryAddressVO;

public class AddDeliveryAddressComponent extends BaseComponent {

	public static final String COMPONENT_PAGE_ID = "colorbox";

	public static final String FIRST_NAME_CSS = "#colorbox #addressForm #firstName";
	@FindBy(css = FIRST_NAME_CSS)
	private WebElement consigneeInput;

	public static final String STREET_NAME_CSS = "#colorbox #addressForm #streetName";
	@FindBy(css = STREET_NAME_CSS)
	private WebElement detailAddressInput;

	public static final String MOBILE_NO_CSS = "#colorbox #addressForm #mobileNo";
	@FindBy(css = MOBILE_NO_CSS)
	private WebElement mobileNoInput;

	public static final String REGION_SELECT_CSS = "#colorbox #addressForm #region";
	@FindBy(css = REGION_SELECT_CSS)
	private WebElement regionSelect;

	public static final String REGION_ITEMS_CSS = "#colorbox #addressForm #region option";
	@FindBy(css = REGION_ITEMS_CSS)
	private List<WebElement> regionItems;

	public static final String CITY_SELECT_CSS = "#colorbox #addressForm #city";
	@FindBy(css = CITY_SELECT_CSS)
	private WebElement citySelect;

	public static final String CITY_ITEMS_CSS = "#colorbox #addressForm #city option";
	@FindBy(css = CITY_ITEMS_CSS)
	private List<WebElement> cityItems;

	public static final String DISTRICT_SELECT_CSS = "#colorbox #addressForm #district";
	@FindBy(css = DISTRICT_SELECT_CSS)
	private WebElement districtSelect;

	public static final String DISTRICT_ITEMS_CSS = "#colorbox #addressForm #district option";
	@FindBy(css = DISTRICT_ITEMS_CSS)
	private List<WebElement> districtItems;

	public static final String DEFAULT_CHECK_CSS = "#colorbox #addressForm .fake-check";
	@FindBy(css = DEFAULT_CHECK_CSS)
	private WebElement defaultAddressCheck;

	public static final String SUBMIT_BUTTON_CSS = "#colorbox #addressForm button[type=submit]";
	@FindBy(css = SUBMIT_BUTTON_CSS)
	private WebElement submitButton;

	public void addNewAddress(DeliveryAddressVO deliveryAddress) {
		consigneeInput.clear();
		consigneeInput.sendKeys(deliveryAddress.getName());
		selectRegion(deliveryAddress.getProvince());
		selectCity(deliveryAddress.getCity());
		selectDistrict(deliveryAddress.getArea());
		detailAddressInput.clear();
		detailAddressInput.sendKeys(deliveryAddress.getDetaileAddress());
		mobileNoInput.clear();
		mobileNoInput.sendKeys(deliveryAddress.getPhone());
		if (!deliveryAddress.isDefault()) {
			defaultAddressCheck.click();
		}
		submitButton.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilHidden(By.id(COMPONENT_PAGE_ID));
	}

	public void selectRegion(String name) {
		// TODO
		// WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(REGION_ITEMS_CSS));
		regionSelect.click();
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(REGION_ITEMS_CSS));

		int index = getRegion(name);

		if (index >= 20) {
			int size = getSizeOfOneElement(regionItems);
			int move = size * 10;
			String setscroll = "$('" + REGION_SELECT_CSS + "').scrollTop(" + move + ")";
			myDriver.executeScript(setscroll);
		}

		WebElement region = regionItems.get(index);
		region.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilAjaxFinish();
	}

	public void selectCity(String name) {
		regionSelect.click();
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(CITY_ITEMS_CSS));

		int index = getCity(name);

		if (index >= 20) {
			int size = getSizeOfOneElement(cityItems);
			int move = size * 10;
			String setscroll = "$('" + CITY_SELECT_CSS + "').scrollTop(" + move + ")";
			myDriver.executeScript(setscroll);
		}

		WebElement city = cityItems.get(index);
		city.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilAjaxFinish();
	}

	public void selectDistrict(String name) {
		regionSelect.click();
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(DISTRICT_ITEMS_CSS));

		int index = getDistrict(name);

		if (index >= 20) {
			int size = getSizeOfOneElement(districtItems);
			int move = size * 10;
			String setscroll = "$('" + DISTRICT_SELECT_CSS + "').scrollTop(" + move + ")";
			myDriver.executeScript(setscroll);
		}

		WebElement district = districtItems.get(index);
		district.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilAjaxFinish();
	}

	private int getRegion(String name) {
		int count = 0;
		for (WebElement region : regionItems) {
			count++;
			String value = region.getAttribute("value");
			if (name.equals(value)) {
				return count - 1;
			}
		}
		return 1;
	}

	private int getCity(String name) {
		int count = 0;
		for (WebElement region : cityItems) {
			count++;
			String value = region.getAttribute("value");
			if (name.equals(value)) {
				return count - 1;
			}
		}
		return 1;
	}

	private int getDistrict(String name) {
		int count = 0;
		for (WebElement region : districtItems) {
			count++;
			String value = region.getAttribute("value");
			if (name.equals(value)) {
				return count - 1;
			}
		}
		return 1;
	}

	private int getSizeOfOneElement(List<WebElement> list) {
		if (list == null || list.size() <= 1) {
			return 0;
		}

		try {
			return list.get(1).getLocation().getY() - list.get(0).getLocation().getY();
		} catch (Exception e) {
			return 0;
		}
	}

}
