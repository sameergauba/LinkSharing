package com.ttnd.linksharing.dto;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;

public class DocumentDTO {
	private Long id;
	private String filePath;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	@Override
	public String toString() {
		return "DocumentDTO [filePath=" + filePath + ", description=" + description + ", createdBy=" + createdBy
				+ ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated + ", topic=" + topicId + "]";
	}
	

	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public DocumentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DocumentDTO(String filePath, String description, User createdBy, Topic topic) {
		super();
		this.filePath = filePath;
		this.description = description;
		this.createdBy = createdBy;
		this.topic = topic;
	}
	
	

}
