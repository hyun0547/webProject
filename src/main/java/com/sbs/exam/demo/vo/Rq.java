package com.sbs.exam.demo.vo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.UriEncoder;

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
	public void printReplaceJs(String msg, String uri) {
		String script = Utility.jsReplace(msg, uri); 
				
		print(script);
	}

	public void printHistoryBackJs(String msg) {
		String script = Utility.jsHistoryBack(msg); 
				
		print(script);
	}
	
	public void print(String script) {
		try {
			res.setContentType("text/html;charset=utf-8");
			res.getWriter().append(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeSession(String delSession) {
		session.removeAttribute(delSession);
	}

	public void setSession(String key, Object arg) {
		session.setAttribute(key, arg);
	}
	
	public Object getSession(String key) {
		return session.getAttribute(key);
	}
	
	public String getEncodedUri(){
		try {
			return URLEncoder.encode(getCurrentUri(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getCurrentUri(){
		return req.getRequestURI();
	}
}
