package com.teamproject.myweb.util;

import lombok.Data;

@Data
public class debate_Criteria {
	
	//sql문에 전달될 값을 가지고 다니는 클래스
	private int page;
	private int amount;
	private int leftpage;
	
	//검색키워드 추가
	private String searchKey;
	private String searchOption;
	
	
	
	public debate_Criteria() {
		this(1, 10);
	}

	public debate_Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	
	
}
