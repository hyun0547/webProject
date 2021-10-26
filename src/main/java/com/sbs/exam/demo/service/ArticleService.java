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
	public String doDelete(int id) {
		if(repository.doDelete(id) < 0) {
			return "해당 게시글은 존재하지 않습니다.";
		}
		return "삭제 되엇습니다.";
	}
	public String doModify(int id, String title, String body) {
		if(repository.doModify(id, title, body) < 0) {
			return "해당 게시글은 존재하지 않습니다."; 
		}
		return "게시글이 수정 되었습니다.";
	}
	public ArrayList<Article> doSearch(String keyward) {
		return repository.doSearch(keyward);
	}
	
}
