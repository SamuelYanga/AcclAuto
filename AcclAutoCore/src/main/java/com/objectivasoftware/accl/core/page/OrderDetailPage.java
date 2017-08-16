package com.objectivasoftware.accl.core.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BasePage;
import com.objectivasoftware.accl.base.wait.WaitUtil;
import com.objectivasoftware.accl.core.util.CommonConstant;
import com.objectivasoftware.accl.core.util.enu.InvoiceType;
import com.objectivasoftware.accl.core.vo.orderdetail.DigitalTicket;

public class OrderDetailPage extends BasePage {

	public static final String PAYMENT_STATUS_CSS = ".info-status span[class^=status-]";
	@FindBy(css = PAYMENT_STATUS_CSS)
	private WebElement payentStatus;

	public static final String CANCEL_ORDER_CSS = ".ambtn-cancel";
	@FindBy(css = CANCEL_ORDER_CSS)
	private WebElement cancelOrderButton;

	public static final String CANCEL_CONFIRM_CSS = "#cboxLoadedContent .order-cancel-popup .cancelpaymentconfirm";
	@FindBy(css = CANCEL_CONFIRM_CSS)
	private WebElement cancelConfirmButton;

	public static final String INVOICE_DETAIL_LINK_CSS = ".item-digi-invoice a";
	@FindBy(css = INVOICE_DETAIL_LINK_CSS)
	private WebElement invoiceDetailLink;
	
	public static final String INVOICE_DETAIL_CLOSE_ID = "cboxClose";
	@FindBy(id = INVOICE_DETAIL_CLOSE_ID)
	private WebElement invoiceDetailClose;
	
	public static final String INVOICE_DETAIL_TITLE_ID = "cboxTitle";
	@FindBy(id = INVOICE_DETAIL_TITLE_ID)
	private WebElement invoiceDetailTitle;

	public static final String ORDER_DETAIL_TICKET_LIST_CSS = ".orderdetail-ticket-list";

	public static final String DIGITAL_TICKET_LIST_CSS = ".orderdetail-ticket-list .payment-detail-list .pagination-values-row";
	@FindBy(css = DIGITAL_TICKET_LIST_CSS)
	private List<WebElement> digiTicketList;

	public static final String DIGITAL_TICKET_NAME_CSS = "div.item-name[class$=item-name]";
	public static final String DIGITAL_TICKET_DIGI_NUM_CSS = "div.item-digi-num[class$=item-digi-num]";
	public static final String DIGITAL_TICKET_DIGI_TYPE_CSS = "div.item-digi-type[class$=item-digi-type]";
	public static final String DIGITAL_TICKET_DIGI_MONEY_CSS = "div.item-digi-money[class$=item-digi-money]";

	public static final String ORDER_DETAIL_HEAD_NUM_CSS = ".orderdetail-head-num";

	public boolean verifyOrderSuccess() {
		String text = payentStatus.getText();
		return text.equals("付款成功") || text.equals("支付中") || text.equals("待付款");
	}

	public boolean verifyOrderCancel() {
		String text = payentStatus.getText();
		return text.equals("取消订单");
	}

	public void cancelOrder() {
		WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(CANCEL_ORDER_CSS));
		super.scrollMoveToElement(cancelOrderButton);
		cancelOrderButton.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.cssSelector(CANCEL_CONFIRM_CSS));
		cancelConfirmButton.click();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_INNER_CSS));
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CANCEL_CONFIRM_CSS));
	}
	
	public boolean verifyInvoiceType(InvoiceType type) {
		try {
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(INVOICE_DETAIL_LINK_CSS));
		} catch (TimeoutException e) {
			return false;
		}

		super.scrollMoveToElement(invoiceDetailLink);
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(invoiceDetailLink);
		invoiceDetailLink.click();
		WaitUtil.waitOn(myDriver).untilPageDown();
		WaitUtil.waitOn(myDriver).untilElementToBeClickable(By.id(INVOICE_DETAIL_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilShown(By.id(INVOICE_DETAIL_TITLE_ID));
		
		String text = invoiceDetailTitle.getText().trim();
		
		if ("REGULAR".equals(type.name())) {
			return text.contains("增值税普通发票") && text.indexOf("电子版") == -1;
		} else if ("SPECIAL".equals(type.name())) {
			return text.contains("增值税专用发票");
		} else if ("DIGITAL".equals(type.name())) {
			return text.contains("电子版");
		}

		return false;
	}
	
	public void closeInvoiceDetail() {
		invoiceDetailClose.click();
		WaitUtil.waitOn(myDriver).untilHidden(By.id(INVOICE_DETAIL_CLOSE_ID));
		WaitUtil.waitOn(myDriver).untilRemoved(By.cssSelector(CommonConstant.LOADER_ICON_CSS));
	}

	private List<DigitalTicket> getUseTicketsInfo() {
		List<DigitalTicket> infos = new ArrayList<>();

		try {
			WaitUtil.waitOn(myDriver).untilShown(By.cssSelector(ORDER_DETAIL_TICKET_LIST_CSS));
		} catch (TimeoutException e) {
			return infos;
		}
		super.scrollMoveToElement(myDriver.findElement(By.cssSelector(ORDER_DETAIL_TICKET_LIST_CSS)));

		for (WebElement digiTicket : digiTicketList) {
			DigitalTicket info = new DigitalTicket();
			WebElement name = digiTicket.findElement(By.cssSelector(DIGITAL_TICKET_NAME_CSS));
			String nameStr = name.getText().trim();
			nameStr = nameStr.substring(nameStr.indexOf("(") + 1, nameStr.indexOf(")"));
			info.setBuyerName(nameStr);

			WebElement digiNum = digiTicket.findElement(By.cssSelector(DIGITAL_TICKET_DIGI_NUM_CSS));
			info.setDigiNum(digiNum.getText().trim());

			WebElement digiType = digiTicket.findElement(By.cssSelector(DIGITAL_TICKET_DIGI_TYPE_CSS));
			info.setDigiType(digiType.getText().trim());

			WebElement digiMoney = digiTicket.findElement(By.cssSelector(DIGITAL_TICKET_DIGI_MONEY_CSS));
			String moneyStr = digiMoney.getText().trim();
			moneyStr = moneyStr.substring(0, moneyStr.indexOf("."));
			info.setUseMoney(moneyStr);

			infos.add(info);
		}

		return infos;
	}

	public boolean verifyTicketIsUsed(List<DigitalTicket> tickets) {
		List<DigitalTicket> usedTicketList = getUseTicketsInfo();
		if (usedTicketList == null || usedTicketList.size() == 0) {
			return false;
		}

		for (DigitalTicket ticket : tickets) {
			if ( !ticket.isUsed(usedTicketList)) {
				return false;
			}
		}
		return true;
	}
	
	//Deduct points
	public static final String DEDUCT_AMPLUS_POINTS_CSS = ".pricing-summary .pricing-summary-value";
	@FindBy(css = DEDUCT_AMPLUS_POINTS_CSS)
	private List<WebElement> deductAmplusPoint;

	public boolean verifyDeductAmplusPoint(String point) {
		String value = deductAmplusPoint.get(4).getText().trim();
		value = value.substring(0, value.indexOf("."));
		return point.equals(value);
	}

}
