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
	}
	
	public ArrayList<Article> getArticles() {
		return repository.getArticles();
	}
	public Article getArticle(int id) {
		return repository.getArticle(id);
	}
	public Article doAdd(String title, String body) {
		repository.doAdd(title, body);
		int id = repository.getLastInsert();
		return repository.getArticle(id);
	}
	public boolean doDelete(int id) {
		return repository.doDelete(id);
	}
	public boolean doModify(int id, String title, String body) {
		return repository.doModify(id, title, body);
	}
	
}
