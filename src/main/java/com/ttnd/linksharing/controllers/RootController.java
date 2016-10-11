package com.ttnd.linksharing.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.linksharing.dao.impl.ResourceDaoImpl;
import com.ttnd.linksharing.dto.DocumentDTO;
import com.ttnd.linksharing.dto.LinkDTO;
import com.ttnd.linksharing.dto.ResourceDTO;
import com.ttnd.linksharing.dto.SubscriptionDTO;
import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.services.impl.ResourceServiceImpl;
import com.ttnd.linksharing.services.impl.SubscriptionServiceImpl;
import com.ttnd.linksharing.services.impl.TopicServiceImpl;
import com.ttnd.linksharing.services.impl.UserServiceImpl;

@Controller
@RequestMapping(value={"/", "/welcome"})
public class RootController {
	
	
	
	@Autowired
	ResourceServiceImpl resourceService;
	@Autowired
	UserController userController;

	@RequestMapping(value={"/", "/welcome"}, method=RequestMethod.GET)
	public ModelAndView indexMap(HttpServletRequest request, String error){
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		//sSystem.out.println("Index control" + userdto);
		if(userdto!=null){
			
			ModelAndView mView = userController.login(request, userdto.getUserName(), userdto.getPassword());
			return mView;
			
			/*ModelAndView mView = new ModelAndView("profile2");
			List<SubscriptionDTO> subscriptionDTOs = subService.getUserSubscriptions(userdto.getUserName());
			List<TopicDTO> topicDTOs = service.getUserTopics(userdto.getUserName());
			List<TopicDTO> trendTopics = topicService.getTrendTopics(userdto.getUserName());
//System.out.println(trendTopics);
			List<LinkDTO> linkDTOs = resourceService.getUserLinkResources(userdto.getUserName());
			List<DocumentDTO> documentDTOs = resourceService.getUserDocumentResources(userdto.getUserName());
			// System.out.println(trendTopics);
			// System.out.println(linkDTOs);
			mView.addObject("topicDTO", new TopicDTO()).addObject("linkResources", linkDTOs)
					.addObject("documentResources", documentDTOs);
			mView.addObject("user", userdto);
			mView.addObject("topics", topicDTOs);
			mView.addObject("trendTopics", trendTopics);
			mView.addObject("subscriptions", subscriptionDTOs);
			mView.addObject("documentDTO", new DocumentDTO());
			mView.addObject("linkDTO", new LinkDTO());
			if (topicDTOs != null && subscriptionDTOs != null)
				mView.addObject("topicSize", topicDTOs.size()).addObject("subscriptionSize", subscriptionDTOs.size());
			return mView;*/
		}else{
			
			List<ResourceDTO> recentResources = resourceService.getRecentResources();
		List<ResourceDTO> topPosts = resourceService.getTopPosts();
			//System.out.println(recentResources + "\n " + topPosts);
			//System.out.println(error+"In index");
			return new ModelAndView("index").addObject("recentResources", recentResources).addObject("user", new UserDTO()).addObject("topPosts", topPosts)
					;
			
		}
	}
	
}
