package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	int id;
	String regDate;
	String updateDate;
	String loginId;
	String loginPw;
	int authLevel;
	String name;
	String nickname;
	String cellphoneNo;
	String email;
	int delStatus;
	String delDate;
	String profileImgUrl;

}
 