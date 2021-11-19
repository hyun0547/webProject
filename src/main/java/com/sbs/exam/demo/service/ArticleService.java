package com.sbs.exam.demo.service;


import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.sbs.exam.demo.repository.ArticleRepository;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class ArticleService {
	ArticleRepository repository;
	
	public ArticleService(ArticleRepository repository) {
		this.repository = repository;
	}
	
	public ResultData<ArrayList<Article>> getArticles(Member loginedMember, int typeId) {
		
		ArrayList<Article> articles = repository.getArticles(typeId);
		
		if(loginedMember != null) {
			for(Article article : articles) {
				updatePrintForData(article, loginedMember.getLoginId());
			}
		}
		
		return ResultData.from("S-1", "전체 게시물 입니다.", articles.getClass().getSimpleName(), articles);
	}
	
	
	public ResultData<Article> doAdd(String title, String body, Member member) {
		repository.doAdd(title, body, member.getLoginId());
		int id = repository.getLastInsert();
		Article article = repository.getArticle(id);
		
		return ResultData.from("S-1", "게시물이 추가되었습니다.", article.getClass().getSimpleName(), article);
	}
	
	public ResultData<Integer> doDelete(Integer id, Member member) {
		
		Article article = repository.getArticle(id);
		
		if(article != null) {
			if(article.getMemberLoginId().equals(member.getLoginId())) {
				
				repository.doDelete(id);
				
				return ResultData.from("S-1", Utility.f("게시물이 삭제 되었습니다.", id), id.getClass().getSimpleName(), id);
			}
			
			return ResultData.from("F-2", "해당 게시물을 삭제 할 수 있는 권한이 없습니다.");
		}
		
		return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다.", id));
	}
	
	public ResultData<Article> doModify(int id, String title, String body, Member member) {
		
		Article article = repository.getArticle(id);
		
		if(article != null) {
			if(article.getMemberLoginId().equals(member.getLoginId())) {
				
				repository.doModify(id, title, body);
				
				return ResultData.from("S-1", Utility.f("%d번 게시물이 변경 되었습니다.", id), article.getClass().getSimpleName(), article);
			}
			
			return ResultData.from("F-1", "해당 게시물을 변경할 수 있는 권한이 없습니다.");
		}
		
		return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다.", id)); 
	}
	public ResultData<ArrayList<Article>> doSearch(String keyword) {
		ArrayList<Article> articles = repository.doSearch(keyword); 
		if(articles != null) {
			return ResultData.from("S-1", Utility.f("%s (으)로 검색한 결과 입니다.", keyword), articles.getClass().getSimpleName(), articles);
		}
		return ResultData.from("F-1", Utility.f("%s (을)를 포함하는 게시물이 존재하지 않습니다.", keyword));
	}

	public ResultData<Article> getForPrintArticle(String loginedMemberId, int id) {
		
		Article article = repository.getForPrintArticle(id);
		
		if(loginedMemberId == null) {
			loginedMemberId = "";
		}
		
		updatePrintForData(article, loginedMemberId);
		
		return ResultData.from("S-1", "게시물 상세정보", article.getClass().getSimpleName(), article);
		
	}
	
	public ResultData<Article> getArticle(int id) {
		
		Article article = repository.getArticle(id);
		
		if(article != null) {
			return ResultData.from("S-1", Utility.f("%d번 게시물 입니다.", id), article.getClass().getSimpleName(), article);
		}
		
		return ResultData.from("F-1", Utility.f("%d번 게시물은 존재하지 않습니다.", id));
	}

	private void updatePrintForData(Article article, String loginedMemberId) {
		
		if(article.getMemberLoginId().equals(loginedMemberId)) {
			article.setExtra__actorAuth(true);
		}
		
		return;
	}
}
