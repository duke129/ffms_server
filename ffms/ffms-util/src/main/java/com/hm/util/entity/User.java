package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String idUser;

	private BigInteger createdByUserId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String firstName;

	private String lastName;

	private String middleName;

	private BigInteger modifiedByUserId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String password;

	//bi-directional many-to-one association to Area
	@OneToMany(mappedBy="user1")
	private List<Area> areas1;

	//bi-directional many-to-one association to Area
	@OneToMany(mappedBy="user2")
	private List<Area> areas2;

	//bi-directional many-to-one association to Asset
	@OneToMany(mappedBy="user1")
	private List<Asset> assets1;

	//bi-directional many-to-one association to Asset
	@OneToMany(mappedBy="user2")
	private List<Asset> assets2;

	//bi-directional many-to-one association to Branch
	@OneToMany(mappedBy="user1")
	private List<Branch> branches1;

	//bi-directional many-to-one association to Branch
	@OneToMany(mappedBy="user2")
	private List<Branch> branches2;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="user1")
	private List<City> cities1;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="user2")
	private List<City> cities2;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="user1")
	private List<Customer> customers1;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="user2")
	private List<Customer> customers2;

	//bi-directional many-to-many association to Group
	@ManyToMany(mappedBy="users")
	private List<Group> groups;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="user1")
	private List<Ticket> tickets1;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="user2")
	private List<Ticket> tickets2;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="user3")
	private List<Ticket> tickets3;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;

	public User() {
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Area> getAreas1() {
		return this.areas1;
	}

	public void setAreas1(List<Area> areas1) {
		this.areas1 = areas1;
	}

	public Area addAreas1(Area areas1) {
		getAreas1().add(areas1);
		areas1.setUser1(this);

		return areas1;
	}

	public Area removeAreas1(Area areas1) {
		getAreas1().remove(areas1);
		areas1.setUser1(null);

		return areas1;
	}

	public List<Area> getAreas2() {
		return this.areas2;
	}

	public void setAreas2(List<Area> areas2) {
		this.areas2 = areas2;
	}

	public Area addAreas2(Area areas2) {
		getAreas2().add(areas2);
		areas2.setUser2(this);

		return areas2;
	}

	public Area removeAreas2(Area areas2) {
		getAreas2().remove(areas2);
		areas2.setUser2(null);

		return areas2;
	}

	public List<Asset> getAssets1() {
		return this.assets1;
	}

	public void setAssets1(List<Asset> assets1) {
		this.assets1 = assets1;
	}

	public Asset addAssets1(Asset assets1) {
		getAssets1().add(assets1);
		assets1.setUser1(this);

		return assets1;
	}

	public Asset removeAssets1(Asset assets1) {
		getAssets1().remove(assets1);
		assets1.setUser1(null);

		return assets1;
	}

	public List<Asset> getAssets2() {
		return this.assets2;
	}

	public void setAssets2(List<Asset> assets2) {
		this.assets2 = assets2;
	}

	public Asset addAssets2(Asset assets2) {
		getAssets2().add(assets2);
		assets2.setUser2(this);

		return assets2;
	}

	public Asset removeAssets2(Asset assets2) {
		getAssets2().remove(assets2);
		assets2.setUser2(null);

		return assets2;
	}

	public List<Branch> getBranches1() {
		return this.branches1;
	}

	public void setBranches1(List<Branch> branches1) {
		this.branches1 = branches1;
	}

	public Branch addBranches1(Branch branches1) {
		getBranches1().add(branches1);
		branches1.setUser1(this);

		return branches1;
	}

	public Branch removeBranches1(Branch branches1) {
		getBranches1().remove(branches1);
		branches1.setUser1(null);

		return branches1;
	}

	public List<Branch> getBranches2() {
		return this.branches2;
	}

	public void setBranches2(List<Branch> branches2) {
		this.branches2 = branches2;
	}

	public Branch addBranches2(Branch branches2) {
		getBranches2().add(branches2);
		branches2.setUser2(this);

		return branches2;
	}

	public Branch removeBranches2(Branch branches2) {
		getBranches2().remove(branches2);
		branches2.setUser2(null);

		return branches2;
	}

	public List<City> getCities1() {
		return this.cities1;
	}

	public void setCities1(List<City> cities1) {
		this.cities1 = cities1;
	}

	public City addCities1(City cities1) {
		getCities1().add(cities1);
		cities1.setUser1(this);

		return cities1;
	}

	public City removeCities1(City cities1) {
		getCities1().remove(cities1);
		cities1.setUser1(null);

		return cities1;
	}

	public List<City> getCities2() {
		return this.cities2;
	}

	public void setCities2(List<City> cities2) {
		this.cities2 = cities2;
	}

	public City addCities2(City cities2) {
		getCities2().add(cities2);
		cities2.setUser2(this);

		return cities2;
	}

	public City removeCities2(City cities2) {
		getCities2().remove(cities2);
		cities2.setUser2(null);

		return cities2;
	}

	public List<Customer> getCustomers1() {
		return this.customers1;
	}

	public void setCustomers1(List<Customer> customers1) {
		this.customers1 = customers1;
	}

	public Customer addCustomers1(Customer customers1) {
		getCustomers1().add(customers1);
		customers1.setUser1(this);

		return customers1;
	}

	public Customer removeCustomers1(Customer customers1) {
		getCustomers1().remove(customers1);
		customers1.setUser1(null);

		return customers1;
	}

	public List<Customer> getCustomers2() {
		return this.customers2;
	}

	public void setCustomers2(List<Customer> customers2) {
		this.customers2 = customers2;
	}

	public Customer addCustomers2(Customer customers2) {
		getCustomers2().add(customers2);
		customers2.setUser2(this);

		return customers2;
	}

	public Customer removeCustomers2(Customer customers2) {
		getCustomers2().remove(customers2);
		customers2.setUser2(null);

		return customers2;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Ticket> getTickets1() {
		return this.tickets1;
	}

	public void setTickets1(List<Ticket> tickets1) {
		this.tickets1 = tickets1;
	}

	public Ticket addTickets1(Ticket tickets1) {
		getTickets1().add(tickets1);
		tickets1.setUser1(this);

		return tickets1;
	}

	public Ticket removeTickets1(Ticket tickets1) {
		getTickets1().remove(tickets1);
		tickets1.setUser1(null);

		return tickets1;
	}

	public List<Ticket> getTickets2() {
		return this.tickets2;
	}

	public void setTickets2(List<Ticket> tickets2) {
		this.tickets2 = tickets2;
	}

	public Ticket addTickets2(Ticket tickets2) {
		getTickets2().add(tickets2);
		tickets2.setUser2(this);

		return tickets2;
	}

	public Ticket removeTickets2(Ticket tickets2) {
		getTickets2().remove(tickets2);
		tickets2.setUser2(null);

		return tickets2;
	}

	public List<Ticket> getTickets3() {
		return this.tickets3;
	}

	public void setTickets3(List<Ticket> tickets3) {
		this.tickets3 = tickets3;
	}

	public Ticket addTickets3(Ticket tickets3) {
		getTickets3().add(tickets3);
		tickets3.setUser3(this);

		return tickets3;
	}

	public Ticket removeTickets3(Ticket tickets3) {
		getTickets3().remove(tickets3);
		tickets3.setUser3(null);

		return tickets3;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

}