package com.teamproject.myweb.util;

import lombok.Data;

@Data
public class freeboard_Criteria {

	private int page;
	private int amount;
	private int leftpage;
	
	//검색키워드

	private String searchKey;
	private String searchoption;
	
	public freeboard_Criteria() {
		this(1, 10);
	}
	
		
	public freeboard_Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
		
		
	}


}