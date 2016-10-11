package com.ttnd.linksharing.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.utils.Visibility;
import com.ttnd.linksharing.validations.Unique;

public class TopicDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3139277200663110589L;
	private Long id;
	/*@Unique(entity = Topic.class, message="This topic already exists.", property="name")*/
	private String name;
	private User createdBy;
	private Date dateCreated;
	private Date lastUpdated;
	private Visibility visibility;
	private Integer subsSize;
	private Integer postsSize;
	
	public Integer getSubsSize() {
		return subsSize;
	}
	public void setSubsSize(Integer subsSize) {
		this.subsSize = subsSize;
	}
	public Integer getPostsSize() {
		return postsSize;
	}
	public void setPostsSize(Integer postsSize) {
		this.postsSize = postsSize;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	@Override
	public String toString() {
		return "TopicDTO [id=" + id + ", name=" + name + ", createdBy=" + createdBy + ", dateCreated=" + dateCreated
				+ ", lastUpdated=" + lastUpdated + ", visibility=" + visibility + "]";
	}
	public TopicDTO(Long id, String name, User createdBy, Date dateCreated, Date lastUpdated,
			Visibility visibility) {
		super();
		this.id = id;
		this.name = name;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.visibility = visibility;
	}
	public TopicDTO() {
		super();
	}
	public TopicDTO(String name, Visibility visibility) {
		super();
		this.name = name;
		this.visibility = visibility;
	}
	public TopicDTO(String name, User createdBy, Visibility visibility) {
		super();
		this.name = name;
		this.createdBy = createdBy;
		this.visibility = visibility;
	}
	
	
	
	

}
