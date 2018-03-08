package org.cpm.zwl.constrants;

public enum GameId {

	RABBIT("G01"),
	POKE("G02");
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private GameId(String value) {
		this.value = value;
	}
}
