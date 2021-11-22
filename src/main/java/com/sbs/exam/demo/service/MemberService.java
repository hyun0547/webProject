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

	public ResultData<Member> doJoin(Member newMember) {
		if(repository.loginIdOverlap(newMember.getLoginId()) > 0) {
			return ResultData.from("F-2", Utility.f("%s (은)는 중복된 아이디 입니다.", newMember.getLoginId()));
		}
		if(repository.emailOverlap(newMember.getEmail()) > 0) {
			return ResultData.from("F-2", Utility.f("%s (은)는 이미 가입된 이메일 입니다.", newMember.getEmail()));
		}
		if(repository.userOverlap(newMember.getName(), newMember.getCellphoneNo()) > 3) {
			return ResultData.from("F-3", "한 사람당 최대 3개의 계정만 생성 할 수 있습니다.");
		}
		
		repository.doJoin(newMember.getLoginId(), newMember.getLoginPw(), newMember.getName(), newMember.getNickname(), newMember.getCellphoneNo(), newMember.getEmail());
		Member joinedMember = repository.getMember(newMember.getLoginId());
		return ResultData.from("S-1", "가입에 성공했습니다.", joinedMember.getClass().getSimpleName(), joinedMember);
	}

	public ResultData<Member> doLogin(String loginId, String loginPw) {
		Member member = repository.getMember(loginId);
		
		if(member == null) {
			
			return ResultData.from("F-1", "존재하지 않는 아이디 입니다.");
		}
		
		else if(member.getLoginPw().equals(loginPw)) {
			
			return ResultData.from("S-1", Utility.f("%s님 안녕하세요", member.getNickname()), member.getClass().getSimpleName(), member);
		}
		
		return ResultData.from("F-2", "비밀번호를 확인해 주세요");
	}
	
	public ResultData<Object> getLoginIdDup(String loginId) {
		if(repository.loginIdOverlap(loginId) > 0) {
			return ResultData.from("F-1", "이미 사용중인 아이디 입니다.");
		}
		
		return ResultData.from("S-1", "사용 가능한 아이디 입니다.");
	}

}
 