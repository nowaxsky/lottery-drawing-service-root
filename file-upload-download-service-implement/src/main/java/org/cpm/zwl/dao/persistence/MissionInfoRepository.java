package org.cpm.zwl.dao.persistence;

import org.cpm.zwl.dao.entities.MissionInfo;
import org.cpm.zwl.dao.entities.MissionInfoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionInfoRepository extends JpaRepository<MissionInfo, MissionInfoId> {

	public MissionInfo findByGameIdAndMissionId(String gameId, String missionId);
}
