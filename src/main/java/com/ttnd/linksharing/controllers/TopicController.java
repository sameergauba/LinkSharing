package com.ttnd.linksharing.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.services.impl.SubscriptionServiceImpl;
import com.ttnd.linksharing.services.impl.TopicServiceImpl;
import com.ttnd.linksharing.services.impl.UserServiceImpl;
import com.ttnd.linksharing.utils.Visibility;

@Controller
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	TopicServiceImpl topicService;
	@Autowired
	UserServiceImpl service;
	@Autowired
	SubscriptionServiceImpl subService;
	
	@Autowired
	UserController userController;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createTopic(@ModelAttribute("topicDTO") TopicDTO topicDTO, HttpServletRequest request,
			Model model, BindingResult result) {
		 //System.out.println("In create");

		/*if(result.hasErrors()){
			result.reject("name");
		}*/
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		Boolean status = topicService.saveTopic(topicDTO, userdto.getUserName());
		if(status==false){
			ModelAndView mView = userController.login(request, userdto.getUserName(), userdto.getPassword()).addObject("pageMsg", "Topic already exists.");
			return mView;
		}
		//System.out.println("after add");
		ModelAndView mView = userController.login(request, userdto.getUserName(), userdto.getPassword());
		return mView;
		/*ModelAndView mView = new ModelAndView("profile");
		List<SubscriptionDTO> subscriptionDTOs = subService.getUserSubscriptions(userdto.getUserName());
		List<TopicDTO> topicDTOs = service.getUserTopics(userdto.getUserName());
		List<TopicDTO> trendTopics = topicService.getTrendTopics(userdto.getUserName());
		mView.addObject("topicDTO", new TopicDTO());
		mView.addObject("user", userdto);
		mView.addObject("topics", topicDTOs);
		mView.addObject("trendTopics", trendTopics);
		mView.addObject("subscriptions", subscriptionDTOs);
		mView.addObject("documentDTO", new DocumentDTO());
		mView.addObject("linkDTO", new LinkDTO());
		if (topicDTOs != null && subscriptionDTOs != null)
			mView.addObject("topicSize", topicDTOs.size()).addObject("subscriptionSize", subscriptionDTOs.size());
		return mView;*/
	}
	
	@RequestMapping(value="/edit")
	@ResponseBody
	public String edit(@RequestParam String topicName, @RequestParam Long topicId){
		//System.out.println("topicController");
		topicService.updateTopic(topicName, topicId);
		return "Done";
	}
	
	@RequestMapping(value="/delete/{topicId}")
	//@ResponseBody
	public ModelAndView delete(HttpServletRequest request,@PathVariable Long topicId){
		//System.out.println("topicController");
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		topicService.deleteTopic(topicId);
			return userController.login(request, userdto.getUserName(), userdto.getPassword());
		
	}
	
	@RequestMapping(value="/request")
	@ResponseBody
	public String request(HttpServletRequest request,@RequestParam Long topicId, @RequestParam String email){
		//System.out.println("topicController");
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		topicService.sendMail(topicId, email, userdto.getUserName());
		return "Done";
		
	}
	
}
