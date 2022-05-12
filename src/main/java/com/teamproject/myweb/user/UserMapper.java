package com.teamproject.myweb.user;



import org.apache.ibatis.annotations.Mapper;

import com.teamproject.myweb.command.ExamineVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;

@Mapper
public interface UserMapper {
	
	public int join(UserVO vo);
	
	public UserVO userCheckes(UserCheckVO vo);
	
	public int modify(UserVO vo);
	
	public int userDelete(int user_no);
	
	public UserVO myPage(int user_no);
	
	public int examine(ExamineVO vo);
	
}
