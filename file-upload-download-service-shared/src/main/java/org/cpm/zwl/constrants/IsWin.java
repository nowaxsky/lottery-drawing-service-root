package org.cpm.zwl.constrants;

/**
 * 
 * @author Chuck
 *
 */
public enum IsWin {

	WIN("Y"),//有中獎
	NOTWIN("N");//沒中獎
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	private IsWin(String value) {
		this.value = value;
	}
}
