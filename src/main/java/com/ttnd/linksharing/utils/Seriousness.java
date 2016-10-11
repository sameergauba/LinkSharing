package com.ttnd.linksharing.utils;

public enum Seriousness {
	
	 VerySerious("Very Serious"), Serious("Serious"), Casual("casual");
	
	private String value;
	
	private Seriousness(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
