package org.cpm.zwl.presentation.vos;


public class GameInfoVos {

	/*
	 * 遊戲編號
	 */
	private String gameId;
	
	/*
	 * 遊戲名稱
	 */
	private String gameName;
	
	/*
	 * 遊戲所須券數
	 */
	private int ticketRequired;

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getTicketRequired() {
		return ticketRequired;
	}

	public void setTicketRequired(int ticketRequired) {
		this.ticketRequired = ticketRequired;
	}
	
}
