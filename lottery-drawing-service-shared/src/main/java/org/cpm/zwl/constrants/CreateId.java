package org.cpm.zwl.constrants;

/**
 * 
 * @author Chuck
 *
 */
public enum CreateId {

	SYSTEM("SYSTEM");//建立者
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	private CreateId (String value) {
		this.value = value;
	}
}
