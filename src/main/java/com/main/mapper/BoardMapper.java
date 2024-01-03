package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.BoardVo;
import com.main.vo.CategoryVo;
import com.main.vo.CommentVo;
import com.main.vo.FileVo;

@Mapper
public interface BoardMapper {
	
	public int insertBoard(BoardVo board);
	
	public List<BoardVo> getBoardList(@Param("search") String search, @Param("category") String category, @Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public int updateBoard(BoardVo board);
	
	public int addHitc(@Param("bordId") String bordId);
	
	public BoardVo getBoard(@Param("bordId") String bordId);
	
	public List<CategoryVo> getCategoryList();
	
	public int insertFile(FileVo file);
	
	public List<FileVo> getFileList(@Param("bordId") String bordId);
	
	public FileVo getFile(@Param("fileId") String fileId);
	
	public int deleteFile(@Param("fileId") String fileId);
	
	public int getCommentCnt(@Param("bordId") String bordId);
	
	public List<CommentVo> getCommentList(@Param("bordId") String bordId, @Param("userId") int userId, @Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public int insertComment(CommentVo comment);
	
	public int deleteComment(@Param("commId") String commId);
	
	
}
