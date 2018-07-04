package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ProductSpecification database table.
 * 
 */
@Entity
@NamedQuery(name="ProductSpecification.findAll", query="SELECT p FROM ProductSpecification p")
public class ProductSpecification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String idProductSpecification;

	private String propertyName;

	private String propertyValue;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;

	public ProductSpecification() {
	}

	public String getIdProductSpecification() {
		return this.idProductSpecification;
	}

	public void setIdProductSpecification(String idProductSpecification) {
		this.idProductSpecification = idProductSpecification;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return this.propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}