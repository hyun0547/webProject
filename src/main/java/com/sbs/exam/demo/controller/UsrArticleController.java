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
	
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public ResultData<Article> doAdd (String title, String body) {
		
		return service.doAdd(title, body, rq.getLoginedMember());
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
	public ResultData<Article> doModify (int id, String title, String body) {
		
		return service.doModify(id, title, body, rq.getLoginedMember());
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