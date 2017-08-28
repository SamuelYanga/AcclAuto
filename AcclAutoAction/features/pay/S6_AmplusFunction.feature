@all@Scenario6
Feature: Test scenario 6

Background:
	Given Open the home page.

@amplusPoints
Scenario Outline: Login->Amplus page->select points->add to cart->check out->payment page->order detail page->check point->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Navigate to Amplus gift zone, and select a product, then navigate to pdp. productName="<productName>"
    And Select Amplus price type with points, then add to cart.
    And Click checkout button on card page, then navigate to check out page.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    And Verify the deduct amplus point in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|provinceName	|productName	|
    |59376340		|123456		|北京市			|拖把布			|

@amplusPoints+Cash
Scenario Outline: Login->Amplus page->select points+cash->add to cart->check out->payment page->order detail page->check point->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Navigate to Amplus gift zone, and select a product, then navigate to pdp. productName="<productName>"
    And Select Amplus price type with points and cash, then add to cart.
    And Click checkout button on card page, then navigate to check out page.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    And Verify the deduct amplus point in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|provinceName	|productName	|
    |59376340		|123456		|北京市			|拖把布			|

@CoffeeCouponPoints
Scenario Outline: Login->search coupon->select points->exchange
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search coffee coupon and navigate to pdp. productId="<product>"
    Then Select exchange type with points, then exchange successful.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|999902		|

@CoffeePoints+Cash
Scenario Outline: Login->search coupon->select points->exchange->check out->payment page
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search coffee coupon and navigate to pdp. productId="<product>"
    And Select exchange type with points and cash, then click exchange, payment page is displayed.
    And Select Union-pay, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment result.
    Then Verify exchange Amplus Coffee Coupon successfully on payment result page.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|999902		|

@GZcentralWHAmplusPoints
Scenario Outline: Login->search coupon->select points->exchange->check out->payment page->order detail page->check point->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And search Amplus gift shipped from GZ central warehouse and navigate to pdp. productId="<product>"
    And Select exchange type with points, then exchange and navigate to check out page.
    And Click payment button, then pay success and navigate to payment page.
    And Navigate to order detail page by click View Detail Link from payment.
    And Verify the deduct amplus point in order detail page.
    Then Cancel the order with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|81067		|

@GZcentralWHAmplusPoints+Cash
Scenario Outline: Login->search coupon->select points->exchange->check out->payment page->order detail page->check point->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And search Amplus gift shipped from GZ central warehouse and navigate to pdp. productId="<product>"
    And Select exchange type with points and cash, then exchange and navigate to check out page.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    And Verify the deduct amplus point in order detail page.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|81067		|
