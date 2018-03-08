package org.cpm.zwl.dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(MissionAndTicketsId.class)
public class MissionAndTickets implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 767440845266426944L;

	@Id
	private String userId;
	
	private String gameId;
	
	@Id
	private String missionId;
	
	private long ticketEarnedNum;
	
	public MissionAndTickets(String userId, String gameId, String missionId, long ticketEarnedNum) {
		super();
		this.userId = userId;
		this.gameId = gameId;
		this.missionId = missionId;
		this.ticketEarnedNum = ticketEarnedNum;
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

	public long getTicketEarnedNum() {
		return ticketEarnedNum;
	}

	public void setTicketEarnedNum(long ticketEarnedNum) {
		this.ticketEarnedNum = ticketEarnedNum;
	}
}
