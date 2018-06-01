package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Asset database table.
 * 
 */
@Entity
@NamedQuery(name="Asset.findAll", query="SELECT a FROM Asset a")
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String idAsset;

	@Lob
	private String assetDescription;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String installationLat;

	private String installationLong;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	//bi-directional many-to-one association to AssetType
	@ManyToOne
	@JoinColumn(name="idAssetType")
	private AssetType assetType;

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

	//bi-directional many-to-many association to Customer
	@ManyToMany(mappedBy="assets")
	private List<Customer> customers;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="asset")
	private List<Ticket> tickets;

	public Asset() {
	}

	public String getIdAsset() {
		return this.idAsset;
	}

	public void setIdAsset(String idAsset) {
		this.idAsset = idAsset;
	}

	public String getAssetDescription() {
		return this.assetDescription;
	}

	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getInstallationLat() {
		return this.installationLat;
	}

	public void setInstallationLat(String installationLat) {
		this.installationLat = installationLat;
	}

	public String getInstallationLong() {
		return this.installationLong;
	}

	public void setInstallationLong(String installationLong) {
		this.installationLong = installationLong;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public AssetType getAssetType() {
		return this.assetType;
	}

	public void setAssetType(AssetType assetType) {
		this.assetType = assetType;
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

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setAsset(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setAsset(null);

		return ticket;
	}

}