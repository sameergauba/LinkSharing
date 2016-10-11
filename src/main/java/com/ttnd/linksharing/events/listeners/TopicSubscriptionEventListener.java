package com.ttnd.linksharing.events.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ttnd.linksharing.events.RegistrationEvent;
import com.ttnd.linksharing.events.TopicSubscriptionEvent;

@Component
public class TopicSubscriptionEventListener implements ApplicationListener<TopicSubscriptionEvent>{
	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage templateMessage;
	
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}
	
	public SimpleMailMessage getTemplateMessage() {
		return templateMessage;
	}
	public void onApplicationEvent(TopicSubscriptionEvent arg0) {
		//System.out.println("EvenListener");
		this.generateSubscriptionCode(arg0.getTopicId(), arg0.getEmail(), arg0.getUserName(), arg0.getTopicName());
		
	}
	public String generateSubscriptionCode(Long topicId,String emailId, String username, String topicName) {
		/*
		 * For Thread Safety Copy of oject is used
		 */
		//System.out.println("Sending MAIL");
		SimpleMailMessage msg=new SimpleMailMessage(this.templateMessage);
         templateMessage.setSubject("Topic subscription invitation");
		msg.setTo(emailId);
		msg.setText("Hi!! \n"
				+ "You have been invited by "+username+" to subscribe to topic : " +topicName+" \n"
				+ "Click the below link to subscribe to the topic \n"
				+ "http://localhost:8080/subscription/subscribe/"+topicId);
		try{
			//System.out.println(msg);
			
			this.mailSender.send(msg);
			
		}
		catch(MailException e){
			System.err.println(e.getMessage());
		}
		return "hello world";
	}

}
