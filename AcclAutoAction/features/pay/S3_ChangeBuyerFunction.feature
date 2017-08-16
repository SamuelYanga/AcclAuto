@all@Scenario3
Feature: Test scenario 3

Background:
	Given Open the home page.

@ChangeBuyer
Scenario Outline: Login->add to cart->change buyer->add to cart->check out->select digital ticket->payment page->order detail page->check digital ticket->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Change buyer in cart page. newBuyer="<newBuyer>"
    And Search and add a product to card. productId="<product>"
    And Change buyer to self in cart page.
    And Click checkout button on card page, then navigate to check out page.
    And Select digital ticket in checkout page. user:"<userName>", ticket1:"<ticket1>", amount1:"<amount1>", ticket2:"<ticket2>", amount2:"<amount2>"
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    And Verify digital ticket info is displayed in order detail page correct. user:"<userName>", ticket1:"<ticket1>", amount1:"<amount1>", ticket2:"<ticket2>", amount2:"<amount2>"
    Then Cancel the order and verify the order status.
    Examples:
    |userName	|password	|provinceName	|product	|newBuyer	|ticket1		|amount1	|ticket2		|amount2	|
    |59376340	|123456		|北京市			|31573		|59378563	|920-56533930	|1			|920-56533931	|1			|
