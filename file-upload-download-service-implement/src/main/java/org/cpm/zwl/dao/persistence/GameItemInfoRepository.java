package org.cpm.zwl.dao.persistence;

import java.util.List;

import org.cpm.zwl.dao.entities.GameItemInfo;
import org.cpm.zwl.dao.entities.GameItemInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameItemInfoRepository extends JpaRepository<GameItemInfo, GameItemInfoId> {

	public List<GameItemInfo> findByGameId(String gameId);
	
	public GameItemInfo findByItemIdAndGameId(String itemId, String gameId);
	
	public List<GameItemInfo> findByGameIdOrderByItemId(String gameId);
	
//	@Modifying
//	//@Transactional
//	@Query("update GameItemInfo g set g.remainAmount = g.remainAmount - 1, g.updateId = :updateId, g.updateTime = :updateTime, g.version = g.version + 1 where g.gameId = :gameId and g.itemId = :itemId")
//	public int updateByGameIdAndItemId(@Param(value="gameId") String gameId, @Param(value="itemId") String itemId, 
//									   @Param(value="updateId") String updateId, @Param(value="updateTime") Date updateTime);
}
