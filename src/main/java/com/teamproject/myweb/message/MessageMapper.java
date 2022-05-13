package com.teamproject.myweb.message;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.teamproject.myweb.command.MessageVO;
import com.teamproject.myweb.util.Criteria;


@Mapper
public interface MessageMapper {

	public int write (MessageVO vo); //메시지 보내기
	public int reply(MessageVO vo); //답장하기
	public ArrayList<MessageVO> getList(@Param("user_id") String user_id, 
										@Param("cri") Criteria cri); //메시지 목록
	public ArrayList<MessageVO> re_getList(@Param("user_id") String user_id, 
										   @Param("cri") Criteria cri); //받은
	public int getSendTotal(String user_id);
	public int getReceiveTotal(String user_id);
	public MessageVO getReceiveDetail(int mno);//받은메시지 상세보기
	public MessageVO getSendDetail(int mno);//보낸메시지 상세보기
	public int delete(int mno); //삭제
	
	public int update(int mno);
}
