package com.teamproject.myweb.user;

import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;

public interface UserService {

	public int join(UserVO vo);
	
	public UserVO userCheckes(UserCheckVO vo);
	
	public int modify(UserVO vo);
	
	public int userDelete(int user_no);
	
	public UserVO myPage(int user_no);
	
	//public 
}
