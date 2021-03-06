package com.sbs.exam.demo.repository;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
		public Article getArticle(@Param("id") int id);
		
		public ArrayList<Article> getArticles(@Param("typeId") int typeId, @Param("searchKeyword") String searchKeyword, @Param("limitStart") int limitStart, @Param("limitRange") int limitRange, @Param("privateType") int privateType, @Param("memberLoginId") String memberLoginId);
		
		public int getAllArticleCount(@Param("typeId") int typeId, @Param("privateType") int privateType, @Param("loginedMemberId") String loginedMemberId, @Param("searchKeyword") String searchKeyword);
		
		public int getLastInsert();
		
		public void doAdd(@Param("title") String title,@Param("body") String body, @Param("typeId") int typeId, @Param("privateType") int privateType, @Param("memberLoginId") String memberLoginId);
		
		public void doDelete(@Param("id") int id);
		
		public void doModify(@Param("id") int id,@Param("title") String title,@Param("body") String body);
		
		public Article getForPrintArticle(@Param("id") int id);
		
}
		
