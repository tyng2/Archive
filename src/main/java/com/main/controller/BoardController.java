package com.main.controller;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
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
	
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("write");
		
		String category = Common.nvl(paramMap.get("category"));
		List<CategoryVo> categoryList = boardService.getCategoryList();
		model.addAttribute("categoryList"	, categoryList);
		model.addAttribute("category"		, category);
		return "board/write";
	}
	
	@PostMapping("/write")
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
		
		return "redirect:/board";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("detail");
		
		String bordId = Common.nvl(paramMap.get("bord_id"));
		
		Map<String, Object> detail = boardService.getDetail(bordId);
		
		model.addAttribute("board", detail.get("board"));
		model.addAttribute("files", detail.get("files"));
		return "board/detail";
	}
	
	@ResponseBody
	@PostMapping("/download")
	public String fileDownloadLink(@RequestParam Map<String, String> paramMap, HttpServletRequest request) {
		log.info("download");
		String fileId	= Common.nvl(paramMap.get("fileId"));
		FileVo file		= boardService.getFile(fileId);
		String msg		= "";
		
		if (!ObjectUtils.isEmpty(file)) {
			DateFormat df 		= new SimpleDateFormat("yyyyMMddHmsS");
			String dateStr		= df.format(new Date());
			
			HttpSession session = request.getSession();
			session.setAttribute("fileDownloadChk", dateStr + "/" + fileId);
			msg = dateStr;
		}
		
		return msg;
	}
	
	@GetMapping("/download")
	public void fileDownload(@RequestParam Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response) {
		log.info("download");
		
		String dateStr	= Common.nvl(paramMap.get("dateStr"));
		String fileId	= Common.nvl(paramMap.get("fileId"));
		
		HttpSession session = request.getSession();
		String chkVal		= (String) session.getAttribute("fileDownloadChk");
		session.removeAttribute("fileDownloadChk");
		
		log.info("chkVal : {}", chkVal);
		try {
			if (chkVal != null) {
				String[] chk		= chkVal.split("/");
				
				if (chk.length > 1 && chk[0].equals(dateStr) && chk[1].equals(fileId)) {
					FileVo file		= boardService.getFile(fileId);
					
					log.info("sdfsd {}", file.toString());
					
					if (!ObjectUtils.isEmpty(file)) {
						byte[] fileBytes	= cmmFile.fileDownload(file.getFile_svnm());
						String fileName		= URLEncoder.encode(file.getFile_olnm(), "UTF-8");
						log.info("fileName {}", fileName);
						
						response.setContentType("application/octet-stream");
						response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName +"\";");
						response.setHeader("Content-Transfer-Encoding", "binary");
						
						response.getOutputStream().write(fileBytes);
						response.getOutputStream().flush();
						response.getOutputStream().close();
							
					} else {
						throw new Exception("file not found");
					}
					
				} else {
					throw new Exception("check value is not correct");
				}
				
			} else {
				throw new Exception("check value is not correct");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@ResponseBody
	@GetMapping("/comment")
	public String comment() {
		
		return "";
	}
	
	

}
