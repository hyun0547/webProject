package com.sbs.exam.demo.service;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.MemberRepository;

@Service
public class MemberService {
	MemberRepository repository;
	
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}

	public String doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		repository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		return "가입 성공";
	}

}
