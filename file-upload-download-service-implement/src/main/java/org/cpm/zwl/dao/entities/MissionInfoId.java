package org.cpm.zwl.dao.entities;

import java.io.Serializable;

public class MissionInfoId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3736481302098788902L;

	/**
	 * 遊戲編號
	 */
	private String gameId;
	
	/**
	 * 任務編號
	 */
	private String missionId;

	public String getGamdId() {
		return gameId;
	}

	public void setGamdId(String gamdId) {
		this.gameId = gamdId;
	}

	public String getMissionId() {
		return missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}

}
