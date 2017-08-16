@all@all1
Feature: Test scenario 1

Background:
	Given Open the home page.

@base
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add a delivery address on check out page.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|31573		|