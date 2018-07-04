package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TicketActivityLog database table.
 * 
 */
@Entity
@NamedQuery(name="TicketActivityLog.findAll", query="SELECT t FROM TicketActivityLog t")
public class TicketActivityLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date activityCreatedOn;

	private java.math.BigInteger activityDoneBy;

	private java.math.BigInteger ticketId;

	//bi-directional many-to-one association to Activity
	@ManyToOne
	@JoinColumn(name="activityId")
	private Activity activity;

	public TicketActivityLog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getActivityCreatedOn() {
		return this.activityCreatedOn;
	}

	public void setActivityCreatedOn(Date activityCreatedOn) {
		this.activityCreatedOn = activityCreatedOn;
	}

	public java.math.BigInteger getActivityDoneBy() {
		return this.activityDoneBy;
	}

	public void setActivityDoneBy(java.math.BigInteger activityDoneBy) {
		this.activityDoneBy = activityDoneBy;
	}

	public java.math.BigInteger getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(java.math.BigInteger ticketId) {
		this.ticketId = ticketId;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}