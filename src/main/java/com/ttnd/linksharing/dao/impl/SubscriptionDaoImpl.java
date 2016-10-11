package com.ttnd.linksharing.dao.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ttnd.linksharing.entities.Subscription;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.utils.Seriousness;
import com.ttnd.linksharing.utils.Visibility;

public class SubscriptionDaoImpl extends HibernateDaoSupport{
	
	SessionFactory sessionFactory = getSessionFactory();
	
	@Autowired
	ResourceDaoImpl resourceDao;
	
	public List<Subscription> retrieveUserSubscription(String userName){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		/*Criteria criteria = session.createCriteria(Subscription.class, "subscription");
		criteria = criteria.createCriteria("subscription.pk", "pk").createCriteria("pk.user", "user").add(Restrictions.eq("user.userName", userName));
		*///criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Query query = session.createQuery("select distinct s from Subscription s, Resource r where s.topic.id=r.topic.id"
				+ " and s.user.userName ='" + userName + "'"
				+ " order by r.lastUpdated DESC");
		//query.setMaxResults(5);
		Query query2 = session.createQuery("select distinct s from Subscription s, Resource r where s.topic.id not in "
				+ "(select r.topic.id from Resource r) "
				+ " and s.user.userName ='" + userName + "'");
		List<Subscription> list = query.list();
		List<Subscription> list2 = query2.list();
		for(Subscription element : list2){
			list.add(element);
		}
		
		
		
		LinkedList<Subscription> subscriptions = new LinkedList<>(list);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public Map<String, Integer> getSizes(Topic topic){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		/*Criteria criteria = session.createCriteria(Topic.class);
		criteria.add(Restrictions.eq("id", topic.getId()));
		List<?> list = criteria.list();
		if(list.size()==0)
			return null;*/
		
		Topic topic2 = (Topic) session.get(Topic.class, topic.getId());
		Integer subsSize = topic2.getSubscriptions().size();
		
		Integer postsSize =resourceDao.getResources(topic).size();
		/*System.out.println(topic2.getSubscriptions() + "  " + topic2.getResources() );
		System.out.println(postsSize);*/
		Map<String, Integer> map = new HashMap<>();
		map.put("subsSize", subsSize);
		map.put("postsSize", postsSize);
		session.getTransaction().commit();
		session.close();
		return map;
	}

	public void changeSerious(Seriousness serious, Long subscriptionId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Subscription subscription = (Subscription) session.get(Subscription.class, subscriptionId);
		subscription.setSeriousness(serious);
		session.getTransaction().commit();
		session.close();
	}

	public void changeVisibility(Visibility visibility, Long topicId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Topic topic = (Topic) session.get(Topic.class, topicId);
		topic.setVisibility(visibility);
		session.getTransaction().commit();
		session.close();
	}

}
