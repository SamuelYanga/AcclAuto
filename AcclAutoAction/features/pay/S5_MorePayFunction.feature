@all@Scenario5
Feature: Test scenario 5

Background:
	Given Open the home page.

@morePay
Scenario Outline: Login->add to cart->check out->payment page->select more pay->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Click payment button, then navigate to payment page.
    And Select Union-pay with multiple partial pay, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is in payment and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|31573		|
