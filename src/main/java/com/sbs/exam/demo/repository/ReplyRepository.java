package com.sbs.exam.demo.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbs.exam.demo.vo.Reply;

@Mapper
public interface ReplyRepository {
	
	ArrayList<Reply> getReplyList(@Param("relArticleId") int relArticleId);

	void doWriteReply(String body, int relArticleId, String loginedMemberId);
	
}
