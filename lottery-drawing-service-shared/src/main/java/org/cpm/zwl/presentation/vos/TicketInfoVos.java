package org.cpm.zwl.presentation.vos;

public class TicketInfoVos {

	/*
	 * 會員id
	 */
	private String userId;
	
	/*
	 * 遊戲編號
	 */
	private String gameId;
	
	/*
	 * 票券剩餘數量
	 */
	private int ticketNum;

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
	
}
