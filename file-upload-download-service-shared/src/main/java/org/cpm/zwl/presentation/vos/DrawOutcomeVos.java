package org.cpm.zwl.presentation.vos;

public class DrawOutcomeVos {

	/*
	 * 會員id
	 */
	private String userId;
	
	/*
	 * 遊戲編號
	 */
	private String gameId;
	
	/*
	 * 是否中獎(Y/N)
	 */
	private boolean isWin;
	
	/*
	 * 獎品id
	 */
	private String itemId;
	
	/*
	 * 獎品名稱
	 */
	private String itemName;
	
	/*
	 * 圖片網址
	 */
	private String graphUrl;

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

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGraphUrl() {
		return graphUrl;
	}

	public void setGraphUrl(String graphUrl) {
		this.graphUrl = graphUrl;
	}
	
}
