package com.objectivasoftware.accl.core.vo.orderdetail;

import java.util.List;

public class DigitalTicket {

	private String num;
	private String consignee;
	private String buyerName;
	private String digiNum;
	private String digiType;
	private String useMoney;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getDigiNum() {
		return digiNum;
	}

	public void setDigiNum(String digiNum) {
		this.digiNum = digiNum;
	}

	public String getDigiType() {
		return digiType;
	}

	public void setDigiType(String digiType) {
		this.digiType = digiType;
	}

	public String getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(String useMoney) {
		this.useMoney = useMoney;
	}

	public boolean isUsed(List<DigitalTicket> list) {
		for (DigitalTicket item: list) {
			if (this.buyerName.equals(item.getBuyerName())
					&& this.digiNum.equals(item.getDigiNum())
					&& this.useMoney.equals(item.getUseMoney())) {
				return true;
			}
		}
		return false;
	}
	
}
