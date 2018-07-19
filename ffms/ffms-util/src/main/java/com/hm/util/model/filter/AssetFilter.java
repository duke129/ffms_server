package com.hm.util.model.filter;

public class AssetFilter extends FfmsFilter {
	private String serialNo;

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	@Override
	public String toString() {
		return "AssetFilter [serialNo=" + serialNo + "]";
	}

}
