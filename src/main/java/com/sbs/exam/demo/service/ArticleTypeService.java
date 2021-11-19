package com.sbs.exam.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import com.sbs.exam.demo.repository.ArticleTypeRepository;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.ArticleType;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class ArticleTypeService {
	ArticleTypeRepository articleTypeRepository;
	
	public ArticleTypeService(ArticleTypeRepository articleTypeRepository) {
		this.articleTypeRepository = articleTypeRepository;
	}

	public ResultData<ArticleType> getType(int typeId) {
		ArticleType at = articleTypeRepository.getType(typeId);
		return ResultData.from("S-1", Utility.f("%s 게시판 입니다.", at.getTypeName()), at.getClass().getSimpleName(), at);
	}

	public ResultData<ArrayList<ArticleType>> getTypes() {
		ArrayList<ArticleType> types = articleTypeRepository.getTypes();
		
		return ResultData.from("S-1", "전체 게시판 입니다.", types.getClass().getSimpleName(), types);
	}
}
