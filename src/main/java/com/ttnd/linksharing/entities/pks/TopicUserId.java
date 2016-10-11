package com.ttnd.linksharing.entities.pks;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;

import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;

@Embeddable
public class TopicUserId implements java.io.Serializable {

	private User user;
    private Topic topic;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public TopicUserId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopicUserId(User user, Topic topic) {
		super();
		this.user = user;
		this.topic = topic;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopicUserId that = (TopicUserId) o;

        if (topic != null ? !topic.equals(that.topic) : that.topic != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
    
}
