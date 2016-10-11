package com.ttnd.linksharing.services.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.ttnd.linksharing.dao.impl.UserDaoImpl;
import com.ttnd.linksharing.dto.TopicDTO;
import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.entities.Topic;
import com.ttnd.linksharing.entities.User;
import com.ttnd.linksharing.events.publishers.RegistrationPublisher;
import com.ttnd.linksharing.services.UserService;

public class UserServiceImpl implements UserService, ServletContextAware {

	UserDaoImpl userDao;
	@Autowired
	RegistrationPublisher publisher;

	ServletContext context;

	@Autowired
	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public void addProfile(String userName) {
		String webPath = context.getRealPath("/");
		new File(webPath + "/../../webapps/profiles/" + userName).mkdirs();
	}

	public void addPhoto(String userName, MultipartFile file) {
		try {
			addProfile(userName);
			String webPath = context.getRealPath("/");
			File filem = new File(webPath + "/../../webapps/profiles/" + userName + "/ProfilePic");
			filem.createNewFile();
			file.transferTo(filem);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public List<TopicDTO> getUserTopics(String userName) {
		Set<Topic> topics = userDao.getUserTopics(userName);
		List<TopicDTO> topicDTOs = new ArrayList<TopicDTO>();
		for (Topic topic : topics) {
			topicDTOs.add(getTopicDTO(topic));
		}
		return topicDTOs;
	}

	private TopicDTO getTopicDTO(Topic topic) {
		TopicDTO topicDTO = new TopicDTO(topic.getName(), topic.getVisibility());
		topicDTO.setId(topic.getId());
		return topicDTO;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long saveUser(UserDTO userdto) {
		Long id = userDao.saveUser(getUser(userdto));
		if (id != null) {
			publisher.publish(userdto.getUserName(), userdto.getEmail());
		}
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserDTO getUser(String username, String password) {
		User user = userDao.retrieveUser(username, password);
		if (user == null)
			return null;
		return getuserdto(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UserDTO verifyUser(String userName) {
		UserDTO userVerified = getuserdto(userDao.verifyUser(userName));
		return userVerified;
	}

	private UserDTO getuserdto(User userdto) {
		UserDTO usDto = new UserDTO(userdto.getEmail(), userdto.getUserName(), userdto.getPassword(),
				userdto.getFirstName(), userdto.getLastName(), userdto.getPhoto(), userdto.getAdmin(),
				userdto.getActive(), userdto.getDateCreated(), userdto.getLastUpdated());
		usDto.setId(userdto.getId());
		return usDto;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private User getUser(UserDTO userdto) {

		return new User(userdto.getEmail(), userdto.getUserName(), userdto.getPassword(), userdto.getFirstName(),
				userdto.getLastName(), null, userdto.getAdmin(), userdto.getActive(), userdto.getDateCreated(),
				userdto.getLastUpdated());
	}

	public Boolean isUserActive(String userName, String password) {
		return userDao.isUserActive(userName, password);
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		context = arg0;

	}


	public Boolean resetUserPass(String email, String newpass) {
		
		User user = userDao.getUserEmail(email);
		if(user==null){
			return null;
		}
		
		Boolean change = userDao.changePass(email, newpass);
		return change;
		
	}

	public UserDTO getUserEmail(String email) {
		User user = userDao.getUserEmail(email);
		return getuserdto(user);
	}

}
