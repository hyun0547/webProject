package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.service.ArticleTypeService;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ArticleType;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	
	private Rq rq;
	
	ArticleService articleService;
	ArticleTypeService articleTypeService;
	public UsrArticleController(ArticleService articleService, Rq rq, ArticleTypeService articleTypeService) {
		this.rq = rq;
		this.articleService = articleService;
		this.articleTypeService = articleTypeService;
	}
	
	@RequestMapping("/usr/article/list")
	public String getArticles (Model model, int typeId, String searchKeyword) {
		ResultData<ArrayList<Article>> articleRd = articleService.getArticles(rq.getLoginedMember(), typeId, searchKeyword);
		ResultData<ArticleType> typeRd = articleTypeService.getType(typeId);
		
		model.addAttribute("articleRd", articleRd);
		model.addAttribute("typeRd", typeRd);
		System.out.println(typeRd);
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doAdd (String title, String body, int typeId) {
		ResultData<Article> rd = articleService.doAdd(title, body, typeId, rq.getLoginedMember());
		return Utility.jsReplace(rd.getMsg(), "/usr/article/detail?id=" + rd.getData1().getId());
	}
	
	@RequestMapping("/usr/article/showWrite")
	public String showWrite (Model model) {
		ResultData<ArrayList<ArticleType>> typeRd = articleTypeService.getTypes();
		
		model.addAttribute("typeRd", typeRd);
		
		return "/usr/article/write";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (int id) {
	
	
		ResultData<Integer> rd = articleService.doDelete(id, rq.getLoginedMember());
		
		if(rd.isSuccess()) {
			return Utility.jsReplace(rd.getMsg(), "/usr/article/list");
		}
		
		return Utility.jsHistoryBack(rd.getMsg());
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify (int id, String title, String body) {
		ResultData<Article> rd = articleService.doModify(id, title, body, rq.getLoginedMember());
		
		if(rd.isFail()) {
			return Utility.jsHistoryBack(rd.getMsg()); 
		}
		
		return Utility.jsReplace(rd.getMsg(), "/usr/article/detail?id=" + id);
	}
	
	@RequestMapping("/usr/article/showModify")
	public String showModify (Model model, int id) {
		ResultData<Article> rd = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(!rd.getData1().isExtra__actorAuth()) {
			return Utility.jsHistoryBack(rd.getMsg()); 
		}
		
		model.addAttribute("rd", rd);
		
		return "/usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public ResultData<Article> GetArticle (int id) {
		
		return articleService.getArticle(id);
	}
	
	@RequestMapping("/usr/article/detail")
	public String getForPrintArticle(Model model, int id) {
		
		
		model.addAttribute("rd", articleService.getForPrintArticle(rq.getLoginedMemberId(), id));
		
		return "/usr/article/detail";
	}
	
}