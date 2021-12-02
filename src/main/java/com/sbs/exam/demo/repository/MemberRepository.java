package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbs.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {

	public void doJoin(@Param("loginId") String loginId,@Param("loginPw") String loginPw,@Param("name") String name,@Param("nickname") String nickname,@Param("cellphoneNo") String cellphoneNo,@Param("email")
			String email);

	public Member getMember(@Param("loginId") String loginId);

	public int getLastInsertId();

	public int loginIdOverlap(@Param("loginId") String loginId);

	public int emailOverlap(@Param("email") String email);

	public int userOverlap(@Param("name") String name,@Param("cellphoneNo") String cellphoneNo);

	public void doModify(@Param("email") String email, @Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("loginId") String loginId);
}
