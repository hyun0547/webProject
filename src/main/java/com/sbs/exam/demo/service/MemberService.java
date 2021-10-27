package com.sbs.exam.demo.service;

import org.springframework.stereotype.Service;
import com.sbs.exam.demo.repository.MemberRepository;
import com.sbs.exam.demo.vo.Member;

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
		repository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		return repository.getMember(repository.getLastInsertId());
	}

}
