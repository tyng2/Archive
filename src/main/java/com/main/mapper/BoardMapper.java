package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.BoardVo;

@Mapper
public interface BoardMapper {
	
	public int insertBoard();
	
	public List<BoardVo> getBoardList(@Param("search") String search, @Param("category") String category, @Param("pageSize") int pageSize, @Param("startRow") int startRow);
}
