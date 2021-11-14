package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.vo.Rq;

@Component
public class NeedLoginInterceptor implements HandlerInterceptor{
	
	private Rq rq;
	
	public NeedLoginInterceptor(Rq rq) {
		this.rq = rq;
	}
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		
		if(!rq.isLogined()) {
			rq.printHistoryBackJs("해당 서비스는 로그인 후 이용 가능 합니다.");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
