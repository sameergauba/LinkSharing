package com.ttnd.linksharing.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Required;

import com.ttnd.linksharing.entities.Resource;
import com.ttnd.linksharing.entities.ResourceRating;
import com.ttnd.linksharing.entities.Subscription;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.validations.Unique;
import javax.validation.constraints.NotNull;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	Long id;
	private static final long serialVersionUID = 5534679990572330712L;
	@Unique(entity = User.class, message="This email already exists.", property="email")
	@NotNull(message="email should not be empty")
	private String email;
	@Unique(entity = User.class, message="This username already exists.", property="userName")
	@NotNull(message="Username should not be empty")
	private String userName;
	@NotNull(message="password should not be empty")
	private String password;
	@NotNull(message="Please confirm the password")
	private String conPass;
	@NotNull(message="Firstname should not be empty")
	private String firstName;
	@NotNull(message="Lastname should not be empty")
	private String lastName;
	/*private Byte[] photo;*/
	private Boolean admin;
	private Boolean active;
	private Date dateCreated;
	private Date lastUpdated;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	Set<Subscription> subscriptions = new HashSet<Subscription>();
	
	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	@OneToMany(cascade=CascadeType.ALL)
	Set<ResourceRating> resourcesRated = new HashSet<ResourceRating>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="createdBy")
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
	/*public Byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}*/
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
	
	
	public String getConPass() {
		return conPass;
	}
	public void setConPass(String conPass) {
		this.conPass = conPass;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", userName=" + userName
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName  + ", admin=" + admin + ", active="
				+ active + ", dateCreated=" + dateCreated + ", lastUpdated="
				+ lastUpdated + "]";
	}
	public UserDTO(String email, String userName, String password,
			String firstName, String lastName, Byte[] photo, Boolean admin,
			Boolean active, Date dateCreated, Date lastUpdated) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admin = admin;
		this.active = active;
		this.dateCreated = dateCreated;
		this.lastUpdated = lastUpdated;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
