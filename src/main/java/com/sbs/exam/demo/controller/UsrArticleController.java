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
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	
	private Rq rq;
	
	ArticleService service;
	public UsrArticleController(ArticleService service, Rq rq) {
		this.rq = rq;
		this.service = service;
	}
	
	@RequestMapping("/usr/article/list")
	public String getArticles (Model model) {
		
		model.addAttribute("rd", service.getArticles(rq.getLoginedMember()));
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doAdd (String title, String body) {
		ResultData<Article> rd = service.doAdd(title, body, rq.getLoginedMember());
		return Utility.jsReplace(rd.getMsg(), "/usr/article/detail?id=" + rd.getData1().getId());
	}
	
	@RequestMapping("/usr/article/showWrite")
	public String showWrite () {
		return "/usr/article/write";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (int id) {
	
	
		ResultData<Integer> rd = service.doDelete(id, rq.getLoginedMember());
		
		if(rd.isSuccess()) {
			return Utility.jsReplace(rd.getMsg(), "/usr/article/list");
		}
		
		return Utility.jsHistoryBack(rd.getMsg());
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify (int id, String title, String body) {
		ResultData<Article> rd = service.doModify(id, title, body, rq.getLoginedMember());
		
		if(rd.isFail()) {
			return Utility.jsHistoryBack(rd.getMsg()); 
		}
		
		return Utility.jsReplace(rd.getMsg(), "/usr/article/detail?id=" + id);
	}
	
	@RequestMapping("/usr/article/showModify")
	public String showModify (Model model, int id) {
		ResultData<Article> rd = service.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(!rd.getData1().isExtra__actorAuth()) {
			return Utility.jsHistoryBack(rd.getMsg()); 
		}
		
		model.addAttribute("rd", rd);
		
		return "/usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public ResultData<Article> GetArticle (int id) {
		
		return service.getArticle(id);
	}
	
	@RequestMapping("/usr/article/detail")
	public String getForPrintArticle(Model model, int id) {
		
		
		model.addAttribute("rd", service.getForPrintArticle(rq.getLoginedMemberId(), id));
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doSearch")
	@ResponseBody
	public ResultData<ArrayList<Article>> doSearch (String keyword) {
		
		return service.doSearch(keyword);
	}
	
	
}