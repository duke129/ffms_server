package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;



/**
 * The persistent class for the City database table.
 * 
 */
@Entity
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCity;

	private String cityName;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	/*//bi-directional many-to-one association to Branch
	@OneToMany(mappedBy="city")
	private List<Branch> branches;*/

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="createdByUserId")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="modifiedByUserId")
	private User user2;
	
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/*//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="city")
	private List<Customer> customers;
*/
	public City() {
	}

	public Long getIdCity() {
		return this.idCity;
	}

	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public String toString() {
		return "City [idCity=" + idCity + ", cityName=" + cityName + ", code=" + code + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", statusBean=" + statusBean + ", user1=" + user1 + ", user2=" + user2
				+ ", state=" + state + "]";
	}

	

	

}