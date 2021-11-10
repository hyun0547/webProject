package com.sbs.exam.demo.repository;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
		public Article getArticle(@Param("id") int id);
		
		public ArrayList<Article> getArticles();
		
		public ArrayList<Article> doSearch(@Param("keyword") String keyword);
		
		public int getLastInsert();
		
		public void doAdd(@Param("title") String title,@Param("body") String body, @Param("memberId") int memberId);
		
		public void doDelete(@Param("id") int id);
		
		public void doModify(@Param("id") int id,@Param("title") String title,@Param("body") String body);
		
		public Map<String, String> detail(@Param("id") int id);
		
}
		
