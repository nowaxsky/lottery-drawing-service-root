package org.cpm.zwl.presentation;

import java.util.List;

import org.cpm.zwl.commons.log.factories.ZwlLogFactory;
import org.cpm.zwl.commons.response.JsonResponse;
import org.cpm.zwl.commons.utils.JsonResponseUtil;
import org.cpm.zwl.constrants.IsThanks;
import org.cpm.zwl.exception.LdsBaseException;
import org.cpm.zwl.presentation.interfaces.LotteryDrawingRest;
import org.cpm.zwl.presentation.interfaces.LotteryDrawingRestByPage;
import org.cpm.zwl.presentation.vos.DrawOutcomeVos;
import org.cpm.zwl.presentation.vos.GameInfoVos;
import org.cpm.zwl.presentation.vos.GameItemInfoVos;
import org.cpm.zwl.presentation.vos.MissionAndTicketVos;
import org.cpm.zwl.presentation.vos.P1Vos;
import org.cpm.zwl.presentation.vos.P2DrawLotteryVos;
import org.cpm.zwl.presentation.vos.P2Vos;
import org.cpm.zwl.presentation.vos.P3Vos;
import org.cpm.zwl.presentation.vos.TicketInfoVos;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wonderlandGeneral/webpage")
public class LotteryDrawingControllerByPage implements LotteryDrawingRestByPage {

	private final Logger logger = ZwlLogFactory.getLogger(LotteryDrawingController.class);
	private LotteryDrawingRest controller;
	
	@Autowired
	public void setController(LotteryDrawingRest controller) {
		this.controller = controller;
	}
	
	@Override
	public JsonResponse<P1Vos> p1(String userId) throws LdsBaseException {
		try {
			JsonResponse<P1Vos> response = null;
			P1Vos p1Vos = new P1Vos();
			
			JsonResponse<List<TicketInfoVos> > responseOfFindTicketsById = controller.findTicketsById(userId);
			p1Vos.setFindTicketsById(responseOfFindTicketsById.getValues());
			response = JsonResponseUtil.getSuccess(p1Vos, "");
			return response;
		} catch (Exception e) {
			logger.error("page 1 error", e);
			throw e;
		}
		
	}
	
	@Override
	public JsonResponse<P2Vos> p2(String userId, String gameId) throws LdsBaseException {
		try {
			JsonResponse<P2Vos> response = null;
			P2Vos p2Vos = new P2Vos();
			
			JsonResponse<TicketInfoVos> responseOfFindTicketsByUserIdAndGameId = controller.findTicketsByUserIdAndGameId(userId, gameId);
			
			//直接預設不含銘謝惠顧
			JsonResponse<List<GameItemInfoVos> > responseOfFindAllRewards = controller.findAllRewards(gameId, IsThanks.EXCLUDE_THANKS);
			JsonResponse<GameInfoVos> reponseOfFindRequiredTickets = controller.findRequiredTickets(gameId);
			p2Vos.setFindTicketsByUserIdAndGameId(responseOfFindTicketsByUserIdAndGameId.getValues());
			p2Vos.setFindAllRewards(responseOfFindAllRewards.getValues());
			p2Vos.setFindRequiredTickets(reponseOfFindRequiredTickets.getValues());

			response = JsonResponseUtil.getSuccess(p2Vos, "");
			return response;
			
		} catch (Exception e) {
			logger.error("page 2 error", e);
			throw e;
		}
	}
	
	@Override
	public JsonResponse<P2DrawLotteryVos> p2DrawLottery(String userId, String gameId) throws LdsBaseException {
		try {
			JsonResponse<P2DrawLotteryVos> response = null;
			P2DrawLotteryVos p2DrawLotteryVos = new P2DrawLotteryVos();
			
			JsonResponse<DrawOutcomeVos> responseOfDrawLottery = controller.drawLottery(userId, gameId);
			p2DrawLotteryVos.setDrawLottery(responseOfDrawLottery.getValues());
			response = JsonResponseUtil.getSuccess(p2DrawLotteryVos, "");
			return response;
		} catch (Exception e) {
			logger.error("page 2 draw lottery error", e);
			throw e;
		}
	}

	@Override
	public JsonResponse<P3Vos> p3(String userId, String gameId) throws LdsBaseException {
		try {
			JsonResponse<P3Vos> response = null;
			P3Vos p3Vos = new P3Vos();
			
			JsonResponse<List<MissionAndTicketVos> > responseOfFindMissionTickets = controller.findMissionTickets(userId, gameId);
			p3Vos.setFindMissionTickets(responseOfFindMissionTickets.getValues());
			response = JsonResponseUtil.getSuccess(p3Vos, "");
			return response;
		} catch (Exception e) {
			logger.error("page 3 error", e);
			throw e;
		}
	}

}
