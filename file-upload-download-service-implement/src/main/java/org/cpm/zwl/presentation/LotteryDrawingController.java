package org.cpm.zwl.presentation;

import java.util.ArrayList;
import java.util.List;

import org.cpm.zwl.commons.log.factories.ZwlLogFactory;
import org.cpm.zwl.commons.response.JsonResponse;
import org.cpm.zwl.commons.utils.JsonResponseUtil;
import org.cpm.zwl.constrants.DrawOutcome;
import org.cpm.zwl.constrants.IsThanks;
import org.cpm.zwl.constrants.IsWin;
import org.cpm.zwl.dao.entities.DrawHist;
import org.cpm.zwl.dao.entities.GameInfo;
import org.cpm.zwl.dao.entities.GameItemInfo;
import org.cpm.zwl.dao.entities.ItemInfo;
import org.cpm.zwl.dao.entities.MissionAndTickets;
import org.cpm.zwl.dao.entities.MissionHist;
import org.cpm.zwl.dao.entities.TicketInfo;
import org.cpm.zwl.exception.LdsBaseException;
import org.cpm.zwl.presentation.interfaces.LotteryDrawingRest;
import org.cpm.zwl.presentation.vos.DrawOutcomeVos;
import org.cpm.zwl.presentation.vos.GameInfoVos;
import org.cpm.zwl.presentation.vos.GameItemInfoVos;
import org.cpm.zwl.presentation.vos.InsertOutcomeVos;
import org.cpm.zwl.presentation.vos.ItemInfoVos;
import org.cpm.zwl.presentation.vos.MissionAndTicketVos;
import org.cpm.zwl.presentation.vos.MissionHistVos;
import org.cpm.zwl.presentation.vos.TicketInfoVos;
import org.cpm.zwl.service.WonderlandGeneralService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wonderlandGeneral")
public class LotteryDrawingController implements LotteryDrawingRest{
	private final Logger logger = ZwlLogFactory.getLogger(LotteryDrawingController.class);
	private WonderlandGeneralService service;
	
	@Autowired
	public void setWonderlandGeneralService(WonderlandGeneralService service) {
		this.service = service;
	}

	@Override
	public JsonResponse<List<TicketInfoVos> > findTicketsById(@RequestParam(value="userId") String userId) throws LdsBaseException {
		try {
			JsonResponse<List<TicketInfoVos> > response = null;
			List<TicketInfo> result = service.findTicketsById(userId);
			List<TicketInfoVos> returnList = new ArrayList<TicketInfoVos>();
			for(int i = 0; i < result.size(); i++) {
				TicketInfoVos vos = new TicketInfoVos();
				vos.setUserId(result.get(i).getUserId());
				vos.setGameId(result.get(i).getGameId());
				vos.setTicketNum(result.get(i).getTicketNum());
				returnList.add(vos);
			}
			response = JsonResponseUtil.getSuccess(returnList, "");
			return response;
		} catch (LdsBaseException e) {
			logger.error("queryTicket error",e);
			throw e;
		}
	}

	@Override
	public JsonResponse<InsertOutcomeVos> addMissionRecord(@RequestBody MissionHistVos missionHistVos) throws LdsBaseException {
		try {			
			//將MissionHistVos class轉換為MissionHist class
			MissionHist entity = new MissionHist();
			entity.setUserId(missionHistVos.getUserId());
			entity.setGameId(missionHistVos.getGameId());
			entity.setMissionId(missionHistVos.getMissionId());
			entity.setCreateId(missionHistVos.getCreateId());
			
//			boolean result = service.addMissionRecord(entity);
//			JsonResponse<Object> response = JsonResponseUtil.getSuccess(result, null);		
//			return response;
			
			InsertOutcomeVos result = service.addMissionRecord(entity);
			JsonResponse<InsertOutcomeVos> response = JsonResponseUtil.getSuccess(result, "");
			return response;
			
		} catch (LdsBaseException e) {
			logger.error("add data into mission history table error",e);
			throw e;
		}
	}

	@Override
	public JsonResponse<List<MissionAndTicketVos>> findMissionTickets(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException {
		try {
			JsonResponse<List<MissionAndTicketVos> > response = null;
			List<MissionAndTickets> result = service.findMissionTickets(userId, gameId);
			List<MissionAndTicketVos> returnList = new ArrayList<MissionAndTicketVos>();
			for (int i = 0; i < result.size(); i++) {
				MissionAndTicketVos vos = new MissionAndTicketVos();
				vos.setUserId(result.get(i).getUserId());
				vos.setGameId(result.get(i).getGameId());
				vos.setMissionId(result.get(i).getMissionId());
				vos.setTicketEarnedNum((int) (result.get(i).getTicketEarnedNum()));
				returnList.add(vos);
			}
			response = JsonResponseUtil.getSuccess(returnList, "");
			return response;
			
		} catch (LdsBaseException e) {
			logger.error("query mission and tickets error",e);
			throw e;
		}

	}

	@Override
	public JsonResponse<List<GameItemInfoVos>> findAllRewards(@RequestParam(value="gameId") String gameId, @RequestParam(value="isThanks") IsThanks isThanks) throws LdsBaseException {		
		try {
			JsonResponse<List<GameItemInfoVos> > response = null;
			List<GameItemInfo> result = service.findAllRewards(gameId);
			List<GameItemInfoVos> returnList = new ArrayList<>();
			
			if(isThanks == IsThanks.EXCLUDE_THANKS) {
				for(GameItemInfo info: result) {
					if(DrawOutcome.THANK.getValue().equals(info.getItemId())) continue;
					GameItemInfoVos vos = this.buildGameItemInfoVos(info);
					returnList.add(vos);
				}
			} else {
				for(GameItemInfo info: result) {
					GameItemInfoVos vos = this.buildGameItemInfoVos(info);
					returnList.add(vos);
				}
			}
			
			response = JsonResponseUtil.getSuccess(returnList, "");
			return response;
			
		} catch (LdsBaseException e) {
			logger.error("query mission and tickets error",e);
			throw e;
		}
	}

	//將GameItemInfo轉換為GameItemInfoVos
	private GameItemInfoVos buildGameItemInfoVos(GameItemInfo info) {
		GameItemInfoVos vos = new GameItemInfoVos();
		vos.setItemId(info.getItemId());
		vos.setItemName(info.getItemName());
		vos.setGameId(info.getGameId());
		vos.setRemainAmount(info.getRemainAmount());
		vos.setGraphUrl(info.getGraphUrl());
		return vos;
	}

	@Override
	public JsonResponse<DrawOutcomeVos> drawLottery(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException {
		try {
						
			JsonResponse<DrawOutcomeVos> response = null;
			DrawHist resultFromDrawLottery = null;
			ItemInfo resultFromFindByItemId = null;

			resultFromDrawLottery = service.drawLottery(userId, gameId);
			
			String itemId = resultFromDrawLottery.getItemId();		
			resultFromFindByItemId = service.findByItemId(itemId);

			DrawOutcomeVos vos = new DrawOutcomeVos();
			vos.setUserId(userId);
			vos.setGameId(gameId);
			vos.setWin(resultFromDrawLottery.getIsWin().equals(IsWin.WIN.getValue()));
			vos.setItemId(itemId);
			
			vos.setItemName(resultFromFindByItemId.getItemName());
			vos.setGraphUrl(resultFromFindByItemId.getGraphUrl());
			response = JsonResponseUtil.getSuccess(vos, "");

			return response;		
			
		} catch (LdsBaseException e) {
			logger.error("draw lottery error",e);
			throw e;
		}
	}

	@Override
	public JsonResponse<GameInfoVos> findRequiredTickets(@RequestParam(value="gameId") String gameId) throws LdsBaseException {
		try {
			JsonResponse<GameInfoVos> response = null;
			GameInfo result = service.findRequiredTickets(gameId);
			GameInfoVos vos = new GameInfoVos();
			
			vos.setGameId(result.getGameId());
			vos.setGameName(result.getGameName());
			vos.setTicketRequired(result.getTicketRequired());
			
			response = JsonResponseUtil.getSuccess(vos, "");
			return response;
			
		} catch (LdsBaseException e) {
			logger.error("find required tickets error",e);
			throw e;
		}
	}

	@Override
	public JsonResponse<TicketInfoVos> findTicketsByUserIdAndGameId(@RequestParam(value="userId") String userId, @RequestParam(value="gameId") String gameId) throws LdsBaseException {
		try {
			JsonResponse<TicketInfoVos> response = null;
			TicketInfo result = service.findTicketsByUserIdAndGameId(userId, gameId);
			TicketInfoVos vos = new TicketInfoVos();
			
			vos.setUserId(result.getUserId());
			vos.setGameId(result.getGameId());		
			vos.setTicketNum(result.getTicketNum());
			
			response = JsonResponseUtil.getSuccess(vos, "");
			return response;
			
		} catch (LdsBaseException e) {
			logger.error("query tickets by userId and gameId error",e);
			throw e;
		}
	}

	@Override
	public JsonResponse<ItemInfoVos> findByItemId(String itemId) throws LdsBaseException {
		try {
			JsonResponse<ItemInfoVos> response = null;
			ItemInfo result = service.findByItemId(itemId);
			ItemInfoVos vos = new ItemInfoVos();
			
			vos.setItemId(result.getItemId());
			vos.setItemName(result.getItemName());
			vos.setGraphUrl(result.getGraphUrl());
			
			response = JsonResponseUtil.getSuccess(vos, "");
			return response;
			
		} catch (LdsBaseException e) {
			logger.error("query item info table error",e);
			throw e;
		}
	}
	
}
