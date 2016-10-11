package com.ttnd.linksharing.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.ttnd.linksharing.dto.ResourceDTO;
import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.entities.DocumentResource;
import com.ttnd.linksharing.entities.LinkResource;
import com.ttnd.linksharing.entities.ReadingItem;
import com.ttnd.linksharing.entities.Resource;
import com.ttnd.linksharing.entities.ResourceRating;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.entities.pks.ResourceUserId;

public class ResourceDaoImpl extends HibernateDaoSupport{
	@Autowired
	TopicDaoImpl topicDao;
	
	@Autowired
	UserDaoImpl userDao;

	public Long saveDocument(DocumentResource document, String userName, Long topicId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		User user = topicDao.getUser(userName);
		//Topic topic1 = topicDao.retrieveTopic(topic.get("topicName"), topic.get("createdByUser"));
		Topic topic = (Topic) session.get(Topic.class, topicId);
		document.setCreatedBy(user);
		document.setTopic(topic);
		Long ret = (Long) session.save(document);
		/*session.save(user);
		session.save(topic);*/
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	public Long saveLink(LinkResource link, String userName, Long topicId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		User user = topicDao.getUser(userName);
		//Topic topic1 = topicDao.retrieveTopic(topic.get("topicName"), topic.get("createdByUser"));
		Topic topic = (Topic) session.get(Topic.class, topicId);
		link.setCreatedBy(user);
		link.setTopic(topic);
		Long ret = (Long) session.save(link);
		//System.out.println("dao");
		/*session.save(user);
		session.save(topic);*/
		session.getTransaction().commit();
		session.close();
		return ret;
	}

	public List<LinkResource> getUserLinkResources(String userName) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		/*Query query = session.createQuery("from Resource r where r.createdBy.userName = '" + userName+""
				+ "'");*/
		Query query = session.createQuery("select r from Subscription s, LinkResource r "
				+ "where s.user.userName = '" +userName +
				"' and s.topic.id = r.topic.id "
				+ " and r.id NOT IN (select ri.pk.resource.id from ReadingItem ri where ri.pk.user.userName='"+userName+"') ");
		/*Criteria criteria = session.createCriteria(Resource.class);
		criteria.add(Restrictions.eq("userName", userName));*/
List<LinkResource> resources = query.list();
//System.out.println(resources);
		if (resources.size() == 0) {
			return null;
		}
		/*User user = (User) users.get(0);
		Set<Topic> topics = user.getTopics();*/
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		return resources;
	}
	
	public List<DocumentResource> getUserDocumentResources(String userName) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		/*Query query = session.createQuery("from Resource r where r.createdBy.userName = '" + userName+""
				+ "'");*/
		Query query = session.createQuery("select r from Subscription s, DocumentResource r "
				+ "where s.user.userName = '" +userName +
				"' and s.topic.id = r.topic.id "
				+ " and r.id NOT IN (select ri.pk.resource.id from ReadingItem ri where ri.pk.user.userName='"+userName+"') ");
		/*Criteria criteria = session.createCriteria(Resource.class);
		criteria.add(Restrictions.eq("userName", userName));*/
List<DocumentResource> resources = query.list();
//System.out.println(resources);
		if (resources.size() == 0) {
			return null;
		}
		/*User user = (User) users.get(0);
		Set<Topic> topics = user.getTopics();*/
		session.getTransaction().commit();
		if (session.isOpen()) {
			session.close();
		}
		return resources;
	}
	
	
	public List<Resource> getResources(Topic topic) {
		/*DetachedCriteria criteria=DetachedCriteria.forClass(Resource.class);
		Criterion firstCondition=Restrictions.eq("topic", topic);
		criteria.add(firstCondition);*/
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("select distinct r from Resource r where r.topic.id='"+topic.getId()+"'");
		List<?> list=query.list();
		session.getTransaction().commit();
		session.close();
		if(list !=null)
		{	
		return (List<Resource>)list;
		}
		return null;
		
	}

	public Boolean isResourceReaded(Resource resource, User user) {
		DetachedCriteria criteria=DetachedCriteria.forClass(ReadingItem.class);
		Criterion firstCondition=Restrictions.eq("pk.resource", resource);
		criteria.add(firstCondition);
		Criterion secondCondition=Restrictions.eq("pk.user", user);
		criteria.add(secondCondition);
		List<?> list=getHibernateTemplate().findByCriteria(criteria);
		if(list !=null&&list.size()>0)
		{	
		return true;
		}
		return false;
	}

	public void markRead(Long resourceId, Long userId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		//Query query = session.createQuery("from Resource where topic.id='"+topic.getId()+"'");
		//List<?> list=query.list();
		User user = (User) session.get(User.class, userId);
		Resource resource = (Resource) session.get(Resource.class, resourceId);
		ReadingItem readingItem = new ReadingItem(new ResourceUserId(user, resource), true);
		session.save(readingItem);
		session.getTransaction().commit();
		session.close();
	}

	public List<Resource> getRecentResources() {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Resource order by lastUpdated DESC");
		query.setMaxResults(5);
		List<?> list=query.list();
		session.getTransaction().commit();
		session.close();
		if(list !=null)
		{	
		return (List<Resource>)list;
		}
		return null;
	}

	public List<Resource> getTopPosts() {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		
		/*org.hibernate.Query query=sessionFactory.getCurrentSession()
				.createQuery("Select res FROM Resource res,ResourceRating rat where rat.userResourceId.resource=res group by res order by avg(rat.score) DESC");
				return (List<Resource>)query.setMaxResults(5).list();*/
		
		Query query = session.createQuery("Select r FROM Resource r,ResourceRating rr where rr.pk.resource.id=r.id group by r order by avg(rr.score) DESC");
		query.setMaxResults(5);
		List<?> list=query.list();
		session.getTransaction().commit();
		session.close();
		if(list !=null)
		{	
		return (List<Resource>)list;
		}
		return null;
	}

	public Resource getResource(Long resourceId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Resource resource = (Resource) session.get(Resource.class, resourceId);
		session.getTransaction().commit();
		session.close();
		return resource;
	}

	public Integer getResourceRating(Long resourceId, String userName) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("select rr.score from Resource r, ResourceRating rr where rr.pk.resource.id="+resourceId
				+ " and rr.pk.user.userName='"+userName+"'");
		//query.setMaxResults(5);
		List<Integer> list=query.list();
		if(list.size()==0)
			return null;
		session.getTransaction().commit();
		session.close();
		//System.out.println(list.get(0));
		return list.get(0);
		
	}

	public void updateRating(Long resourceId, Integer score, String email) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		User user = userDao.getUserEmail(email);
		Resource resource = (Resource) session.get(Resource.class, resourceId);
		Query query = session.createQuery("from ResourceRating rr where rr.pk.user.id="+user.getId()+" "
				+ "and rr.pk.resource.id = " + resource.getId());
		List<ResourceRating> rrs = query.list();
		ResourceRating rr;
		if(rrs.size()==0){
			rr = new ResourceRating(new ResourceUserId(user, resource), score);
		}
		else{
		rr = rrs.get(0);
		rr.setScore(score);
        System.out.println(rr.getScore());
		}
		session.merge(rr);
		System.out.println(rr);
		session.getTransaction().commit();
		session.close();
		
	}

	public void deletePost(Long resourceId) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		org.hibernate.Query query4=session
				.createQuery("Delete from ResourceRating rr where rr.pk.resource.id ="+resourceId+"");
				query4.executeUpdate();

				org.hibernate.Query query5=session
				.createQuery("Delete from ReadingItem ri where ri.pk.resource.id ="+resourceId+"");
				query5.executeUpdate();
				
				session.delete(session.get(Resource.class, resourceId));
				
				session.getTransaction().commit();
				session.close();
	}

	public void updatePost(Long resourceId, String description) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("update Resource r set r.description='"+description+"' "
				+ "where r.id = "+resourceId);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	public List<Resource> getResourcesSearch(Topic topic, String searchKeys, Boolean admin) {
		
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query=null;
		if(searchKeys.equals("")){
			if(admin!=null){
				query = session.createQuery("select distinct r from Resource r where r.topic.id='"+topic.getId()+"' "
				+ "");
			}else{
				return null;
			}
		}else{
		
		query = session.createQuery("select distinct r from Resource r where r.topic.id='"+topic.getId()+"' "
				+ "and (r.topic.name LIKE '%"+searchKeys+"%' or r.description LIKE '%"+searchKeys+"%')");
		}
		
		//System.out.println(query);
		List<?> list=query.list();
		session.getTransaction().commit();
		session.close();
		if(list !=null)
		{	
		return (List<Resource>)list;
		}
		return null;
		
	}

	


}
