package com.ttnd.linksharing.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.ttnd.linksharing.dao.impl.ResourceDaoImpl;
import com.ttnd.linksharing.dao.impl.TopicDaoImpl;
import com.ttnd.linksharing.dao.impl.UserDaoImpl;
import com.ttnd.linksharing.dto.DocumentDTO;
import com.ttnd.linksharing.dto.LinkDTO;
import com.ttnd.linksharing.dto.ResourceDTO;
import com.ttnd.linksharing.dto.SubscriptionDTO;
import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.entities.DocumentResource;
import com.ttnd.linksharing.entities.LinkResource;
import com.ttnd.linksharing.entities.Resource;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;

public class ResourceServiceImpl implements ServletContextAware {

	@Autowired
	ResourceDaoImpl resourceDao;
	@Autowired
	TopicDaoImpl topicDao;

	@Autowired
	UserDaoImpl userDao;

	ServletContext context;

	public Long saveDocument(DocumentDTO documentDTO, String userName, MultipartFile file) {
		DocumentResource document = getDocument(documentDTO);
		try {
			String webPath = context.getRealPath("/");
			// new File(webPath + "/Topics/" + documentDTO.getTopic() +
			// "/documents/" ).mkdirs();
			// System.out.println(documentDTO.getTopic()==null);
			Topic topic = topicDao.getTopic(documentDTO.getTopicId());
			Date date = new Date();
			File filem = new File(webPath + "/../../webapps/Topics/" + topic.getName() + "/documents/" + userName
					+ date);
			document.setFilePath("/Topics/" + topic.getName() + "/documents/" + userName + "" +date);
			filem.createNewFile();
			file.transferTo(filem);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return resourceDao.saveDocument(document, userName, documentDTO.getTopicId());
	}

	private DocumentResource getDocument(DocumentDTO documentDTO) {
		DocumentResource document = new DocumentResource();
		document.setDescription(documentDTO.getDescription());
		return document;
	}

	@Override
	public void setServletContext(ServletContext arg0) {

		context = arg0;

	}

	public ResourceDaoImpl getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDaoImpl resourceDao) {
		this.resourceDao = resourceDao;
	}

	public Long saveLink(LinkDTO linkDTO, String userName) {

		LinkResource link = getLink(linkDTO);
		// System.out.println(link+"save");
		return resourceDao.saveLink(link, userName, linkDTO.getTopicId());
	}

	private LinkResource getLink(LinkDTO linkDTO) {
		LinkResource link = new LinkResource();
		link.setDescription(linkDTO.getDescription());
		link.setUrl(linkDTO.getUrl());
		return link;
	}

	/*
	 * public List<LinkDTO> getUserLinkResources(String userName){
	 * List<LinkResource> resources =
	 * resourceDao.getUserLinkResources(userName); if(resources==null) return
	 * null; List<LinkDTO> linkDTOs = new ArrayList<>();
	 * 
	 * for(LinkResource resource : resources){
	 * linkDTOs.add(getLinkDTO(resource)); } return linkDTOs; }
	 */

	public List<ResourceDTO> getResources(List<SubscriptionDTO> subscriptionDTOs, UserDTO userDto) {
		//System.out.println(topics);
		List<SubscriptionDTO> subscriptions = new LinkedList<>(subscriptionDTOs);
		User user = userDao.retrieveUser(userDto.getUserName(), userDto.getPassword());
		List<ResourceDTO> resources = new LinkedList<ResourceDTO>();
		Collections.sort(subscriptions, new Comparator<SubscriptionDTO>() {
			public int compare(SubscriptionDTO o1, SubscriptionDTO o2) {
				return o1.getSeriousness().compareTo(o2.getSeriousness());
			}
		});
		for (SubscriptionDTO subscriptionDto : subscriptions) {
			Topic topic = subscriptionDto.getTopic();
			List<Resource> resource = resourceDao.getResources(topic);
			//for(Resource resource2:resource)
				//System.out.println(resource2);
			for (Resource res : resource) {
				if (!resourceDao.isResourceReaded(res, user)) {
					if (res instanceof LinkResource) {
						ResourceDTO resourceDto = getLinkDTO((LinkResource) res);
						resourceDto.setType("link");
						resources.add(resourceDto);
					} else {
						ResourceDTO resourceDto = getDocumentDTO((DocumentResource) res);
						resourceDto.setType("document");
						resources.add(resourceDto);
					}
				}
			}
		}
		
		return resources;
	}

	private ResourceDTO getLinkDTO(LinkResource resource) {
		ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getDescription(), resource.getCreatedBy(),
				resource.getTopic(), resource.getUrl());
		return resourceDTO;
	}

	/*
	 * public List<DocumentDTO> getUserDocumentResources(String userName){
	 * List<DocumentResource> resources =
	 * resourceDao.getUserDocumentResources(userName); if(resources==null)
	 * return null; List<DocumentDTO> documentDTOs = new ArrayList<>();
	 * 
	 * for(DocumentResource resource : resources){
	 * documentDTOs.add(getDocumentDTO(resource)); } return documentDTOs; }
	 */

	private ResourceDTO getDocumentDTO(DocumentResource resource) {
		//System.out.println(resource);
		ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getDescription(), resource.getCreatedBy(),
				resource.getTopic(), resource.getFilePath());
		return resourceDTO;
	}

	public void markRead(Long resourceId, UserDTO userdto) {
		resourceDao.markRead(resourceId, userdto.getId());
	}

	public List<ResourceDTO> getRecentResources() {
		List<ResourceDTO> resourceDTOs = new LinkedList<>();
		List<Resource> resources = resourceDao.getRecentResources();
		for (Resource resource : resources) {

			if(resource instanceof LinkResource){
				ResourceDTO resourceDto=getLinkDTO((LinkResource)resource);
				resourceDto.setType("link");
				resourceDTOs.add(resourceDto);
				}
				else{
				ResourceDTO resourceDto=getDocumentDTO((DocumentResource)resource);
				resourceDto.setType("document");
				resourceDTOs.add(resourceDto);
				}
		}
		return resourceDTOs;
	}

	public List<ResourceDTO> getTopPosts() {
		List<ResourceDTO> resourceDTOs = new LinkedList<>();
		List<Resource> resources = resourceDao.getTopPosts();
		for (Resource resource : resources) {

			if(resource instanceof LinkResource){
				ResourceDTO resourceDto=getLinkDTO((LinkResource)resource);
				resourceDto.setType("link");
				resourceDTOs.add(resourceDto);
				}
				else{
				ResourceDTO resourceDto=getDocumentDTO((DocumentResource)resource);
				resourceDto.setType("document");
				resourceDTOs.add(resourceDto);
				}
		}
		return resourceDTOs;
	}

	public ResourceDTO getResource(Long resourceId, String userName) {
		Resource resource = resourceDao.getResource(resourceId);
		Integer rate = resourceDao.getResourceRating(resourceId, userName);
		ResourceDTO resourceDTO;
		
		if (resource instanceof LinkResource) {
			resourceDTO = getLinkDTO((LinkResource) resource);
			resourceDTO.setType("link");
			//resources.add(resourceDto);
		} else {
			resourceDTO = getDocumentDTO((DocumentResource) resource);
			resourceDTO.setType("document");
			//resources.add(resourceDto);
		}
		resourceDTO.setRating(rate);
		return resourceDTO;
	}

	public Integer getResourceRating(Long resourceId, String userName) {
		return resourceDao.getResourceRating(resourceId, userName);
		
	}

	public void updateRating(Long resourceId, Integer score, UserDTO userdto) {
		
		resourceDao.updateRating(resourceId, score, userdto.getEmail());
		
	}

	public void deletePost(Long resourceId) {
		resourceDao.deletePost(resourceId);
	}

	public void updatePost(Long resourceId,String description,  UserDTO userdto) {
		resourceDao.updatePost(resourceId, description);
		
	}

	public void downloadDocument(HttpServletRequest request, HttpServletResponse response, Long resourceId) throws IOException  {
		final int BUFFER_SIZE = 4096;
        
		   DocumentResource resource = (DocumentResource)resourceDao.getResource(resourceId);
	     String filePath = resource.getFilePath();
	
   // get absolute path of the application
   ServletContext context = request.getSession().getServletContext();
   String appPath = context.getRealPath("/");
   appPath+="../../webapps/";
   
   System.out.println("appPath = " + appPath);

   // construct the complete absolute path of the file
   String fullPath = appPath + filePath;      
   File downloadFile = new File(fullPath);
   FileInputStream inputStream = new FileInputStream(downloadFile);
    
   // get MIME type of the file
   String mimeType = context.getMimeType(fullPath);
   if (mimeType == null) {
       // set to binary type if MIME mapping not found
       mimeType = "application/octet-stream";
   }
   System.out.println("MIME type: " + mimeType);

   // set content attributes for the response
   response.setContentType(mimeType);
   response.setContentLength((int) downloadFile.length());

   // set headers for the response
   String headerKey = "Content-Disposition";
   String headerValue = String.format("attachment; filename=\"%s\"",
           downloadFile.getName());
   response.setHeader(headerKey, headerValue);

   // get output stream of the response
   OutputStream outStream = response.getOutputStream();

   byte[] buffer = new byte[BUFFER_SIZE];
   int bytesRead = -1;

   // write bytes read from the input stream into the output stream
   while ((bytesRead = inputStream.read(buffer)) != -1) {
       outStream.write(buffer, 0, bytesRead);
   }

   inputStream.close();
   outStream.close();
	}

	public List<ResourceDTO> getResourcesSearch(List<SubscriptionDTO> subscriptionDTOs, UserDTO userDto,
			String searchKeys) {
		User user = userDao.retrieveUser(userDto.getUserName(), userDto.getPassword());
		List<ResourceDTO> resources = new LinkedList<ResourceDTO>();
		//System.out.println("user : " + user + "\n userAdmin : " + user.getAdmin());
		if(user.getAdmin()!=null){
			List<Topic> topics = (List<Topic>) topicDao.retrieveAllTopics();
			System.out.println(topics);
			/*Collections.sort(topics, new Comparator<Topic>() {
				public int compare(Topic o1, SubscriptionDTO o2) {
					return o1.getSeriousness().compareTo(o2.getSeriousness());
				}
			});*/
			
			for(Topic topic : topics){
				
List<Resource> resource = resourceDao.getResourcesSearch(topic, searchKeys, user.getAdmin());
				
				if(resource==null)
					continue;
				
				
				for (Resource res : resource) {
					if (!resourceDao.isResourceReaded(res, user)) {
						if (res instanceof LinkResource) {
							ResourceDTO resourceDto = getLinkDTO((LinkResource) res);
							resourceDto.setType("link");
							resources.add(resourceDto);
						} else {
							ResourceDTO resourceDto = getDocumentDTO((DocumentResource) res);
							resourceDto.setType("document");
							resources.add(resourceDto);
						}
					}
				}
				
			}
			System.out.println("Admin resources returned\n " + resources);
			return resources;
			
		}else{
		
		List<SubscriptionDTO> subscriptions = new LinkedList<>(subscriptionDTOs);
			
			
			Collections.sort(subscriptions, new Comparator<SubscriptionDTO>() {
				public int compare(SubscriptionDTO o1, SubscriptionDTO o2) {
					return o1.getSeriousness().compareTo(o2.getSeriousness());
				}
			});
			for (SubscriptionDTO subscriptionDto : subscriptions) {
				Topic topic = subscriptionDto.getTopic();
				List<Resource> resource = resourceDao.getResourcesSearch(topic, searchKeys, user.getAdmin());
				
				if(resource==null)
					continue;
				
				//for(Resource resource2:resource)
					//System.out.println(resource2);
				for (Resource res : resource) {
					if (!resourceDao.isResourceReaded(res, user)) {
						if (res instanceof LinkResource) {
							ResourceDTO resourceDto = getLinkDTO((LinkResource) res);
							resourceDto.setType("link");
							resources.add(resourceDto);
						} else {
							ResourceDTO resourceDto = getDocumentDTO((DocumentResource) res);
							resourceDto.setType("document");
							resources.add(resourceDto);
						}
					}
				}
			}
			System.out.println("Non-Admin resources returned\n " + resources);
			return resources;
		}
	}

	/*private ResourceDTO getResourceDTO(Resource resource) {
		ResourceDTO resourceDTO = new ResourceDTO(resource.getId(), resource.getDescription(), resource.getCreatedBy(), resource.getTopic(), null);
		return null;
	}*/

}
