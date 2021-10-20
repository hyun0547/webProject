package com.sbs.exam.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.vo.Article;

@Service
public class ArticleService {
		private int lastId;
		private ArrayList<Article> articles;
		
		public ArticleService () {
			lastId = 0;
			articles = new ArrayList<>();
			makeTest();
		}
		
		public void makeTest() {
			for(int i = 1; i <= 10; i++) {
				String title = "제목" + i;
				String body = "내용" + i;
				doAdd(title, body);
			}
		}
		public Article getArticle(int id) {
			for(Article article : articles) {
				if(article.getId() == id) {
					return article;
				}
			}
			return null;
		}
		public ArrayList<Article> getArticles() {
			if(articles.size() == 0) {
				return null;
			}
			return articles;
		}
		public void doAdd(String title, String body) {
			Article article = new Article(++lastId, title, body);
			articles.add(article);
		}
		public boolean doDelete(int id) {
			Article article = getArticle(id);
			articles.remove(article);
			return article != null;
		}
		public boolean doModify(int id, String title, String body) {
			Article article = getArticle(id);
			if(article != null) {
				article.setBody(body);
				article.setTitle(title);
			}
			return article != null;
		}
}
