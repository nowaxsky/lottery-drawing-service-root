package org.cpm.zwl.service;

import java.util.List;

import org.cpm.zwl.dao.entities.DrawHist;
import org.cpm.zwl.dao.entities.GameInfo;
import org.cpm.zwl.dao.entities.GameItemInfo;
import org.cpm.zwl.dao.entities.ItemInfo;
import org.cpm.zwl.dao.entities.MissionAndTickets;
import org.cpm.zwl.dao.entities.MissionHist;
import org.cpm.zwl.dao.entities.TicketInfo;
import org.cpm.zwl.exception.LdsBaseException;
import org.cpm.zwl.presentation.vos.InsertOutcomeVos;


/**
 * 樂園一般功能
 * @author Chuck
 *
 */

public interface WonderlandGeneralService {

	/**
	 * 查詢會員剩餘抽獎券數
	 * @param userId
	 * @return
	 * @throws LdsBaseException
	 */
	public List<TicketInfo> findTicketsById(String userId) throws LdsBaseException;
	
	/**
	 * 查詢指定遊戲之會員剩餘抽獎券
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	public TicketInfo findTicketsByUserIdAndGameId(String userId, String gameId) throws LdsBaseException;
	
	/**
	 * 查詢會員每個任務已獲得抽獎券數
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	public List<MissionAndTickets> findMissionTickets(String userId, String gameId) throws LdsBaseException;
	
	/**
	 * 完成任務後把紀錄加入資料庫
	 * @param missionHist
	 * @return
	 * @throws LdsBaseException
	 */
	public InsertOutcomeVos addMissionRecord(MissionHist missionHist) throws LdsBaseException;
	
	/**
	 * 查詢獎品之剩餘數量
	 * @return
	 * @throws LdsBaseException
	 */
	public List<GameItemInfo> findAllRewards(String gameId) throws LdsBaseException;
	
	/**
	 * 進行抽獎動作並回傳有無中獎
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	public DrawHist drawLottery(String userId, String gameId) throws LdsBaseException;

	/**
	 * 查詢遊戲所須券數
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	public GameInfo findRequiredTickets(String gameId) throws LdsBaseException;
	
	/**
	 * 查詢獎品對應名稱和圖片網址
	 * @param itemId
	 * @return
	 * @throws LdsBaseException
	 */
	public ItemInfo findByItemId(String itemId) throws LdsBaseException;
}
