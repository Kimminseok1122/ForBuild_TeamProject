package com.teamproject.myweb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.review.boardService;

public class CheckHandler implements HandlerInterceptor{

	@Autowired
	boardService boardservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		if(userVO == null) {
			System.out.println("인터셉터");
			System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath()+ "/user/userLogin");
			return false;
		} else {

			return true;
		}
		
	}
	
	

}
