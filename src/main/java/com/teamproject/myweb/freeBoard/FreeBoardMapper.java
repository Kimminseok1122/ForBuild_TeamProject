package com.teamproject.myweb.freeBoard;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import com.teamproject.myweb.command.freeBoardVO;
import com.teamproject.myweb.util.freeboard_Criteria;


@Mapper
public interface FreeBoardMapper {

	public ArrayList<freeBoardVO> getList(freeboard_Criteria cri);
	public int getTotal(freeboard_Criteria cri);
	public int regist(freeBoardVO vo);
	public freeBoardVO getDetail(int free_bno);
	public int update(freeBoardVO vo);
	public int delete(int free_bno);
}
