package org.cpm.zwl.presentation.vos;

import java.util.List;

public class P1Vos {

	/*
	 * 查詢會員剩餘抽獎券數
	 */
	private List<TicketInfoVos> findTicketsById;

	public List<TicketInfoVos> getFindTicketsById() {
		return findTicketsById;
	}

	public void setFindTicketsById(List<TicketInfoVos> findTicketsById) {
		this.findTicketsById = findTicketsById;
	}
	
}
