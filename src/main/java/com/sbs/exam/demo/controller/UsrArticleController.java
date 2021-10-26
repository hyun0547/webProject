package com.sbs.exam.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.vo.Article;

@Controller
public class UsrArticleController {
	@Autowired
	ArticleService service;
	
	//액션 메소드
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ArrayList<Article> getArticles () {
		return service.getArticles();
	}
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd (String title, String body) {
		return service.doAdd(title, body);
	}
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (int id) {
		return service.doDelete(id);
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify (int id, String title, String body) {
		return service.doModify(id, title, body);
	}
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public Article doGetArticle (int id) {
		return service.getArticle(id);
	}
	@RequestMapping("/usr/article/doSearch")
	@ResponseBody
	public ArrayList<Article> doSearch (String keyword) {
		return service.doSearch(keyword);
	}
	
	
}