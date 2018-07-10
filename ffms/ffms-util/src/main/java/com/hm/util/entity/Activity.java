package com.hm.util.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Activity database table.
 * 
 */
@Entity
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String activityName;

	//bi-directional many-to-one association to ActivityTicketTypeMapping
	@OneToMany(mappedBy="activity")
	private List<ActivityTicketTypeMapping> activityTicketTypeMappings;

	//bi-directional many-to-one association to TicketActivityLog
	@OneToMany(mappedBy="activity")
	private List<TicketActivityLog> ticketActivityLogs;

	public Activity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public List<ActivityTicketTypeMapping> getActivityTicketTypeMappings() {
		return this.activityTicketTypeMappings;
	}

	public void setActivityTicketTypeMappings(List<ActivityTicketTypeMapping> activityTicketTypeMappings) {
		this.activityTicketTypeMappings = activityTicketTypeMappings;
	}

	public ActivityTicketTypeMapping addActivityTicketTypeMapping(ActivityTicketTypeMapping activityTicketTypeMapping) {
		getActivityTicketTypeMappings().add(activityTicketTypeMapping);
		activityTicketTypeMapping.setActivity(this);

		return activityTicketTypeMapping;
	}

	public ActivityTicketTypeMapping removeActivityTicketTypeMapping(ActivityTicketTypeMapping activityTicketTypeMapping) {
		getActivityTicketTypeMappings().remove(activityTicketTypeMapping);
		activityTicketTypeMapping.setActivity(null);

		return activityTicketTypeMapping;
	}

	public List<TicketActivityLog> getTicketActivityLogs() {
		return this.ticketActivityLogs;
	}

	public void setTicketActivityLogs(List<TicketActivityLog> ticketActivityLogs) {
		this.ticketActivityLogs = ticketActivityLogs;
	}

	public TicketActivityLog addTicketActivityLog(TicketActivityLog ticketActivityLog) {
		getTicketActivityLogs().add(ticketActivityLog);
		ticketActivityLog.setActivity(this);

		return ticketActivityLog;
	}

	public TicketActivityLog removeTicketActivityLog(TicketActivityLog ticketActivityLog) {
		getTicketActivityLogs().remove(ticketActivityLog);
		ticketActivityLog.setActivity(null);

		return ticketActivityLog;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityName=" + activityName  + "]";
	}

	
	

}