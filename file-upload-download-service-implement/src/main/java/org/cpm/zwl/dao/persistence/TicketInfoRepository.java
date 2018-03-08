package org.cpm.zwl.dao.persistence;

import java.util.Date;
import java.util.List;

import org.cpm.zwl.dao.entities.TicketInfo;
import org.cpm.zwl.dao.entities.TicketInfoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketInfoRepository extends JpaRepository<TicketInfo,TicketInfoId>{

	public List<TicketInfo> findByUserIdOrderByGameId(String userId);
	
	public TicketInfo findByUserIdAndGameId(String userId, String gameId);
		
	@Modifying
	//@Transactional
	@Query("update TicketInfo t set t.ticketNum = t.ticketNum + :addTicket, t.updateId = :updateId, t.updateTime = :updateTime, t.version = t.version + 1 where t.userId = :userId and t.gameId = :gameId")
	public int addTicketNum(@Param(value="userId") String userId, @Param(value="gameId") String gameId, 
							@Param(value="addTicket") int addTicket, @Param(value="updateId") String updateId,
							@Param(value="updateTime") Date updateTime);
	
	@Modifying
	//@Transactional
	@Query("update TicketInfo t set t.ticketNum = t.ticketNum - :subtractTicket, t.updateId = :updateId, t.updateTime = :updateTime, t.version = t.version + 1 where t.userId = :userId and t.gameId = :gameId")
	public int subtractTicketNum(@Param(value="userId") String userId, @Param(value="gameId") String gameId, 
							@Param(value="subtractTicket") int subtractTicket, @Param(value="updateId") String updateId,
							@Param(value="updateTime") Date updateTime);
	
}
