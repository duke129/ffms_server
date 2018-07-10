package com.hm.util.model;

public class ProductSpecificationDTO {
	
	//private String idProductSpecification;

	private String propertyName;

	private String propertyValue;
	
	private String productId;

//	public String getIdProductSpecification() {
//		return idProductSpecification;
//	}
//
//	public void setIdProductSpecification(String idProductSpecification) {
//		this.idProductSpecification = idProductSpecification;
//	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "ProductSpecificationDTO [propertyName=" + propertyName + ", propertyValue=" + propertyValue
				+ ", productId=" + productId + "]";
	}

	
	


}
