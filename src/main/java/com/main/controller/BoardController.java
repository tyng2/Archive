package com.main.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.main.comm.Common;
import com.main.service.BoardService;
import com.main.vo.BoardVo;
import com.main.vo.PageInfo;

@Controller
public class BoardController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board.do")
	public String board(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("board");
		
		paramMap.put("category", Common.nvl(paramMap.get("category")));
		
		Map<String, Object> result = boardService.getBoardList(paramMap);
		
		List<BoardVo> boardList	= (List<BoardVo>) result.get("boardList");
		PageInfo pageInfo		= (PageInfo) result.get("pageInfo");
		
		model.addAttribute("pageInfo"	, pageInfo);
		model.addAttribute("boardList"	, boardList);
		model.addAttribute("search"		, paramMap.get("search"));
//		model.addAttribute("hitList"	, hitList);
		return "board/board";
	}
	
	@GetMapping("/write.do")
	public String write() {
		log.info("write");
		return "board/write";
	}

}
