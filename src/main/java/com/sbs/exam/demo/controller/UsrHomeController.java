package com.sbs.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Data;

@Data
@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/main")
	public String main(Model model) {
		model.addAttribute("position", "MainPage");
		return "/usr/home/main";
	}
	@RequestMapping("/")
	public String showMain() {
		return "redirect:/usr/home/main";
	}
	
}