package org.cpm.zwl.constrants;

import org.cpm.zwl.commons.constrants.ErrorCodeInterface;

public enum LdsErrorCode implements ErrorCodeInterface{
	
	/**
	 * unknown
	 */
    LDS_UNKNOW_ERROR("LDS_UNKNOW_ERROR", "unknown error"),
    
    /**
     * error in querying table
     */
    LDS_QUERY_ERROR("LDS_QUERY_ERROR", "error in querying table"),
    
    /*
     * error in querying T_WL_USR_TICKET_INFO table
     */
    LDS_QUERY_TICKETS_ERROR("LDS_QUERY_TICKETS_ERROR", "error in querying T_WL_USR_TICKET_INFO table"),
    
    /*
     * error in querying T_WL_USR_MISSION_HIST table
     */
    LDS_QUERY_MISSION_TICKETS_ERROR("LDS_QUERY_MISSION_TICKETS_ERROR", "error in querying T_WL_USR_MISSION_HIST table"),
    
    /*
     * error in querying T_WL_ITEM_INFO table"
     */
    LDS_QUERY_ITEMINFO_ERROR("LDS_QUERY_ITEMINFO_ERROR", "error in querying T_WL_ITEM_INFO table"),
    
    /**
     * data cannot be inserted to the table
     */
    LDS_INSERT_TABLE_ERROR("LDS_INSERT_TABLE_ERROR", "data cannot be inserted to the table"),
    
    /**
     * draw lottery error
     */
    LDS_DRAW_LOTTERY_ERROR("LDS_DRAW_LOTTERY_ERROR", "draw lottery error"),
    
    /*
     * ticket is not enough to draw lottery
     */
    LDS_TICKET_NOT_ENOUGH_ERROR("LDS_TICKET_NOT_ENOUGH_ERROR", "ticket is not enough to draw lottery"),
    
    /*
     * data cannot be inserted into USR_DRAW_HIST table
     */
    LDS_INSERT_USR_DRAW_HIST_TABLE_ERROR("LDS_INSERT_USR_DRAW_HIST_TABLE_ERROR", "data cannot be inserted into USR_DRAW_HIST table"),
    
    /*
     * updateTable method failed
     */
    LDS_UPDATE_TABLE_METHOD_ERROR("LDS_UPDATE_TABLE_METHOD_ERROR", "updateTable method failed"),
    
    /*
     * subtractTicketNum error
     */
    LDS_SUBTRACT_TICKET_IN_TICKET_INFO_TABLE_ERROR("LDS_SUBTRACT_TICKET_IN_TICKET_INFO_TABLE_ERROR", "subtractTicketNum error"),
    
    /*
     * update user ticket info table error
     */
    LDS_UPDATE_USER_TICKET_INFO_TABLE_ERRPR("LDS_UPDATE_USER_TICKET_INFO_TABLE_ERRPR","update user ticket info table error"),
	
    /*
     * insert user ticket info table error
     */
    LDS_INSERT_USER_TICKET_INFO_TABLE_ERRPR("LDS_INSERT_USER_TICKET_INFO_TABLE_ERRPR","insert user ticket info table error"),
    
    /*
     * query game info table error
     */
    LDS_QUERY_GAME_INFO_TABLE_ERROR("LDS_QUERY_GAME_INFO_TABLE_ERROR", "query game info table error"),
    
    /*
     * query tickets by userId and gameId error
     */
    LDS_QUERY_TICKETS_BY_USERID_AND_GAMEID_ERROR("LDS_QUERY_TICKETS_BY_USERID_AND_GAMEID_ERROR", "query tickets by userId and gameId error"),
    
    /*
     * query item info table error
     */
    LDS_QUERY_ITEM_INFO_TABLE_ERROR("LDS_QUERY_ITEM_INFO_TABLE_ERROR", "query item info table error")
	;
	
	private String errorCode;
	private String message;
	
	LdsErrorCode(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

	@Override
	public String getErrorCode() {
		return this.errorCode;
	}
	
	@Override
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
		
	}


}
