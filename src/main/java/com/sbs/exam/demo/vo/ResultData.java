package com.sbs.exam.demo.vo;

import lombok.Getter;

public class ResultData {
	@Getter
	private String resultcode;
	@Getter
	private String msg;
	@Getter
	private Object data1;
	
	private ResultData() {
	}
	 
	public static ResultData from(String rc, String msg, Object data1) {
		ResultData rd = new ResultData();
		rd.resultcode = rc;
		rd.msg = msg;
		rd.data1 = data1;
		return rd;
	}
	
	
	
	public boolean isSuccess() {
		return resultcode.startsWith("S-");
	}
	
	public boolean isFail() {
		return !isSuccess();
	}

}
