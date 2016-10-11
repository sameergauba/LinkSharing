package com.ttnd.linksharing.dao.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.web.context.ServletContextAware;

import com.ttnd.linksharing.entities.Subscription;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.entities.pks.TopicUserId;
import com.ttnd.linksharing.utils.Seriousness;

public class TopicDaoImpl extends HibernateDaoSupport implements ServletContextAware{
	SessionFactory sessionFactory = getSessionFactory();
	ServletContext context;
	
	public Boolean saveTopic(Topic topic, String userName){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		User user = getUser(userName);
		topic.setCreatedBy(user);
		Topic topicRetrieved = retrieveTopic(topic.getName(), topic.getCreatedBy().getUserName());
		//System.out.println(topicRetrieved);
		if(topicRetrieved!=null){
			return false;
		}else{
			topicRetrieved=topic;
			String webPath = context.getRealPath("/");
			new File(webPath + "/../../webapps/Topics/" + topic.getName() + "/documents/" ).mkdirs();
		Long i = (Long) session.save(topic);
		Subscription subscription = new Subscription(Seriousness.VerySerious,topic.getCreatedBy(), topicRetrieved);
		topic.getSubscriptions().add(subscription);
		session.save(subscription);
		session.getTransaction().commit();
		session.close();
		return true;
		}
	}
	
	public Long editTopic(Topic topic){
		Topic topicRetrieved = retrieveTopic(topic.getName(), topic.getCreatedBy().getUserName());
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		if(topicRetrieved!=null){

			topicRetrieved.setName(topic.getName());
			topicRetrieved.setCreatedBy(topic.getCreatedBy());
			topicRetrieved.setDateCreated(topic.getDateCreated());
			topicRetrieved.setLastUpdated(topic.getLastUpdated());
			topicRetrieved.setSubscriptions(topic.getSubscriptions());
			topicRetrieved.setVisibility(topic.getVisibility());
			
		}else{
			topicRetrieved=topic;
			String webPath = context.getRealPath("/");
			new File(webPath + "/../../webapps/Topics/" + topic.getName() + "/documents/" ).mkdirs();
		}
		Long i = (Long) session.save(topic);
		Subscription subscription = new Subscription(Seriousness.VerySerious,topic.getCreatedBy(), topicRetrieved);
		topic.getSubscriptions().add(subscription);
		session.save(subscription);
		session.getTransaction().commit();
		session.close();
		return i;
	}
	
	public List<?> retrieveAllTopics(){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Topic.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<?> list = criteria.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	
	public Topic retrieveTopic(String name, String userName){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		/*Criteria criteria = session.createCriteria(Topic.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("", value))*/
		//System.out.println(name +" "+ userName);
		Query query = session.createQuery("from Topic t where name = '"+name+"' "
				+ "and t.createdBy.userName='"+userName+"'");
		//criteria.add(Restrictions.eq("password", password));
		
		List<?> users = query.list();
		
		session.getTransaction().commit();
		session.close();
		//System.out.println(users);
		if(users.size()==0)
			return null;
		return (Topic)users.get(0);
		
	}
	
	public User getUser(String userName){
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
	
	public Topic getTopic(Long topicId){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Topic topic = (Topic) session.get(Topic.class, topicId);
		session.getTransaction().commit();
		session.close();
		return topic;
	}
	
	/*public Boolean deleteTopic(Long topicId){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Topic.class);
		criteria.add(Restrictions.eq("name", topicId));
		//criteria.add(Restrictions.eq("password", password));
		Topic topic = (Topic) session.get(Topic.class, topicId);
		Query query = session.createQuery("delete from Subscription s where s.pk.topic.id='"+topicId+"'");
		Integer i = query.executeUpdate();
		query = session.createQuery("delete from Resource s where s.topic.id='"+topicId+"'");
		query.executeUpdate();
		query = session.createQuery("delete from LinkResource s where s.topic.id='"+topicId+"'");
		query.executeUpdate();
		session.delete(topic);
		session.getTransaction().commit();
		try{session.close();
		return true;
		}finally{
			return false;
		}
		if(getTopic(topicId)==null)
		{
			
			session.close();
			return true;
		}else{
		List<?> users = criteria.list();
		 session.delete((Topic)users.get(0));
		//session.getTransaction().commit();
		session.close();
		return false;
		}
		
	}*/

	public Boolean deleteTopic(Long topicId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete from Subscription s where s.topic.id='"+topicId+"'");
		query.executeUpdate();
		
		org.hibernate.Query query3=session
				.createQuery("Select id from Resource r where r.topic.id ="+topicId+"");
				List<Long> resources=(List<Long>)query3.list();
				for(Long id:resources){
				org.hibernate.Query query4=session
				.createQuery("Delete from ResourceRating rr where rr.pk.resource.id ="+id+"");
				query4.executeUpdate();

				org.hibernate.Query query5=session
				.createQuery("Delete from ReadingItem ri where ri.pk.resource.id ="+id+"");
				query5.executeUpdate();
				}
		
		query = session.createQuery("delete from Resource s where s.topic.id='"+topicId+"'");
		query.executeUpdate();
		query = session.createQuery("delete from Topic where id='"+topicId+"'");
		query.executeUpdate();
		
		//Topic topic=(Topic) session.get(Topic.class, topicId);
		//topic.getSubscriptions().clear();
		/*getSessionFactory().getCurrentSession().beginTransaction();
		org.hibernate.Query query=getSessionFactory().getCurrentSession()
		.createQuery("delete from Subscription s where s.pk.topic.id="+topicId+"");
		query.executeUpdate();

		org.hibernate.Query query2=getSessionFactory().getCurrentSession()
		.createQuery("Delete from Resource where topic.id ="+topicId+"");
		query2.executeUpdate();	
		getSessionFactory().getCurrentSession().getTransaction().commit();*/
		//session.delete(topic);
		session.getTransaction().commit();
		session.close();
		//System.out.println("asdasd");
		return true;
		/*if(((Topic)getHibernateTemplate().get(Topic.class, topicId))==null)
		{
		return true;
		}
		else
		{
		return false;
		}*/
		}
	
	
/*	public Boolean deleteTopic(Long topicId) {
		Topic topic=(Topic)getHibernateTemplate().get(Topic.class, topicId);
		org.hibernate.Query query=sessionFactory.getCurrentSession()
		.createQuery("Delete from Subscription where userTopicId.topic.topicId ="+topicId+"");
		query.executeUpdate();

		org.hibernate.Query query3=sessionFactory.getCurrentSession()
		.createQuery("Select resourceId from Resource where topic.topicId ="+topicId+"");
		List<Long> resources=(List<Long>)query3.list();
		for(Long id:resources){
		org.hibernate.Query query4=sessionFactory.getCurrentSession()
		.createQuery("Delete from ResourceRating where userResourceId.resource.resourceId ="+id+"");
		query4.executeUpdate();

		org.hibernate.Query query5=sessionFactory.getCurrentSession()
		.createQuery("Delete from ReadingItem where userResourceId.resource.resourceId ="+id+"");
		query5.executeUpdate();
		}





		org.hibernate.Query query2=sessionFactory.getCurrentSession()
		.createQuery("Delete from Resource where topic.topicId ="+topicId+"");
		query2.executeUpdate();	
		getHibernateTemplate().delete(topic);	
		if(((Topic)getHibernateTemplate().get(Topic.class, topicId))==null)
		{
		return true;
		}
		else
		{
		return false;
		}
		}*/
	
	
	
	public List<?> getTrendTopics(String userName){
		Session session = getSessionFactory().openSession(); 
		session.beginTransaction();
		Query query = session.createQuery("select t from Topic t, Subscription s where t.createdBy.userName <> '"+userName
				+ "' and s.topic.id = t.id and t.visibility='Public' and s.topic.id Not In (select s.topic.id from Subscription s where s.user.userName='"+userName+"')"
				+ " order by size(t.subscriptions) DESC");
		query.setMaxResults(5);
		List<?> list = query.list();
		//System.out.println(list);
		session.getTransaction().commit();
		session.close();
		return list;
		
	}
	
	public Map<String, Integer> getSizes(Topic topic){
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Topic.class);
		criteria.add(Restrictions.eq("id", topic.getId()));
		List<?> list = criteria.list();
		if(list.size()==0)
			return null;
		Topic topic2 = (Topic) list.get(0);
		Integer subsSize = topic2.getSubscriptions().size();
		Integer postsSize = topic2.getResources().size();
		Map<String, Integer> map = new HashMap<>();
		map.put("subsSize", subsSize);
		map.put("postsSize", postsSize);
		session.getTransaction().commit();
		session.close();
		return map;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		context=arg0;
		
	}

	public void subscribeUser(Long topicId, String userName) {
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Topic topic = (Topic) session.get(Topic.class, topicId);
		Query query = session.createQuery("from User where userName = '"+userName+"'");
		List<User> users = query.list();
		if(users.size()==0)
			return;
		User user = users.get(0);
		Subscription subscription = new Subscription(Seriousness.VerySerious,user, topic);
		topic.getSubscriptions().add(subscription);
		session.save(subscription);
		session.getTransaction().commit();
		session.close();
		
		
	}

	public Integer unsubscribeUser(Long topicId, String userName) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		//Topic topic = (Topic) session.get(Topic.class, topicId);
		Query query = session.createQuery("from User where userName = '"+userName+"'");
		List<User> users = query.list();
		if(users.size()==0)
			return null;
		User user = users.get(0);
		
		Query query2 = session.createQuery("delete from Subscription s where s.user.id = '"+user.getId()
				+"' and s.topic.id = "+topicId);
		//System.out.println(query2);
		
		Integer i = query2.executeUpdate();
		/*Subscription subscription = new Subscription(new TopicUserId(user, topic), Seriousness.VerySerious);
		topic.getSubscriptions().add(subscription);
		session.save(subscription);*/
		
		session.getTransaction().commit();
		session.close();
		return i;
		
	}

	public void updateTopic(String topicName, Long topicId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Topic topic = (Topic) session.get(Topic.class, topicId);
		topic.setName(topicName);
		session.save(topic);
		session.getTransaction().commit();
		session.close();
	}
	

}
