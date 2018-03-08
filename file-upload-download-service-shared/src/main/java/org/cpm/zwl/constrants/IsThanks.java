package org.cpm.zwl.constrants;

public enum IsThanks {

	INCLUDE_THANKS("Y"),
	EXCLUDE_THANKS("N");
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private IsThanks(String value) {
		this.value = value;
	}
	
}
