package org.cpm.zwl.presentation.vos;

/**
 * 任務完成歷程資訊
 * @author Chuck
 *
 */
public class MissionHistVos {

	/**
	 * 會員id
	 */
	//@ApiModelProperty(value="userId",required = true,example = "A123456789")
	private String userId;
	
	/**
	 * 遊戲編號
	 */
	private String gameId;
	
	/**
	 * 任務編號
	 */
	private String missionId;
	
	/*
	 * 
	 */
	private String createId;

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

	public String getMissionId() {
		return missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}
	
}
