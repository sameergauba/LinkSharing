package com.ttnd.linksharing.entities;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;

import com.ttnd.linksharing.entities.pks.TopicUserId;
import com.ttnd.linksharing.utils.Seriousness;

@Entity
@Table(name = "subscription")
/*@AssociationOverrides({
		@AssociationOverride(name = "pk.topic", 
			joinColumns = @JoinColumn(name = "topic_id")),
		@AssociationOverride(name = "pk.user", 
			joinColumns = @JoinColumn(name = "user_id")) })*/
public class Subscription implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2010038687569901290L;
	
	/*@EmbeddedId
	private TopicUserId pk;*/
	
	@Id
	@GeneratedValue
	@Column(name="subscription_id")
	private Long id;
	@Column(name="subscription_seriousness")
	@Enumerated(EnumType.STRING)
	private Seriousness seriousness;
	@Column(name="subscription_date_created")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateCreated;
	
	@ManyToOne(fetch = FetchType.EAGER , targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne(fetch = FetchType.EAGER , targetEntity=Topic.class)
	@JoinColumn(name="topic_id")
	private Topic topic;

	public Subscription() {
	}
	
	

	/*public Subscription(TopicUserId pk, Seriousness seriousness, Date dateCreated) {
		super();
		this.pk = pk;
		this.seriousness = seriousness;
		this.dateCreated = dateCreated;
	}


	

	public Subscription(TopicUserId pk, Seriousness seriousness) {
		super();
		this.pk = pk;
		this.seriousness = seriousness;
	}



	@EmbeddedId
	public TopicUserId getPk() {
		return pk;
	}

	public void setPk(TopicUserId pk) {
		this.pk = pk;
	}

	@Transient
	public Topic getTopic() {
		return getPk().getTopic();
	}

	public void setTopic(Topic topic) {
		getPk().setTopic(topic);
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}*/
	
	

	

	

	public Long getId() {
		return id;
	}



	@Override
	public String toString() {
		return "Subscription [id=" + id + ", seriousness=" + seriousness + ", dateCreated=" + dateCreated + ", user="
				+ user + ", topic=" + topic + "]";
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Seriousness getSeriousness() {
		return seriousness;
	}

	public Subscription(Seriousness seriousness, User user, Topic topic) {
		super();
		this.seriousness = seriousness;
		this.user = user;
		this.topic = topic;
	}



	public Subscription(Seriousness seriousness, Date dateCreated, User user, Topic topic) {
		super();
		this.seriousness = seriousness;
		this.dateCreated = dateCreated;
		this.user = user;
		this.topic = topic;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Topic getTopic() {
		return topic;
	}



	public void setTopic(Topic topic) {
		this.topic = topic;
	}



	public void setSeriousness(Seriousness seriousness) {
		this.seriousness = seriousness;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	
}
