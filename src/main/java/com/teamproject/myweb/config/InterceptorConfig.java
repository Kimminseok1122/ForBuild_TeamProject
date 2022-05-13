package com.teamproject.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.teamproject.myweb.interceptor.CheckHandler;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Bean
	public CheckHandler checkHandler() {
		return new CheckHandler();
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor( checkHandler() )
				.addPathPatterns("/user/*")
				.addPathPatterns("/board/review*")
				.addPathPatterns("/board/debate*")
				.addPathPatterns("/board/free*")
				.excludePathPatterns("/board/debateBoard")
				.excludePathPatterns("/board/reviewDetail")
				.excludePathPatterns("/board/reviewBoard")
				.excludePathPatterns("/board/freeBoard")
				.excludePathPatterns("/board/freeBoardDetail")
				.excludePathPatterns("/user/userLogin")
				.excludePathPatterns("/user/userJoin")
				.excludePathPatterns("/user/joinform")
				.excludePathPatterns("/user/userFind")
				.excludePathPatterns("/user/userCheck")
				.excludePathPatterns("/user/examine");
		 
	}
	
	
}
