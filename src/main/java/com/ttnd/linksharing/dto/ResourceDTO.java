package com.ttnd.linksharing.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;

public class ResourceDTO {
	
	private Long id;
	private String description;
	private User createdBy;
	private Date dateCreated;
	private Date lastUpdated;
	private Topic topic;
	private String type;
	private String path;
	private Integer rating;
	
	
	
	
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public ResourceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "ResourceDTO [id=" + id + ", description=" + description + ", createdBy=" + createdBy + ", dateCreated="
				+ dateCreated + ", lastUpdated=" + lastUpdated + ", topic=" + topic + ", type=" + type + ", path="
				+ path + ", rating=" + rating + "]";
	}
	public ResourceDTO(String description, User createdBy) {
		super();
		this.description = description;
		this.createdBy = createdBy;
	}
	public ResourceDTO(Long id, String description, User createdBy, Topic topic, String path) {
		super();
		this.id = id;
		this.description = description;
		this.createdBy = createdBy;
		this.topic = topic;
		this.path = path;
	}
	
	
	

}
