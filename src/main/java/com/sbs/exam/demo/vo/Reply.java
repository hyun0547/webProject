package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
	int id;
	String body;
	String regDate;
	String updateDate;
	int relArticleId;
	String delstatus;
	String delDate;
	Member writer;
}
