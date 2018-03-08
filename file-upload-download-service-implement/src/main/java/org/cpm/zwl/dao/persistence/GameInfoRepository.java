package org.cpm.zwl.dao.persistence;

import org.cpm.zwl.dao.entities.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoRepository extends JpaRepository<GameInfo, Long>{

	public GameInfo findByGameId(String gameId);
}
