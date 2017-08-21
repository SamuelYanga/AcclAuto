@all@Scenario8
Feature: Test scenario 8

Background:
	Given Open the home page.

@HomePageUI
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
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
