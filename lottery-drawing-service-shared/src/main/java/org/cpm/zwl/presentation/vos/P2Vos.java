package org.cpm.zwl.presentation.vos;

import java.util.List;

public class P2Vos {

	/*
	 * 查詢指定遊戲之會員剩餘抽獎券
	 */
	private TicketInfoVos findTicketsByUserIdAndGameId;
	
	/*
	 * 查詢獎品之剩餘數量
	 */
	private List<GameItemInfoVos> findAllRewards;
	
	/*
	 * 查詢遊戲所須券數
	 */
	private GameInfoVos findRequiredTickets;

	public TicketInfoVos getFindTicketsByUserIdAndGameId() {
		return findTicketsByUserIdAndGameId;
	}

	public void setFindTicketsByUserIdAndGameId(TicketInfoVos findTicketsByUserIdAndGameId) {
		this.findTicketsByUserIdAndGameId = findTicketsByUserIdAndGameId;
	}

	public List<GameItemInfoVos> getFindAllRewards() {
		return findAllRewards;
	}

	public void setFindAllRewards(List<GameItemInfoVos> findAllRewards) {
		this.findAllRewards = findAllRewards;
	}

	public GameInfoVos getFindRequiredTickets() {
		return findRequiredTickets;
	}

	public void setFindRequiredTickets(GameInfoVos findRequiredTickets) {
		this.findRequiredTickets = findRequiredTickets;
	}
	
}
