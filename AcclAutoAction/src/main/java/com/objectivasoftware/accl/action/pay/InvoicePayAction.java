package com.objectivasoftware.accl.action.pay;

import org.junit.Assert;

import com.objectivasoftware.accl.core.desk.page.CheckOutPage;
import com.objectivasoftware.accl.core.desk.page.OrderDetailPage;
import com.objectivasoftware.accl.core.util.enu.InvoiceType;

import cucumber.api.java.en.And;

public class InvoicePayAction {

	@And("Select a Regular invoice.")
	public void selectRegularInvoice() {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.selectInvoiceContent(InvoiceType.REGULAR, 0);
	}

	@And("Select a Special invoice.")
	public void selectSpecialInvoice() {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.selectInvoiceContent(InvoiceType.SPECIAL, 0);
	}

	@And("Select a Digital invoice.")
	public void selectDigitalInvoice() {
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.selectInvoiceContent(InvoiceType.DIGITAL, 0);
	}

	@And("Verify the Regular invoice in order detail page.")
	public void verifyRegularInvoice() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		Assert.assertTrue(orderDetailPage.verifyInvoiceType(InvoiceType.REGULAR));
		orderDetailPage.closeInvoiceDetail();
	}

	@And("Verify the Special invoice in order detail page.")
	public void verifySpecialInvoice() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		Assert.assertTrue(orderDetailPage.verifyInvoiceType(InvoiceType.SPECIAL));
		orderDetailPage.closeInvoiceDetail();
	}

	@And("Verify the Digital invoice in order detail page.")
	public void verifyDigitalInvoice() {
		OrderDetailPage orderDetailPage = new OrderDetailPage();
		Assert.assertTrue(orderDetailPage.verifyInvoiceType(InvoiceType.DIGITAL));
		orderDetailPage.closeInvoiceDetail();
	}
	
	
}
