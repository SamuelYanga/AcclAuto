@all@all2
Feature: Test scenario 2

Background:
	Given Open the home page.

@RegularInvoice
Scenario Outline: Login->add to cart->check out->select Regular invoice->payment page->order detail page->Verify Special invoice->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Select a Regular invoice.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    And Verify the Regular invoice in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|

@SpecialInvoice
Scenario Outline: Login->add to cart->check out->select Special invoice->payment page->order detail page->Verify Special invoice->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Select a Special invoice.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    And Verify the Special invoice in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|

@DigitalInvoice
Scenario Outline: Login->add to cart->check out->select Digital invoice->payment page->order detail page->Verify Digital invoice->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Select a Digital invoice.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    And Verify the Digital invoice in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|product	|
    |59376340		|123456		|31573		|
