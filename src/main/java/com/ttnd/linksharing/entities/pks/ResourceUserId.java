package com.ttnd.linksharing.entities.pks;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.ttnd.linksharing.entities.Resource;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;

@Embeddable
public class ResourceUserId implements java.io.Serializable {

	private User user;
    private Resource resource;

	
    @ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	
	public ResourceUserId(User user, Resource resource) {
		super();
		this.user = user;
		this.resource = resource;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceUserId that = (ResourceUserId) o;

        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null)
            return false;

        return true;
    }
	
	

    public ResourceUserId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int hashCode() {
        int result;
        result = (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
    
}
