package com.sbs.exam.demo.controller;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;


@Controller
public class UsrMemberController {
	MemberService service; 
	Rq rq;
	
	public UsrMemberController(MemberService service, Rq rq) {
		this.service = service;
		this.rq = rq;
	}
	
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(@ModelAttribute Member newMember ) {
		HashMap<String, String> joinParam = new HashMap<>();
		joinParam.put("loginId", newMember.getLoginId());
		joinParam.put("loginPw", newMember.getLoginPw());
		joinParam.put("name", newMember.getName());
		joinParam.put("nickname", newMember.getNickname());
		joinParam.put("cellphoneNo", newMember.getCellphoneNo());
		joinParam.put("email", newMember.getEmail());
		
		Iterator<String> keys = joinParam.keySet().iterator();
		while(keys.hasNext() ){
			String key = keys.next();
			if(Utility.checkNull(joinParam.get(key))) {
				return ResultData.from("F-1", Utility.f("%s 값을 입력하세요", key));
			}
		}
		return service.doJoin(newMember);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw){
		if(rq.isLogined()) {
			return Utility.jsReplace("이미 로그인 되어 있습니다.", "/");
		};
		
		ResultData<Member> rd = service.doLogin(loginId, loginPw);
		
		if(rd.isFail()) {
			return Utility.jsHistoryBack(rd.getMsg());
		}
		
		Member loginedMember = rd.getData1();
		
		req.getSession().setAttribute("loginedMember", loginedMember);
		return Utility.jsReplace(Utility.f("%s님 안녕하세요.", loginedMember.getNickname()), "/");
		
	}
	
	@RequestMapping("/usr/member/showLogin")
	public String showLogin(String loginId, String loginPw){
		
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public String logout(HttpSession session){
		if(!rq.isLogined()) {
			return Utility.jsHistoryBack("현재 로그인 되어있는 계정이 없습니다.");
		};
		
		session.removeAttribute("loginedMember");
		return Utility.jsReplace("로그아웃 되었습니다.", "/");
	}
	
	
}