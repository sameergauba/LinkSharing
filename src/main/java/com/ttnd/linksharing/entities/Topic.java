package com.ttnd.linksharing.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.time.DateTime;

import com.ttnd.linksharing.utils.Visibility;

@Entity
@Table(name="topic")
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7259871604498615469L;
	
	@Id
	@GeneratedValue
	@Column(name="topic_id")
	private Long id;
	@Column(name="topic_name")
	private String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="topic_created_by")
	private User createdBy;
	
	@Column(name="topic_date_created")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateCreated;
	@Column(name="topic_last_updated")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date lastUpdated;
	@Column(name="topic_visibility")
	private Visibility visibility;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	Set<Resource> resources = new HashSet<Resource>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="topic")
	Set<Subscription> subscriptions = new HashSet<Subscription>();
	
	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public Topic()
	{
		
	}
	
	
	public Topic(Long id, String name, User createdBy, Date dateCreated, Date lastUpdated, Visibility visibility) {
		super();
		this.id = id;
		this.name = name;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
		this.visibility = visibility;
	}
	@Column(name="name")
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
	
	
	
	public Set<Resource> getResources() {
		return resources;
	}
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Enumerated(EnumType.STRING)
	public Visibility getVisibility() {
		return visibility;
	}
	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}
	public Topic(String name, User createdBy, Visibility visibility) {
		super();
		this.name = name;
		this.createdBy = createdBy;
		this.visibility = visibility;
	}
	public Topic(String name, Visibility visibility) {
		super();
		this.name = name;
		this.visibility = visibility;
	}
	
	
	/*@PrePersist
	  protected void onCreate() {
	    dateCreated = new Date();
	  }

	  @PreUpdate
	  protected void onUpdate() {
	    lastUpdated = new Date();
	  }*/
	
	
}
