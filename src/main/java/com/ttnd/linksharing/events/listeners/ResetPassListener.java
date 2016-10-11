package com.ttnd.linksharing.events.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ttnd.linksharing.events.RegistrationEvent;
import com.ttnd.linksharing.events.ResetPassEvent;

@Component
public class ResetPassListener implements ApplicationListener<ResetPassEvent>{
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
	
	
	public String generateReset(String emailId) {
		/*
		 * For Thread Safety Copy of oject is used
		 */
		//System.out.println("Sending MAIL");
		SimpleMailMessage msg=new SimpleMailMessage(this.templateMessage);
		templateMessage.setSubject("Reset Password!");
		msg.setTo(emailId);
		msg.setSubject("Topic subscription invitation.");
		msg.setText("Hi  \n"
				+ "Click the below link to reset your password. \n"
				+ "http://localhost:8080/user/reset/"+emailId);
		try{
			//System.out.println(msg);
			this.mailSender.send(msg);
		}
		catch(MailException e){
			System.err.println(e.getMessage());
		}
		return "hello world";
	}
	@Override
	public void onApplicationEvent(ResetPassEvent arg0) {
	  this.generateReset( arg0.getEmail());
	}
	
}
