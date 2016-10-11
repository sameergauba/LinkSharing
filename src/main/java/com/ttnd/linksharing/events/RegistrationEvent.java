package com.ttnd.linksharing.events;

import org.springframework.context.ApplicationEvent;

public class RegistrationEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6998631755436451759L;
	private String name;
	private String email;

	  

	public RegistrationEvent(Object source) {
		super(source);
	}

	public RegistrationEvent(Object source, String name, String email) {
		super(source);
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
