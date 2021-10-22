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
	
		@Select("SELECT * FROM ARTICLE WHERE ID = #{id}")
		public Article getArticle(@Param("id") int id);
		
		@Select("SELECT * FROM ARTICLE ORDER BY ID DESC")
		public ArrayList<Article> getArticles();
		
		@Insert("INSERT INTO ARTICLE SET REGDATE = NOW(), UPDATEDATE = NOW(), TITLE = #{title}, BODY = #{body}")
		public void doAdd(@Param("title") String title,@Param("body") String body);
		
		@Delete("DELETE FROM ARTICLE WHERE ID = #{id}")
		public boolean doDelete(@Param("id") int id);
		
		@Update("UPDATE ARTICLE SET UPDATEDATE = NOW(), TITLE=#{title}, BODY = #{body} WHERE ID = #{id}")
		public boolean doModify(@Param("id") int id,@Param("title") String title,@Param("body") String body);
		
		@Select("SELECT LAST_INSERT_ID()")
		public int getLastInsert();
}
