package com.teamproject.myweb.comment;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import com.teamproject.myweb.command.CommentVO;

@Mapper
public interface CommentMapper {
	
	public ArrayList<CommentVO> commentList(int free_bno);
	public int commentReg(CommentVO vo);
	public int commentDelete(int cno);
	public int commentTotal(int free_bno);
}
