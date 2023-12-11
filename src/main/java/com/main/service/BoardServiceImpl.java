package com.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.main.comm.Common;
import com.main.comm.PageSet;
import com.main.mapper.BoardMapper;
import com.main.vo.BoardVo;
import com.main.vo.CategoryVo;
import com.main.vo.FileVo;
import com.main.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	private int LIMIT_SIZE, PAGE_BLOCK_SIZE;
	// 한 페이지당 글 개수, 페이지 블록 개수
	
	@Value("${page.limit}")
	private void setLimit(int page_limit) {
		LIMIT_SIZE = page_limit;
	}
	
	@Value("${page.block}")
	private void setBlockSize(int page_block) {
		PAGE_BLOCK_SIZE = page_block;
	}
	
	@Autowired
	private BoardMapper boardMapper;
	
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
		
		int startRow = (pageNum - 1) * PAGE_BLOCK_SIZE;
		
		List<BoardVo> boardList = boardMapper.getBoardList(search, category, LIMIT_SIZE, startRow);
		int allRowCount = (boardList.get(0) != null) ? boardList.get(0).getCnt() : 0;
		
		PageInfo pageInfo = PageSet.getPageData(pageNum, allRowCount);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pageInfo"	, pageInfo);
		result.put("boardList"	, boardList);
		return result;
	}

	@Override
	public List<CategoryVo> getCategoryList() {
		return boardMapper.getCategoryList();
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
	
	
	
	
	
	
}
