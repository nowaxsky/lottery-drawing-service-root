package org.cpm.zwl.dao.entities;

import java.io.Serializable;

public class GameItemInfoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -473574952841517249L;

	/*
	 * 物品編號(含獎項和銘謝惠顧)
	 */
	private String itemId;
	
	/*
	 * 遊戲編號
	 */
	private String gameId;

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
	
}
