package org.cpm.zwl.constrants;

public enum MissionType {

	CYCLE_MISSION("cycle mission"),
	NOT_CYCLE_MISSION("not cycle mission");
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private MissionType(String value) {
		this.value = value;
	}
	
}
