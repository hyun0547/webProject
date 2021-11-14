package com.sbs.exam.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private Member loginedMember;
	@Getter
	private String loginedMemberId;
	
	public Rq(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("loginedMember");
		
		if(member != null) {
			this.isLogined = true;
			this.loginedMember = member;
			this.loginedMemberId = member.getLoginId();
		}
		
	}
}
