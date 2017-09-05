@all@Scenario1
Feature: Test scenario 1

Background:
	Given Open the home page.

@cancelOrderError
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Select Union-pay with one pay, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page by click View Detail Link from payment.
    Then Cancel order multiple times, then verify cancel error after 5 times. productId="<product>" provinceName="<provinceName>"
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376050		|123456		|广东省			|61694		|
