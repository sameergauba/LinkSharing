package com.ttnd.linksharing.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

public class SessionUtill {
	
	@Autowired
	private SessionFactory sessionFactory;

	

	private SessionUtill() {
		// TODO Auto-generated constructor stub
	}

	public SessionFactory getSessionFactory() {	
	return sessionFactory;
	}


	public static void closeSession(Session session) {
	if(session != null && session.isOpen()) {
	session.close();
	}
	}


	public Session getSession(){
		Session session = sessionFactory.openSession();
		return session;
	}

}
