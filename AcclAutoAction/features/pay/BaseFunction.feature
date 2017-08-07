@all
Feature: Test scenario 1

Background:
	Given Open the home page.

@base
Scenario Outline: search product, and confirm the product title.
    And Login with valid user. userName="<userName>" password="<password>"
    And Add a product to card. productId="<product>"
    And Navigate to check out page.
    And Add a delivery address on check out page.
    And Navigate to payment page.
    And Select Union-pay.
    And Pay on the Union-pay page.
    And Navigate to order detail page from payment.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|
