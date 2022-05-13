package com.teamproject.myweb.message;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamproject.myweb.command.MessageVO;
import com.teamproject.myweb.util.Criteria;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	@Autowired MessageMapper messageMapper;

	@Override
	public int write(MessageVO vo) {

		return messageMapper.write(vo);
	}

	@Override
	public int reply(MessageVO vo) {
		return messageMapper.reply(vo);
	}
	
	@Override
	public ArrayList<MessageVO> getList(String user_id, Criteria cri) {
		return messageMapper.getList(user_id, cri);
	}

	@Override
	public MessageVO getReceiveDetail(int mno) {
		return messageMapper.getReceiveDetail(mno);
	}
	

	@Override
	public MessageVO getSendDetail(int mno) {
		return messageMapper.getSendDetail(mno);
	}

	@Override
	public ArrayList<MessageVO> re_getList(String user_id, Criteria cri) {
		return messageMapper.re_getList(user_id, cri);
	}

	@Override
	public int delete(int mno) {
		return messageMapper.delete(mno);
	}

	@Override
	public int getSendTotal(String user_id) {
		return messageMapper.getSendTotal(user_id);
	}

	@Override
	public int getReceiveTotal(String user_id) {
		return messageMapper.getReceiveTotal(user_id);
	}

	@Override
	public int update(int mno) {
		return messageMapper.update(mno);
	}


	

}
