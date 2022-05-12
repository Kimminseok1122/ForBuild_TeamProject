package com.teamproject.myweb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamproject.myweb.command.UserCheckVO;
import com.teamproject.myweb.command.UserVO;
import com.teamproject.myweb.pnc.user.UserMapper;
import com.teamproject.myweb.pnc.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired UserMapper userMapper;

	@Override
	public int join(UserVO vo) {
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

}