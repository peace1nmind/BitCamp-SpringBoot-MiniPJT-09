package com.model2.mvc.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.model2.mvc.common.web.LogonCheckInterceptor;
import com.model2.mvc.common.web.RoleCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	public WebConfig() {
		System.out.println("\n�� "+getClass().getSimpleName()+" default constructor");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// URL Pattern �� Ȯ���ϰ�. interceptor �������� �����.
		registry.addInterceptor( new LogonCheckInterceptor()).addPathPatterns("/user/**");
		
		// product�� purchase�� ���� Interceptor �߰�
		registry.addInterceptor(new RoleCheckInterceptor())
								.addPathPatterns("/product/**")
								.addPathPatterns("/purchase/**");
	}
	
}
