package com.ttnd.linksharing.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.jboss.logging.MessageBundle;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.ttnd.linksharing.events.publishers.RegistrationPublisher;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6704528677941537459L;
	@Id
	@GeneratedValue
	@Column(name = "user_id", unique = true)
	private Long id;
	@Column(name = "user_email", unique = true)
	private String email;
	@Column(name = "user_name", unique = true)
	private String userName;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_firstname")
	private String firstName;
	@Column(name = "user_lastname")
	private String lastName;
	@Column(name = "user_photo", columnDefinition="mediumblob")
	@Lob
	private Byte[] photo;
	@Column(name = "user_admin")
	private Boolean admin;
	@Column(name = "user_active")
	private Boolean active;
	@Column(name = "user_date_created")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateCreated;
	@Column(name = "user_last_updated")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date lastUpdated;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy", fetch=FetchType.EAGER)
	/*
	 * @JoinTable(name="user_topics",joinColumns=@JoinColumn(name="user_id"),
	 * inverseJoinColumns=@JoinColumn(name="topic_id"))
	 */
	Set<Topic> topics = new HashSet<Topic>();
	
	

	/*
	 * @ManyToMany(cascade=CascadeType.ALL)
	 * 
	 * @JoinTable(name="user_subscription",joinColumns=@JoinColumn(name=
	 * "user_id"),inverseJoinColumns=@JoinColumn(name="subscription_id"))
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	Set<Subscription> subscriptions = new HashSet<Subscription>();

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Set<ResourceRating> getResourcesRated() {
		return resourcesRated;
	}

	public void setResourcesRated(Set<ResourceRating> resourcesRated) {
		this.resourcesRated = resourcesRated;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy="pk.user")
	Set<ResourceRating> resourcesRated = new HashSet<ResourceRating>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
	Set<Resource> resources = new HashSet<Resource>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	@Override
	public String toString() {
		return "User [email=" + email + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", photo=" + Arrays.toString(photo) + ", admin=" + admin + ", active="
				+ active + ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated + "]";
	}

	public User(String email, String userName, String password, String firstName, String lastName, Byte[] photo,
			Boolean admin, Boolean active, Date dateCreated, Date lastUpdated) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.photo = photo;
		this.admin = admin;
		this.active = active;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
