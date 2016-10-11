package com.ttnd.linksharing.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.events.publishers.RegistrationPublisher;

public class UserDaoImpl extends HibernateDaoSupport {

	SessionFactory sessionFactory = getSessionFactory();

	

	public Long saveUser(User user) {
		return editUser(user);
	}

	public Long editUser(User user) {

		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		User userRetrieved = retrieveUser(user.getUserName(), user.getPassword());
		if (userRetrieved != null) {
			//System.out.println("in dao");
			
			userRetrieved.setActive(user.getActive());
			userRetrieved.setAdmin(user.getAdmin());
			userRetrieved.setDateCreated(user.getDateCreated());
			userRetrieved.setEmail(user.getEmail());
			userRetrieved.setFirstName(user.getFirstName());
			userRetrieved.setLastName(user.getLastName());
			userRetrieved.setLastUpdated(user.getLastUpdated());
			userRetrieved.setPassword(user.getPassword());
			userRetrieved.setPhoto(user.getPhoto());
			userRetrieved.setUserName(user.getUserName());
		} else {
			userRetrieved = user;
		}
		userRetrieved.setActive(false);
		Long status = (Long) session.save(userRetrieved);
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		return status;
		// return saveUser(userRetrieved);

	}
	
	public Set<Topic> getUserTopics(String userName){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
List<?> users = criteria.list();
		if (users.size() == 0) {
			return null;
		}
		User user = (User) users.get(0);
		Set<Topic> topics = user.getTopics();
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		return topics;
	}
	

	public List<?> retrieveAllUsers() {
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	public User retrieveUser(String userName, String password) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));

		List<?> users = criteria.list();
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		if (users.size() == 0) {
			return null;
		}
		return (User) users.get(0);

	}

	public void deleteUser(String userName, String password) {
		Session session = getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));

		List<?> users = criteria.list();
		session.delete((User) users.get(0));
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
	}

	public User verifyUser(String userName) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));

		List<?> users = criteria.list();
		
		if (users==null || users.size() == 0) {
			return null;
		}
		User user = (User) users.get(0);
		user.setActive(true);
		session.save(user);
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		return user;
	}

	public Boolean isUserActive(String userName, String password) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		criteria.add(Restrictions.eq("password", password));

		List<?> users = criteria.list();
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		if (users.size() == 0) {
			return null;
		}
		User user = (User) users.get(0);
		return user.getActive();
	}



	public Boolean changePass(String email, String newpass) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("update User set password = '"+newpass+"' where email='"+email+"'");
		Integer i = query.executeUpdate();
		System.out.println(i);
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		if(i!=null)
		return true;
		
		return false;
	}

	public User getUserEmail(String email) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));

		List<?> users = criteria.list();
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		if (users.size() == 0) {
			return null;
		}
		return (User) users.get(0);
	}

	

	

}
