package com.objectivasoftware.accl.core.desk.page;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.desk.component.AddDeliveryAddressComponent;
import com.objectivasoftware.accl.core.desk.component.UseCouponComponent;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.util.enu.InvoiceType;
import com.objectivasoftware.accl.core.vo.checkout.DeliveryAddressVO;
import com.objectivasoftware.accl.core.vo.orderdetail.DigitalTicket;

public class CheckOutPage extends BasePage {

	public static final String ADD_ADDRESS_CSS = ".show-error.checkout-address";
	@FindBy(css = ADD_ADDRESS_CSS)
	private WebElement addAddress;

	public static final String ADD_ADDRESS_WRAPPER_ID = "colorbox";

	public static final String CHECKOUT_BUTTON_CSS = ".checkout-btn-cont";
	@FindBy(css = CHECKOUT_BUTTON_CSS)
	private WebElement checkoutButton;

	public static final String INVOICE_RECEIPT_TRUE_CSS = "input#invoiceReceipt[data-value=true]+i.xradio";
	@FindBy(css = INVOICE_RECEIPT_TRUE_CSS)
	private WebElement invoiceReceiptTrue;

	public static final String INVOICE_RECEIPT_FALSE_CSS = "input#invoiceReceipt[data-value=false]+i.xradio";
	@FindBy(css = INVOICE_RECEIPT_FALSE_CSS)
	private WebElement invoiceReceiptFalse;

	public static final String INVOICE_WAY_CSS = "input#invoice-way+i.xradio";
	@FindBy(css = INVOICE_WAY_CSS)
	private WebElement invoiceWay;

	public static final String RADIO_REGULAR_INVOICE_CSS = "input#radio_RegularInvoice+i.xradio";
	@FindBy(css = RADIO_REGULAR_INVOICE_CSS)
	private WebElement regularInvoiceRadio;

	public static final String RADIO_SPECIAL_INVOICE_CSS = "input#radio_SpecialInvoice+i.xradio";
	@FindBy(css = RADIO_SPECIAL_INVOICE_CSS)
	private WebElement specialInvoiceRadio;

	public static final String RADIO_DIGITAL_INVOICE_CSS = "input#radio_DigitalInvoice+i.xradio";
	@FindBy(css = RADIO_DIGITAL_INVOICE_CSS)
	private WebElement digitalInvoiceRadio;

	public static final String REGULAR_INVOICE_CONTENT_LIST_CSS = ".common-invoice .invoice-row";
	@FindBy(css = REGULAR_INVOICE_CONTENT_LIST_CSS)
	private List<WebElement> regularInvoiceContents;

	public static final String SPECIAL_INVOICE_CONTENT_LIST_CSS = ".special-invoice .invoice-row";
	@FindBy(css = SPECIAL_INVOICE_CONTENT_LIST_CSS)
	private List<WebElement> specialInvoiceContents;

	public static final String CART_LIST_CSS = ".cart-list-wrapper .cart-header";
	@FindBy(css = CART_LIST_CSS)
	private List<WebElement> cartList;

	public static final String CART_ITEM_USER_CSS = ".account-name strong";
	public static final String CART_ITEM_TICKET_CSS = ".account-name .red-cylinder.ecoupon-btn";

	/**
	 * 添加一個新地址
	 * @param deliveryAddressVO
	 */
	public void addNewAddress(DeliveryAddressVO deliveryAddressVO) {
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(addAddress);
		addAddress.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ADD_ADDRESS_WRAPPER_ID));
		AddDeliveryAddressComponent addDeliveryAddressComponent = new AddDeliveryAddressComponent();
		addDeliveryAddressComponent.addNewAddress(deliveryAddressVO);
	}

	public void addNewAddress() {
		DeliveryAddressVO vo = DeliveryAddressVO.getDefaultVO();
		if (isAdded(vo.getName())) {
			return;
		}
		addNewAddress(vo);
	}

	/**
	 * 根據省份添加一個新地址
	 * @param deliveryAddressVO
	 */
	public void addNewAddress(String provinceName) {
		DeliveryAddressVO vo = DeliveryAddressVO.getDefaultVO(provinceName);
		if (isAdded(vo.getName())) {
			return;
		}
		addNewAddress(vo);
	}

	public static final String ADDRESS_ITEMS_CSS = ".addresslist-item .right-con .custom-radio";
	@FindBy(css = ADDRESS_ITEMS_CSS)
	private List<WebElement> addressItems;
	public static final String ADDRESS_ITEM_CHECK_CSS = ".xradio";
	public static final String ADDRESS_ITEM_NAME_CSS = "em";

	private WebElement getAddressItem(String userName) {
		for (WebElement addressItem : addressItems) {
			WebElement nameElement = addressItem.findElement(By.cssSelector(ADDRESS_ITEM_NAME_CSS));
			String value = nameElement.getText();
			if (userName.equals(value)) {
				return addressItem;
			}
		}
		return null;
	}

	/**
	 * 選擇一個地址
	 * @param provinceName
	 */
	public void selectAddress(String provinceName) {
		DeliveryAddressVO vo = DeliveryAddressVO.getDefaultVO(provinceName);
		openAddresses();
		WebElement addressItem = getAddressItem(vo.getName());
		addressItem.click();
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
	}

	public static final String ADDRESS_LIST_CSS = ".address-list .addresslist-item";
	@FindBy(css = ADDRESS_LIST_CSS)
	private List<WebElement> addressList;
	
	public static final String VIEW_ALL_ADDRESS_CSS = ".checkout-address.hidden-xs.hidden-sm";
	@FindBy(css = VIEW_ALL_ADDRESS_CSS)
	private WebElement viewAllAddress;

	private void openAddresses() {
		try {
			WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL2)
					.untilListShown(By.cssSelector(VIEW_ALL_ADDRESS_CSS));
			String classValue0 = viewAllAddress.getAttribute("class");
			if (!classValue0.contains("more")) {
				return;
			}
			viewAllAddress.click();
			WaitUtil.waitOn(myDriver, new UntilEvent() {
				@Override
				public boolean excute() {
					String classValue = viewAllAddress.getAttribute("class");
					return classValue.indexOf("more") == -1;
				}
			}).untilEventHappened();

		} catch (TimeoutException e) {
			return;
		}
	}

	/**
	 * 根據收貨人名稱判斷是否已添加
	 * @param userName
	 * @return
	 */
	private boolean isAdded(String userName) {
		openAddresses();
		int size = addressList.size();
		WaitUtil.waitOn(myDriver, new UntilEvent() {
			@Override
			public boolean excute() {
				addressScroll(size - 1);
				return addressList.get(size - 1).isDisplayed();
			}
		}).untilEventHappened();

		for (int i = 0; i < size; i++) {
			int move = i - 6;
			if (move > 0) {
				addressScroll(move);
			}
			String text = addressList.get(i).getText().trim();
			if (StringUtils.isEmpty(text)) {
				continue;
			}
			String textName = getUserName(text);
			if (userName.equals(textName)) {
				return true;
			}
		}
		return false;
	}

	private String getUserName(String text) {
		String[] args = text.split(" ");
		return args[0];
	}

	/**
	 * 移動滾動條
	 * @param index
	 */
	private void addressScroll(int index) {
		int size = getSizeOfOneElement(addressList);
		int move = size * (index);
		String setscroll = "$('.address-list').scrollTop(" + move + ")";
		myDriver.executeScript(setscroll);
	}

	/**
	 * 点击【去支付】进入支付页面
	 */
	public void checkOutAndNaviToPayment() {
		WaitUtil.waitOn(myDriver, CommonConstant.WAIT_TIME_LEVEL3).untilShown(By.cssSelector(CHECKOUT_BUTTON_CSS));
		super.scrollMoveToElement(checkoutButton);
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(CHECKOUT_BUTTON_CSS));
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
		checkoutButton.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CHECKOUT_BUTTON_CSS));
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	/**
	 * 根据类型和标号选择一个发票
	 * @param type
	 * @param index
	 */
	public void selectInvoiceContent(InvoiceType type, int index) {
		if ("REGULAR".equals(type.name())) {
			selectRegularInvoiceContent(index);
		} else if ("SPECIAL".equals(type.name())) {
			selectSpecialInvoiceContent(index);
		} else if ("DIGITAL".equals(type.name())) {
			selectDigitalInvoiceContent(index);
		}
	}

	/**
	 * 根据标号选择一个普通发票
	 * @param index
	 */
	public void selectRegularInvoiceContent(int index) {
		super.scrollMoveToElement(invoiceReceiptTrue);
		invoiceReceiptTrue.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(INVOICE_WAY_CSS));

		invoiceWay.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(RADIO_SPECIAL_INVOICE_CSS));

		regularInvoiceRadio.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));

		try {
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".common-invoice"));
		} catch (TimeoutException e) {
			specialInvoiceRadio.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
			regularInvoiceRadio.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".common-invoice"));
		}
		
		
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));

		WebElement regularInvoiceContent = regularInvoiceContents.get(index);
		WebElement regularInvoiceIcon = regularInvoiceContent.findElement(By.cssSelector(".tick"));
		regularInvoiceIcon.click();

		WaitUtil.waitOn(myDriver, new UntilEvent() {

			@Override
			public boolean excute() {
				String classVlaue = regularInvoiceContent.getAttribute("class");
				return classVlaue.indexOf("red-invoice") > -1;
			}
		}).untilEventHappened();
	}

	/**
	 * 根据标号选择一个专用发票
	 * @param index
	 */
	public void selectSpecialInvoiceContent(int index) {
		super.scrollMoveToElement(invoiceReceiptTrue);
		invoiceReceiptTrue.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(INVOICE_WAY_CSS));

		invoiceWay.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(RADIO_SPECIAL_INVOICE_CSS));

		specialInvoiceRadio.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));

		try {
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".special-invoice"));
		} catch (TimeoutException e) {
			regularInvoiceRadio.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
			specialInvoiceRadio.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".special-invoice"));
		}

		WebElement specialInvoiceContent = specialInvoiceContents.get(index);
		WebElement specialInvoiceIcon = specialInvoiceContent.findElement(By.cssSelector(".tick"));
		specialInvoiceIcon.click();

		WaitUtil.waitOn(myDriver, new UntilEvent() {

			@Override
			public boolean excute() {
				String classVlaue = specialInvoiceContent.getAttribute("class");
				return classVlaue.indexOf("red-invoice") > -1;
			}
		}).untilEventHappened();
	}

	/**
	 * 根据标号选择一个电子发票
	 * @param index
	 */
	public void selectDigitalInvoiceContent(int index) {
		super.scrollMoveToElement(invoiceReceiptTrue);
		invoiceReceiptTrue.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(INVOICE_WAY_CSS));

		invoiceWay.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(RADIO_SPECIAL_INVOICE_CSS));

		digitalInvoiceRadio.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));

		try {
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".common-invoice"));
		} catch (TimeoutException e) {
			regularInvoiceRadio.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
			digitalInvoiceRadio.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".common-invoice"));
		}

		WebElement regularInvoiceContent = regularInvoiceContents.get(index);
		WebElement regularInvoiceIcon = regularInvoiceContent.findElement(By.cssSelector(".tick"));
		regularInvoiceIcon.click();

		WaitUtil.waitOn(myDriver, new UntilEvent() {

			@Override
			public boolean excute() {
				String classVlaue = regularInvoiceContent.getAttribute("class");
				return classVlaue.indexOf("red-invoice") > -1;
			}
		}).untilEventHappened();
	}

	/**
	 * 使用优惠券
	 * @param map
	 */
	public void useDigiTicket(Map<String, List<DigitalTicket>> map) {
		for (Map.Entry<String, List<DigitalTicket>> e : map.entrySet()) {
			String user = e.getKey();
			List<DigitalTicket> ticketList = e.getValue();

			WebElement cartElement = getCartItemByUser(user);
			WebElement digiTicketElement = cartElement.findElement(By.cssSelector(CART_ITEM_TICKET_CSS));
			super.scrollMoveToElement(digiTicketElement);
			digiTicketElement.click();
			WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(UseCouponComponent.COUPON_LIST_CSS));

			UseCouponComponent useCouponComponent = new UseCouponComponent();
			useCouponComponent.selectCoupon(ticketList);
		}
	}

	private WebElement getCartItemByUser(String user) {
		WaitUtil.waitOn(myDriver).untilListShown(By.cssSelector(CART_LIST_CSS));
		WaitUtil.waitOn(myDriver).waitTime(CommonConstant.WAIT_TIME_LEVEL0);
		for (WebElement cart : cartList) {
			WebElement userElement = cart.findElement(By.cssSelector(CART_ITEM_USER_CSS));
			String value = userElement.getText();
			if (value.indexOf(user) > -1) {
				return cart;
			}
		}
		return null;
	}

}
