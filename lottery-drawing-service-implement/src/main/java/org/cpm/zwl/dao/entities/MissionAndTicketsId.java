package org.cpm.zwl.dao.entities;

import java.io.Serializable;

public class MissionAndTicketsId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8242963756538707728L;

	/*
	 * 使用者身份證字號
	 */
	private String userId;
	
	/*
	 * 任務編號
	 */
	private String missionId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMissionId() {
		return missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}
	
	
}
