package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	int id;
	String title;
	String body;
	String regDate;
    String updateDate;
    String memberLoginId;
    boolean extra__actorAuth;
    Member member;
}
