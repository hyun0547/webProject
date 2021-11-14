package com.sbs.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.sbs.exam.demo.util.Utility;

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
	private HttpServletRequest req;
	private HttpServletResponse res;
	HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse res) {
		this.req = req;
		this.res = res;
		this.session = req.getSession();
		
		Member member = (Member) session.getAttribute("loginedMember");
		
		if(member != null) {
			this.isLogined = true;
			this.loginedMember = member;
			this.loginedMemberId = member.getLoginId();
		}
		
	}

	public void printHistoryBackJs(String msg) {
		String script = 
				Utility.f("<script>"
						+"alert('%s');"
						+"history.back();"
						+ "</script>", msg);
		print(script);
	}
	
	public void print(String msg) {
		try {
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().append(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
