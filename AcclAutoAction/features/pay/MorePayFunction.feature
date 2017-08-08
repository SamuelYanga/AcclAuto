@all@all5
Feature: Test scenario 5

Background:
	Given Open the home page.

@morePay
Scenario Outline: search product, and confirm the product title.
    And Login with valid user. userName="<userName>" password="<password>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Click payment button, then navigate to payment page.
    And Select Union-pay with payment more, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|
