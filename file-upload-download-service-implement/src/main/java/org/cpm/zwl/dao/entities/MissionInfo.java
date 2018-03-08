package org.cpm.zwl.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="T_WL_MISSION_INFO")
@IdClass(MissionInfoId.class)
public class MissionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1964090593896722796L;

	@Id
	@Column(name="GAME_ID")
	private String gameId;
	
	@Id
	@Column(name="MISSION_ID")
	private String missionId;
	
	@Column(name="MISSION_NAME")
	private String missionName;
	
	@Column(name="TICKET_MONTHLY_LIMIT")
	private int ticketMonthlyLimit;
	
	@Column(name="TICKET_GOT")
	private int ticketGot;
	
	@Column(name="TICKET_MAX")
	private Integer ticketMax;
	
	@ColumnDefault(value = "SYSTEM")
	@Column(name="CREATE_ID")
	private String createId;
	
	@CreationTimestamp
	@Column(name="CREATE_TIME", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_ID")
	private String updateId;
	
	@UpdateTimestamp
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Version
	@Column(name="VERSION")
	private Long version;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getMissionId() {
		return missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}

	public String getMissionName() {
		return missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}

	public int getTicketMonthlyLimit() {
		return ticketMonthlyLimit;
	}

	public void setTicketMonthlyLimit(int ticketMonthlyLimit) {
		this.ticketMonthlyLimit = ticketMonthlyLimit;
	}

	public int getTicketGot() {
		return ticketGot;
	}

	public void setTicketGot(int ticketGot) {
		this.ticketGot = ticketGot;
	}

	public Integer getTicketMax() {
		return ticketMax;
	}

	public void setTicketMax(Integer ticketMax) {
		this.ticketMax = ticketMax;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	
	
	
}
