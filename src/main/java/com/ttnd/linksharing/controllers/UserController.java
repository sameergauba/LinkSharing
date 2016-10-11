package com.ttnd.linksharing.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.linksharing.dto.DocumentDTO;
import com.ttnd.linksharing.dto.LinkDTO;
import com.ttnd.linksharing.dto.ResourceDTO;
import com.ttnd.linksharing.dto.SubscriptionDTO;
import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.events.publishers.ResetPassPublisher;
import com.ttnd.linksharing.services.impl.ResourceServiceImpl;
import com.ttnd.linksharing.services.impl.SubscriptionServiceImpl;
import com.ttnd.linksharing.services.impl.TopicServiceImpl;
import com.ttnd.linksharing.services.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/user")
public class UserController implements ServletContextAware {

	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserServiceImpl service;
	@Autowired
	TopicServiceImpl topicService;
	@Autowired
	SubscriptionServiceImpl subService;
	@Autowired
	ResourceServiceImpl resourceService;

	@Autowired
	RootController rootController;
	@Autowired
	ResetPassPublisher resetPub;
	ServletContext context;

	/*
	 * @RequestMapping("*") public void mapper(HttpServletRequest request){
	 * System.out.println(request.getServletPath()); }
	 */

	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return rootController.indexMap(request, null).addObject("user", new UserDTO());

	}

	@RequestMapping("/validate/{userName}")
	public ModelAndView validate(HttpServletRequest request, @PathVariable String userName) {
		UserDTO userdto = service.verifyUser(userName);
      // System.out.println();
		ModelAndView mView = login(request, userName, userdto.getPassword());
		return mView;
		/*
		 * HttpSession session = request.getSession();
		 * session.setAttribute("user", userdto); ModelAndView mView = new
		 * ModelAndView("profile"); List<SubscriptionDTO> subscriptionDTOs =
		 * subService.getUserSubscriptions(userName); List<TopicDTO> topicDTOs =
		 * service.getUserTopics(userName); List<TopicDTO> trendTopics =
		 * topicService.getTrendTopics();
		 * 
		 * 
		 * mView.addObject("topicDTO", new TopicDTO()); mView.addObject("user",
		 * userdto); mView.addObject("topics", topicDTOs);
		 * mView.addObject("trendTopics", trendTopics);
		 * mView.addObject("subscriptions", subscriptionDTOs);
		 * mView.addObject("documentDTO", new DocumentDTO());
		 * mView.addObject("linkDTO", new LinkDTO()); if (topicDTOs != null &&
		 * subscriptionDTOs != null) mView.addObject("topicSize",
		 * topicDTOs.size()).addObject("subscriptionSize",
		 * subscriptionDTOs.size()); return mView;
		 */

	}

	@RequestMapping(value="/register")
	public ModelAndView register(HttpServletRequest request, @ModelAttribute("user") @Valid UserDTO userdto, BindingResult result,
			@RequestParam(value = "photo", required = false) MultipartFile file) {
		//System.out.println("in register");
		// System.out.println(userdto);
		// System.out.println(result.hasErrors() + " register");
		if (result.hasErrors()) {
			return rootController.indexMap(request, null).addObject("user", new UserDTO()).addObject(BindingResult.class.getName()+".user", result);
			//return new ModelAndView("index");
		}

		//BeanUtils.copyProperties(source, target);
		
		Long i = service.saveUser(userdto);
		if (file != null)
			service.addPhoto(userdto.getUserName(), file);
		return rootController.indexMap(request, null).addObject("user", new UserDTO()).addObject("activeError",
				"The account is not active. Please activate it.");

	}
	

	@RequestMapping(value="/login", method={RequestMethod.POST, RequestMethod.GET})
	public ModelAndView login(HttpServletRequest request, @RequestParam(value="username", required=false) String username,
			@RequestParam(value="password", required=false) String password) {

		Boolean active;
		if(request.getSession().getAttribute("user")!=null){
			UserDTO u = (UserDTO) request.getSession().getAttribute("user");
			username = u.getUserName();
			password = u.getPassword();
		}
		active = service.isUserActive(username, password);

		if (active != null && active == true) {
			UserDTO userdto = service.getUser(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("user", userdto);
			ModelAndView mView = new ModelAndView("profile2");
			List<SubscriptionDTO> subscriptionDTOs = subService.getUserSubscriptions(username);
		//	List<TopicDTO> userTopics = subService.getUserSubscriptionTopics(username);
			List<TopicDTO> topicDTOs = service.getUserTopics(username);
			List<TopicDTO> trendTopics = topicService.getTrendTopics(username);
			
			List<ResourceDTO> resources = resourceService.getResources(subscriptionDTOs, userdto);
			//System.out.println(resources);
			//List<LinkDTO> linkDTOs = resourceService.getUserLinkResources(username);
			//List<DocumentDTO> documentDTOs = resourceService.getUserDocumentResources(username);
			mView.addObject("topicDTO", new TopicDTO()).addObject("resources", resources);
			//mView.addObject("linkResources", linkDTOs)
					//.addObject("documentResources", documentDTOs);
			mView.addObject("user", userdto);
			mView.addObject("topics", topicDTOs);
			mView.addObject("trendTopics", trendTopics);
			mView.addObject("subscriptions", subscriptionDTOs);
			mView.addObject("documentDTO", new DocumentDTO());
			mView.addObject("linkDTO", new LinkDTO());
			if (topicDTOs != null && subscriptionDTOs != null)
				mView.addObject("topicSize", topicDTOs.size()).addObject("subscriptionSize", subscriptionDTOs.size());
			return mView;
		}
		if (active == null) {
			// throw new IdPassNotMatchException();
			return rootController.indexMap(request, null).addObject("user", new UserDTO()).addObject("loginError",
					"username or password is incorrect. Try again");
		}
		return rootController.indexMap(request, null).addObject("user", new UserDTO()).addObject("activeError",
				"The account is not active. Please activate it.");

	}
	
	@RequestMapping("/pass")
	@ResponseBody
	public String passEmail(@RequestParam String email){
		//System.out.println("In passemail");
		resetPub.publish(email);
		//System.out.println("Published");
		return "Done";
	}
	
	@RequestMapping("/reset/{email}")
	public ModelAndView resetPage(@PathVariable String email){
		ModelAndView mView = new ModelAndView("passwordReset");
		mView.addObject("email", email);
		return mView;
	}
	
	@RequestMapping("/passreset")
	public ModelAndView resetPass(HttpServletRequest request, @RequestParam String email, @RequestParam String newpass){
		
		//System.out.println(email);
		email+=".com";
		Boolean status = service.resetUserPass(email, newpass);
		if(status==null)
		return rootController.indexMap(request, null).addObject("pageMsg", "user with given email does not exist");
		if(status==true){
		UserDTO user = service.getUserEmail(email);
		ModelAndView mView = login(request, user.getUserName(), user.getPassword());
		return mView;
		}else{
		return rootController.indexMap(request, null).addObject("pageMsg", "password not changed, try again.");
		}
	}
	
	@RequestMapping("/search")
	public ModelAndView search(HttpServletRequest request,@RequestParam(value="search") String searchKeys , @RequestParam(value="username", required=false) String username,
			@RequestParam(value="password", required=false) String password) {

		//Boolean active;
		if(request.getSession().getAttribute("user")!=null){
			UserDTO u = (UserDTO) request.getSession().getAttribute("user");
			username = u.getUserName();
			password = u.getPassword();
		}else{
			return rootController.indexMap(request, null);
		}
		//active = service.isUserActive(username, password);

		
			UserDTO userdto = service.getUser(username, password);
			System.out.println(userdto);
			HttpSession session = request.getSession();
			session.setAttribute("user", userdto);
			ModelAndView mView = new ModelAndView("search");
			List<SubscriptionDTO> subscriptionDTOs = subService.getUserSubscriptions(username);
		//	List<TopicDTO> userTopics = subService.getUserSubscriptionTopics(username);
			List<TopicDTO> topicDTOs = service.getUserTopics(username);
			List<TopicDTO> trendTopics = topicService.getTrendTopics(username);
			
			List<ResourceDTO> resources = resourceService.getResourcesSearch(subscriptionDTOs, userdto, searchKeys);
			if(resources==null)
				mView.addObject("pgMsg", "Nothing found");
			
			//System.out.println(resources);
			//List<LinkDTO> linkDTOs = resourceService.getUserLinkResources(username);
			//List<DocumentDTO> documentDTOs = resourceService.getUserDocumentResources(username);
			mView.addObject("topicDTO", new TopicDTO()).addObject("resources", resources);
			//mView.addObject("linkResources", linkDTOs)
					//.addObject("documentResources", documentDTOs);
			mView.addObject("user", userdto);
			mView.addObject("topics", topicDTOs);
			mView.addObject("trendTopics", trendTopics);
			mView.addObject("subscriptions", subscriptionDTOs);
			mView.addObject("documentDTO", new DocumentDTO());
			mView.addObject("linkDTO", new LinkDTO());
			if (topicDTOs != null && subscriptionDTOs != null)
				mView.addObject("topicSize", topicDTOs.size()).addObject("subscriptionSize", subscriptionDTOs.size());
			return mView;
		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		context=arg0;
	}

	

}
