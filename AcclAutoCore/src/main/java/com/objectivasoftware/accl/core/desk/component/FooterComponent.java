package com.objectivasoftware.accl.core.desk.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.objectivasoftware.accl.base.frame.BaseComponent;

public class FooterComponent extends BaseComponent {

	// 安利公益基金会Amway Charity Foundation
	public static final String AMWAY_CHARITY_FOUNDATION_LINK_URL = "http://www.amwayfoundation.org/";
	public static final String AMWAY_CHARITY_FOUNDATION_LINK_CSS = ".main-footer a[title=\"安利公益基金会\"]";
	@FindBy(css = AMWAY_CHARITY_FOUNDATION_LINK_CSS)
	private WebElement amwayCharityFoundationLink;

	public boolean checkAmwayCharityFoundation() {
		if (!elementIsShown(By.cssSelector(AMWAY_CHARITY_FOUNDATION_LINK_CSS))) {
			return false;
		}

		String linkUrl = amwayCharityFoundationLink.getAttribute("href");
		return AMWAY_CHARITY_FOUNDATION_LINK_URL.equals(linkUrl);
	}

	// 安利教育网
	public static final String AMWAY_EDU_LINK_URL = "https://www.amwayedu.com.cn/Login/";
	public static final String AMWAY_EDU_LINK_CSS = ".main-footer a[title=\"安利教育网\"]";
	@FindBy(css = AMWAY_EDU_LINK_CSS)
	private WebElement amwayEduLink;

	public boolean checkAmwayEdu() {
		if (!elementIsShown(By.cssSelector(AMWAY_EDU_LINK_CSS))) {
			return false;
		}

		String linkUrl = amwayEduLink.getAttribute("href");
		return AMWAY_EDU_LINK_URL.equals(linkUrl);
	}

	// 联系我们
	public static final String CONTACT_US_LINK_URL = "http://www.amwayfoundation.org/";
	public static final String CONTACT_US_LINK_CSS = ".main-footer a[title=\"联系我们\"]";
	@FindBy(css = CONTACT_US_LINK_CSS)
	private WebElement contactUsLink;

	public boolean checkContactUs() {
		if (!elementIsShown(By.cssSelector(CONTACT_US_LINK_CSS))) {
			return false;
		}

		String linkUrl = contactUsLink.getAttribute("href");
		return CONTACT_US_LINK_URL.equals(linkUrl);
	}

	// 服务条款Terms of Service
	public static final String TERMS_OF_SERVICE_LINK_URL = "/termsAndConditions";
	public static final String TERMS_OF_SERVICE_LINK_CSS = ".main-footer a[title=\"服务条款\"]";
	@FindBy(css = TERMS_OF_SERVICE_LINK_CSS)
	private WebElement termsOfServiceLink;

	public boolean checkTermsOfService() {
		if (!elementIsShown(By.cssSelector(CONTACT_US_LINK_CSS))) {
			return false;
		}

		String linkUrl = termsOfServiceLink.getAttribute("href");
		return linkUrl.contains(TERMS_OF_SERVICE_LINK_URL);
	}

	// 安利全球网
	public static final String AMWAY_GLOBAL_NETWORK_LINK_URL = "http://www.amway.com/";
	public static final String AMWAY_GLOBAL_NETWORK_LINK_CSS = ".main-footer a[title=\"安利全球网\"]";
	@FindBy(css = AMWAY_GLOBAL_NETWORK_LINK_CSS)
	private WebElement amwayGlobalNetworkLink;

	public boolean checkAmwayGlobalNetwork() {
		if (!elementIsShown(By.cssSelector(AMWAY_GLOBAL_NETWORK_LINK_CSS))) {
			return false;
		}

		String linkUrl = amwayGlobalNetworkLink.getAttribute("href");
		return AMWAY_GLOBAL_NETWORK_LINK_URL.equals(linkUrl);
	}

	// 直销信息披露
	public static final String DIRECT_SELLING_INFO_DISCLOSURE_LINK_URL = "http://www.amway.com.cn/info/content/01/index.htm";
	public static final String DIRECT_SELLING_INFO_DISCLOSURE_LINK_CSS = ".main-footer a[title=\"直销信息披露\"]";
	@FindBy(css = DIRECT_SELLING_INFO_DISCLOSURE_LINK_CSS)
	private WebElement directSellingInfoDisclosureLink;

	public boolean checkDirectSellingInfoDisclosure() {
		if (!elementIsShown(By.cssSelector(DIRECT_SELLING_INFO_DISCLOSURE_LINK_CSS))) {
			return false;
		}

		String linkUrl = directSellingInfoDisclosureLink.getAttribute("href");
		return DIRECT_SELLING_INFO_DISCLOSURE_LINK_URL.equals(linkUrl);
	}

	// 企业证照
	public static final String BUSINESS_LICENSE_LINK_URL = "http://www.amway.com.cn/toolbar/ec_preview1.html";
	public static final String BUSINESS_LICENSE_LINK_CSS = ".main-footer a[title=\"企业证照\"]";
	@FindBy(css = BUSINESS_LICENSE_LINK_CSS)
	private WebElement businessLicenseLink;

	public boolean checkBusinessLicense() {
		if (!elementIsShown(By.cssSelector(BUSINESS_LICENSE_LINK_CSS))) {
			return false;
		}

		String linkUrl = businessLicenseLink.getAttribute("href");
		return BUSINESS_LICENSE_LINK_URL.equals(linkUrl);
	}

	// 国家工商总局直销行业管理SAIC direct selling industry administration
	public static final String SAIC_ADMINISTRATION_LINK_URL = "http://zxjg.saic.gov.cn/saicmrktout/";
	public static final String SAIC_ADMINISTRATION_LINK_CSS = ".main-footer a[title=\"国家工商总局直销行业管理\"]";
	@FindBy(css = SAIC_ADMINISTRATION_LINK_CSS)
	private WebElement saicAdministrationLink;

	public boolean checkSaicAdministration() {
		if (!elementIsShown(By.cssSelector(SAIC_ADMINISTRATION_LINK_CSS))) {
			return false;
		}

		String linkUrl = saicAdministrationLink.getAttribute("href");
		return SAIC_ADMINISTRATION_LINK_URL.equals(linkUrl);
	}

	// 版权声明+ICP号
	public static final String COPYRIGHT_INFO_AND_ICP_CONTENT = "版权为安利（中国）日用品有限公司所有未经许可不得转载或链接，2016年粤ICP备05013154";
	public static final String COPYRIGHT_INFO_AND_ICP_CSS = ".main-footer .copyright";
	@FindBy(css = COPYRIGHT_INFO_AND_ICP_CSS)
	private WebElement copyrightInfoAndIcp;

	public boolean checkCopyrightInfoAndIcp() {
		if (!elementIsShown(By.cssSelector(SAIC_ADMINISTRATION_LINK_CSS))) {
			return false;
		}

		String content = copyrightInfoAndIcp.getText().toString();
		return COPYRIGHT_INFO_AND_ICP_CONTENT.equals(content);
	}

}
