package com.ttnd.linksharing.events.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.ttnd.linksharing.events.RegistrationEvent;
import com.ttnd.linksharing.events.TopicSubscriptionEvent;

@Component
public class TopicSubscriptionPublisher {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	

	public void publish(Long topicId,String email, String username, String topicName) {
		//System.out.println("saveUser");
		this.publisher.publishEvent(new TopicSubscriptionEvent(this,topicId, email, username, topicName));
	}



	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		//System.out.println("setter");
		publisher = applicationEventPublisher;
	}

}
