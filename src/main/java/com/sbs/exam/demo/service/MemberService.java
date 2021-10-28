package com.sbs.exam.demo.service;

import org.springframework.stereotype.Service;
import com.sbs.exam.demo.repository.MemberRepository;
import com.sbs.exam.demo.util.Utility;

@Service
public class MemberService {
	MemberRepository repository;
	
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}

	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		if(repository.checkOverlap(loginId) > 0) {
			return "중복된 아이디 입니다.";
		}
		Utility ut = new Utility();
		if(ut.checkNull(loginId)) {
			return "loginId 값을 입력하세요";
		}
		if(ut.checkNull(loginPw)) {
			return "loginPw 값을 입력하세요";
		}
		if(ut.checkNull(name)) {
			return "name 값을 입력하세요";
		}
		if(ut.checkNull(nickname)) {
			return "nickname 값을 입력하세요";
		}
		if(ut.checkNull(cellphoneNo)) {
			return "cellphoneNo 값을 입력하세요";
		}
		if(ut.checkNull(email)) {
			return "email 값을 입력하세요";
		}
		
		repository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		return repository.getMember(repository.getLastInsertId());
	}

}
