package org.cpm.zwl.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="T_WL_USR_MISSION_HIST")
public class MissionHist implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2606631741804914564L;
	
	@Id
	@SequenceGenerator(name = "SEQ_NO_GEN", sequenceName = "T_WL_USR_MISSION_HIST_SEQID_SEQ",schema="DBMT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NO_GEN")
	@Column(name="SEQ_NO")
	private int seqNo;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="GAME_ID")
	private String gameId;
	
	@Column(name="MISSION_ID")
	private String missionId;
	
	@Column(name="TICKET_EARNED_NUM")
	private int ticketEarnedNum;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="COMP_DATETIME")
	private Date finishedTime;
	
	@ColumnDefault(value = "SYSTEM")
	@Column(name="CREATE_ID")
	private String createId;
	
	@CreationTimestamp
	@Column(name="CREATE_TIME", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_ID")
	private String updateId;
	
	//@UpdateTimestamp
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Version
	@Column(name="VERSION")
	private Long version;
	
	public int getSeqNo() {
		return seqNo;
	}

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

	public String getMissionId() {
		return missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}

	public int getTicketEarnedNum() {
		return ticketEarnedNum;
	}

	public void setTicketEarnedNum(int ticketEarnedNum) {
		this.ticketEarnedNum = ticketEarnedNum;
	}

	public Date getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
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
