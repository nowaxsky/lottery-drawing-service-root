package org.cpm.zwl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cpm.zwl.commons.log.factories.ZwlLogFactory;
import org.cpm.zwl.constrants.CreateId;
import org.cpm.zwl.constrants.DrawOutcome;
import org.cpm.zwl.constrants.GameId;
import org.cpm.zwl.constrants.IsWin;
import org.cpm.zwl.constrants.MissionType;
import org.cpm.zwl.constrants.LdsErrorCode;
import org.cpm.zwl.dao.entities.DrawHist;
import org.cpm.zwl.dao.entities.GameInfo;
import org.cpm.zwl.dao.entities.GameItemInfo;
import org.cpm.zwl.dao.entities.ItemInfo;
import org.cpm.zwl.dao.entities.MissionAndTickets;
import org.cpm.zwl.dao.entities.MissionHist;
import org.cpm.zwl.dao.entities.MissionInfo;
import org.cpm.zwl.dao.entities.TicketInfo;
import org.cpm.zwl.dao.persistence.DrawHistRepository;
import org.cpm.zwl.dao.persistence.GameInfoRepository;
import org.cpm.zwl.dao.persistence.GameItemInfoRepository;
import org.cpm.zwl.dao.persistence.ItemInfoRepository;
import org.cpm.zwl.dao.persistence.MissionAndTicketsRepository;
import org.cpm.zwl.dao.persistence.MissionHistRepository;
import org.cpm.zwl.dao.persistence.MissionInfoRepository;
import org.cpm.zwl.dao.persistence.TicketInfoRepository;
import org.cpm.zwl.exception.LdsBaseException;
import org.cpm.zwl.presentation.vos.InsertOutcomeVos;
import org.cpm.zwl.service.WonderlandGeneralService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WonderlandGeneralServiceImpl implements WonderlandGeneralService{
	private final Logger logger = ZwlLogFactory.getLogger(WonderlandGeneralServiceImpl.class);
	private TicketInfoRepository ticketInfoRepository;
	private MissionHistRepository missionHistRepository; 
	private MissionAndTicketsRepository missionAndTicketsRepository;
	private GameItemInfoRepository gameItemInfoRepository;
	private MissionInfoRepository missionInfoRepository;
	private DrawHistRepository drawHistRepository;
	private GameInfoRepository gameInfoRepository;
	private ItemInfoRepository itemInfoRepository;
	
	@Autowired
	public void setTicketInfoRepository(TicketInfoRepository ticketInfoRepository) {
		this.ticketInfoRepository = ticketInfoRepository;
	}
	
	@Autowired
	public void setMissionHistRepository(MissionHistRepository missionHistRepository) {
		this.missionHistRepository = missionHistRepository;
	}
	
	@Autowired
	public void setMissionAndTicketsRepository(MissionAndTicketsRepository missionAndTicketsRepository) {
		this.missionAndTicketsRepository = missionAndTicketsRepository;
	}

	@Autowired
	public void setGameItemInfoRepository(GameItemInfoRepository gameItemInfoRepository) {
		this.gameItemInfoRepository = gameItemInfoRepository;
	}
	
	@Autowired
	public void setMissionInfoRepository(MissionInfoRepository missionInfoRepository) {
		this.missionInfoRepository = missionInfoRepository;
	}
	
	@Autowired
	public void setDrawHistRepository(DrawHistRepository drawHistRepository) {
		this.drawHistRepository = drawHistRepository;
	}
	
	@Autowired
	public void setGameInfoRepository(GameInfoRepository gameInfoRepository) {
		this.gameInfoRepository = gameInfoRepository;
	}
	
	@Autowired
	public void setItemInfoRepository(ItemInfoRepository itemInfoRepository) {
		this.itemInfoRepository = itemInfoRepository;
	}
	
	@Override
	public List<TicketInfo> findTicketsById(String userId) throws LdsBaseException {
		
		List<TicketInfo> returnList = new ArrayList<TicketInfo>();
		try {
			returnList = ticketInfoRepository.findByUserIdOrderByGameId(userId);
			return returnList;
		} catch (Exception e) {
			logger.error(LdsErrorCode.LDS_QUERY_TICKETS_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_QUERY_TICKETS_ERROR);
		}
	}

	@Transactional(rollbackFor = LdsBaseException.class)
	@Override
	public InsertOutcomeVos addMissionRecord(MissionHist missionHist) throws LdsBaseException {
		
		try {
			
			MissionAndTickets ticket = null;	
			MissionInfo missionInfo = missionInfoRepository.findByGameIdAndMissionId(missionHist.getGameId(), missionHist.getMissionId());

			//若為循環任務(無上限)
			if(missionInfo.getTicketMax() == null) {
				ticket = missionAndTicketsRepository.findTicketsCycle(missionHist.getUserId(), missionHist.getGameId(), missionHist.getMissionId());
			
			//非循環任務
			} else {
				ticket = missionAndTicketsRepository.findTicketsNotCycle(missionHist.getUserId(), missionHist.getGameId(), missionHist.getMissionId()); 
			}
			
			int availableTickets = this.calculateAvailableTickets(ticket, missionInfo);
			InsertOutcomeVos outcome = new InsertOutcomeVos();
			
			//還有可獲得券數
			if(availableTickets > 0) {
				//把任務完成資訊加入任務完成log
				missionHist.setTicketEarnedNum(missionInfo.getTicketGot());
				missionHistRepository.save(missionHist);
				boolean isSuccess = false;
				List<MissionHist> missionRecord = missionHistRepository.findByUserIdAndMissionId(missionHist.getUserId(), missionHist.getMissionId());
				
				//新任務
				if(missionRecord.size() == 0) {
					
					List<TicketInfo> ticketInfos = ticketInfoRepository.findByUserIdOrderByGameId(missionHist.getUserId());
					for (TicketInfo ticketInfo: ticketInfos) {
						//若已玩過該遊戲則"更新"會員剩餘票券資訊
						if(ticketInfo.getGameId().equals(missionHist.getGameId())) {
							isSuccess = this.updateUserTicketInfo(missionHist);
							return new InsertOutcomeVos(isSuccess, "insert successfully");
						}
					}				
					//若未玩過該遊戲則"增加"會員剩餘票券資訊			
					isSuccess = this.insertUserTicketInfo(missionHist);
				
				//舊任務
				} else {
					//更新會員剩餘票券資訊並回傳是否成功
					isSuccess = this.updateUserTicketInfo(missionHist);
				}
				
				outcome.setSuccess(isSuccess);
				outcome.setMessage("insert successfully");
				
				return outcome;
				
			} else {
				outcome.setSuccess(false);
				//若無可獲得券數會回傳是否為循環任務
				String returnMessage = "insert unsuccessfully in " + (missionInfo.getTicketMax() == null ? MissionType.CYCLE_MISSION.getValue() : MissionType.NOT_CYCLE_MISSION.getValue());
				outcome.setMessage(returnMessage);
				
				return outcome;
			}			

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LdsErrorCode.LDS_INSERT_TABLE_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_INSERT_TABLE_ERROR);
		}
		
	}

	//更新會員剩餘票券資訊並回傳是否成功
	private boolean updateUserTicketInfo(MissionHist missionHist) throws LdsBaseException {
		int updateRow = ticketInfoRepository.addTicketNum(missionHist.getUserId(), missionHist.getGameId(), missionHist.getTicketEarnedNum(), CreateId.SYSTEM.getValue(), new Date());
		if(updateRow == 1) {
			return true;
		} else {
			logger.error("update user ticket info table error");
			throw new LdsBaseException(LdsErrorCode.LDS_UPDATE_USER_TICKET_INFO_TABLE_ERRPR);
		}
	}

	//增加會員剩餘票券資訊並回傳是否成功
	private boolean insertUserTicketInfo(MissionHist missionHist) throws LdsBaseException {
		try {
			TicketInfo ticketInfo = new TicketInfo();
			ticketInfo.setUserId(missionHist.getUserId());
			ticketInfo.setGameId(missionHist.getGameId());
			ticketInfo.setTicketNum(missionHist.getTicketEarnedNum());
			ticketInfo.setCreateId(CreateId.SYSTEM.getValue());
			ticketInfoRepository.save(ticketInfo);
			return true;
		} catch (Exception e) {
			logger.error("insert user ticket info table error");
			throw new LdsBaseException(LdsErrorCode.LDS_INSERT_USER_TICKET_INFO_TABLE_ERRPR);
		}
		
	}

	//計算可獲得的剩餘票券
	private int calculateAvailableTickets(MissionAndTickets ticket, MissionInfo missionInfo) {		
		
		int availableTickets;
		//sumval為Integer, 若無資料會顯示null, 將null轉換為0以便計算		
		int ticketEarnedNum = (ticket == null ? 0 : (int) ticket.getTicketEarnedNum());
		
		//循環任務
		if(missionInfo.getTicketMax() == null) {
			availableTickets = missionInfo.getTicketMonthlyLimit() - ticketEarnedNum;
			
		//非循環任務
		} else {
			availableTickets = missionInfo.getTicketMax() - ticketEarnedNum;
		}
		return availableTickets;
		 
	}

	@Override
	public List<MissionAndTickets> findMissionTickets(String userId, String gameId) throws LdsBaseException {		
		
		List<MissionAndTickets> returnList = new ArrayList<MissionAndTickets>();
		try {
			
			List<MissionInfo> missionInfos = missionInfoRepository.findAll();
			List<MissionAndTickets> cycleTemp = missionAndTicketsRepository.findCycleMissionByUserIdAndGameIdOrderByMissionId(userId, gameId);
			List<MissionAndTickets> notCycleTemp = missionAndTicketsRepository.findNotCycleMissionByUserIdAndGameIdOrderByMissionId(userId, gameId);

			for(MissionInfo missionInfo: missionInfos) {
				//若為循環任務(無上限)
				if(missionInfo.getTicketMax() == null) {
					for(MissionAndTickets cycle: cycleTemp) {
						if(cycle.getMissionId().equals(missionInfo.getMissionId())) {
							returnList.add(cycle);
							break;
						}
					}
				//若為非循環任務
				} else {
					for(MissionAndTickets notCycle: notCycleTemp) {
						if(notCycle.getMissionId().equals(missionInfo.getMissionId())) {
							returnList.add(notCycle);
							break;
						}
					}
				}
			}
			return returnList;
		} catch (Exception e) {
			logger.error(LdsErrorCode.LDS_QUERY_MISSION_TICKETS_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_QUERY_MISSION_TICKETS_ERROR);
		}
	}

	@Override
	public List<GameItemInfo> findAllRewards(String gameId) throws LdsBaseException {
		List<GameItemInfo> returnList = new ArrayList<>();
		try {
			returnList = gameItemInfoRepository.findByGameId(gameId);
			return returnList;
		} catch (Exception e) {
			logger.error(LdsErrorCode.LDS_QUERY_ITEMINFO_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_QUERY_ITEMINFO_ERROR);
		}
	}

	@Transactional(rollbackFor = LdsBaseException.class)
	@Override
	public DrawHist drawLottery(String userId, String gameId) throws LdsBaseException {
	
		TicketInfo ticketInfo = null;
		//for ticket shared
		//G02使用G01的抽獎券
		if(GameId.POKE.getValue().equals(gameId)) {
			ticketInfo = ticketInfoRepository.findByUserIdAndGameId(userId, GameId.RABBIT.getValue());
	
		//for general
		//遊戲各自使用各自的抽獎券
		} else {
			ticketInfo = ticketInfoRepository.findByUserIdAndGameId(userId, gameId);
		}
		
		GameInfo gameInfo = gameInfoRepository.findByGameId(gameId);
		
		//檢查會員票券是否大於抽獎所需的券數
		if(ticketInfo != null && ticketInfo.getTicketNum() >= gameInfo.getTicketRequired()) {
			
			//先消耗掉抽獎券
			//for ticket shared
			//G02消耗G01的抽獎券
			if(GameId.POKE.getValue().equals(gameId)) {
				this.consumeTicket(userId, GameId.RABBIT.getValue(), gameInfo.getTicketRequired());	
				
			} else {
				//for general
				this.consumeTicket(userId, gameId, gameInfo.getTicketRequired());
			}
			
			//取得所有剩餘的項目(含獎品和銘謝惠顧)
			List<GameItemInfo> itemInfos = gameItemInfoRepository.findByGameIdOrderByItemId(gameId);
			
			//有無中獎並取得獎品編號
			String itemId = this.getDrawResult(itemInfos);
			GameItemInfo gameItemInfo = gameItemInfoRepository.findByItemIdAndGameId(itemId, gameId);			
			
			//更新GAME_ITEM_INFO
			this.updateGameItemInfoTable(gameItemInfo);
			
			DrawHist drawHist = null;	
			//item不為THANK表示有中獎
			drawHist = this.insertDrawHist(userId, gameId, !(DrawOutcome.THANK.getValue().equals(itemId)), itemId);
			
			return drawHist;
			
		//會員票券小於抽獎所需券數拋出例外
		} else {
			logger.error(LdsErrorCode.LDS_TICKET_NOT_ENOUGH_ERROR.getMessage());
			throw new LdsBaseException(LdsErrorCode.LDS_TICKET_NOT_ENOUGH_ERROR);
		}
	}

	//消耗抽獎券
	private void consumeTicket(String userId, String gameId, int consume) throws LdsBaseException {
		try {
			ticketInfoRepository.subtractTicketNum(userId, gameId, consume, CreateId.SYSTEM.getValue(), new Date());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LdsErrorCode.LDS_SUBTRACT_TICKET_IN_TICKET_INFO_TABLE_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_SUBTRACT_TICKET_IN_TICKET_INFO_TABLE_ERROR);
		}	
	}

	//更新GAME_ITEM_INFO
	private void updateGameItemInfoTable(GameItemInfo gameItemInfo) throws LdsBaseException {
		try {
			gameItemInfo.setRemainAmount(gameItemInfo.getRemainAmount() - 1);
			gameItemInfo.setUpdateId(CreateId.SYSTEM.getValue());
			gameItemInfo.setUpdateTime(new Date());
			
			gameItemInfoRepository.save(gameItemInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LdsErrorCode.LDS_UPDATE_TABLE_METHOD_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_UPDATE_TABLE_METHOD_ERROR);
		}
	}

	//將抽獎紀錄寫入USR_DRAW_HIST
	private DrawHist insertDrawHist(String userId, String gameId, boolean isWin, String itemId) throws LdsBaseException {
		try {
			DrawHist drawHist = new DrawHist();
			drawHist.setUserId(userId);
			drawHist.setGameId(gameId);
			drawHist.setIsWin(isWin == true ? IsWin.WIN.getValue() : IsWin.NOTWIN.getValue());
			drawHist.setItemId(itemId);
			drawHist.setCreateId(CreateId.SYSTEM.getValue());
			drawHistRepository.save(drawHist);
			return drawHist;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(LdsErrorCode.LDS_INSERT_USR_DRAW_HIST_TABLE_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_INSERT_USR_DRAW_HIST_TABLE_ERROR);
		}
	}

	//抽獎邏輯
	private String getDrawResult(List<GameItemInfo> itemInfos) {
		
		//所有獎項總合
		int sumOfRewards = 0;
		//所有銘謝惠顧總和
		int sumOfThanks = 0;
		for(GameItemInfo itemInfo: itemInfos) {
			if(itemInfo.getItemId().equals(DrawOutcome.THANK.getValue())) {
				sumOfThanks = itemInfo.getRemainAmount();
				continue;
			}
			sumOfRewards += itemInfo.getRemainAmount();
		}
		
		//若銘謝惠顧被抽完就永遠不會中獎(安全機制)
		if(sumOfThanks == 0) {
			return DrawOutcome.THANK.getValue();
		}
		
		//抽出一個亂數從1開始到總券數
		int rand = (int) (Math.random() * (sumOfRewards + sumOfThanks)) + 1;
		logger.info("--random number--" + rand);
		
		//抽出結果(有中獎或沒中獎)
		int count = 0;
		for(GameItemInfo itemInfo: itemInfos) {
			if(rand <= itemInfo.getRemainAmount() + count) {
				//回傳項目
				return itemInfo.getItemId();
			}
			count += itemInfo.getRemainAmount();
		}
		
		//若沒有執行結果表示所有項目均被抽光, 則預設永遠不會中獎
		return DrawOutcome.THANK.getValue();
	}

	@Override
	public GameInfo findRequiredTickets(String gameId) throws LdsBaseException {
		GameInfo entity = null;
		try {
			entity = gameInfoRepository.findByGameId(gameId);
			return entity;
		} catch (Exception e) {
			logger.error(LdsErrorCode.LDS_QUERY_GAME_INFO_TABLE_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_QUERY_GAME_INFO_TABLE_ERROR);
		}
	}

	@Override
	public TicketInfo findTicketsByUserIdAndGameId(String userId, String gameId) throws LdsBaseException {
		TicketInfo ticket = new TicketInfo();
		try {
			
			//G02顯示G01的票券
			if(GameId.POKE.getValue().equals(gameId)) {
				ticket = ticketInfoRepository.findByUserIdAndGameId(userId, GameId.RABBIT.getValue());
				ticket.setGameId(GameId.POKE.getValue());
				
			//for general
			} else {
				ticket = ticketInfoRepository.findByUserIdAndGameId(userId, gameId);
			}
			
			return ticket;
		} catch (Exception e) {
			logger.error(LdsErrorCode.LDS_QUERY_TICKETS_BY_USERID_AND_GAMEID_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_QUERY_TICKETS_BY_USERID_AND_GAMEID_ERROR);
		}
	}

	@Override
	public ItemInfo findByItemId(String itemId) throws LdsBaseException {
		ItemInfo itemInfo = new ItemInfo();
		try {
			itemInfo = itemInfoRepository.findByItemId(itemId);
			return itemInfo;			
		} catch (Exception e) {
			logger.error(LdsErrorCode.LDS_QUERY_ITEM_INFO_TABLE_ERROR.getErrorCode(), e);
			throw new LdsBaseException(LdsErrorCode.LDS_QUERY_ITEM_INFO_TABLE_ERROR);
		}
	}
	
}
