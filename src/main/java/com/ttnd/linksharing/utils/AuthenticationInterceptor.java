package com.ttnd.linksharing.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.ttnd.linksharing.dto.UserDTO;
import com.ttnd.linksharing.entities.User;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	 public boolean preHandle(HttpServletRequest request,
	   HttpServletResponse response, Object handler) throws Exception
	 {
	  
	   UserDTO userData = (UserDTO) request.getSession().getAttribute("user");
	  /* System.out.println("InPreHandle");
	   System.out.println(userData);*/
	   if(userData == null)
	   {
		   //System.out.println("inside authenticate.");
		  // System.out.println(request.getContextPath());
		  response.sendRedirect("/");
		  // request.getRequestDispatcher("/welcome").forward(request, response);
	  // rootController.indexMap(request, "Please login.");
	    return false;
	   }   
	  return true;
	 }

}
