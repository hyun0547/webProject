package com.sbs.exam.demo.service;


import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	ArticleRepository repository;
	
	public ArticleService(ArticleRepository repository) {
		this.repository = repository;
	}
	
	public ResultData<ArrayList<Article>> getArticles() {
		return ResultData.from("S-1", "전체 게시물 입니다.", repository.getArticles());
	}
	public ResultData<Article> getArticle(int id) {
		if(repository.getArticle(id) != null) {
			return ResultData.from("S-1", Utility.f("%d번 게시물 입니다.", id), repository.getArticle(id));
		}
		return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다.", id));
	}
	public ResultData<Article> doAdd(String title, String body) {
		repository.doAdd(title, body);
		int id = repository.getLastInsert();
		return ResultData.from("S-1", "게시물이 추가되었습니다.", repository.getArticle(id));
	}
	public ResultData<Integer> doDelete(int id) {
		if(repository.doDelete(id) > 0) {
			return ResultData.from("S-1", Utility.f("게시물이 삭제 되었습니다.", id), id);
		}
		return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다.", id));
	}
	public ResultData<Article> doModify(int id, String title, String body) {
		if(repository.doModify(id, title, body) > 0) {
			return ResultData.from("S-1", Utility.f("%d번 게시물이 변경 되었습니다.", id), repository.getArticle(id));
		}
		return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다.", id)); 
	}
	public ResultData<ArrayList<Article>> doSearch(String keyword) {
		ArrayList<Article> articles = repository.doSearch(keyword); 
		if(articles != null) {
			return ResultData.from("S-1", Utility.f("%s (으)로 검색한 결과 입니다.", keyword), articles);
		}
		return ResultData.from("F-1", Utility.f("%s (을)를 포함하는 게시물이 존재하지 않습니다.", keyword));
	}
	
}
