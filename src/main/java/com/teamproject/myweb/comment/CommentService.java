package com.teamproject.myweb.comment;

import java.util.ArrayList;



import com.teamproject.myweb.command.CommentVO;

public interface CommentService {
	
	public ArrayList<CommentVO> commentList(int free_bno);
	public int commentReg(CommentVO vo);
	public int commentDelete(int cno);
	public int commentTotal(int free_bno);
}
