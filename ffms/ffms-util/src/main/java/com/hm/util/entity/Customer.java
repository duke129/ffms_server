package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	private String alternativeMobileNo;

	@Lob
	private String communicationAdderss;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	private String customerType;

	private String emailId;

	private String firstName;

	private String lastName;

	private String mobileNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;

	private String title;

	//bi-directional many-to-one association to Area
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="areaId")
	private Area area;

	//bi-directional many-to-many association to Asset
	/*@ManyToMany
	@JoinTable(
		name="CustomerAssetMapping"
		, joinColumns={
			@JoinColumn(name="idCustomer")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idAsset")
			}
		)
	private List<Asset> assets;*/

	//bi-directional many-to-one association to Branch
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="branchId")
	private Branch branch;

	//bi-directional many-to-one association to City
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cityId")
	private City city;

	//bi-directional many-to-one association to Status
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="status")
	private Status statusBean;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="createdByUserId")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="modifiedByUserId")
	private User user2;

	/*//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="customer")
	private List<Ticket> tickets;*/

	public Customer() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlternativeMobileNo() {
		return this.alternativeMobileNo;
	}

	public void setAlternativeMobileNo(String alternativeMobileNo) {
		this.alternativeMobileNo = alternativeMobileNo;
	}

	public String getCommunicationAdderss() {
		return this.communicationAdderss;
	}

	public void setCommunicationAdderss(String communicationAdderss) {
		this.communicationAdderss = communicationAdderss;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

/*	public List<Asset> getAssets() {
		return this.assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}
*/
	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

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

	/*public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}*/

	/*public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setCustomer(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setCustomer(null);

		return ticket;
	}*/

}