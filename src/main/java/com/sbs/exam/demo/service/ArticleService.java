package com.sbs.exam.demo.service;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.vo.Article;

@Service
public class ArticleService {
	@Autowired
	ArticleRepository repository;
	
	public ArticleService() {}
	
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
