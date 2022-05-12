package com.teamproject.myweb.util;

import lombok.Data;

@Data
public class message_Criteria {

	private int page;
	private int amount;
	private int pagee;
	
	public message_Criteria() {
		this(1, 5);
	}

	public message_Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	
}
