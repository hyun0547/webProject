package com.sbs.exam.demo.vo;

import lombok.Getter;

public class ResultData <DT>{
	@Getter
	private String resultcode;
	@Getter
	private String msg;
	@Getter
	private DT data1;
	@Getter
	private String data1Name;
	
	private ResultData() {
	}
	 
	public static<DT> ResultData<DT> from(String rc, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultcode = rc;
		rd.msg = msg;
		rd.data1 = data1;
		rd.data1Name = data1Name;
		return rd;
	}
	
	public static<DT> ResultData<DT> from(String rc, String msg) {
		return from(rc, msg, null, null);
	}
	
	public boolean isSuccess() {
		return resultcode.startsWith("S-");
	}
	
	public boolean isFail() {
		return !isSuccess();
	}

}
