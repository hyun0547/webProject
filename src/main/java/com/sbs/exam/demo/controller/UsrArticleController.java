package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.ArticleService;
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
	public ResultData<Integer> doDelete (HttpSession session, int id) {
		Member member = (Member) session.getAttribute("loginedMember");
		if(member != null) {
			return service.doDelete(id, member);
		}
		return ResultData.from("F-1", "해당 서비스는 로그인을 하셔야 이용 가능 합니다.");
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
	@RequestMapping("/usr/article/doSearch")
	@ResponseBody
	public ResultData<ArrayList<Article>> doSearch (String keyword) {
		return service.doSearch(keyword);
	}
	
	
}