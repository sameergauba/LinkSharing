package com.ttnd.linksharing.entities;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ttnd.linksharing.entities.pks.ResourceUserId;
import com.ttnd.linksharing.entities.pks.TopicUserId;

@Entity
@Table(name="reading_item")
@AssociationOverrides({
	@AssociationOverride(name = "pk.resource", 
		joinColumns = @JoinColumn(name = "resource_id")),
	@AssociationOverride(name = "pk.user", 
		joinColumns = @JoinColumn(name = "user_id")) })
public class ReadingItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7154197500455685917L;
	
	private ResourceUserId pk = new ResourceUserId();
	
	@Column(name="reading_item_isread")
	private Boolean isRead;
	
	
	@EmbeddedId
	public ResourceUserId getPk() {
		return pk;
	}

	public void setPk(ResourceUserId pk) {
		this.pk = pk;
	}

	@Transient
	public Resource getResourse() {
		return getPk().getResource();
	}

	public void setResource(Resource resource) {
		getPk().setResource(resource);
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setCategory(User user) {
		getPk().setUser(user);
	}
	
	
	public ReadingItem()
	{
		
	}
	
	
	public ReadingItem(ResourceUserId pk, Boolean isRead) {
		super();
		this.pk = pk;
		this.isRead = isRead;
	}

	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	@Override
	public String toString() {
		return "ReadingItem [isRead=" + isRead + "]";
	}
	
	
}
