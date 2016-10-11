package com.ttnd.linksharing.events.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ttnd.linksharing.events.RegistrationEvent;

@Component
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent>{

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
	public void onApplicationEvent(RegistrationEvent arg0) {
		//System.out.println("EvenListener");
		this.generateVerificationCode(arg0.getName(), arg0.getEmail());
		
	}
	public String generateVerificationCode(String name,String emailId) {
		/*
		 * For Thread Safety Copy of oject is used
		 */
		//System.out.println("Sending MAIL");
		SimpleMailMessage msg=new SimpleMailMessage(this.templateMessage);
		templateMessage.setSubject("Email verification!");
		msg.setTo(emailId);
		msg.setSubject("Topic subscription invitation.");
		msg.setText("Hi "+name+" \n"
				+ "Click the below link to verify your email account and activate you linksharing account \n"
				+ "http://www.localhost:8080/user/validate/"+name);
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