package com.sbs.exam.demo.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.ReplyRepository;
import com.sbs.exam.demo.vo.Reply;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class ReplyService {

	ReplyRepository replyRepository;
	
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}
	
	public ResultData<ArrayList<Reply>> getReplyList(int relArticleId) {
		
		ArrayList<Reply> replyList = replyRepository.getReplyList(relArticleId);
		return ResultData.from("S-1", "댓글 리스트", replyList.getClass().getSimpleName(), replyList);
	}

}
