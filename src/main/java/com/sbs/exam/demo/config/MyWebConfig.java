package com.sbs.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.sbs.exam.demo.interceptor.BeforeActionInterceptor;
import com.sbs.exam.demo.interceptor.NeedLoginInterceptor;
import com.sbs.exam.demo.interceptor.NeedLogoutInterceptor;


@Configuration
public class MyWebConfig implements WebMvcConfigurer{
	
	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	@Autowired
	NeedLoginInterceptor needLoginInterceptor;
	@Autowired
	NeedLogoutInterceptor needLogoutInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration beforeActionIr = registry.addInterceptor(beforeActionInterceptor);
		beforeActionIr.addPathPatterns("/**").excludePathPatterns("/resource/**");
		
		InterceptorRegistration needLoginIr = registry.addInterceptor(needLoginInterceptor);
		needLoginIr.addPathPatterns("/usr/article/write**").addPathPatterns("/usr/article/doDelete**")
		.addPathPatterns("/usr/article/doModify**");
		
		InterceptorRegistration needLogoutIr = registry.addInterceptor(needLogoutInterceptor);
		needLogoutIr.addPathPatterns("/usr/member/showLogin**").addPathPatterns("/usr/member/login**");
	}
}
