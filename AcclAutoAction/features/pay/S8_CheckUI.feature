@all@Scenario8
Feature: Test scenario 8

Background:
	Given Open the home page.

@HomePageUI
Scenario Outline: open homepage->login->log out->login
	And Check home page is displayed correctly with guest.
    And Login with valid user. userName="<userName>" password="<password>"
	And Check home page is displayed correctly when user login with Standard Edition.
	And Change business view.
	And Check home page is displayed correctly when user login with Shortcut Edition.
	And Log out from home page.
    And Login with valid user. userName="<userName>" password="<password>"
    Then Verify the business view is not changed.
    Examples:
    |userName		|password	|
    |59376340		|123456		|

@HeaderUI
Scenario Outline: open homepage->login->add to cart
	And Check header is displayed correctly with guest.
    And Login with valid user. userName="<userName>" password="<password>"
    And Check header is displayed correctly when user login with.
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    Then Check header is displayed correctly in cart page.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|31573		|
