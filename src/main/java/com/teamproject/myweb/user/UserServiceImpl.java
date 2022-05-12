package com.teamproject.myweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamproject.myweb.command.ExamineVO;
import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired 
	UserMapper userMapper;

	@Override
	public int join(UserVO vo) {
		System.out.println(vo+"서비스임플");
		return userMapper.join(vo);
	}
	
	@Override
	public UserVO userCheckes(UserCheckVO vo) {
		return userMapper.userCheckes(vo);
	}

	@Override
	public int modify(UserVO vo) {
		return userMapper.modify(vo);
	}
	
	@Override
	public int userDelete(int user_no) {
		return userMapper.userDelete(user_no);
	}

	@Override
	public UserVO myPage(int user_no) {
		return userMapper.myPage(user_no);
	}

	@Override
	public int examine(ExamineVO vo) {
		return userMapper.examine(vo);
	}

	
	
}