package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	String loginId;
	String loginPw;
	String name;
	String nickname;
	String cellphoneNo;
	String email;
}
