package org.cpm.zwl.exception;

import org.cpm.zwl.commons.constrants.ErrorCodeInterface;
import org.cpm.zwl.commons.exceptions.ZwlBaseException;

public class LdsBaseException extends ZwlBaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9013397480314253100L;

	public LdsBaseException(ErrorCodeInterface errorCode) {
		super(errorCode);
	}
	
	public LdsBaseException(ErrorCodeInterface errorCode,String message){
		super(errorCode.getErrorCode(),message);
		this.errorCode = errorCode.getErrorCode();
	}

}
