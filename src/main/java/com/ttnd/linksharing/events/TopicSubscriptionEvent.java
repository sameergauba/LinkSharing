package com.ttnd.linksharing.events;

import org.springframework.context.ApplicationEvent;

public class TopicSubscriptionEvent extends ApplicationEvent{
	
	private static final long serialVersionUID = -6998631755436451759L;
	private Long topicId;
	private String email;
	private String userName;
	private String topicName;
	
	



	public TopicSubscriptionEvent(Object source, Long topicId, String email, String userName, String topicName) {
		super(source);
		this.topicId = topicId;
		this.email = email;
		this.userName = userName;
		this.topicName = topicName;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getTopicName() {
		return topicName;
	}



	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	

	

	public TopicSubscriptionEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}



	



	public Long getTopicId() {
		return topicId;
	}



	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
