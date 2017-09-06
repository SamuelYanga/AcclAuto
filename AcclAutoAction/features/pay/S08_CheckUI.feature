@all@Scenario8
Feature: Test scenario 8

Background:
	Given Open the home page.

@HomePageUI
Scenario Outline: open homepage->login->log out->login
	And If is login, log out from home page.
	And Check home page is displayed correctly with guest.
    And Login with valid user. userName="<userName>" password="<password>"
	And Check home page is displayed correctly when user login with Standard Edition.
	And Change business view.
	And Check home page is displayed correctly when user login with Shortcut Edition.
	And If is login, log out from home page.
    And Login with valid user. userName="<userName>" password="<password>"
    Then Verify the business view is not changed.
    Examples:
    |userName		|password	|
    |59376340		|123456		|

@HeaderUI
Scenario Outline: open homepage->login->add to cart
	And If is login, log out from home page.
	And Check header is displayed correctly with guest.
    And Login with valid user. userName="<userName>" password="<password>"
    And Check header is displayed correctly when user login.
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    Then Check header is displayed correctly in cart page.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|31573		|

@FloatMenuUI
Scenario Outline: open homepage->login->add to cart
	And If is login, log out from home page.
	And Check float menu is displayed correctly with guest.
    And Login with valid user. userName="<userName>" password="<password>"
    And Check float menu is displayed correctly when user login.
    Then Mouse move to [Change buyer] of float ment, then change buyer link displayed.
    Then Mouse move to [Cart] of float ment, then cart link displayed.
    Then Mouse move to [Order history] of float ment, then order history link displayed.
    Then Mouse move to [E-voucher] of float ment, then e-voucher link displayed.
    Then Mouse move to [Quick buy] of float ment, then Quick buy content displayed.
    Examples:
    |userName		|password	|
    |59376340		|123456		|

@FooterUI
Scenario Outline: open homepage
	And If is login, log out from home page.
    Then Check footer info in home page.
    Examples:
    ||
    ||

@PlpUI
Scenario Outline: open homepage
	And If is login, log out from home page.
    Then Check first menu in home page.
    Then Move the mouse to first menu Nutrilite, then sencon menu of Nutrilite is displayed.
    Then Move the mouse to first menu Artistry, then sencon menu of Artistry is displayed.
    Then Move the mouse to first menu Home Tech, then sencon menu of Home Tech is displayed.
    Then Move the mouse to first menu Home Care, then sencon menu of Home Care is displayed.
    Then Move the mouse to first menu Personaly Care, then sencon menu of Personaly Care is displayed.
    Examples:
    ||
    ||
