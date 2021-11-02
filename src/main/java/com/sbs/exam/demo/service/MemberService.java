package com.sbs.exam.demo.service;

import org.springframework.stereotype.Service;
import com.sbs.exam.demo.repository.MemberRepository;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class MemberService {
	MemberRepository repository;
	
	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}

	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		if(repository.loginIdOverlap(loginId) > 0) {
			return ResultData.from("F-2", Utility.f("%s (은)는 중복된 아이디 입니다.", loginId));
		}
		if(repository.emailOverlap(email) > 0) {
			return ResultData.from("F-2", Utility.f("%s (은)는 이미 가입된 이메일 입니다.", email));
		}
		if(repository.userOverlap(name, cellphoneNo) > 3) {
			return ResultData.from("F-3", "한 사람당 최대 3개의 계정만 생성 할 수 있습니다.");
		}
		
		repository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		return ResultData.from("S-1", "가입에 성공했습니다.", repository.getMember(repository.getLastInsertId()));
	}

}
 