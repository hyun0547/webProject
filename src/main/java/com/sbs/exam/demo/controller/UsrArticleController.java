package com.sbs.exam.demo.controller;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import com.sbs.exam.demo.service.ArticleService;
import com.sbs.exam.demo.service.ArticleTypeService;
import com.sbs.exam.demo.service.GenFileService;
import com.sbs.exam.demo.service.ReplyService;
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ArticleType;
import com.sbs.exam.demo.vo.GenFile;
import com.sbs.exam.demo.vo.Reply;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	
	private Rq rq;
	ArticleService articleService;
	ArticleTypeService articleTypeService;
	GenFileService genFileService;
	ReplyService replyService;
	
	public UsrArticleController(ArticleService articleService, Rq rq, ArticleTypeService articleTypeService, GenFileService genFileService, ReplyService replyService) {
		this.rq = rq;
		this.articleService = articleService;
		this.articleTypeService = articleTypeService;
		this.genFileService = genFileService;
		this.replyService = replyService;
	}
	
	@RequestMapping("/usr/article/list")
	public String getArticles (Model model, int typeId, String searchKeyword, @RequestParam(defaultValue = "1") int curPage) {
		
		ResultData<ArticleType> typeRd = articleTypeService.getType(typeId);
		int privateType = typeRd.getData1().getPrivateType();
		ResultData<ArrayList<Article>> articleRd = articleService.getArticles(rq.getLoginedMemberId(), typeId, searchKeyword, curPage, privateType);
		
		ResultData<Integer> allArticles = articleService.getAllArticleCount(typeId, privateType, rq.getLoginedMemberId());
		int allArticlesCount = allArticles.getData1();
		int allPages = allArticlesCount % 10 == 0 ? allArticlesCount / 10 : allArticlesCount / 10 + 1;
		
		model.addAttribute("allArticle", allArticles);
		model.addAttribute("allPages", allPages);
		model.addAttribute("curPage", curPage);
		model.addAttribute("articleRd", articleRd);
		model.addAttribute("typeRd", typeRd);
		model.addAttribute("searchKeyword", searchKeyword);
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doAdd (String title, String body, int typeId, MultipartRequest mr) {
		ResultData<ArticleType> typeRd = articleTypeService.getType(typeId);
		ResultData<Article> articleRd = articleService.doAdd(title, body, typeId, typeRd.getData1().getPrivateType(), rq.getLoginedMember());
		Article newArticle = articleRd.getData1();
		
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		
		ResultData<ArrayList<String>> fileRd = genFileService.save(fileMap, newArticle.getId());
		
		return Utility.jsReplace(articleRd.getMsg(), "/usr/article/detail?articleId=" + articleRd.getData1().getId());
	}
	
	@RequestMapping("/usr/article/showWrite")
	public String showWrite (Model model) {
		ResultData<ArrayList<ArticleType>> typeRd = articleTypeService.getTypes();
		
		model.addAttribute("typeRd", typeRd);
		
		return "/usr/article/write";
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete (int id, String afterDeleteUri) {
	
		ResultData<Integer> rd = articleService.doDelete(id, rq.getLoginedMember());
		
		if(rd.isSuccess()) {
			return Utility.jsReplace(rd.getMsg(), afterDeleteUri);
		}
		
		return Utility.jsHistoryBack(rd.getMsg());
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify (int id, String title, String body) {
		ResultData<Article> rd = articleService.doModify(id, title, body, rq.getLoginedMember());
		
		if(rd.isFail()) {
			return Utility.jsHistoryBack(rd.getMsg()); 
		}
		
		return Utility.jsReplace(rd.getMsg(), "/usr/article/detail?articleId=" + id);
	}
	
	@RequestMapping("/usr/article/showModify")
	public String showModify (Model model, int id) {
		ResultData<Article> rd = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		if(!rd.getData1().isExtra__actorAuth()) {
			return Utility.jsHistoryBack(rd.getMsg()); 
		}
		
		model.addAttribute("rd", rd);
		
		return "/usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doGetArticle")
	@ResponseBody
	public ResultData<Article> GetArticle (int id) {
		
		return articleService.getArticle(id);
	}
	
	@RequestMapping("/usr/article/detail")
	public String getForPrintArticle(Model model, int articleId, String searchKeyword) {
		
		ResultData<Article> articleRd = articleService.getForPrintArticle(rq.getLoginedMemberId(), articleId);
		ResultData<ArrayList<Reply>> replyRd = replyService.getReplyList(articleId);
		GenFile attachFile = genFileService.getFileForRel(articleId, "article");
		
		String afterDeleteUri = rq.getEncodedUri(Utility.f("/usr/article/list?typeId=%d&searchKeyword=%s", articleRd.getData1().getTypeId(), searchKeyword));
		
		if(attachFile != null) {
			model.addAttribute("attachFileUrl", attachFile.getForPrintDir());
		}
		
		model.addAttribute("replyRd", replyRd);
		model.addAttribute("afterDeleteUri", afterDeleteUri);
		model.addAttribute("articleRd", articleRd);
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/doWriteReply")
	@ResponseBody
	public String doWriteReply (String body, int relArticleId) {
		
		replyService.doWriteReply(body, relArticleId, rq.getLoginedMemberId());
		return Utility.jsReplace("", "/usr/article/detail?articleId=" + relArticleId);
	}
	
}