package com.teamproject.myweb.debate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamproject.myweb.command.DebateUploadVO;
import com.teamproject.myweb.command.DebateVO;
import com.teamproject.myweb.util.debate_Criteria;

@Service("debateService")
public class DebateServiceImpl implements DebateService {
	
	@Autowired
	private DebateMapper debateMapper;

	@Override
	public int regist(DebateVO vo) {
		return debateMapper.regist(vo);
	}

	@Override
	public int registFile(DebateUploadVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DebateVO> getList(debate_Criteria cri) {
		
		return debateMapper.getList(cri);
	}

	@Override
	public int getTotal(debate_Criteria cri) {
		
		return debateMapper.getTotal(cri);
	}

	@Override
	public DebateVO getDetail(int debate_no) {
		
		return debateMapper.getDetail(debate_no);
	}

	@Override
	public int update(DebateVO vo) {
		
		return debateMapper.update(vo);
	}

	@Override
	public int delete(int debate_no) {
		
		return debateMapper.delete(debate_no);
	}

}
