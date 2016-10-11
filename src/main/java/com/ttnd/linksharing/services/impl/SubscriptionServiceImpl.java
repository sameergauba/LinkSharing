package com.ttnd.linksharing.services.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ttnd.linksharing.dao.impl.SubscriptionDaoImpl;
import com.ttnd.linksharing.dto.SubscriptionDTO;
import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.entities.Subscription;
import com.ttnd.linksharing.entities.pks.TopicUserId;
import com.ttnd.linksharing.utils.Seriousness;
import com.ttnd.linksharing.utils.Visibility;

public class SubscriptionServiceImpl {

	@Autowired
	SubscriptionDaoImpl subscriptionDao;
	@Autowired
	TopicServiceImpl topicService;

	public SubscriptionDaoImpl getSubscriptionDao() {
		return subscriptionDao;
	}

	public void setSubscriptionDao(SubscriptionDaoImpl subscriptionDao) {
		this.subscriptionDao = subscriptionDao;
	}

	public List<SubscriptionDTO> getUserSubscriptions(String userName) {
		List<Subscription> subscriptions = (List<Subscription>) subscriptionDao.retrieveUserSubscription(userName);

		
		//System.out.println(subscriptions);
		List<SubscriptionDTO> subscriptionDTOs = new LinkedList<SubscriptionDTO>();
		for (Subscription subscription : subscriptions) {
			SubscriptionDTO subscriptionDTO = getSubscriptionDTO(subscription);
			subscriptionDTO.setId(subscription.getId());
			Map<String, Integer> map = subscriptionDao.getSizes(subscription.getTopic());
			subscriptionDTO.setSubsSize(map.get("subsSize"));
			subscriptionDTO.setPostsSize(map.get("postsSize"));
			subscriptionDTOs.add(subscriptionDTO);
		}
		/*for(SubscriptionDTO subscription : subscriptionDTOs)
			System.out.println(subscription);*/
		
		return subscriptionDTOs;

	}
	
	/*public List<TopicDTO> getUserSubscriptionTopics(String userName){
		List<SubscriptionDTO> subscriptionDTOs = getUserSubscriptions(userName);
		for(SubscriptionDTO subscriptionDTO : subscriptionDTOs)
		System.out.println(subscriptionDTO);
		List<TopicDTO> topicDTOs = new ArrayList<>();
		for(SubscriptionDTO subscriptionDTO : subscriptionDTOs)
		topicDTOs.add(topicService.getTopicDTO(subscriptionDTO.getTopic()));
		return topicDTOs;
	}*/

	private SubscriptionDTO getSubscriptionDTO(Subscription subscription) {
		//TopicUserId pk = new TopicUserId(subscription.getPk().getUser(), subscription.getPk().getTopic());
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO( subscription.getSeriousness(),
				subscription.getDateCreated(),subscription.getUser(),subscription.getTopic());

		return subscriptionDTO;
	}

	public void changeSerious(Seriousness serious, Long subscriptionId) {
		subscriptionDao.changeSerious(serious, subscriptionId);
	}

	public void changeVisibility(Visibility visibility, Long topicId) {
		subscriptionDao.changeVisibility(visibility, topicId);
		
	}

}
