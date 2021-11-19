package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleType {
	int id;
	String regDate;
    String updateDate;
    String typeName;
    int delStatus;
    String delDate;
}
