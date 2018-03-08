package org.cpm.zwl.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="T_WL_USR_DRAW_HIST")
public class DrawHist implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9145644906113515508L;

	@Id
	@SequenceGenerator(name = "SEQ_NO_GEN", sequenceName = "T_WL_USR_DRAW_HIST_SEQID_SEQ",schema="DBMT")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NO_GEN")
	@Column(name="SEQ_NO")
	private int seqNo;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="GAME_ID")
	private String gameId;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name="DRAW_DATETIME")
	private Date drawTime;
	
	@Column(name="IS_WIN")
	private String isWin;
	
	@Column(name="ITEM_ID")
	private String itemId;

	@ColumnDefault(value = "SYSTEM")
	@Column(name="CREATE_ID")
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

	public int getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	public String getIsWin() {
		return isWin;
	}

	public void setIsWin(String isWin) {
		this.isWin = isWin;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	
}
