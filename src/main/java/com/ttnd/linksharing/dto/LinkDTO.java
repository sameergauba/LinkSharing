package com.ttnd.linksharing.dto;

import java.util.Date;
import java.util.Map;

import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;

public class LinkDTO {
	
	private Long id;
	private String url;
	private String description;
	private User createdBy;
	private Date dateCreated;
	private Date lastUpdated;
	Long topicId;
	Topic topic;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	

	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	@Override
	public String toString() {
		return "LinkDTO [url=" + url + ", description=" + description + ", createdBy=" + createdBy + ", dateCreated="
				+ dateCreated + ", lastUpdated=" + lastUpdated + ", topic=" + topicId + "]";
	}
	public LinkDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LinkDTO(String url, String description, User createdBy, Topic topic) {
		super();
		this.url = url;
		this.description = description;
		this.createdBy = createdBy;
		this.topic = topic;
	}

	
	
	

}
