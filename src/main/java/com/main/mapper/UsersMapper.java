package com.main.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.main.vo.UsersVo;

@Mapper
public interface UsersMapper {
	
	public UsersVo getUsersBySnsId(@Param("snsId") String snsId);
	
	public int insertUsers(UsersVo user);
	
	public int updateExistUsers(UsersVo user);
	
	public int insertUserLog(Map<String, String> input);
	
	public int deleteUser(@Param("userId") String userId);
	
	
}
