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
	public ArrayList<MessageVO> getList(String sender, Criteria cri) {
		return messageMapper.getList(sender, cri);
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
	public ArrayList<MessageVO> re_getList(String sender, Criteria cri) {
		return messageMapper.re_getList(sender, cri);
	}

	@Override
	public int delete(int mno) {
		return messageMapper.delete(mno);
	}

	@Override
	public int getSendTotal(String sender) {
		return messageMapper.getSendTotal(sender);
	}

	@Override
	public int getReceiveTotal(String sender) {
		return messageMapper.getReceiveTotal(sender);
	}

	@Override
	public int update(int mno) {
		return messageMapper.update(mno);
	}


	

}
