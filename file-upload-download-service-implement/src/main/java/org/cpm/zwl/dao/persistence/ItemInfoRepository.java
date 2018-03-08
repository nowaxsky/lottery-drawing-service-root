package org.cpm.zwl.dao.persistence;

import org.cpm.zwl.dao.entities.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemInfoRepository extends JpaRepository<ItemInfo, Long>{

	public ItemInfo findByItemId(String itemId);
}
