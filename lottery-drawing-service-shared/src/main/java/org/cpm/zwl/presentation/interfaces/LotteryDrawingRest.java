package org.cpm.zwl.presentation.interfaces;

import java.util.List;

import org.cpm.zwl.commons.response.JsonResponse;
import org.cpm.zwl.constrants.IsThanks;
import org.cpm.zwl.exception.LdsBaseException;
import org.cpm.zwl.presentation.vos.DrawOutcomeVos;
import org.cpm.zwl.presentation.vos.GameInfoVos;
import org.cpm.zwl.presentation.vos.GameItemInfoVos;
import org.cpm.zwl.presentation.vos.InsertOutcomeVos;
import org.cpm.zwl.presentation.vos.ItemInfoVos;
import org.cpm.zwl.presentation.vos.MissionAndTicketVos;
import org.cpm.zwl.presentation.vos.MissionHistVos;
import org.cpm.zwl.presentation.vos.TicketInfoVos;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * wonderland general rest
 * @author Chuck
 *
 */
public interface LotteryDrawingRest {
	
	/**
	 * 查詢會員剩餘抽獎券數
	 * @param userId
	 * @return
	 * @throws LdsBaseException
	 */	
	@RequestMapping(value="/findTicketsById", method = RequestMethod.GET)
	JsonResponse<List<TicketInfoVos>> findTicketsById(@RequestParam(value="userId") String userId) throws LdsBaseException;

	
	/**
	 * 完成任務後把紀錄加入資料庫
	 * @param missionHistVos
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/addMissionRecord", method = RequestMethod.POST)
	JsonResponse<InsertOutcomeVos> addMissionRecord(@RequestBody MissionHistVos missionHistVos) throws LdsBaseException;
	
	
	/**
	 * 查詢會員每個任務已獲得抽獎券數
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/findMissionTickets", method = RequestMethod.GET)
	JsonResponse<List<MissionAndTicketVos>> findMissionTickets(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException;
	
	
	/**
	 * 查詢獎品之剩餘數量(含銘謝惠顧或不含)
	 * @param gameId
	 * @param isThanks
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/findAllRewards", method = RequestMethod.GET)
	JsonResponse<List<GameItemInfoVos>> findAllRewards(@RequestParam(value="gameId") String gameId, @RequestParam(value="isThanks") IsThanks isThanks) throws LdsBaseException;
	
	
	/**
	 * 進行抽獎動作並回傳有無中獎
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/drawLottery", method = RequestMethod.GET)
	JsonResponse<DrawOutcomeVos> drawLottery(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException;

	
	/**
	 * 查詢遊戲所須券數
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/findRequiredTickets", method = RequestMethod.GET)
	JsonResponse<GameInfoVos> findRequiredTickets(@RequestParam(value="gameId") String gameId) throws LdsBaseException;
	
	
	/**
	 * 查詢指定遊戲之會員剩餘抽獎券
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/findTicketsByUserIdAndGameId", method = RequestMethod.GET)
	JsonResponse<TicketInfoVos> findTicketsByUserIdAndGameId(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException;
	

	/**
	 * 查詢獎品對應名稱和圖片網址
	 * @param itemId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/findByItemId", method = RequestMethod.GET)
	JsonResponse<ItemInfoVos> findByItemId(@RequestParam(value="itemId") String itemId) throws LdsBaseException;
	
}
