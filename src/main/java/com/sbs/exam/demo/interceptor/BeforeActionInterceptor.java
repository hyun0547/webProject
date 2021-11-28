package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor{
	
	private Rq rq;
	
	public BeforeActionInterceptor(Rq rq) {
		this.rq = rq;
	}
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		if(req.getParameter("privateType") != null){
			if(req.getParameter("privateType").equals("1") && !rq.isLogined()) {
				rq.printReplaceJs("로그인 후 이용해 주세요", "/usr/member/showLogin");
				return false;
			}
		}
		
		req.setAttribute("rq", rq);
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
