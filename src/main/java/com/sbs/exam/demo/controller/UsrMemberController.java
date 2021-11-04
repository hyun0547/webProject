package com.sbs.exam.demo.controller;

import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.util.Utility;
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
	@RequestMapping("/usr/member/login")
	@ResponseBody
	public ResultData<Member> login(HttpSession session, String loginId, String loginPw){
		Member loginedMember = (Member) session.getAttribute("loginedMember");
		if(loginedMember != null) {
			return ResultData.from("F-3", Utility.f("이미 %s 계정으로 로그인 되어 있습니다.", loginedMember.getLoginId()));
		};
		
		return service.login(session, loginId, loginPw);
	}
	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public ResultData<Member> logout(HttpSession session){
		Member loginedMember = (Member) session.getAttribute("loginedMember");
		if(loginedMember == null) {
			return ResultData.from("F-1","현재 로그인 되어있는 계정이 없습니다.");
		};
		
		session.removeAttribute("loginedMember");
		return ResultData.from("S-1", "로그아웃 되었습니다.");
	}
	
	
}