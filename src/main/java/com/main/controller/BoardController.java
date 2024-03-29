package com.main.controller;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.main.comm.SessionUtil;
import com.main.comm.cmmFile;
import com.main.service.BoardService;
import com.main.vo.BoardVo;
import com.main.vo.CategoryVo;
import com.main.vo.FileVo;
import com.main.vo.LoginSessionVo;
import com.main.vo.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/board")
	public String board(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("board {}", paramMap.toString());
		
		String category	= Common.nvl(paramMap.get("category"));
		String search	= Common.nvl(paramMap.get("search"));
		
		Map<String, Object> result	= boardService.getBoardList(paramMap);
		
		List<BoardVo> boardList	= (List<BoardVo>) result.get("boardList");
		PageInfo pageInfo		= (PageInfo) result.get("pageInfo");
		
		model.addAttribute("pageInfo"	, pageInfo);
		model.addAttribute("boardList"	, boardList);
		
		if (!"".equals(search)) {
			model.addAttribute("search", search);
		}
		if (!"".equals(category)) {
			Map<String, String> cateMap = Map.of(
				"catgName"	, boardService.getCategoryName(category),
				"catgId"	, category
			);
			model.addAttribute("category", cateMap);
		}
//		model.addAttribute("hitList"	, hitList);
		return "board/board";
	}
	
	@GetMapping("/write")
	public String write(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("write");
		
		String pageNum	= Common.nvl(paramMap.get("pageNum"));
		String category = Common.nvl(paramMap.get("category"));
		String search	= Common.nvl(paramMap.get("search"));
		List<CategoryVo> categoryList = boardService.getCategoryList();
		model.addAttribute("categoryList"	, categoryList);
		model.addAttribute("pageNum"		, pageNum);
		model.addAttribute("category"		, category);
		model.addAttribute("search"			, search);
		return "board/write";
	}
	
	@PostMapping("/write")
	public String writeProcess(HttpServletRequest request, @RequestParam Map<String, String> paramMap, @RequestParam(required = false, name = "mFile") MultipartFile[] mFile, Model model) {
		log.info("write POST");
		
//		ResponseEntity<String> res = null;
		
		LoginSessionVo login = SessionUtil.getLoginData(request);
		
		int userId = (SessionUtil.isLogin(request)) ? login.getUserId() : 0;
		
		BoardVo board = new BoardVo();
		board.setUserId(userId);
		board.setBordCatg(Common.str2Int(paramMap.get("categy")));
		board.setBordTitl(Common.nvl(paramMap.get("title")));
		board.setBordCont(Common.nvl(paramMap.get("content")));
		board.setBordWrip(Common.getClientIP());
		
		boardService.insertBoard(board);
		log.info("INSERT ID : {}", board.getBordId());
		
		int cntFile = 0;
		List<String> mFileList = new ArrayList<String>();
		for (MultipartFile f : mFile) {
			if (f.isEmpty()) {
				continue;
			} else {
				FileVo file = cmmFile.fileUpload(f);
				mFileList.add(file.getFileSvnm());

				file.setBordId(board.getBordId());
				boardService.insertFile(file);
				cntFile++;
			}
		}
		log.info("FILE : {}", cntFile);
		
		return "redirect:/board";
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, @RequestParam Map<String, String> paramMap, Model model) {
		log.info("update");
		
//		String category = Common.nvl(paramMap.get("category"));
//		model.addAttribute("category"		, category);
		
		String bordId	= Common.nvl(paramMap.get("bordId"));
		int userId		= Common.str2Int(paramMap.get("userId"));
		
		LoginSessionVo login = SessionUtil.getLoginData(request);
		
		if (!ObjectUtils.isEmpty(login) && userId == login.getUserId()) {
			List<CategoryVo> categoryList = boardService.getCategoryList();
			Map<String, Object> detail = boardService.getDetail(bordId);
			
			model.addAttribute("categoryList"	, categoryList);
			model.addAttribute("board"			, detail.get("board"));
			model.addAttribute("files"			, detail.get("files"));
			return "board/update";
			
		} else {
			return "redirect:/board";
		}
	}
	
	@PostMapping("/updateProcess")
	public String updateProcess(HttpServletRequest request, @RequestParam Map<String, String> paramMap, @RequestParam(required = false, name = "mFile") MultipartFile[] mFile, Model model) {
		
		int bordId	= Common.str2Int(paramMap.get("bordId"));
		int userId	= Common.str2Int(paramMap.get("userId"));
		
		LoginSessionVo login = SessionUtil.getLoginData(request);
		if (!ObjectUtils.isEmpty(login) && userId == login.getUserId()) {
			
			BoardVo board = new BoardVo();
			board.setBordId(bordId);
			board.setBordCatg(Common.str2Int(paramMap.get("categy")));
			board.setBordTitl(Common.nvl(paramMap.get("title")));
			board.setBordCont(Common.nvl(paramMap.get("content")));
			
			boardService.updateBoard(board);
			log.info("UPDATE ID : {}", bordId);
			
			int cntFile = 0;
			List<String> mFileList = new ArrayList<String>();
			for (MultipartFile f : mFile) {
				if (f.isEmpty()) {
					continue;
				} else {
					FileVo file = cmmFile.fileUpload(f);
					mFileList.add(file.getFileSvnm());
					
					file.setBordId(bordId);
					boardService.insertFile(file);
					cntFile++;
				}
			}
			log.info("FILE : {}", cntFile);
		}
		
		return "redirect:/detail?bordId="+bordId;
	}
	
	@PostMapping("/deleteBoard")
	public String deleteBoard(HttpServletRequest request, @RequestParam Map<String, String> paramMap, Model model) {
		
		String bordId	= Common.nvl(paramMap.get("bordId"));
		int userId		= Common.str2Int(paramMap.get("userId"));
		
		String category = Common.nvl(paramMap.get("category"));
		String search	= Common.nvl(paramMap.get("search"));
		String pageNum	= Common.nvl(paramMap.get("pageNum"));
		
		LoginSessionVo login = SessionUtil.getLoginData(request);
		if (!ObjectUtils.isEmpty(login) && userId == login.getUserId()) {
			int cnt = boardService.deleteBoard(bordId);
			log.info("bc deleteFile :: {}", cnt);
		}
		
		Map<String, String> q = new HashMap<String, String>();
		if (!"".equals(category)) {
			q.put("category"	, category);
		}
		if (!"".equals(search)) {
			q.put("search"		, search);
		}
		if (!"".equals(pageNum)) {
			q.put("pageNum"		, pageNum);
		}
		String qs = Common.getQueryString(q);
		return "redirect:/board"+qs;
	}
	
	
	@GetMapping("/detail")
	public String detail(@RequestParam Map<String, String> paramMap, Model model) {
		log.info("detail :: {}", paramMap.get("bordId"));
		
		String bordId	= Common.nvl(paramMap.get("bordId"));
		String category = Common.nvl(paramMap.get("category"));
		String search	= Common.nvl(paramMap.get("search"));
		String pageNum	= Common.nvl(paramMap.get("pageNum"));
		
		Map<String, Object> detail = boardService.getDetail(bordId);
		
		model.addAttribute("board", detail.get("board"));
		model.addAttribute("files", detail.get("files"));
		model.addAttribute("category"	, category);
		model.addAttribute("search"		, search);
		model.addAttribute("pageNum"	, pageNum);
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
			session.setAttribute(cmmFile.FILE_DOWNLOAD_SESSION_CHK, dateStr + "/" + fileId);
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
		String chkVal		= (String) session.getAttribute(cmmFile.FILE_DOWNLOAD_SESSION_CHK);
		session.removeAttribute(cmmFile.FILE_DOWNLOAD_SESSION_CHK);
		
		log.info("chkVal : {}", chkVal);
		try {
			if (chkVal != null) {
				String[] chk		= chkVal.split("/");
				
				if (chk.length > 1 && chk[0].equals(dateStr) && chk[1].equals(fileId)) {
					FileVo file		= boardService.getFile(fileId);
					
					log.info("sdfsd {}", file.toString());
					
					if (!ObjectUtils.isEmpty(file)) {
						byte[] fileBytes	= cmmFile.fileDownload(file.getFileSvnm());
						String fileName		= URLEncoder.encode(file.getFileOlnm(), "UTF-8");
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
	@PostMapping("/deleteFile")
	public int deleteFile(@RequestParam Map<String, String> paramMap) {
		String fileId = Common.nvl(paramMap.get("fileId"));
		return boardService.deleteFile(fileId);
	}
	
	
	@ResponseBody
	@GetMapping("/comment")
	public Map<String, Object> comment(@RequestParam Map<String, String> paramMap) {
		log.info("comment");
		String bordId	= Common.nvl(paramMap.get("bordId"));
		String pageNum	= Common.nvl(paramMap.get("pageNum"), "0");
		
		Map<String, Object> commentMap = boardService.getCommentList(bordId, pageNum);
		log.info("comment :: {}", commentMap.toString());
		return commentMap;
	}
	
	@ResponseBody
	@PostMapping("/insertComment")
	public int insertComment(@RequestParam Map<String, String> paramMap) {
		log.info("insert Comment");
		int cnt = 0;
		cnt = boardService.insertComment(paramMap);
		
		return cnt;
	}
	
	@ResponseBody
	@PostMapping("/deleteComment")
	public int deleteComment(@RequestParam Map<String, String> paramMap) {
		log.info("delete Comment");
		
		String commId = Common.nvl(paramMap.get("commId"));
		int cnt = 0;
		
		if (!commId.isEmpty()) {
			cnt = boardService.deleteComment(commId, null);
		}
		
		return cnt;
	}
	
	

}
