package com.sbs.exam.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sbs.exam.demo.vo.Member;

import lombok.Getter;

public class Rq {
	
	@Getter
	private boolean isLogined;
	@Getter
	private Member loginedMember;
	
	public Rq(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("loginedMember");
		
		if(member != null) {
			this.isLogined = true;
			this.loginedMember = member;
		}
		
	}
}
