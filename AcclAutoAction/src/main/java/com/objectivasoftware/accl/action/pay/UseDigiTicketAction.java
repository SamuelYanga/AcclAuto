package com.objectivasoftware.accl.action.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.objectivasoftware.accl.core.page.CheckOutPage;
import com.objectivasoftware.accl.core.page.OrderDetailPage;
import com.objectivasoftware.accl.core.vo.orderdetail.DigitalTicket;

import cucumber.api.java.en.And;

public class UseDigiTicketAction {

	@And("Select digital ticket in checkout page. user:\"(.*)\", ticket1:\"(.*)\", amount1:\"(.*)\", ticket2:\"(.*)\", amount2:\"(.*)\"")
	public void changeBuyerInCartPage(String user, String ticket1, String amount1, String ticket2, String amount2) {
		Map<String, List<DigitalTicket>> map = new HashMap<>();
		List<DigitalTicket> ticketList = new ArrayList<>();
		DigitalTicket ticket = new DigitalTicket();
		ticket.setDigiNum(ticket1);
		ticket.setUseMoney(amount1);
		ticketList.add(ticket);
		ticket = new DigitalTicket();
		ticket.setDigiNum(ticket2);
		ticket.setUseMoney(amount2);
		ticketList.add(ticket);
		map.put(user, ticketList);

		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.useDigiTicket(map);
	}

	@And("Verify digital ticket info is displayed in order detail page correct. user:\"(.*)\", ticket1:\"(.*)\", amount1:\"(.*)\", ticket2:\"(.*)\", amount2:\"(.*)\"")
	public void verifyDigiTicketInfoInOrderDetailPage(String user, String ticket1, String amount1, String ticket2, String amount2) {
		List<DigitalTicket> ticketList = new ArrayList<>();
		DigitalTicket ticket = new DigitalTicket();
		ticket.setBuyerName(user);
		ticket.setDigiNum(ticket1);
		ticket.setUseMoney(amount1);
		ticketList.add(ticket);
		ticket = new DigitalTicket();
		ticket.setBuyerName(user);
		ticket.setDigiNum(ticket2);
		ticket.setUseMoney(amount2);
		ticketList.add(ticket);

		OrderDetailPage orderDetailPage = new OrderDetailPage();
		Assert.assertTrue(orderDetailPage.verifyTicketIsUsed(ticketList));
	}
	
	

}
