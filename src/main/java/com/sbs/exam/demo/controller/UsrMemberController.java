package com.sbs.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;


@Controller
public class UsrMemberController {
	MemberService service; 
	
	public UsrMemberController(MemberService service) {
		this.service = service;
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		return service.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
	}
	
}