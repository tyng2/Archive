package com.main.service;

import java.util.List;
import java.util.Map;

import com.main.vo.BoardVo;
import com.main.vo.CategoryVo;
import com.main.vo.FileVo;

public interface BoardService {
	
	public int insertBoard(BoardVo board);
	
	public Map<String, Object> getBoardList(Map<String, String> paramMap);
	
	public int updateBoard(BoardVo board);
	
	public List<CategoryVo> getCategoryList();
	
	public int insertFile(FileVo file);
	
	public FileVo getFile(String fileId);
	
	public int deleteFile(String fileId);
	
	public Map<String, Object> getDetail(String bordId);
	
	public Map<String, Object> getCommentList(String bordId, String pageNow);
	
	public int insertComment(Map<String, String> paramMap);
	
	public int deleteComment(String commId);
}
