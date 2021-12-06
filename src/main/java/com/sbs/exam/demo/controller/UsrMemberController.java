package com.sbs.exam.demo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import com.sbs.exam.demo.service.GenFileService;
import com.sbs.exam.demo.service.MemberService;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.GenFile;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;


@Controller
public class UsrMemberController {
	GenFileService genFileService;
	MemberService memberService; 
	Rq rq;
	
	public UsrMemberController(MemberService memberService, Rq rq, GenFileService genFileService) {
		this.genFileService = genFileService;
		this.memberService = memberService;
		this.rq = rq;
	}
	
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(@ModelAttribute Member newMember) {
		ResultData<Member> rd = null;
		
		HashMap<String, String> joinParam = new HashMap<>();
		joinParam.put("cellphoneNo", newMember.getCellphoneNo());
		joinParam.put("email", newMember.getEmail());
		joinParam.put("nickname", newMember.getNickname());
		joinParam.put("name", newMember.getName());
		joinParam.put("loginPw", newMember.getLoginPw());
		joinParam.put("loginId", newMember.getLoginId());
		
		Iterator<String> keys = joinParam.keySet().iterator();
		while(keys.hasNext() ){
			String key = keys.next();
			if(Utility.checkNull(joinParam.get(key))) {
				rd = ResultData.from("F-1", Utility.f("%s 값을 입력하세요", key));
			}
		}
		
		if(rd == null) {
			rd = memberService.doJoin(newMember);
		}
		
		if(rd.isSuccess()){
			return Utility.jsReplace(rd.getMsg(), "/usr/member/showLogin");
		}
		
		
		return Utility.jsHistoryBack(rd.getMsg());
	}
	
	@RequestMapping("/usr/member/showJoin")
	public String showJoin(){
		
		return "/usr/member/join";
	}
	
	@RequestMapping("/usr/member/getLoginIdDup")
	@ResponseBody
	public String getLoginIdDup(String loginId){
		
		ResultData<Object> rd = memberService.getLoginIdDup(loginId);
		return rd.getMsg();
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, String afterLoginUri){
		if(rq.isLogined()) {
			return Utility.jsReplace("이미 로그인 되어 있습니다.", "/");
		};
		
		ResultData<Member> rd = memberService.doLogin(loginId, loginPw);
		
		if(rd.isFail()) {
			return Utility.jsHistoryBack(rd.getMsg());
		}
		
		Member loginedMember = rd.getData1();
		
		rq.setSession("loginedMember", loginedMember);
		
		System.out.println(afterLoginUri);
		
		if(Utility.checkNull(afterLoginUri)) {
			afterLoginUri = "/";
		}
		
		return Utility.jsReplace(Utility.f("%s님 안녕하세요.", loginedMember.getNickname()), afterLoginUri);
		
	}
	
	@RequestMapping("/usr/member/showLogin")
	public String showLogin(Model model){
		String afterLoginUri = rq.getBeforeUri();
		
		if(afterLoginUri.toUpperCase().contains("LOGIN")) {
			afterLoginUri = "/";
		}
		
		model.addAttribute("afterLoginUri", afterLoginUri);
		
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/logout")
	@ResponseBody
	public String logout(){
		if(!rq.isLogined()) {
			return Utility.jsHistoryBack("현재 로그인 되어있는 계정이 없습니다.");
		};
		
		String afterLoginUri = rq.getBeforeUri();
		
		rq.removeSession("loginedMember");
		
		return Utility.jsReplace("로그아웃 되었습니다.", afterLoginUri);
	}
	
	@RequestMapping("/usr/member/showProfile")
	public String showProfile(){
		return "/usr/member/profile";
	}
	
	@RequestMapping("/usr/member/showModifyProfile")
	public String showModifyProfile(){
		return "/usr/member/modifyProfile";
	}
	
	@RequestMapping("/usr/member/doModifyProfile")
	@ResponseBody
	public String doModifyProfile(MultipartRequest mr, String email, String nickname, String cellphoneNo, int relId){
		
		String profileImgUrl = changeProfileImg(mr, relId);
		
		ResultData<Member> modifyRd = memberService.doModify(email, nickname, cellphoneNo, profileImgUrl, rq.getLoginedMemberId());
		rq.removeSession("loginedMember");
		rq.setSession("loginedMember", modifyRd.getData1());
		
		return Utility.jsReplace("수정 되었습니다.", "/usr/member/showProfile");
	}
	
	@RequestMapping("/usr/member/changeProfileImg")
	@ResponseBody
	public String changeProfileImg(MultipartRequest mr, int relId){
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		if(fileMap.get("file__member__0__common__profile").getSize() > 0) {
			genFileService.delFile(relId, "member");
			genFileService.save(fileMap, relId);
		}
		
		GenFile profileImg = genFileService.getFileForRel(relId, "member");
		return profileImg.getForPrintDir();
	}
}