package com.ttnd.linksharing.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ttnd.linksharing.dao.impl.TopicDaoImpl;
import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.events.publishers.TopicSubscriptionPublisher;

public class TopicServiceImpl {
	
	
	TopicDaoImpl topicDao; 
	
	@Autowired
	TopicSubscriptionPublisher topicPub;
	
	
	@Autowired
	public TopicDaoImpl getTopicDao() {
		return topicDao;
	}



	public void setTopicDao(TopicDaoImpl topicDao) {
		this.topicDao = topicDao;
	}


	@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean saveTopic(TopicDTO topicDTO, String userName){
		Topic topic = getTopic(topicDTO);
		return topicDao.saveTopic(topic, userName);
	}
	
	public void subscribeUser(Long topicId, String userName){
		topicDao.subscribeUser(topicId, userName);
	}
	
	public List<TopicDTO> getTrendTopics(String userName){
		List<Topic> topics = (List<Topic>) topicDao.getTrendTopics(userName);
		
		List<TopicDTO> topicDTOs = new ArrayList<>();
		
		for(Topic topic : topics){
			
			Map<String, Integer> map = topicDao.getSizes(topic);
			TopicDTO topicDTO = getTopicDTO(topic);
			topicDTO.setSubsSize(map.get("subsSize"));
			topicDTO.setPostsSize(map.get("postsSize"));
			topicDTOs.add(topicDTO);
		}
		return topicDTOs;
		
	}
   
	public TopicDTO getTopicDTO(Topic topic) {
		TopicDTO topicDTO = new TopicDTO(topic.getName(), topic.getCreatedBy(), topic.getVisibility());
		topicDTO.setId(topic.getId());
		return topicDTO;
	}



	private Topic getTopic(TopicDTO topicDTO) {
		Topic topic = new Topic(topicDTO.getName(), topicDTO.getVisibility());
		return topic;
	}



	public Integer unsubscribeUser(Long topicId, String userName) {
		return topicDao.unsubscribeUser(topicId, userName);
		}



	public void updateTopic(String topicName, Long topicId) {
		topicDao.updateTopic(topicName, topicId);
		
	}



	public Boolean deleteTopic(Long topicId) {
		return topicDao.deleteTopic(topicId);
	}



	public void sendMail(Long topicId, String email, String username) {
		Topic topic = topicDao.getTopic(topicId);
		topicPub.publish(topicId, email, username, topic.getName());
	}

}
