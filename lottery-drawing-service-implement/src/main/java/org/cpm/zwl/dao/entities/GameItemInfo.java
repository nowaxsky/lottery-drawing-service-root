package org.cpm.zwl.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="T_WL_GAME_ITEM_INFO")
@IdClass(GameItemInfoId.class)
public class GameItemInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8834533493843304507L;

	@Column(name="GAME_ITEM_ID")
	private String gameItemId;
	
	@Id
	@Column(name="ITEM_ID")
	private String itemId;
	
	@Column(name="ITEM_NAME")
	private String itemName;
	
	@Id
	@Column(name="GAME_ID")
	private String gameId;
	
	@Column(name="REMAIN_AMOUNT")
	private int remainAmount;
	
	@Column(name="GRAPH_URL")
	private String graphUrl;
	
	@Column(name="CREATE_ID", updatable=false)
	private String createId;
	
	@CreationTimestamp
	@Column(name="CREATE_TIME", updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(name="UPDATE_ID")
	private String updateId;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Version
	@Column(name="VERSION")
	private Long version;

	public String getGameItemId() {
		return gameItemId;
	}

	public void setGameItemId(String gameItemId) {
		this.gameItemId = gameItemId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public int getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(int remainAmount) {
		this.remainAmount = remainAmount;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGraphUrl() {
		return graphUrl;
	}

	public void setGraphUrl(String graphUrl) {
		this.graphUrl = graphUrl;
	}
	
}
