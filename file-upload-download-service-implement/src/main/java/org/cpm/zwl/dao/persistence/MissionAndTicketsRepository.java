package org.cpm.zwl.dao.persistence;

import java.util.List;

import org.cpm.zwl.dao.entities.MissionAndTickets;
import org.cpm.zwl.dao.entities.MissionAndTicketsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionAndTicketsRepository extends JpaRepository<MissionAndTickets, MissionAndTicketsId>{
	
	@Query("select new MissionAndTickets(M.userId, M.gameId, M.missionId, sum(M.ticketEarnedNum)) from MissionHist M " 
			+ "where M.userId = :userId and M.gameId = :gameId and M.finishedTime between trunc(sysdate(),'MONTH') and current_date "
			+ "group by M.userId, M.gameId, M.missionId "
			+ "order by M.missionId")	
	public List<MissionAndTickets> findCycleMissionByUserIdAndGameIdOrderByMissionId(@Param(value="userId") String userId, @Param(value="gameId") String gameId);
	
	
	@Query("select new MissionAndTickets(M.userId, M.gameId, M.missionId, sum(M.ticketEarnedNum)) from MissionHist M " 
			+ "where M.userId = :userId and M.gameId = :gameId "
			+ "group by M.userId, M.gameId, M.missionId "
			+ "order by M.missionId")	
	public List<MissionAndTickets> findNotCycleMissionByUserIdAndGameIdOrderByMissionId(@Param(value="userId") String userId, @Param(value="gameId") String gameId);

	
	@Query("select new MissionAndTickets(M.userId, M.gameId, M.missionId, sum(M.ticketEarnedNum)) from MissionHist M " 
			+ "where M.userId = :userId and M.gameId = :gameId and M.missionId = :missionId and M.finishedTime between trunc(sysdate(),'MONTH') and current_date "
			+ "group by M.userId, M.gameId, M.missionId")	
	public MissionAndTickets findTicketsCycle(@Param(value="userId") String userId, @Param(value="gameId") String gameId, @Param(value="missionId") String missionId);
	
	
	@Query("select new MissionAndTickets(M.userId, M.gameId, M.missionId, sum(M.ticketEarnedNum)) from MissionHist M " 
			+ "where M.userId = :userId and M.gameId = :gameId and M.missionId = :missionId "
			+ "group by M.userId, M.gameId, M.missionId")	
	public MissionAndTickets findTicketsNotCycle(@Param(value="userId") String userId, @Param(value="gameId") String gameId, @Param(value="missionId") String missionId);

}
