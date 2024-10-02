package com.model2.mvc.common.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.model2.mvc.service.domain.User;

public class RoleCheckInterceptor implements HandlerInterceptor {

	public RoleCheckInterceptor() {
		System.out.println("\n�� Interceptor :: "+getClass().getSimpleName()+" default constructor call");
	}
	
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response, 
							Object handler) throws ServletException, IOException {
		
		System.out.println("\n[ RoleCheckInterceptor ]\n");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		// menu, fnc, user.role
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		// product interceptor
		if (uri.contains("product")) {
			
		} 
		
		// purchase interceptor
		else if (uri.contains("purchase")) {
			
			if (uri.indexOf("listPurchase") != -1) {
				if (user == null || !user.getRole().equals("user")) {
					
					System.out.println("Error : �α����� ȸ���� �����̷��� �� �� �ֽ��ϴ�.");
					request.getRequestDispatcher("/").forward(request, response);
					System.out.println("\n=========================\n");
					
					return false; 
				}
			}
			
		}
		
		System.out.println("\n=========================\n");
		
		return true;
	}

}
