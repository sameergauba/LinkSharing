package com.ttnd.linksharing.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;

@Entity
@Table(name="resource")
@Inheritance(strategy=InheritanceType.JOINED) 
public class Resource implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1291015801553277525L;
	
	@Id
	@GeneratedValue
	@Column(name="resources_id")
	private Long id;
	@Column(name="resource_description")
	private String description;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User createdBy;
	@Column(name="resource_date_created")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateCreated;
	@Column(name="resource_last_updated")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date lastUpdated;

	
	
	@ManyToOne
	@JoinColumn(name="topic_id")
	Topic topic;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Resource(String description, User createdBy, Date dateCreated, Date lastUpdated) {
		super();
		this.description = description;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}
	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Resource(String description, User createdBy, Topic topic) {
		super();
		this.description = description;
		this.createdBy = createdBy;
		this.topic = topic;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
