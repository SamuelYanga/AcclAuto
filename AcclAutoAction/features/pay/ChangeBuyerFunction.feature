@all
Feature: Test scenario 3

Background:
	Given Open the home page.

@ChangeBuyer
Scenario Outline: search product, and confirm the product title.
    And Login with valid user. userName="<userName>" password="<password>"
    And Add a product to card. productId="<product>"
    And Change buyer in cart page. newBuyer="<newBuyer>"
    And Add a product to card. productId="<product>"
    And Change buyer to self in cart page.
    And Navigate to check out page.
    And Select digital ticket in checkout page. user:"<userName>", ticket1:"<ticket1>", amount1:"<amount1>", ticket2:"<ticket2>", amount2:"<amount2>"
    And Navigate to payment page.
    And Select Union-pay.
    And Pay on the Union-pay page.
    And Navigate to order detail page from payment.
    And Verify digital ticket info is displayed in order detail page correct. user:"<userName>", ticket1:"<ticket1>", amount1:"<amount1>", ticket2:"<ticket2>", amount2:"<amount2>"
    Then Cancel the order and verify the order status.
    Examples:
    |userName	|password	|product	|newBuyer	|ticket1		|amount1	|ticket2		|amount2	|
    |59376340	|123456		|31573		|59378563	|920-56533930	|1			|920-56533931	|1			|

@ChangeBuyer
Scenario Outline: search product, and confirm the product title.
    And Login with valid user. userName="<userName>" password="<password>"
    And Add a product to card. productId="<product>"
    And Change buyer in cart page. newBuyer="<newBuyer>"
    And Add a product to card. productId="<product>"
    And Change buyer to self in cart page.
    And Navigate to check out page.
    And Select digital ticket in checkout page. user:"<userName>", ticket1:"<ticket1>", amount1:"<amount1>", ticket2:"<ticket2>", amount2:"<amount2>"
    And Navigate to payment page.
    And Select Union-pay.
    And Pay on the Union-pay page.
    And Navigate to order detail page from payment.
    And Verify digital ticket info is displayed in order detail page correct. user:"<userName>", ticket1:"<ticket1>", amount1:"<amount1>", ticket2:"<ticket2>", amount2:"<amount2>"
    Then Cancel the order and verify the order status.
    Examples:
    |userName	|password	|product	|newBuyer	|ticket1		|amount1	|ticket2		|amount2	|
    |59376340	|123456		|31573		|59378563	|920-56533930	|1			|920-56533931	|1			|
