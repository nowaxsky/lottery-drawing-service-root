package org.cpm.zwl.presentation.interfaces;

import org.cpm.zwl.commons.response.JsonResponse;
import org.cpm.zwl.exception.LdsBaseException;
import org.cpm.zwl.presentation.vos.P1Vos;
import org.cpm.zwl.presentation.vos.P2DrawLotteryVos;
import org.cpm.zwl.presentation.vos.P2Vos;
import org.cpm.zwl.presentation.vos.P3Vos;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * wonderland general rest by page
 * @author Chuck
 *
 */
public interface LotteryDrawingRestByPage {

	/**
	 * P1含有下列API:
	 * 1.查詢會員剩餘抽獎券數(findTicketsById), 參數(userId)
	 * 
	 * @param userId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/p1", method = RequestMethod.GET)
	JsonResponse<P1Vos> p1(@RequestParam(value="userId") String userId) throws LdsBaseException;
	
	
	/**
	 * P2含有下列API:
	 * 1.查詢指定遊戲之會員剩餘抽獎券(findTicketsByUserIdAndGameId), 參數(userId)
	 * 2.查詢獎品之剩餘數量(不含銘謝惠顧)(findAllRewards), 參數(gameId)
	 * 3.查詢遊戲所須券數(findRequiredTickets), 參數(gameId)
	 * 
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/p2", method = RequestMethod.GET)
	JsonResponse<P2Vos> p2(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException;
	
	
	/**
	 * P2DrawLottery含有下列API:
	 * 1.進行抽獎動作並回傳有無中獎(drawLottery), 參數(userId, gameId)
	 * 
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/p2DrawLottery", method = RequestMethod.GET)
	JsonResponse<P2DrawLotteryVos> p2DrawLottery(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException;
	
	
	/**
	 * P3含有下列API:
	 * 1.查詢會員每個任務已獲得抽獎券數(findMissionTickets), 參數(userId, gameId)
	 * 
	 * @param userId
	 * @param gameId
	 * @return
	 * @throws LdsBaseException
	 */
	@RequestMapping(value="/p3", method = RequestMethod.GET)
	JsonResponse<P3Vos> p3(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException;
	
}
