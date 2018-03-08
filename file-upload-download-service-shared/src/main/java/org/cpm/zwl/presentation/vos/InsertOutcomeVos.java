package org.cpm.zwl.presentation.vos;

public class InsertOutcomeVos {
	
	/*
	 * 加入表格是否成功
	 */
	private boolean isSuccess;
	
	/*
	 * 回傳訊息
	 */
	private String message;
	
	public InsertOutcomeVos() {
		super();
	}

	public InsertOutcomeVos(boolean isSuccess, String message) {
		super();
		this.isSuccess = isSuccess;
		this.message = message;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
