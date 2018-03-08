package org.cpm.zwl.dao.persistence;

import java.util.List;

import org.cpm.zwl.dao.entities.MissionHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionHistRepository extends JpaRepository<MissionHist, Long>{

	public List<MissionHist> findByUserIdAndMissionId(String userId, String missionId);
}
