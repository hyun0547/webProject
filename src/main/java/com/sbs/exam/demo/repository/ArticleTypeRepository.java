package com.sbs.exam.demo.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.sbs.exam.demo.vo.ArticleType;

@Mapper
public interface ArticleTypeRepository {
	
	@Select("SELECT * FROM article_type WHERE id = #{typeId}")
	public ArticleType getType(@Param("typeId") int typeId);
	
	@Select("SELECT * FROM article_type")
	public ArrayList<ArticleType> getTypes();

}
