package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	ArticleService service;
	public UsrArticleController(ArticleService service) {
		this.service = service;
	}
	
	@RequestMapping("/usr/article/list")
	public String getArticles (Model model) {
		
		model.addAttribute("rd", service.getArticles());
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd (HttpSession session, String title, String body) {
		
		Member member = (Member) session.getAttribute("loginedMember");
		
		if(member != null) {
			return service.doAdd(title, body, member);
		}
		
		return ResultData.from("F-1", "해당 서비스는 로그인을 하셔야 이용 가능 합니다.");
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (HttpSession session, int id) {
		
		Member member = (Member) session.getAttribute("loginedMember");
		System.out.println(member);
		
		if(member != null) {
			
			ResultData<Integer> rd = service.doDelete(id, member);
			
			if(rd.isSuccess()) {
				return Utility.jsReplace(rd.getMsg(), "/usr/article/list");
			}
			
			return Utility.jsHistoryBack(rd.getMsg());
		}
		
		return Utility.jsHistoryBack("해당 서비스는 로그인을 하셔야 이용 가능 합니다.");
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify (HttpSession session, int id, String title, String body) {
		
		Member member = (Member) session.getAttribute("loginedMember");
		
		if(member != null) {
			return service.doModify(id, title, body, member);
		}
		
		return ResultData.from("F-1", "해당 서비스는 로그인을 하셔야 이용 가능 합니다.");
	}
	
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public ResultData<Article> doGetArticle (int id) {
		
		return service.getArticle(id);
	}
	
	@RequestMapping("/usr/article/detail")
	public String getForPrintArticle(HttpSession session, Model model, int id) {
		
		boolean isLogined = false;
		String loginedMemberId = "";
		Member loginedMember = (Member) session.getAttribute("loginedMember");
		
		if(loginedMember != null) {
			loginedMemberId = loginedMember.getLoginId();
			isLogined = true;
		}
		
		model.addAttribute("rd", service.getForPrintArticle(loginedMemberId, id));
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doSearch")
	@ResponseBody
	public ResultData<ArrayList<Article>> doSearch (String keyword) {
		
		return service.doSearch(keyword);
	}
	
	
}