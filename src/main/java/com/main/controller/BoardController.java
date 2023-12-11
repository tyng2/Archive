package com.main.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.main.comm.Common;
import com.main.comm.cmmFile;
import com.main.service.BoardService;
import com.main.vo.BoardVo;
import com.main.vo.CategoryVo;
import com.main.vo.FileVo;
import com.main.vo.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	public String write(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("write");
		
		String category = Common.nvl(paramMap.get("category"));
		List<CategoryVo> categoryList = boardService.getCategoryList();
		model.addAttribute("categoryList"	, categoryList);
		model.addAttribute("category"		, category);
		return "board/write";
	}
	
	@PostMapping("/write.do")
	public String writeProcess(HttpServletRequest request, @RequestParam Map<String, String> paramMap, @RequestParam(required = false, name = "mFile") MultipartFile[] mFile, Model model) {
		log.info("write POST");
		
//		ResponseEntity<String> res = null;
		
		BoardVo board = new BoardVo();
		board.setUser_id("");
		board.setBord_catg(Common.str2Int(paramMap.get("categy")));
		board.setBord_titl(Common.nvl(paramMap.get("title")));
		board.setBord_cont(Common.nvl(paramMap.get("content")));
		board.setBord_wrip(Common.getClientIP(request));
		
		int bordId = boardService.insertBoard(board);
		log.info("BOARD INSERT : {}", bordId);
		
		int cntFile = 0;
		List<String> mFileList = new ArrayList<String>();
		for (MultipartFile f : mFile) {
			if (f.isEmpty()) {
				continue;
			} else {
				FileVo file = cmmFile.fileUpload(f);
				mFileList.add(file.getFile_svnm());

				file.setBord_id(bordId);
				boardService.insertFile(file);
				cntFile++;
			}
		}
		log.info("FILE : {}", cntFile);
		
		return "redirect:/board.do";
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("detail");
		
		String bordId = Common.nvl(paramMap.get("bord_id"));
		
		Map<String, Object> detail = boardService.getDetail(bordId);
		
		model.addAttribute("board", detail.get("board"));
		model.addAttribute("files", detail.get("files"));
		return "board/detail";
	}
	
	@ResponseBody
	@PostMapping("/download.do")
	public void fileDownload(@RequestParam Map<String, String> paramMap, HttpServletResponse response) {
		log.info("download");
		
		String fileId	= Common.nvl(paramMap.get("fileId"));
		FileVo file		= boardService.getFile(fileId);
		log.info("sdfsd {}", file.toString());
		if (file != null) {
			try {
				byte[] fileBytes	= cmmFile.fileDownload(file.getFile_svnm());
				String fileName		= URLEncoder.encode(file.getFile_olnm(), "UTF-8");
				
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName +"\";");
				response.setHeader("Content-Transfer-Encoding", "binary");
				
				response.getOutputStream().write(fileBytes);
				response.getOutputStream().flush();
				response.getOutputStream().close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
