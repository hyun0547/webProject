package com.sbs.exam.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.vo.Article;

@Controller
public class UsrArticleController {
	//인스턴스 변수
	int lastId;
	ArrayList<Article> articles;
	//생성자
	public UsrArticleController () {
		lastId = 0;
		articles = new ArrayList<>();
		makeTest();
	}
	//서비스 메소드
	private void makeTest() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			doAdd(title, body);
		}
	}
	private Article getArticle(int id) {
		Article article = null;
		if(id > articles.size()) {
			return null;
		}
		for(int i = id - 1; i >= 0; i--) {
			if(articles.get(i).getId() == id) {
				article = articles.get(i);
			}
		}
		return article;
	}
	//액션 메소드
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ArrayList<Article> getArticles () {
		return articles;
	}
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public String doAdd (String title, String body) {
		int id = ++lastId;
		Article article = new Article(id, title, body);
		articles.add(article);
		return "게시물이 추가되었습니다.";
	}
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (int id) {
		Article article = getArticle(id);
		if(article == null) {
			return "게시글이 존재하지 않습니다.";
		}
		articles.remove(article);
		return id + "번 글이 삭제되었습니다.";
	}
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify (int id, String title, String body) {
		Article article = getArticle(id);
		if(article == null) {
			return "게시글이 존재하지 않습니다.";
		}
		article.setTitle(title);
		article.setBody(body);
		return id + "번 글이 변경되었습니다. </br> " + article.toString();
	}
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public String doGetArticle (int id) {
		Article article = getArticle(id);
		if(article == null) {
			return "게시글이 존재하지 않습니다.";
		}
		return article.toString();
	}
	
	
}