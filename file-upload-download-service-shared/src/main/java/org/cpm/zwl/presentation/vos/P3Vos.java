package org.cpm.zwl.presentation.vos;

import java.util.List;

public class P3Vos {

	/*
	 * 查詢會員每個任務已獲得抽獎券數
	 */
	private List<MissionAndTicketVos> findMissionTickets;

	public List<MissionAndTicketVos> getFindMissionTickets() {
		return findMissionTickets;
	}

	public void setFindMissionTickets(List<MissionAndTicketVos> findMissionTickets) {
		this.findMissionTickets = findMissionTickets;
	}
	
}
