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
		if(repository.loginIdOverlap(loginId) > 0) {
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
		if(repository.emailOverlap(email) > 0) {
			return "이미 가입한 email 입니다.";
		}
		if(repository.userOverlap(name, cellphoneNo) > 3) {
			return "한사람당 최대 3개의 계정만 생성 가능합니다.";
		}
		
		repository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		return repository.getMember(repository.getLastInsertId());
	}

}
