package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TicketType database table.
 * 
 */
@Entity
@NamedQuery(name="TicketType.findAll", query="SELECT t FROM TicketType t")
public class TicketType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idTicketType;

	private String ticketType;

	/*//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="ticketType")
	private List<Ticket> tickets;*/

	/*//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;*/

	public TicketType() {
	}

	public Integer getIdTicketType() {
		return this.idTicketType;
	}

	public void setIdTicketType(Integer idTicketType) {
		this.idTicketType = idTicketType;
	}

	public String getTicketType() {
		return this.ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	@Override
	public String toString() {
		return "TicketType [idTicketType=" + idTicketType + ", ticketType=" + ticketType + "]";
	}

	/*public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setTicketType(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setTicketType(null);

		return ticket;
	}
*/
	/*public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}
*/
	
	
	
}