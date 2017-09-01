@all@Scenario7
Feature: Test scenario 7

Background:
	Given Open the home page.

@province_beijing
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|北京市			|31573		|

@province_jiangsu
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|江苏省			|31573		|

@province_heilongjiang
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|黑龙江省			|31573		|

@province_henan
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|河南省			|31573		|

@province_hainan
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|海南省			|31573		|

@province_xinjiang
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|新疆自治区		|31573		|

@province_neimenggu
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|内蒙古自治区		|31573		|

@province_hebei
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|河北省			|31573		|

@province_shanghai
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|上海市			|31573		|

@province_liaoning
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|辽宁省			|31573		|

@province_jilin
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|吉林省			|31573		|

@province_anhui
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|安徽省			|31573		|

@province_jiangxi
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|江西省			|31573		|

@province_chongqing
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|重庆市			|31573		|

@province_hunan
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|湖南省			|31573		|

@province_guangdong
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|广东省			|31573		|

@province_guizhou
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|贵州省			|31573		|

@province_sichuan
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|四川省			|31573		|

@province_shangxi
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|陕西省			|31573		|

@province_ningxia
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|宁夏自治区		|31573		|

@province_qinghai
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|青海省			|31573		|

@province_shanxi
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|山西省			|31573		|

@province_zhejiang
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|浙江省			|31573		|

@province_shandong
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|山东省			|31573		|

@province_tianjin
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|天津市			|31573		|

@province_fujian
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|福建省			|31573		|

@province_hubei
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|湖北省			|31573		|

@province_guangxi
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|广西自治区		|31573		|

@province_yunnan
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|云南省			|31573		|

@province_gansu
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|甘肃省			|31573		|

@province_xicang
Scenario Outline: Login->add to cart->check out->add new address->payment page->order detail page->cancel order
    And Login with valid user. userName="<userName>" password="<password>"
    And Select a province for stock. provinceName="<provinceName>"
    And Search and add a product to card. productId="<product>"
    And Click checkout button on card page, then navigate to check out page.
    And Add and select a delivery address by province on check out page. province="<provinceName>"
    And Click payment button, then navigate to payment page.
    And Navigate to order detail page from payment.
    Then Cancel the order which is pending payment with no pay and verify the order status.
    Examples:
    |userName		|password	|provinceName	|product	|
    |59376340		|123456		|西藏自治区		|31573		|
