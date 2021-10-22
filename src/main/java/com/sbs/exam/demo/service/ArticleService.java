package com.sbs.exam.demo.service;


import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.vo.Article;

@Service
public class ArticleService {
	ArticleRepository repository;
	
	public ArticleService(ArticleRepository repository) {
		this.repository = repository;
		makeTest();
	}
	public void makeTest() {
		for(int i = 1; i <= 10; i++) {
			String title = "제목" + i;
			String body = "내용" + i;
			doAdd(title, body);
		}
	}
	
	public ArrayList<Article> getArticles() {
		return repository.getArticles();
	}
	public Article getArticle(int id) {
		return repository.getArticle(id);
	}
	public void doAdd(String title, String body) {
		repository.doAdd(title, body);
	}
	public boolean doDelete(int id) {
		return repository.doDelete(id);
	}
	public boolean doModify(int id, String title, String body) {
		return repository.doModify(id, title, body);
	}
	
	
}
