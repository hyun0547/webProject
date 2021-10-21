package com.sbs.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Data
@Controller
public class UsrHomeController {
	private int count = 0;
	
	public UsrHomeController(){
		count = 0;
	}
	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int getCount() {
		return count;
	}
	@RequestMapping("/usr/home/doSetCount")
	@ResponseBody
	public String doSetCount(int count) {
		this.count = count;
		return "count의 값이 "+ this.count +"으로 초기화 되었습니다.";
	}
	
}