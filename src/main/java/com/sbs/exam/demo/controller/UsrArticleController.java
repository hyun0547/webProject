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
import com.sbs.exam.demo.util.Utility;
import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ArticleType;
import com.sbs.exam.demo.vo.GenFile;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrArticleController {
	
	private Rq rq;
	ArticleService articleService;
	ArticleTypeService articleTypeService;
	GenFileService genFileService; 
	
	public UsrArticleController(ArticleService articleService, Rq rq, ArticleTypeService articleTypeService, com.sbs.exam.demo.service.GenFileService genFileService) {
		this.rq = rq;
		this.articleService = articleService;
		this.articleTypeService = articleTypeService;
		this.genFileService = genFileService;
	}
	
	@RequestMapping("/usr/article/list")
	public String getArticles (Model model, int typeId, String searchKeyword, @RequestParam(defaultValue = "1") int curPage) {
		
		ResultData<ArrayList<Article>> articleRd = articleService.getArticles(rq.getLoginedMember(), typeId, searchKeyword, curPage);
		ResultData<ArticleType> typeRd = articleTypeService.getType(typeId);
		
		ResultData<Integer> allArticles = articleService.getAllArticleCount(typeId);
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
		ResultData<Article> articleRd = articleService.doAdd(title, body, typeId, rq.getLoginedMember());
		Article newArticle = articleRd.getData1();
		
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		
		for(String fileInputName : fileMap.keySet()) {
			MultipartFile mf = fileMap.get(fileInputName);
			String fileInputNameBits[] = mf.getName().split("__");
			
			if(fileInputNameBits[0].equals("file") == false) {
				continue;
			}
			
			long fileSize = mf.getSize();
			
			if(fileSize <= 0) {
				continue;
			}
			
			String relTypeCode = fileInputNameBits[1];
			int relId = newArticle.getId();
			String originFileName = mf.getOriginalFilename();
			String fileExt = Utility.getFileExtFromFileName(originFileName);
			String typeCode = fileInputNameBits[3]; 
			String type2Code = fileInputNameBits[4]; 
			String fileExtTypeCode = Utility.getFileExtTypeCodeFromFileName(originFileName);
			String fileExtType2Code = Utility.getFileExtType2CodeFromFileName(originFileName);
			int fileNo = Integer.parseInt(fileInputNameBits[2]);
			String fileDir = Utility.getNowYearMonthDateStr();
			
			
			genFileService.save(relTypeCode, relId, originFileName, fileExt, typeCode, type2Code, fileSize, fileExtTypeCode, fileExtType2Code, fileNo, fileDir);
		}
		
		return Utility.jsReplace(articleRd.getMsg(), "/usr/article/detail?id=" + articleRd.getData1().getId());
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
		
		return Utility.jsReplace(rd.getMsg(), "/usr/article/detail?id=" + id);
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
	public String getForPrintArticle(Model model, int id, String searchKeyword) {
		ResultData<Article> articleRd = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		String afterDeleteUri = rq.getEncodedUri(Utility.f("/usr/article/list?typeId=%d&searchKeyword=%s", articleRd.getData1().getTypeId(), searchKeyword));
		model.addAttribute("afterDeleteUri", afterDeleteUri);
		model.addAttribute("articleRd", articleRd);
		
		return "/usr/article/detail";
	}
	
	
}