package org.cpm.zwl.dao.entities;

import java.io.Serializable;

public class TicketInfoId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4158926707138043468L;

	/**
	 * 使用者身份證字號
	 */
	private String userId;
	
	/**
	 * 遊戲編號
	 */
	private String gameId;

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
}
