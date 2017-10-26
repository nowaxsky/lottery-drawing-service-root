package org.cpm.zwl.presentation.vos;

public class GameItemInfoVos {
	
	/*
	 * 獎品id
	 */
	private String itemId;
	
	/*
	 * 獎品名稱
	 */
	private String itemName;
	
	/*
	 * 遊戲id
	 */
	private String gameId;
	
	/*
	 * 剩餘數量
	 */
	private int remainAmount;
	
	/*
	 * 圖片網址
	 */
	private String graphUrl;
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(int remainAmount) {
		this.remainAmount = remainAmount;
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
