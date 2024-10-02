package com.model2.mvc.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.model2.mvc.common.web.LogonCheckInterceptor;
import com.model2.mvc.common.web.RoleCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public WebConfig() {
		System.out.println("\n▶ "+getClass().getSimpleName()+" default constructor");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// URL Pattern 을 확인하고. interceptor 적용유무 등록함.
		registry.addInterceptor( new LogonCheckInterceptor()).addPathPatterns("/user/**");
		
		// product와 purchase에 대한 Interceptor 추가
		registry.addInterceptor(new RoleCheckInterceptor())
								.addPathPatterns("/product/**")
								.addPathPatterns("/purchase/**");
	}
	
}
