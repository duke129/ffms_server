package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the Group database table.
 * 
 */
@Entity
@NamedQuery(name="Group.findAll", query="SELECT g FROM Group g")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String idUserGroup;

	private BigInteger createdByUserId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private BigInteger modifiedByUserId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Column(name="UserGroupDescription")
	private String userGroupDescription;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;

	//bi-directional many-to-many association to User
	/*@ManyToMany
	@JoinTable(
		name="UserGroupMapping"
		, joinColumns={
			@JoinColumn(name="idGroup")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idUser")
			}
		)
	private List<User> users;*/

	//bi-directional many-to-many association to Role
	/*@ManyToMany(mappedBy="groups")
	private List<Role> roles;*/

	public Group() {
	}

	public String getIdUserGroup() {
		return this.idUserGroup;
	}

	public void setIdUserGroup(String idUserGroup) {
		this.idUserGroup = idUserGroup;
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

	public String getUserGroupDescription() {
		return this.userGroupDescription;
	}

	public void setUserGroupDescription(String userGroupDescription) {
		this.userGroupDescription = userGroupDescription;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

	/*public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
*/
}