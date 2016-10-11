package com.ttnd.linksharing.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name="document_resource")
@PrimaryKeyJoinColumn(name="documnet_resource_id")
public class DocumentResource extends Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2284787413949677643L;
	
	@Column(name="filepath")
	private String filePath;
	
	
	/*public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}*/


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public DocumentResource() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DocumentResource(String description, User createdBy, Date dateCreated, Date lastUpdated) {
		super(description, createdBy, dateCreated, lastUpdated);
		// TODO Auto-generated constructor stub
	}


	public DocumentResource(String description, User createdBy, Date dateCreated, Date lastUpdated,
			String filePath) {
		super(description, createdBy, dateCreated, lastUpdated);
		this.filePath = filePath;
	}


	public DocumentResource(String description, User createdBy, Topic topic, String filePath) {
		super(description, createdBy, topic);
		this.filePath = filePath;
	}
	
	

}
