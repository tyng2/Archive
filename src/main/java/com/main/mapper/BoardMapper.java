package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.BoardVo;
import com.main.vo.CategoryVo;
import com.main.vo.FileVo;

@Mapper
public interface BoardMapper {
	
	public int insertBoard(BoardVo board);
	
	public List<BoardVo> getBoardList(@Param("search") String search, @Param("category") String category, @Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public int addHitc(@Param("bord_id") String bord_id);
	
	public BoardVo getBoard(@Param("bord_id") String bord_id);
	
	public List<CategoryVo> getCategoryList();
	
	public int insertFile(FileVo file);
	
	public List<FileVo> getFileList(@Param("bord_id") String bord_id);
	
	public FileVo getFile(@Param("file_id") String file_id);
	
}
