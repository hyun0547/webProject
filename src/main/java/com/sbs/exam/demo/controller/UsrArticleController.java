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
		ArrayList<Article> articles = service.getArticles();
		return articles;
	}
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd (String title, String body) {
		service.doAdd(title, body);
		return "게시물이 추가되었습니다.";
	}
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (int id) {
		if(service.doDelete(id)) {
			return id + "번 글이 삭제되었습니다.";
		}
		return "게시글이 존재하지 않습니다.";
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify (int id, String title, String body) {
		if(service.doModify(id, title, body)) {
			return id + "번 글이 변경되었습니다. </br> ";
		}
		return "존재하지 않는 글 입니다.";
	}
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public Object doGetArticle (int id) {
		Article article = service.getArticle(id);
		if(article == null) {
			return "게시글이 존재하지 않습니다.";
		}
		return article;
	}
	
	
}