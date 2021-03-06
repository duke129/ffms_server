package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;



/**
 * The persistent class for the Branch database table.
 * 
 */
@Entity
@NamedQuery(name="Branch.findAll", query="SELECT b FROM Branch b")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idBranch;

	private String branchName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
	private String code;

	//bi-directional many-to-one association to Area
	/*@OneToMany(mappedBy="branch")
	private List<Area> areas;*/

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="cityId")
	private City city;

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

/*	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="branch")
	private List<Customer> customers;*/

	public Branch() {
	}

	public Long getIdBranch() {
		return this.idBranch;
	}

	public void setIdBranch(Long idBranch) {
		this.idBranch = idBranch;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

/*	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Area addArea(Area area) {
		getAreas().add(area);
		area.setBranch(this);

		return area;
	}

	public Area removeArea(Area area) {
		getAreas().remove(area);
		area.setBranch(null);

		return area;
	}
*/
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
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
		return "Branch [idBranch=" + idBranch + ", branchName=" + branchName + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", code=" + code + ", city=" + city + ", statusBean=" + statusBean
				+ ", user1=" + user1 + ", user2=" + user2 + "]";
	}

	

	

}