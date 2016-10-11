package com.ttnd.linksharing.events.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.ttnd.linksharing.events.ResetPassEvent;


@Component
public class ResetPassPublisher {
	@Autowired
	private ApplicationEventPublisher publisher;
	
	

	public void publish(String email) {
		//System.out.println("saveUser");
		this.publisher.publishEvent(new ResetPassEvent(this, email));
	}



	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		//System.out.println("setter");
		publisher = applicationEventPublisher;
	}
}
