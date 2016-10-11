package com.ttnd.linksharing.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttnd.linksharing.dto.DocumentDTO;
import com.ttnd.linksharing.dto.LinkDTO;
import com.ttnd.linksharing.dto.ResourceDTO;
import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.services.impl.ResourceServiceImpl;
import com.ttnd.linksharing.services.impl.SubscriptionServiceImpl;
import com.ttnd.linksharing.services.impl.TopicServiceImpl;
import com.ttnd.linksharing.services.impl.UserServiceImpl;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	ResourceServiceImpl resourceService;
	@Autowired
	UserServiceImpl service;
	@Autowired
	SubscriptionServiceImpl subService;
	@Autowired
	TopicServiceImpl topicService;

	@Autowired
	RootController rootController;
	@Autowired
	UserController userController;

	@RequestMapping("/create/document")
	public ModelAndView createDoc(@ModelAttribute("documentDTO") DocumentDTO documentDTO,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {

		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		resourceService.saveDocument(documentDTO, userdto.getUserName(), file);
		ModelAndView mView = userController.login(request, userdto.getUserName(), userdto.getPassword());
		return mView;
		/*
		 * ModelAndView mView = new ModelAndView("profile");
		 * List<SubscriptionDTO> subscriptionDTOs =
		 * subService.getUserSubscriptions(userdto.getUserName());
		 * List<TopicDTO> topicDTOs =
		 * service.getUserTopics(userdto.getUserName()); List<TopicDTO>
		 * trendTopics = topicService.getTrendTopics(userdto.getUserName());
		 * List<LinkDTO> linkDTOs =
		 * resourceService.getUserLinkResources(userdto.getUserName());
		 * List<DocumentDTO> documentDTOs =
		 * resourceService.getUserDocumentResources(userdto.getUserName()); //
		 * System.out.println(trendTopics); // System.out.println(linkDTOs);
		 * mView.addObject("topicDTO", new
		 * TopicDTO()).addObject("linkResources", linkDTOs)
		 * .addObject("documentResources", documentDTOs);
		 * mView.addObject("user", userdto); mView.addObject("topics",
		 * topicDTOs); mView.addObject("trendTopics", trendTopics);
		 * mView.addObject("subscriptions", subscriptionDTOs);
		 * mView.addObject("documentDTO", new DocumentDTO());
		 * mView.addObject("linkDTO", new LinkDTO()); if (topicDTOs != null &&
		 * subscriptionDTOs != null) mView.addObject("topicSize",
		 * topicDTOs.size()).addObject("subscriptionSize",
		 * subscriptionDTOs.size()); return mView;
		 */

	}

	@RequestMapping("/create/link")
	public ModelAndView createLink(@ModelAttribute("linkDTO") LinkDTO linkDTO, HttpServletRequest request) {
		// System.out.println("Mapped");
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");

		// System.out.println(linkDTO.getTopicMap());
		resourceService.saveLink(linkDTO, userdto.getUserName());
		ModelAndView mView = userController.login(request, userdto.getUserName(), userdto.getPassword());
		return mView;

		/*
		 * ModelAndView mView = new ModelAndView("profile");
		 * List<SubscriptionDTO> subscriptionDTOs =
		 * subService.getUserSubscriptions(userdto.getUserName());
		 * List<TopicDTO> topicDTOs =
		 * service.getUserTopics(userdto.getUserName()); List<TopicDTO>
		 * trendTopics = topicService.getTrendTopics(userdto.getUserName());
		 * List<LinkDTO> linkDTOs =
		 * resourceService.getUserLinkResources(userdto.getUserName());
		 * List<DocumentDTO> documentDTOs =
		 * resourceService.getUserDocumentResources(userdto.getUserName()); //
		 * System.out.println(trendTopics); // System.out.println(linkDTOs);
		 * mView.addObject("topicDTO", new
		 * TopicDTO()).addObject("linkResources", linkDTOs)
		 * .addObject("documentResources", documentDTOs);
		 * mView.addObject("user", userdto); mView.addObject("topics",
		 * topicDTOs); mView.addObject("trendTopics", trendTopics);
		 * mView.addObject("subscriptions", subscriptionDTOs);
		 * mView.addObject("documentDTO", new DocumentDTO());
		 * mView.addObject("linkDTO", new LinkDTO()); if (topicDTOs != null &&
		 * subscriptionDTOs != null) mView.addObject("topicSize",
		 * topicDTOs.size()).addObject("subscriptionSize",
		 * subscriptionDTOs.size()); return mView;
		 */

	}

	@RequestMapping("/mark")
	@ResponseBody
	public String mark(HttpServletRequest request, @RequestParam Long resourceId) {
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		resourceService.markRead(resourceId, userdto);
		return "Done";
	}

	@RequestMapping("/vposts/{resourceId}")
	public ModelAndView viewPosts(HttpServletRequest request, @PathVariable Long resourceId) {
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		if (userdto == null) {
			return rootController.indexMap(request, null).addObject("pageMsg", "User not logged in. Log in first.");
		}
		List<TopicDTO> trendTopics = topicService.getTrendTopics(userdto.getUserName());
		ResourceDTO resourceDTO = resourceService.getResource(resourceId, userdto.getUserName());
		// System.out.println(resourceDTO);
		ModelAndView mView = new ModelAndView("posts_res");
		mView.addObject("trendingTopics", trendTopics);
		mView.addObject("user", resourceDTO.getCreatedBy());
		mView.addObject("resource", resourceDTO);
		return mView;
	}

	@RequestMapping("/update/rating")
	@ResponseBody
	public String updateRating(HttpServletRequest request, @RequestParam Long resourceId, @RequestParam Integer score) {
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		resourceService.updateRating(resourceId, score, userdto);
		return "Done";
	}

	@RequestMapping("/delete/post")
	@ResponseBody
	public String deletePost(HttpServletRequest request, @RequestParam Long resourceId) {
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		resourceService.deletePost(resourceId);
		return "Done";
	}

	@RequestMapping("/update/post")
	@ResponseBody
	public String updatePost(HttpServletRequest request, @RequestParam Long resourceId,
			@RequestParam String description) {
		HttpSession session = request.getSession();
		UserDTO userdto = (UserDTO) session.getAttribute("user");
		resourceService.updatePost(resourceId, description, userdto);
		return "Done";
	}

	@RequestMapping(value = "/download/document/{resourceId}")
	@ResponseBody
	public String doDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable Long resourceId)
			throws IOException {

		resourceService.downloadDocument(request, response, resourceId);

		return "Done";

	}

}
