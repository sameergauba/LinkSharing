package com.ttnd.linksharing.utils;

public enum Visibility {
	Public("Public"),
	Private("Private");
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private Visibility(String value) {
		this.value = value;
	}
	
	
	
}
