@all
Feature: Test scenario 2

Background:
	Given Open the home page.

@RegularInvoice
Scenario Outline: search product, and confirm the product title.
    And Login with valid user. userName="<userName>" password="<password>"
    And Add a product to card. productId="<product>"
    And Navigate to check out page.
    And Select a Regular invoice.
    And Navigate to payment page.
    And Select Union-pay.
    And Pay on the Union-pay page.
    And Navigate to order detail page from payment.
    And Verify the Regular invoice in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|

@SpecialInvoice
Scenario Outline: search product, and confirm the product title.
    And Login with valid user. userName="<userName>" password="<password>"
    And Add a product to card. productId="<product>"
    And Navigate to check out page.
    And Select a Special invoice.
    And Navigate to payment page.
    And Select Union-pay.
    And Pay on the Union-pay page.
    And Navigate to order detail page from payment.
    And Verify the Special invoice in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|

@DigitalInvoice
Scenario Outline: search product, and confirm the product title.
    And Login with valid user. userName="<userName>" password="<password>"
    And Add a product to card. productId="<product>"
    And Navigate to check out page.
    And Select a Digital invoice.
    And Navigate to payment page.
    And Select Union-pay.
    And Pay on the Union-pay page.
    And Navigate to order detail page from payment.
    And Verify the Digital invoice in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|
