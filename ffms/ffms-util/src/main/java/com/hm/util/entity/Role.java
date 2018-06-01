package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the Role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String idRole;

	private BigInteger createdByUserId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private BigInteger modifiedByUserId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name="RoleDescription")
	private String roleDescription;

	//bi-directional many-to-many association to Group
	@ManyToMany
	@JoinTable(
		name="GroupRoleMapping"
		, joinColumns={
			@JoinColumn(name="idRole")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idGroup")
			}
		)
	private List<Group> groups;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;

	public Role() {
	}

	public String getIdRole() {
		return this.idRole;
	}

	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}

	public BigInteger getCreatedByUserId() {
		return this.createdByUserId;
	}

	public void setCreatedByUserId(BigInteger createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigInteger getModifiedByUserId() {
		return this.modifiedByUserId;
	}

	public void setModifiedByUserId(BigInteger modifiedByUserId) {
		this.modifiedByUserId = modifiedByUserId;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

}