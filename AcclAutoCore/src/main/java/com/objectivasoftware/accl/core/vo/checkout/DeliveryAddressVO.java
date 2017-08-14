package com.objectivasoftware.accl.core.vo.checkout;

public class DeliveryAddressVO {

	private String name;
	private String province;
	private String city;
	private String area;
	private String detaileAddress;
	private String phone;
	private boolean isDefault = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetaileAddress() {
		return detaileAddress;
	}

	public void setDetaileAddress(String detaileAddress) {
		this.detaileAddress = detaileAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public static DeliveryAddressVO getDefaultVO() {
		DeliveryAddressVO vo = new DeliveryAddressVO();
		vo.setName("小李子");
		vo.setDetaileAddress("天安门广场");
		vo.setPhone("13333333333");
		vo.setProvince("北京市");
		vo.setCity("北京市");
		vo.setArea("海淀区");
		vo.setDefault(false);
		return vo;
	}

}
