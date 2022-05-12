package com.teamproject.myweb.freeBoard;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamproject.myweb.command.freeBoardVO;
import com.teamproject.myweb.util.freeboard_Criteria;

@Service("freeboardService")
public class FreeBoardServiceImpl implements FreeBoardService {
	
	@Autowired
	private FreeBoardMapper freeBoardMapper;

	@Override
	public ArrayList<freeBoardVO> getList(freeboard_Criteria cri) {
		return freeBoardMapper.getList(cri);
	}

	@Override
	public int regist(freeBoardVO vo) {
		return freeBoardMapper.regist(vo);
	}
	
	@Override
	public freeBoardVO getDetail(int free_bno) {
		return freeBoardMapper.getDetail(free_bno);
	}

	@Override
	public int update(freeBoardVO vo) {
		return freeBoardMapper.update(vo);
	}

	@Override
	public int delete(int free_bno) {
		return freeBoardMapper.delete(free_bno);
	}

	@Override
	public int getTotal(freeboard_Criteria cri) {
		return freeBoardMapper.getTotal(cri);
	}

}
