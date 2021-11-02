package com.sbs.exam.demo.vo;

import lombok.Getter;

public class ResultData <DT>{
	@Getter
	private String resultcode;
	@Getter
	private String msg;
	@Getter
	private DT data1;
	
	private ResultData() {
	}
	 
	public static<DT> ResultData<DT> from(String rc, String msg, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultcode = rc;
		rd.msg = msg;
		rd.data1 = data1;
		return rd;
	}
	
	public static<DT> ResultData<DT> from(String rc, String msg) {
		return from(rc, msg, null);
	}
	
	public boolean isSuccess() {
		return resultcode.startsWith("S-");
	}
	
	public boolean isFail() {
		return !isSuccess();
	}

}
