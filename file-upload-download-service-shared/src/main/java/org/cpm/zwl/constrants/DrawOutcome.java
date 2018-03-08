package org.cpm.zwl.constrants;

public enum DrawOutcome {

	THANK("ITK");	//沒中獎
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	private DrawOutcome(String value) {
		this.value = value;
	}
}
