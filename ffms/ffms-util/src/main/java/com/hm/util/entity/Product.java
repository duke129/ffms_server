package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the Product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idProduct;

	//private BigInteger assetId;
	
	private Long assetTypeId;
	
	
	
	/*//bi-directional many-to-one association to AssetType
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="idAssetType")
		private AssetType assetType;*/


/*	public AssetType getAssetType() {
			return assetType;
		}

		public void setAssetType(AssetType assetType) {
			this.assetType = assetType;
		}*/

	public Long getAssestTypeId() {
		return assetTypeId;
	}

	public void setAssestTypeId(Long assetTypeId) {
		this.assetTypeId = assetTypeId;
	}

	private String description;

	private String imgPath;

	private String name;

	private String videoPath;
	
	private String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	//bi-directional many-to-one association to ProductSpecification
	@OneToMany(mappedBy="product")
	private List<ProductSpecification> productSpecifications;

	public Product() {
	}

	public Long getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	/*public BigInteger getAssetId() {
		return this.assetId;
	}

	public void setAssetId(BigInteger assetId) {
		this.assetId = assetId;
	}*/

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVideoPath() {
		return this.videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public List<ProductSpecification> getProductSpecifications() {
		return this.productSpecifications;
	}

	public void setProductSpecifications(List<ProductSpecification> productSpecifications) {
		this.productSpecifications = productSpecifications;
	}

	public ProductSpecification addProductSpecification(ProductSpecification productSpecification) {
		getProductSpecifications().add(productSpecification);
		productSpecification.setProduct(this);

		return productSpecification;
	}

	public ProductSpecification removeProductSpecification(ProductSpecification productSpecification) {
		getProductSpecifications().remove(productSpecification);
		productSpecification.setProduct(null);

		return productSpecification;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", assestTypeId=" + assetTypeId
				+ ", description=" + description + ", imgPath=" + imgPath + ", name="
				+ name + ", videoPath=" + videoPath + ", price=" + price + ", productSpecifications="
				+ productSpecifications + "]";
	}

	
	
	
	
	
}