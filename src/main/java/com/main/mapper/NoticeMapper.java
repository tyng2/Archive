package com.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.NoticeVo;

@Mapper
public interface NoticeMapper {
	
	public List<NoticeVo> getNotice(@Param("pageSize") int pageSize, @Param("startRow") int startRow);
	
	public int insertNotice(NoticeVo notice);

}
