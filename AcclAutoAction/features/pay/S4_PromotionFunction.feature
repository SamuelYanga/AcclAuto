@all@Scenario4
Feature: Test scenario 4

Background:
	Given Open the home page.

@promotion
Scenario Outline: Login->promotion page->add to cart->check gift->check out->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Navigate to promotion zone, and select a promotion. promotionName="<promotionName>"
    And Select a promotion product and navigate to pdp, then add to card. productName="<productName>"
    And Verify the promotion gift is displayed correctly. promotionName="<promotionName>" giftName="<giftName>"
    And Click checkout button on card page, then navigate to check out page.
    And Click payment button, then navigate to payment page.
    And Select Union-pay on payment page, and click pay now, then the Union-pay page displayed.
    And Pay on the Union-pay page, then back to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order and verify the order status.
    Examples:
    |userName		|password	|provinceName	|promotionName			|productName		|giftName	|
    |59376340		|123456		|北京市			|雅姿恒时凝颜系列试用装	|雅姿®恒时凝颜®精华乳	|恒时试用装	|
