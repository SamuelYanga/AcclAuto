@all@Scenario9
Feature: Test scenario 9

Background:
	Given Open the home page.

@applyRefund
Scenario Outline: Login->add to cart->check out->select address->payment page->apply refund->verify status
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Select Union-pay with multiple partial pay, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Apply for refund on payment page.
    Then Verify the order status and refund status on order detail page.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|31573		|
