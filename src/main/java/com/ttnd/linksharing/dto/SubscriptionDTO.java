package com.ttnd.linksharing.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;

import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.entities.pks.TopicUserId;
import com.ttnd.linksharing.utils.Seriousness;

public class SubscriptionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1387996077021696543L;
	//private TopicUserId pk = new TopicUserId();
	private Seriousness seriousness;
	private Date dateCreated;
	private Integer subsSize;
	private Integer postsSize;
	private User user;
	private Topic topic;
	private Long id;
	
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SubscriptionDTO [seriousness=" + seriousness + ", dateCreated=" + dateCreated + ", subsSize=" + subsSize
				+ ", postsSize=" + postsSize + ", user=" + user + ", topic=" + topic + "]";
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
	
	public Seriousness getSeriousness() {
		return seriousness;
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
	
	public SubscriptionDTO(Seriousness seriousness, Date dateCreated, Integer subsSize, Integer postsSize, User user,
			Topic topic) {
		super();
		this.seriousness = seriousness;
		this.dateCreated = dateCreated;
		this.subsSize = subsSize;
		this.postsSize = postsSize;
		this.user = user;
		this.topic = topic;
	}
	
	public SubscriptionDTO(Seriousness seriousness, Date dateCreated, User user, Topic topic) {
		super();
		this.seriousness = seriousness;
		this.dateCreated = dateCreated;
		this.user = user;
		this.topic = topic;
	}
	public SubscriptionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
