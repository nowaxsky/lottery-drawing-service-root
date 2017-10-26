package org.cpm.zwl.presentation.vos;

public class MissionAndTicketVos {
	
	/**
	 * 會員id
	 */
	private String userId;
	
	/**
	 * 遊戲編號
	 */
	private String gameId;
	
	/*
	 * 任務編號
	 */
	private String missionId;
	
	/*
	 * 已獲得抽獎券
	 */
	private int ticketEarnedNum;

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
	
}
