package com.ttnd.linksharing.events.publishers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import com.ttnd.linksharing.events.RegistrationEvent;


@Component
public class RegistrationPublisher implements ApplicationEventPublisherAware {


	@Autowired
		private ApplicationEventPublisher publisher;
		
		

		public void publish(String name,String email) {
			//System.out.println("saveUser");
			this.publisher.publishEvent(new RegistrationEvent(this, name, email));
		}



		public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
			//System.out.println("setter");
			publisher = applicationEventPublisher;
		}
	
}
