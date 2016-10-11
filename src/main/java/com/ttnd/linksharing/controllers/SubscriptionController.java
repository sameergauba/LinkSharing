package com.ttnd.linksharing.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.services.impl.SubscriptionServiceImpl;
import com.ttnd.linksharing.services.impl.TopicServiceImpl;
import com.ttnd.linksharing.utils.Seriousness;
import com.ttnd.linksharing.utils.Visibility;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {
	
	@Autowired
	TopicServiceImpl topicService;
	@Autowired
	UserController userController;
	@Autowired
	RootController rootController;
	@Autowired
	SubscriptionServiceImpl subscriptionService;

	@RequestMapping("/subscribe/{topicId}")
	public ModelAndView subs(@PathVariable("topicId") Long topicId, HttpServletRequest request){
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		if(userdto==null){
			return rootController.indexMap(request,null).addObject("user", new UserDTO()).addObject("loginError",
					"Login first and then click on the link");
		}
		//System.out.println(userdto);
	    topicService.subscribeUser(topicId, userdto.getUserName());
	    ModelAndView mView = userController.login(request, userdto.getUserName(), userdto.getPassword());
	    
		return mView;
	}
	
	@RequestMapping("/unsubscribe/{topicId}")
	public ModelAndView unsubs(@PathVariable("topicId") Long topicId, HttpServletRequest request){
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
	    topicService.unsubscribeUser(topicId, userdto.getUserName());
	    ModelAndView mView = userController.login(request, userdto.getUserName(), userdto.getPassword());
	    return mView;
	}
	
	@RequestMapping("/serious")
	@ResponseBody
	public String changeSerious(HttpServletRequest request, @RequestParam Seriousness serious, @RequestParam Long subscriptionId){
		subscriptionService.changeSerious(serious, subscriptionId);
		
		return "Done";
	}
	
	@RequestMapping("/private")
	@ResponseBody
	public String changePrivate(HttpServletRequest request, @RequestParam Visibility visibility, @RequestParam Long topicId){
		subscriptionService.changeVisibility(visibility, topicId);
		
		return "Done";
	}

	
}
