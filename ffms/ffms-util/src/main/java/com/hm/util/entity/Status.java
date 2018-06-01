package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Status database table.
 * 
 */
@Entity
//@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
@Table(name = "Status")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "idStatus")
	private Integer idStatus;

	//@Column(name = "statusDescription")
	private String statusDescription;

	/*//bi-directional many-to-one association to Area
	@OneToMany(mappedBy="statusBean")
	private List<Area> areas;

	//bi-directional many-to-one association to Asset
	@OneToMany(mappedBy="statusBean")
	private List<Asset> assets;

	//bi-directional many-to-one association to Branch
	@OneToMany(mappedBy="statusBean")
	private List<Branch> branches;

	//bi-directional many-to-one association to City
	@OneToMany(mappedBy="statusBean")
	private List<City> cities;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="statusBean")
	private List<Customer> customers;

	//bi-directional many-to-one association to Group
	@OneToMany(mappedBy="statusBean")
	private List<Group> groups;

	//bi-directional many-to-one association to Role
	@OneToMany(mappedBy="statusBean")
	private List<Role> roles;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="statusBean")
	private List<Ticket> tickets;

	//bi-directional many-to-one association to TicketType
	@OneToMany(mappedBy="statusBean")
	private List<TicketType> ticketTypes;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="statusBean")
	private List<User> users;*/

	public Status() {
	}

	public Integer getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Integer idStatus) {
		this.idStatus = idStatus;
	}

	public String getStatusDescription() {
		return this.statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
/*
	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Area addArea(Area area) {
		getAreas().add(area);
		area.setStatusBean(this);

		return area;
	}

	public Area removeArea(Area area) {
		getAreas().remove(area);
		area.setStatusBean(null);

		return area;
	}

	public List<Asset> getAssets() {
		return this.assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public Asset addAsset(Asset asset) {
		getAssets().add(asset);
		asset.setStatusBean(this);

		return asset;
	}

	public Asset removeAsset(Asset asset) {
		getAssets().remove(asset);
		asset.setStatusBean(null);

		return asset;
	}

	public List<Branch> getBranches() {
		return this.branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Branch addBranch(Branch branch) {
		getBranches().add(branch);
		branch.setStatusBean(this);

		return branch;
	}

	public Branch removeBranch(Branch branch) {
		getBranches().remove(branch);
		branch.setStatusBean(null);

		return branch;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setStatusBean(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setStatusBean(null);

		return city;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setStatusBean(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setStatusBean(null);

		return customer;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Group addGroup(Group group) {
		getGroups().add(group);
		group.setStatusBean(this);

		return group;
	}

	public Group removeGroup(Group group) {
		getGroups().remove(group);
		group.setStatusBean(null);

		return group;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Role addRole(Role role) {
		getRoles().add(role);
		role.setStatusBean(this);

		return role;
	}

	public Role removeRole(Role role) {
		getRoles().remove(role);
		role.setStatusBean(null);

		return role;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setStatusBean(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setStatusBean(null);

		return ticket;
	}

	public List<TicketType> getTicketTypes() {
		return this.ticketTypes;
	}

	public void setTicketTypes(List<TicketType> ticketTypes) {
		this.ticketTypes = ticketTypes;
	}

	public TicketType addTicketType(TicketType ticketType) {
		getTicketTypes().add(ticketType);
		ticketType.setStatusBean(this);

		return ticketType;
	}

	public TicketType removeTicketType(TicketType ticketType) {
		getTicketTypes().remove(ticketType);
		ticketType.setStatusBean(null);

		return ticketType;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setStatusBean(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setStatusBean(null);

		return user;
	}*/

}