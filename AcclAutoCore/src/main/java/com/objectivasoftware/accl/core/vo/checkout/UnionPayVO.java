package com.objectivasoftware.accl.core.vo.checkout;

public class UnionPayVO {

	private String bankNumber;
	private String realName;
	private String credentialNo;
	private String smsCode;
	private String phone;

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static UnionPayVO getDefaultVO() {
		UnionPayVO vo = new UnionPayVO();
		vo.setBankNumber("6216261000000000018");
		vo.setRealName("全渠道");
		vo.setCredentialNo("341126197709218366");
		vo.setPhone("13552535506");
		vo.setSmsCode("123456");
		return vo;
	}
}
