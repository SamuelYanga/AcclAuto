package com.objectivasoftware.accl.core.page;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.base.wait.WaitUtil.UntilEvent;
import com.objectivasoftware.accl.core.component.AddDeliveryAddressComponent;
import com.objectivasoftware.accl.core.component.UseCouponComponent;
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

	public void addNewAddress(DeliveryAddressVO deliveryAddressVO) {
		addAddress.click();
		WaitUtil.waitOn(myDriver).untilShown(By.id(ADD_ADDRESS_WRAPPER_ID));
		AddDeliveryAddressComponent addDeliveryAddressComponent = new AddDeliveryAddressComponent();
		addDeliveryAddressComponent.addNewAddress(deliveryAddressVO);
	}

	public void addNewAddress() {
		addNewAddress(DeliveryAddressVO.getDefaultVO());
	}

	public void checkOutAndNaviToPayment() {
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(CHECKOUT_BUTTON_CSS));
		super.scrollMoveToElement(checkoutButton);
		WaitUtil.waitOn(myDriver).waitTime(500);
		checkoutButton.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CHECKOUT_BUTTON_CSS));
		// WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilPageDown();
	}

	public void selectInvoiceContent(InvoiceType type, int index) {
		if ("REGULAR".equals(type.name())) {
			selectRegularInvoiceContent(index);
		} else if ("SPECIAL".equals(type.name())) {
			selectSpecialInvoiceContent(index);
		} else if ("DIGITAL".equals(type.name())) {
			selectDigitalInvoiceContent(index);
		}
	}

	public void selectRegularInvoiceContent(int index) {
		super.scrollMoveToElement(invoiceReceiptTrue);
		invoiceReceiptTrue.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(INVOICE_WAY_CSS));

		invoiceWay.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(RADIO_SPECIAL_INVOICE_CSS));

		regularInvoiceRadio.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".common-invoice"));

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

	public void selectSpecialInvoiceContent(int index) {
		super.scrollMoveToElement(invoiceReceiptTrue);
		invoiceReceiptTrue.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(INVOICE_WAY_CSS));

		invoiceWay.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(RADIO_SPECIAL_INVOICE_CSS));

		specialInvoiceRadio.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".special-invoice"));

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

	public void selectDigitalInvoiceContent(int index) {
		super.scrollMoveToElement(invoiceReceiptTrue);
		invoiceReceiptTrue.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(INVOICE_WAY_CSS));

		invoiceWay.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(RADIO_SPECIAL_INVOICE_CSS));

		digitalInvoiceRadio.click();
		WaitUtil.waitOn(myDriver).waitTime(500);
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(".common-invoice"));

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
