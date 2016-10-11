package com.ttnd.linksharing.events;

import org.springframework.context.ApplicationEvent;

public class ResetPassEvent extends ApplicationEvent{
	
	public ResetPassEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -6998631755436451759L;
	
	private String email;

	  

	
	public ResetPassEvent(Object source, String email) {
		super(source);
		//this.name = name;
		this.email = email;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
