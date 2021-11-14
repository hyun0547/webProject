package com.sbs.exam.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	ArticleService service;
	public UsrArticleController(ArticleService service) {
		this.service = service;
	}
	
	@RequestMapping("/usr/article/list")
	public String getArticles (HttpServletRequest req, Model model) {
		
		Rq rq = new Rq(req);
		
		model.addAttribute("rd", service.getArticles(rq.getLoginedMember()));
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public ResultData<Article> doAdd (HttpServletRequest req, String title, String body) {
		
		Rq rq = new Rq(req);
		
		if(rq.isLogined()) {
			return service.doAdd(title, body, rq.getLoginedMember());
		}
		
		return ResultData.from("F-1", "해당 서비스는 로그인을 하셔야 이용 가능 합니다.");
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (HttpServletRequest req, int id) {
		
		Rq rq = new Rq(req);
		
		if(rq.isLogined()) {
			
			ResultData<Integer> rd = service.doDelete(id, rq.getLoginedMember());
			
			if(rd.isSuccess()) {
				return Utility.jsReplace(rd.getMsg(), "/usr/article/list");
			}
			
			return Utility.jsHistoryBack(rd.getMsg());
		}
		
		return Utility.jsHistoryBack("해당 서비스는 로그인을 하셔야 이용 가능 합니다.");
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify (HttpServletRequest req, int id, String title, String body) {
		
		Rq rq = new Rq(req);
		
		if(rq.isLogined()) {
			return service.doModify(id, title, body, rq.getLoginedMember());
		}
		
		return ResultData.from("F-1", "해당 서비스는 로그인을 하셔야 이용 가능 합니다.");
	}
	
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public ResultData<Article> GetArticle (int id) {
		
		return service.getArticle(id);
	}
	
	@RequestMapping("/usr/article/detail")
	public String getForPrintArticle(HttpServletRequest req, Model model, int id) {
		
		Rq rq = new Rq(req);
		
		model.addAttribute("rd", service.getForPrintArticle(rq.getLoginedMemberId(), id));
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doSearch")
	@ResponseBody
	public ResultData<ArrayList<Article>> doSearch (String keyword) {
		
		return service.doSearch(keyword);
	}
	
	
}