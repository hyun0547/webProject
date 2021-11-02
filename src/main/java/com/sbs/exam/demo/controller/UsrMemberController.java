package com.sbs.exam.demo.controller;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
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
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		HashMap<String, String> joinParam = new HashMap<>();
		joinParam.put("loginId", loginId);
		joinParam.put("loginPw", loginPw);
		joinParam.put("name", name);
		joinParam.put("nickname", nickname);
		joinParam.put("cellphoneNo", cellphoneNo);
		joinParam.put("email", email);
		
		Iterator<String> keys = joinParam.keySet().iterator();
		while(keys.hasNext() ){
			String key = keys.next();
			if(Utility.checkNull(joinParam.get(key))) {
				return ResultData.from("F-1", Utility.f("%s 값을 입력하세요", key));
			}
		}
		return service.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
	}
	
}