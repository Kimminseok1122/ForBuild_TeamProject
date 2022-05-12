package com.teamproject.myweb.comment;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamproject.myweb.command.CommentVO;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	@Override
	public ArrayList<CommentVO> commentList(int free_bno) {
		return commentMapper.commentList(free_bno);
	}

	@Override
	public int commentReg(CommentVO vo) {
		return commentMapper.commentReg(vo);
	}

	@Override
	public int commentDelete(int cno) {
		return commentMapper.commentDelete(cno);
	}

	@Override
	public int commentTotal(int free_bno) {
		return commentMapper.commentTotal(free_bno);
	}
	
	
}
