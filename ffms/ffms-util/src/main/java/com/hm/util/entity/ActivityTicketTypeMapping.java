package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ActivityTicketTypeMapping database table.
 * 
 */
@Entity
@NamedQuery(name="ActivityTicketTypeMapping.findAll", query="SELECT a FROM ActivityTicketTypeMapping a")
public class ActivityTicketTypeMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int levelId;

	private int ticketTypeId;

	//bi-directional many-to-one association to Activity
	@ManyToOne
	@JoinColumn(name="activityId")
	private Activity activity;

	public ActivityTicketTypeMapping() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevelId() {
		return this.levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getTicketTypeId() {
		return this.ticketTypeId;
	}

	public void setTicketTypeId(int ticketTypeId) {
		this.ticketTypeId = ticketTypeId;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}