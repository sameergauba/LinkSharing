package com.ttnd.linksharing.tempClients;

import java.applet.AppletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ttnd.linksharing.entities.Subscription;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;

public class Client {
	
	
	
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		
		SessionFactory sessionFactory = context.getBean("sessionFactory", SessionFactory.class);
		
		User user = new User();
		Topic topic = new Topic();
		Subscription subscription = new Subscription();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		user.setFirstName("Sameer");
		topic.setName("Well, Any Topic");
		session.save(topic);
		subscription.setUser(user);
		user.getSubscriptions().add(subscription);
		topic.getSubscriptions().add(subscription);
		subscription.setTopic(topic);
		subscription.setUser(user);
		session.save(user);
		session.save(topic);
		session.save(subscription);
		session.getTransaction().commit();
		session.close();
		
		
		System.out.println("Done!");
	}

}
