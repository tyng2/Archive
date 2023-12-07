package com.main.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/board.do")
	public String board(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("board");
		
//		model.addAttribute("pageInfoMap"	, pageInfoMap);
//		model.addAttribute("list"			, boardList);
		model.addAttribute("search"			, paramMap.get("search"));
//		model.addAttribute("hitList"		, hitList);
		return "board/board";
	}
	
	@GetMapping("/write.do")
	public String write() {
		log.info("write");
		return "board/write";
	}

}
