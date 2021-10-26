package com.sbs.exam.demo.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sbs.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
		public Article getArticle(@Param("id") int id);
		
		public ArrayList<Article> getArticles();
		
		public int getLastInsert();
		
		public ArrayList<Article> doSearch(@Param("keyword") String keyword);
		
		public void doAdd(@Param("title") String title,@Param("body") String body);
		
		public int doDelete(@Param("id") int id);
		
		public int doModify(@Param("id") int id,@Param("title") String title,@Param("body") String body);
		
}
		
