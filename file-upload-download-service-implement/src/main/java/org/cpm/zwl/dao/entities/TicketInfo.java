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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="T_WL_USR_TICKET_INFO")
@IdClass(TicketInfoId.class)
public class TicketInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4065876754289698441L;

	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Id
	@Column(name="GAME_ID")
	private String gameId;
	
	@Column(name="TICKET_NUM")
	private int ticketNum;
	
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(int ticketNum) {
		this.ticketNum = ticketNum;
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
