package com.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.comm.Common;
import com.main.comm.PageSet;
import com.main.comm.cmmFile;
import com.main.mapper.BoardMapper;
import com.main.vo.BoardVo;
import com.main.vo.CategoryVo;
import com.main.vo.CommentVo;
import com.main.vo.FileVo;
import com.main.vo.LoginSessionVo;
import com.main.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	private int LIMIT_SIZE, PAGE_BLOCK_SIZE;
	// 한 페이지당 글 개수, 페이지 블록 개수
	
	private int COMM_LIMIT_SIZE, COMM_PAGE_BLOCK_SIZE;
	// 한 페이지당 댓글 개수, 댓글 페이지 블록 개수
	
	@Value("${board.limit}")
	private void setLimit(int page_limit) {
		LIMIT_SIZE = page_limit;
	}
	
	@Value("${board.block}")
	private void setBlockSize(int page_block) {
		PAGE_BLOCK_SIZE = page_block;
	}
	
	@Value("${comment.limit}")
	private void setCommLimit(int comment_limit) {
		COMM_LIMIT_SIZE = comment_limit;
	}
	
	@Value("${comment.block}")
	private void setCommBlockSize(int comment_block) {
		COMM_PAGE_BLOCK_SIZE = comment_block;
	}
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private LoginService loginService;
	
	@Override
	public int insertBoard(BoardVo board) {
		return boardMapper.insertBoard(board);
	}

	@Override
	public Map<String, Object> getBoardList(Map<String, String> paramMap) {
		
		String pageNow	= Common.nvl(paramMap.get("pageNum"), "1");
		String search	= paramMap.get("search");
		String category	= paramMap.get("category");
		int pageNum		= Common.str2Int(pageNow);
		
		pageNum			= (pageNum > 0) ? pageNum : 1;
		int startRow	= (pageNum - 1) * LIMIT_SIZE;
		
		List<BoardVo> boardList = boardMapper.getBoardList(search, category, LIMIT_SIZE, startRow);
		
		int allRowCount = 0;
		if (boardList.size() > 0) {
			allRowCount = (boardList.get(0) != null) ? boardList.get(0).getCnt() : 0;
		}
		
		PageInfo pageInfo = PageSet.getPageData(pageNum, allRowCount, LIMIT_SIZE, PAGE_BLOCK_SIZE);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageInfo"	, pageInfo);
		result.put("boardList"	, boardList);
		return result;
	}
	
	@Override
	public int updateBoard(BoardVo board) {
		return boardMapper.updateBoard(board);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteBoard(String bordId) {
		boardMapper.deleteBoard(bordId);
		boardMapper.deleteComment(null, bordId);
		
		List<FileVo> fileList = boardMapper.getFileList(bordId);
		int res = 0;
		for (FileVo fileVo : fileList) {
			if (cmmFile.deleteFile(boardMapper.getFile(fileVo.getFileId()+""))) {
				res += boardMapper.deleteFile(fileVo.getFileId()+"");
			} else {
				log.error("파일 삭제 실패");
			}
		}
		log.info("파일 {}건 삭제", res);
		return res;
	}

	@Override
	public List<CategoryVo> getCategoryList() {
		return boardMapper.getCategoryList();
	}
	
	@Override
	public String getCategoryName(String catgId) {
		return boardMapper.getCategoryName(catgId);
	}

	@Override
	public int insertFile(FileVo file) {
		return boardMapper.insertFile(file);
	}

	@Override
	public FileVo getFile(String fileId) {
		return boardMapper.getFile(fileId);
	}
	
	@Override
	public int deleteFile(String fileId) {
		
		int res = 0;
		if (cmmFile.deleteFile(boardMapper.getFile(fileId))) {
			res = boardMapper.deleteFile(fileId);
		} else {
			log.error("파일 삭제 실패");
		}
		return res;
	}

	@Override
	public Map<String, Object> getDetail(String bordId) {
		
		boardMapper.addHitc(bordId);
		BoardVo board		= boardMapper.getBoard(bordId);
		List<FileVo> files	= boardMapper.getFileList(bordId);
		
		Map<String, Object> res = Map.of(
			"board"	, board,
			"files"	, files
		);
		return res;
	}

	@Override
	public Map<String, Object> getCommentList(String bordId, String pageNow) {
		
		int pageNum		= Common.str2Int(pageNow);
		int allRowCount	= boardMapper.getCommentCnt(bordId);
log.info("pageNum : {}",pageNum);		
		List<CommentVo> commentList = null;
		
		if (allRowCount > 0) {
			
			int maxPage		= (int) Math.ceil(allRowCount / (double) COMM_LIMIT_SIZE);
			pageNum			= (pageNum > 0) ? pageNum : maxPage;
			int startRow	= (pageNum - 1) * COMM_LIMIT_SIZE;
log.info("pageNum : {}",pageNum);			
			LoginSessionVo login = null;
			int userId		= 0;
			if (loginService.isLogin()) {
				login		= loginService.getLoginData();
				userId		= login.getUserId();
			}
			commentList = boardMapper.getCommentList(bordId, userId, COMM_LIMIT_SIZE, startRow);
		} else {
			commentList = new ArrayList<CommentVo>();
		}
		
//		int allRowCount = 0;
//		if (commentList.size() > 0) {
//			allRowCount = (commentList.get(0) != null) ? commentList.get(0).getCnt() : 0;
//		}
		
		PageInfo pageInfo = PageSet.getPageData(pageNum, allRowCount, COMM_LIMIT_SIZE, COMM_PAGE_BLOCK_SIZE);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageInfo"	, pageInfo);
		result.put("commentList", commentList);
		return result;
	}
	
	@Override
	public int insertComment(Map<String, String> paramMap) {
		
		LoginSessionVo login = loginService.getLoginData();
		
		int bordId		= Common.str2Int(paramMap.get("bordId"));
		String commCont	= Common.nvl(paramMap.get("commCont"));
		
		CommentVo comment = new CommentVo();
		comment.setBordId(bordId);
		comment.setCommCont(commCont);
		comment.setUserId(login.getUserId());
		
		return boardMapper.insertComment(comment);
	}

	@Override
	public int deleteComment(String commId, String bordId) {
		return boardMapper.deleteComment(commId, bordId);
	}
	
	
	
	
	
	
	
}
