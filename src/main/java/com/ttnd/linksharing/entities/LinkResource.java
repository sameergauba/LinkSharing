package com.ttnd.linksharing.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name="link_resource")
@PrimaryKeyJoinColumn(name="link_resource_id")
public class LinkResource  extends Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5177552919064009750L;
	
	@Column(name="url")
	private String url;
	
	
	public LinkResource(String description, User createdBy, Date dateCreated, Date lastUpdated, String url) {
		super(description, createdBy, dateCreated, lastUpdated);
		this.url = url;
	}
	public LinkResource() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LinkResource(String description, User createdBy, Date dateCreated, Date lastUpdated) {
		super(description, createdBy, dateCreated, lastUpdated);
		// TODO Auto-generated constructor stub
	}
	/*public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}*/
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
